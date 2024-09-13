package com.library.model;

import java.time.LocalDate;

public class UniversityThesis extends Document {

    private String degreeProgram;

    public UniversityThesis(String title, String author, LocalDate pubDate, int numPages, String degreeProgram) {
        super(title, author, pubDate, numPages);
        this.degreeProgram = degreeProgram;
    }

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public void borrow() {
        this.borrowed = true;
    }

    public void _return() {
        this.borrowed = false;
    }


    public void display() {
        System.out.println("Thesis   -> ID: " + getId() + " | Title: " + getTitle() + " | Author: " + getAuthor() + " | Pub Date: " + getPubDate() + " | Num of Pages: " + getNumPages() + " | Degree Program: " + getDegreeProgram() + (borrowed ? " | Borrowed" : " | Not Borrowed"));
    }
    public void cancelReserve (){}
    public void reserve(){}
}
