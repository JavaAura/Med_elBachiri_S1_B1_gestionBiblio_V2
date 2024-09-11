package com.library.daoImpl;

import com.library.dao.UserDAO;
import com.library.model.Professor;
import com.library.model.Student;
import com.library.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDaoImpl implements UserDAO {

    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private ProfessorDaoImpl professorDao = new ProfessorDaoImpl();


    @Override
    public void create(Student student) {
        studentDao.create(student);
    }

    @Override
    public void create(Professor professor) {
        professorDao.create(professor);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void update(Professor professor) {
        professorDao.update(professor);
    }

    @Override
    public void delete(Student student) {
        studentDao.delete(student);
    }

    @Override
    public void delete(Professor professor) {
        professorDao.delete(professor);
    }
}
