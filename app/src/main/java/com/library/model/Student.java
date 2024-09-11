package com.library.model;

import com.library.model.User;
import java.time.LocalDate;

public class Student extends User {
    private LocalDate integrationDate;
    public Student(String id, String name, String email, int age, LocalDate integrationDate){
        super(id, name, email, age);
        this.integrationDate = integrationDate;
    }

    public LocalDate getIntegrationDate() {
        return integrationDate;
    }
}
