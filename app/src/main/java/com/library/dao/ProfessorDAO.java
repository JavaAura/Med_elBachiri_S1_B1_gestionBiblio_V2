package com.library.dao;

import com.library.model.Professor;

import java.util.ArrayList;

public interface ProfessorDAO {
    public ArrayList<Professor> getAll();
    public Professor get(String id);
    public void create(Professor professor);
    public void delete(String id);
    public void update(Professor professor);
}
