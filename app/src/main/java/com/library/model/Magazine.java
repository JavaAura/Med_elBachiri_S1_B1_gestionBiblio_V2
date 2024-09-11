package com.library.model;

import java.time.LocalDate;

public class Magazine extends Document {
    private int number;

    public Magazine(String title, String author, LocalDate pubDate, int numPages, int number){
        super(title, author, pubDate, numPages);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void borrow(){

    }

    public void _return() {

    }

    public void display() {

    }
}
