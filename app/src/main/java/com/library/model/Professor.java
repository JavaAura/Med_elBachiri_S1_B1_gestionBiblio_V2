package com.library.model;

import com.library.model.User;

public class Professor extends User {
    private String department;

    public Professor(String id, String name, String email, int age, String department) {
        super(id, name, email, age);
        this.department = department;
    }
}
