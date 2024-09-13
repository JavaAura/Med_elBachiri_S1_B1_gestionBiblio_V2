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
        this.borrowed = true;
    }

    public void _return() {
        this.borrowed = false;
    }

    public void display() {
        System.out.println("Magazine -> ID: " + getId() + " | Title: " + getTitle() + " | Author: " + getAuthor() + " | Pub Date: " + getPubDate() + " | Num of Pages: " + getNumPages() + " | Number: " + getNumber() + (borrowed ? " | Borrowed" : " | Not Borrowed"));
    }
    public void cancelReserve (){}
    public void reserve(){}
}
