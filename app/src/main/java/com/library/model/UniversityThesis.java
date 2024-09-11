package com.library.model;

import java.time.LocalDate;

public class UniversityThesis extends Document {

    private String degreeProgram;

    public UniversityThesis(String title, String author, LocalDate pubDate, int numPages, String degreeProgram) {
        super(title, author, pubDate, numPages);
        this.degreeProgram = degreeProgram;
    }

    public void borrow() {

    }

    public void _return() {

    }


    public void display() {

    }
}
