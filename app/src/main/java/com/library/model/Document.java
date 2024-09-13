package com.library.model;

import java.time.LocalDate;

public abstract class Document implements Bookable {
    private String id;
    private String title;
    private String author;
    private LocalDate pubDate;
    private int numPages;
    protected boolean borrowed;


    public Document(String title, String author, LocalDate pubDate, int numPages){
        this.title = title;
        this.author = author;
        this.pubDate = pubDate;
        this.numPages = numPages;
        this.borrowed = false;
    }

    public abstract void borrow();
    public abstract void _return();
    public abstract void display();


    public void setId(String id) {
        this.id = id;
    }

    public void setBorrowed(boolean brw){
        this.borrowed = brw;
    }
    public boolean getBorrowed(){
        return this.borrowed;
    }

    public String getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }
    public LocalDate getPubDate(){
        return this.pubDate;
    }
    public int getNumPages(){
        return this.numPages;
    }

}
