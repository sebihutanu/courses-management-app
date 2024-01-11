package org.example;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Secretariat secretariat = new Secretariat();
        String test = args[0];
        String folder = "src/main/resources/" + test;
        String inputFile = folder + "/" + test + ".in";
        String outputFile = folder + "/" + test + ".out";
        File file = new File(inputFile);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] command = line.split(" - ");
                String commandName = command[0];
                switch (commandName) {
                    case "adauga_student": {
                        String program_de_studii = command[1];
                        String nume = command[2];
                        try {
                            secretariat.adaugaStudent(program_de_studii, nume);
                        } catch (ExceptieStudentDejaExistent e) {
                            System.out.println(e.getMessage());
                            try {
                                FileWriter fileWriter = new FileWriter(outputFile, true);
                                fileWriter.write("***\n" + e.getMessage() + "\n");
                                fileWriter.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        break;
                    }
                    case "adauga_curs": {
                        String program_de_studii = command[1];
                        String nume_curs = command[2];
                        int capacitatea_maxima = Integer.parseInt(command[3]);
                        secretariat.adaugaCurs(program_de_studii, nume_curs, capacitatea_maxima);
                        break;
                    }
                    case "citeste_mediile": {
                        File folderFile = new File(folder);
                        try {
                            File[] files = folderFile.listFiles();
                            for (File file1 : files) {
                                if (file1.isFile() && file1.getName().startsWith("note_")) {
                                    Scanner scannerNote = new Scanner(file1);
                                    while (scannerNote.hasNextLine()) {
                                        String Nota = scannerNote.nextLine();
                                        String[] nota = Nota.split(" - ");
                                        String nume = nota[0];
                                        double medie = Double.parseDouble(nota[1]);
                                        secretariat.citesteNota(nume, medie);
                                    }
                                }
                            }
                        } catch(NullPointerException e) {
                            System.out.println("Nu exista fisiere de note");
                        }
                        break;
                    }
                    case "posteaza_mediile": {
                        secretariat.listaStudenti.sort(new ComparatorStudenti());
                        try {
                            FileWriter fileWriter = new FileWriter(outputFile, true);
                            fileWriter.write("***\n");
                            for (Student student : secretariat.listaStudenti) {
                                fileWriter.write(student.getNume() + " - " + student.getMedie() + "\n");
                            }
                            fileWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "contestatie": {
                        String nume = command[1];
                        double medie = Double.parseDouble(command[2]);
                        for (Student student : secretariat.listaStudenti) {
                            if (student.getNume().equals(nume)) {
                                student.setMedie(medie);
                            }
                        }
                        break;
                    }
                    case "adauga_preferinte": {
                        String nume = command[1];
                        Student student = null;
                        for(Student aux : secretariat.listaStudenti) {
                            if(aux.getNume().equals(nume)) {
                                student = aux;
                            }
                        }
                        if(student == null) {
                            System.out.println("Studentul nu exista");
                            break;
                        }
                        for(int i = 2; i < command.length; i++) {
                            for (Curs<?> curs : secretariat.listaCursuri) {
                                if (curs.getNumeCurs().equals(command[i])) {
                                    try {
                                        secretariat.adaugarePreferinta(student, curs);
                                    } catch (RuntimeException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "repartizeaza": {
                        secretariat.listaStudenti.sort(new ComparatorStudenti());
                        for(Student student : secretariat.listaStudenti) {
                            for(Curs<?> curs : student.preferinte) {
                                try {
                                    secretariat.InscriereStudent(student, curs);
                                } catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                        break;
                    }
                    case "posteaza_curs": {
                        String numeCurs = command[1];
                        try {
                            FileWriter fileWriter = new FileWriter(outputFile, true);
                            fileWriter.write("***\n");
                            for (Curs<?> curs : secretariat.listaCursuri) {
                                curs.studenti.sort(new ComparatorStudentiAlfabetic());
                                if (curs.getNumeCurs().equals(numeCurs)) {
                                    fileWriter.write(curs.getNumeCurs() + " " + "(" + curs.getCapacitateMaxima() + ")" + "\n");
                                    for (Student student : curs.studenti) {
                                        fileWriter.write(student.getNume() + " - " + student.getMedie() + "\n");
                                    }
                                }
                            }
                            fileWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "posteaza_student": {
                        String numeStudent = command[1];
                        try {
                            FileWriter fileWriter = new FileWriter(outputFile, true);
                            fileWriter.write("***\n");
                            for (Student student : secretariat.listaStudenti) {
                                if (student.getNume().equals(numeStudent)) {
                                    fileWriter.write("Student ");
                                    if(student instanceof StudentLicenta) {
                                        fileWriter.write("Licenta: ");
                                    } else if(student instanceof StudentMaster) {
                                        fileWriter.write("Master: ");
                                    }
                                    fileWriter.write(student.getNume() + " - " + student.getMedie() + " - " + student.cursInrolat.getNumeCurs() + "\n");
                                }
                            }
                            fileWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}