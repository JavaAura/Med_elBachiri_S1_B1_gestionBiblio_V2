package com.library.model;

import java.time.LocalDate;

public class ScientificJournal extends Document {
    private double impactFactor;
    public ScientificJournal(String title, String author, LocalDate pubDate, int numPages, double impactFactor) {
        super(title, author, pubDate, numPages);
        this.impactFactor = impactFactor;
    }

    @Override
    public void borrow(){};
    @Override
    public void _return(){};
    @Override
    public void display() {
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publication Date: " + getPubDate());
        System.out.println("Number of Pages: " + getNumPages());
        System.out.println("Impact Factor: " + impactFactor);
    }

    public double getImpactFactor() {
        return impactFactor;
    }

    public void setImpactFactor(double impactFactor) {
        this.impactFactor = impactFactor;
    }
}
