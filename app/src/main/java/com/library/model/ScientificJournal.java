package com.library.model;

import java.time.LocalDate;

public class ScientificJournal extends Document {
    private double impactFactor;
    public ScientificJournal(String title, String author, LocalDate pubDate, int numPages, double impactFactor) {
        super(title, author, pubDate, numPages);
        this.impactFactor = impactFactor;
    }

    @Override
    public void borrow(){this.borrowed = true;};
    @Override
    public void _return(){this.borrowed = false;};
    @Override
    public void display() {
        System.out.println("Journal  -> ID: " + getId() + " | Title: " + getTitle() + " | Author: " + getAuthor() + " | Pub Date: " + getPubDate() + " | Num of Pages: " + getNumPages() + " | Impact Factor: " + getImpactFactor() + (borrowed ? " | Borrowed" : " | Not Borrowed"));;
    }

    public double getImpactFactor() {
        return impactFactor;
    }

    public void setImpactFactor(double impactFactor) {
        this.impactFactor = impactFactor;
    }
    public void cancelReserve (){}
    public void reserve(){}
}
