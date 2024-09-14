package com.library.daoImpl;

import com.library.dao.MagazineDAO;
import com.library.model.Magazine;
import com.library.utils.db.DbConnection;

import java.sql.*;
import java.util.HashMap;

public class MagazineDaoImpl implements MagazineDAO {

    private Connection cn;

    public MagazineDaoImpl() { this.cn = DbConnection.connect();}

    public HashMap<String, Magazine> getAll() {
        String query = "SELECT * FROM magazines";
        HashMap<String, Magazine> magazines = new HashMap<>();
        try {
            Statement statement = cn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Magazine magazine = new Magazine(
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDate("pub_date").toLocalDate(),
                        resultSet.getInt("num_pages"),
                        resultSet.getInt("number")
                );
                magazine.setId(resultSet.getString("id"));
                magazine.setBorrowed(resultSet.getBoolean("borrowed"));
                magazines.put("MAGAZINE_" + magazine.getId(), magazine);
            }
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
        return magazines;
    }

    public Magazine get(String id) {
        String query = "SELECT * FROM magazines WHERE id = ?";
        try {
            PreparedStatement stm = cn.prepareStatement(query);
            stm.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()){
                Magazine magazine = new Magazine(
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDate("pub_date").toLocalDate(),
                        resultSet.getInt("num_pages"),
                        resultSet.getInt("number")
                );
                magazine.setBorrowed(resultSet.getBoolean("borrowed"));
                magazine.setId(String.valueOf(resultSet.getInt("id")));
                return magazine;
            }
        } catch (SQLException e){
            System.out.println("SQL error: " + e);
        }
        return null;
    }

    public void create(Magazine magazine) {
        String query = "INSERT INTO magazines (title, author, pub_date, num_pages, number) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement prestm = cn.prepareStatement(query);
            prestm.setString(1, magazine.getTitle());
            prestm.setString(2, magazine.getAuthor());
            prestm.setDate(3, Date.valueOf(magazine.getPubDate()));
            prestm.setInt(4, magazine.getNumPages());
            prestm.setInt(5, magazine.getNumber());


            prestm.executeUpdate();
            System.out.println("[+] Magazine added.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    public void delete(String id) {
        String query = "DELETE FROM magazines WHERE id = ?";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
            System.out.println("[+] Magazine deleted.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    public void update(Magazine magazine) {
        String query = "UPDATE magazines SET title = ?, author = ?, pub_date = ?, num_pages = ?, number = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(query);
            preparedStatement.setString(1, magazine.getTitle());
            preparedStatement.setString(2, magazine.getAuthor());
            preparedStatement.setDate(3, Date.valueOf(magazine.getPubDate()));
            preparedStatement.setInt(4, magazine.getNumPages());
            preparedStatement.setInt(5, magazine.getNumber());
            preparedStatement.setInt(6, Integer.parseInt(magazine.getId()));
            preparedStatement.executeUpdate();
            System.out.println("[+] Magazine updated successfully.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }
}
