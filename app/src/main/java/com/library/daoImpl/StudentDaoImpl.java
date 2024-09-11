package com.library.daoImpl;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.utils.db.DbConnection;

import java.sql.*;
import java.util.HashMap;

public class StudentDaoImpl implements StudentDAO {

    private Connection cn;

    public StudentDaoImpl() {
        this.cn = DbConnection.connect();
    }

    @Override
    public HashMap<String, Student> getAll() {
        HashMap<String, Student> students = new HashMap<>();
        String query = "SELECT * FROM students";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("age"),
                        resultSet.getDate("integration_date").toLocalDate()
                );
                students.put(resultSet.getString("id"), student);
            }
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
        return students;
    }

    @Override
    public Student get(String id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Student(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("age"),
                            resultSet.getDate("integration_date").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
        return null;
    }

    @Override
    public void create(Student student) {
        String query = "INSERT INTO students (id, name, email, age, integration_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preStm = cn.prepareStatement(query)) {
            preStm.setString(1, student.getId());
            preStm.setString(2, student.getName());
            preStm.setString(3, student.getEmail());
            preStm.setInt(4, student.getAge());
            preStm.setDate(5, Date.valueOf(student.getIntegrationDate()));
            preStm.executeUpdate();
            System.out.println("[+] Student added.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    @Override
    public void delete(Student student) {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setString(1, student.getId());
            preparedStatement.executeUpdate();
            System.out.println("[+] Student deleted.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    @Override
    public void update(Student student) {
        String query = "UPDATE students SET name = ?, email = ?, age = ?, integration_date = ? WHERE id = ?";
        try (PreparedStatement prestm = cn.prepareStatement(query)) {
            prestm.setString(1, student.getName());
            prestm.setString(2, student.getEmail());
            prestm.setInt(3, student.getAge());
            prestm.setDate(4, Date.valueOf(student.getIntegrationDate()));
            prestm.setString(5, student.getId());
            prestm.executeUpdate();
            System.out.println("[+] Student updated.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }
}
