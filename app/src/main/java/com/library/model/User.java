package com.library.model;

public abstract class User {
    private int id;
    private String name;
    private String email;
    private int age;

    public User(String name, String email, int age){
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public abstract void display();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id){
        this.id = id;
    }
}
