package com.library.dao;

import com.library.model.Professor;
import java.util.HashMap;

public interface ProfessorDAO {
    public HashMap<String, Professor> getAll();
    public Professor get(String id);
    public void create(Professor professor);
    public void delete(Professor professor);
    public void update(Professor professor);
}
