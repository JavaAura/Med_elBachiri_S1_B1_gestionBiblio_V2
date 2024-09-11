package com.library.dao;

import com.library.model.Student;
import java.util.HashMap;

public interface StudentDAO {
    public HashMap<String, Student> getAll();
    public Student get(String id);
    public void create(Student student);
    public void delete(Student student);
    public void update(Student student);
}
