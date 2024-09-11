package com.library.model;

public abstract class User {
    private String id;
    private String name;
    private String email;
    private int age;

    public User(String id, String name, String email, int age){
        this.id = id;
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public String getId() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
