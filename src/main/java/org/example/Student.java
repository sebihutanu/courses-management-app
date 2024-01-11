package org.example;

import java.util.ArrayList;

public class Student {
    public String nume;
    public double medie;
    public ArrayList<Curs<?>> preferinte = new ArrayList<>();
    public Curs<?> cursInrolat;
    public boolean inrolat = false;
    Student (String nume) {
        this.nume = nume;
    }
    public void setMedie(double medie) {
        this.medie = medie;
    }
    public double getMedie() {
        return this.medie;
    }
    public String getNume() {
        return this.nume;
    }
}
