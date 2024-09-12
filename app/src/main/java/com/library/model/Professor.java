package com.library.model;

import com.library.model.User;
import com.library.utils.UI;

public class Professor extends User {
    private String department;

    public Professor(String name, String email, int age, String department) {
        super(name, email, age);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
    @Override
    public void display(){
        System.out.println("Student -> ID: " + getId() + " | Name: " + getName() + " | Email: " + getEmail() + " | Age: " + getAge() + " | department: " + getDepartment());
        UI.underline();
    }
}
