package com.library.dao;

import com.library.model.Professor;
import com.library.model.Student;
import com.library.model.User;

import java.util.ArrayList;

public interface UserDAO {
//    public ArrayList<User> getAll();
//    public ArrayList<Student> getAll();

    public void create(Student student);
    public void create(Professor professor);

    public void update(Student student);
    public void update(Professor professor);

    public void delete(Student student);
    public void delete(Professor professor);
}
