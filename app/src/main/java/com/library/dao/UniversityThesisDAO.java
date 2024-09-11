package com.library.dao;

import com.library.model.UniversityThesis;
import java.util.HashMap;

public interface UniversityThesisDAO {
    public HashMap<String, UniversityThesis> getAll();
    public UniversityThesis get(String id);
    public void create(UniversityThesis universityThesis);
    public void delete(UniversityThesis universityThesis);
    public void update(UniversityThesis universityThesis);
}
