package com.library.daoImpl;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.utils.db.DbConnection;

import java.sql.*;
import java.sql.Date;
import java.util.HashMap;

public class BookDaoImpl implements BookDAO {

    private Connection cn;

    public BookDaoImpl(){
        this.cn = DbConnection.connect();
    }

    public HashMap<String, Book> getAll() {
        return null;
    }

    public void create(Book book) {
        String query = "INSERT INTO books (title, author, pub_date, num_pages, isbn) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDate(3, Date.valueOf(book.getPubDate()));
            preparedStatement.setInt(4, book.getNumPages());
            preparedStatement.setString(5, book.getIsbn());

            preparedStatement.executeUpdate();
            System.out.println("[+] Book added.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }

    public Book get(String id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Book(
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDate("pub_date").toLocalDate(),
                        resultSet.getInt("num_pages"),
                        resultSet.getString("isbn")
                );
            }
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
        return null;
    }


    public void delete(Book book) {
        String query = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement preStm = cn.prepareStatement(query)) {
            preStm.setString(1, book.getId());
            preStm.executeUpdate();
            System.out.println("[+] Book deleted.");
        } catch (SQLException e){
            System.out.println("[-] SQl error: " + e);
        }
    }

    public void update(Book book) {
        String query = "UPDATE scientific_journals SET title = ?, author = ?, pub_date = ?, num_pages = ? WHERE id = ?";
        try (PreparedStatement prestm = cn.prepareStatement(query)) {
            prestm.setString(1, book.getTitle());
            prestm.setString(2, book.getAuthor());
            prestm.setDate(3, Date.valueOf(book.getPubDate()));
            prestm.setInt(4, book.getNumPages());
            prestm.setString(5, book.getIsbn());
            prestm.setString(6, book.getId());
            prestm.executeUpdate();
            System.out.println("[+] Scientific Journal updated.");
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
    }
}
