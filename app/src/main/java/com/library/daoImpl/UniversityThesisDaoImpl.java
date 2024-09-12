package com.library.daoImpl;

import com.library.dao.UniversityThesisDAO;
import com.library.model.UniversityThesis;
import com.library.utils.db.DbConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public class UniversityThesisDaoImpl implements UniversityThesisDAO {

    private Connection cn;

    public UniversityThesisDaoImpl() {
        this.cn = DbConnection.connect();
    }

    @Override
    public HashMap<String, UniversityThesis> getAll() {
        HashMap<String, UniversityThesis> theses = new HashMap<>();
        String query = "SELECT * FROM university_thesis";

        try (Statement statement = cn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                LocalDate pubDate = resultSet.getDate("pub_date").toLocalDate();
                int numPages = resultSet.getInt("num_pages");
                String dp = resultSet.getString("degree_program");

                UniversityThesis thesis = new UniversityThesis(title, author, pubDate, numPages, dp);
                thesis.setId(id);
                theses.put("UT" +id, thesis);
            }

        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }

        return theses;
    }

    @Override
    public UniversityThesis get(String id) {
        String query = "SELECT * FROM university_thesis WHERE id = ?";
        try (PreparedStatement preStm = cn.prepareStatement(query)) {
            preStm.setString(1, id);
            ResultSet resultSet = preStm.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                LocalDate pubDate = resultSet.getDate("pub_date").toLocalDate();
                int numPages = resultSet.getInt("num_pages");
                String dp = resultSet.getString("degree_program");

                return new UniversityThesis(title, author, pubDate, numPages, dp);
            }

        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
        return null;
    }

    @Override
    public void create(UniversityThesis universityThesis) {
        String query = "INSERT INTO university_thesis (title, author, pub_date, num_pages, degree_program) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setString(1, universityThesis.getTitle());
            preparedStatement.setString(2, universityThesis.getAuthor());
            preparedStatement.setDate(3, Date.valueOf(universityThesis.getPubDate()));
            preparedStatement.setInt(4, universityThesis.getNumPages());
            preparedStatement.setString(5, universityThesis.getDegreeProgram());

            preparedStatement.executeUpdate();
            System.out.println("[+] University Thesis added.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM university_thesis WHERE id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
            System.out.println("[+] University Thesis deleted.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    @Override
    public void update(UniversityThesis universityThesis) {
        String query = "UPDATE university_thesis SET title = ?, author = ?, pub_date = ?, num_pages = ?, degree_program = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setString(1, universityThesis.getTitle());
            preparedStatement.setString(2, universityThesis.getAuthor());
            preparedStatement.setDate(3, Date.valueOf(universityThesis.getPubDate()));
            preparedStatement.setInt(4, universityThesis.getNumPages());
            preparedStatement.setString(5, universityThesis.getDegreeProgram());
            preparedStatement.setString(6, universityThesis.getId());

            preparedStatement.executeUpdate();
            System.out.println("[+] University Thesis updated.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }
}
