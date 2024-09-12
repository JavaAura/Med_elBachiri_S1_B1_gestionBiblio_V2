package com.library.daoImpl;

import com.library.dao.ProfessorDAO;
import com.library.model.Professor;
import com.library.utils.db.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProfessorDaoImpl implements ProfessorDAO {

    private Connection cn;

    public ProfessorDaoImpl() {
        this.cn = DbConnection.connect();
    }

    @Override
    public ArrayList<Professor> getAll() {
        ArrayList<Professor> professors = new ArrayList<Professor>();
        String query = "SELECT * FROM professors";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Professor professor = new Professor(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("age"),
                        resultSet.getString("department")
                );
                professor.setId(resultSet.getInt("id"));
                professors.add(professor);
            }
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
        return professors;
    }

    @Override
    public Professor get(String id) {
        String query = "SELECT * FROM professors WHERE id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Professor professor = new Professor(
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("age"),
                            resultSet.getString("department")
                    );
                    professor.setId(resultSet.getInt("id"));
                    return professor;
                }
            }
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
        return null;
    }

    @Override
    public void create(Professor professor) {
        String query = "INSERT INTO professors (name, email, age, department) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preStm = cn.prepareStatement(query)) {
            preStm.setString(1, professor.getName());
            preStm.setString(2, professor.getEmail());
            preStm.setInt(3, professor.getAge());
            preStm.setString(4, professor.getDepartment());
            preStm.executeUpdate();
            System.out.println("[+] Professor added.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM professors WHERE id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
            System.out.println("[+] Professor deleted.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    @Override
    public void update(Professor professor) {
        String query = "UPDATE professors SET name = ?, email = ?, department = ? WHERE id = ?";
        try (PreparedStatement prestm = cn.prepareStatement(query)) {
            prestm.setString(1, professor.getName());
            prestm.setString(2, professor.getEmail());
            prestm.setString(3, professor.getDepartment());
            prestm.setInt(4, professor.getId());
            prestm.executeUpdate();
            System.out.println("[+] Professor updated.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }
}
