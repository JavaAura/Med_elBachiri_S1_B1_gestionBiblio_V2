package com.library.model;


import com.library.daoImpl.BookDaoImpl;

import java.time.LocalDate;

public class Book extends Document implements Bookable {
    private String isbn;

    public Book(String title, String author, LocalDate pubDate, int numPages, String isbn){
        super(title, author, pubDate, numPages);
        this.isbn = isbn;
    }
    public String getIsbn(){ return this.isbn; }
    public void borrow(){
        this.borrowed = true;
    }

    public void _return() {
        this.borrowed = false;
    }

    public void display() {
        System.out.println("Book     -> ID: " + getId() + " | Title: " + getTitle() + " | Author: " + getAuthor() + " | Pub Date: " + getPubDate() + " | Num of Pages: " + getNumPages() + " | ISBN: " + getIsbn());
    }

    @Override
    public void reserve() {

    }

    @Override
    public void cancelReserve() {

    }
}
