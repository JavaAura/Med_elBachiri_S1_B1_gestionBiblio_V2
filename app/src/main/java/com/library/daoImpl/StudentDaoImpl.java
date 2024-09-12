package com.library.daoImpl;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.utils.db.DbConnection;

import java.sql.*;
import java.util.ArrayList;

public class StudentDaoImpl implements StudentDAO {

    private Connection cn;

    public StudentDaoImpl() {
        this.cn = DbConnection.connect();
    }

    @Override
    public ArrayList<Student> getAll() {
        ArrayList<Student> students = new ArrayList<Student>();
        String query = "SELECT * FROM students";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("age"),
                        resultSet.getDate("integration_date").toLocalDate()
                );
                student.setId(resultSet.getInt("id"));
                students.add(student);
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
            preparedStatement.setInt(1, Integer.parseInt(id));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    Student student = new Student(
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("age"),
                            resultSet.getDate("integration_date").toLocalDate());
                    student.setId(resultSet.getInt("id"));
                    return student;
                }
            }
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
        return null;
    }

    @Override
    public void create(Student student) {
        String query = "INSERT INTO students (name, email, age, integration_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preStm = cn.prepareStatement(query)) {
            preStm.setString(1, student.getName());
            preStm.setString(2, student.getEmail());
            preStm.setInt(3, student.getAge());
            preStm.setDate(4, Date.valueOf(student.getIntegrationDate()));
            preStm.executeUpdate();
            System.out.println("[+] Student added.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
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
            prestm.setInt(5, student.getId());
            prestm.executeUpdate();
            System.out.println("[+] Student updated.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }
}
