package com.library.daoImpl;

import com.library.dao.UserDAO;
import com.library.model.Professor;
import com.library.model.Student;
import com.library.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDaoImpl implements UserDAO {

    private static StudentDaoImpl studentDao = new StudentDaoImpl();
    private static ProfessorDaoImpl professorDao = new ProfessorDaoImpl();
    private static ArrayList<User> users = new ArrayList<User>();

    @Override
    public ArrayList<User> getAll() {
        users.clear();
        users.addAll(studentDao.getAll());
        users.addAll(professorDao.getAll());
        return users;
    }

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
    public void delete(String id, int userType) {
        switch (userType){
            case 1:
                studentDao.delete(id);
                break;
            case 2:
                professorDao.delete(id);
        }
    }
}
