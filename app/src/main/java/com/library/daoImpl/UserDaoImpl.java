package com.library.daoImpl;

import com.library.dao.UserDAO;
import com.library.model.Professor;
import com.library.model.Student;
import com.library.model.User;
import com.library.utils.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDaoImpl implements UserDAO {

    private static StudentDaoImpl studentDao = new StudentDaoImpl();
    private static ProfessorDaoImpl professorDao = new ProfessorDaoImpl();
    private static ArrayList<User> users = new ArrayList<User>();
    private static Connection cn = DbConnection.connect();

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

    public boolean userExist(int id){
        String query = "SELECT FROM users WHERE id = ?";
        try (PreparedStatement stm = cn.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e){
            System.out.println("SQL error : " + e);
        }
        return false;
    }
}
