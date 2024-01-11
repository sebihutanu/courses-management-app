package org.example;

import java.util.Comparator;

public class ComparatorStudentiAlfabetic implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.getNume().compareTo(s2.getNume());
    }
}
