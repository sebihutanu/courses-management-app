package org.example;
import java.util.ArrayList;

public class Secretariat {
    ArrayList<Student> listaStudenti = new ArrayList<>();
    ArrayList<Curs<?>> listaCursuri = new ArrayList<>();
    public void adaugaStudent(String program_de_studii, String nume) {
        for(Student student : listaStudenti) {
            if(student.getNume().equals(nume)) {
                throw new ExceptieStudentDejaExistent("Student duplicat: " + nume);
            }
        }
        if (program_de_studii.equals("licenta")) {
            StudentLicenta studentLicenta = new StudentLicenta(nume);
            listaStudenti.add(studentLicenta);
        } else if (program_de_studii.equals("master")) {
            StudentMaster studentMaster = new StudentMaster(nume);
            listaStudenti.add(studentMaster);
        } else {
            System.out.println("Program de studii invalid");
        }
    }
    public void adaugaCurs(String program_de_studii, String nume_curs, int capacitatea_maxima) {
        if(program_de_studii.equals("licenta")) {
            CursLicenta cursLicenta = new CursLicenta(nume_curs, capacitatea_maxima);
            listaCursuri.add(cursLicenta);
        } else if(program_de_studii.equals("master")) {
            CursMaster cursMaster = new CursMaster(nume_curs, capacitatea_maxima);
            listaCursuri.add(cursMaster);
        } else {
            System.out.println("Program de studii invalid");
        }
    }
    public void citesteNota(String nume, double medie) {
        for (Student student : listaStudenti) {
            if (student.getNume().equals(nume)) {
                student.setMedie(medie);
            }
        }
    }
    public void adaugarePreferinta(Student student, Curs<?> curs) {
        if (student.preferinte.contains(curs)) {
            throw new RuntimeException("Studentul " + student.getNume() + " este deja înscris la cursul: " + curs.getNumeCurs());
        }
        if(student instanceof StudentLicenta && curs instanceof CursLicenta) {
            student.preferinte.add(curs);
        } else if(student instanceof StudentMaster && curs instanceof CursMaster) {
            student.preferinte.add(curs);
        } else {
            throw new RuntimeException("Studentul " + student.getNume() + " nu poate fi inscris la acest curs: " + curs.getNumeCurs());
        }
    }
    public void InscriereStudent(Student student, Curs<?> curs) {
        try {
            curs.InscriereStudent(student);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
