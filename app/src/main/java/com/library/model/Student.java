package com.library.model;

import com.library.model.User;
import com.library.utils.UI;

import java.time.LocalDate;

public class Student extends User {
    private LocalDate integrationDate;
    public Student(String name, String email, int age, LocalDate integrationDate){
        super(name, email, age);
        this.integrationDate = integrationDate;
    }

    public LocalDate getIntegrationDate() {
        return integrationDate;
    }

    @Override
    public void display(){
        System.out.println("Student -> ID: " + getId() + " | Name: " + getName() + " | Email: " + getEmail() + " | Age: " + getAge() + " | Integration Date: " + getIntegrationDate());
        UI.underline();
    }

}
