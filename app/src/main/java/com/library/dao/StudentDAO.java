package com.library.dao;

import com.library.model.Student;

import java.util.ArrayList;

public interface StudentDAO {
    public ArrayList<Student> getAll();
    public Student get(String id);
    public void create(Student student);
    public void delete(String id);
    public void update(Student student);
}
