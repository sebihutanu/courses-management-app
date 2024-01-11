package org.example;
import java.util.ArrayList;
public class Curs<T extends Student> {
    private final String numeCurs;
    private int capacitateMaxima;
    public ArrayList<Student> studenti;
    public double ultimaMedie;
    public Curs(String numeCurs, int capacitateMaxima) {
        this.numeCurs = numeCurs;
        this.capacitateMaxima = capacitateMaxima;
        this.studenti = new ArrayList<Student>();
    }
    public String getNumeCurs() {
        return this.numeCurs;
    }
    public int getCapacitateMaxima() {
        return this.capacitateMaxima;
    }
    public void InscriereStudent(Student student) {
        if (studenti.size() >= capacitateMaxima && ultimaMedie != student.getMedie()) {
            throw new RuntimeException("Capacitatea maxima a fost atinsa pt cursul:" + this.numeCurs);
        }
        if (student.inrolat) {
            return;
        }
        studenti.add(student);
        student.inrolat = true;
        student.cursInrolat = this;
        ultimaMedie = student.getMedie();
    }
}
