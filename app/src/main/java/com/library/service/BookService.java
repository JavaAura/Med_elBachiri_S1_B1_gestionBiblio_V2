package com.library.service;

import com.library.daoImpl.BookDaoImpl;
import com.library.model.Book;

import java.util.HashMap;

public class BookService {

    private BookDaoImpl bookDAO;

    public BookService() {
        this.bookDAO = new BookDaoImpl();
    }

    public void addNewBook(Book book) {
        bookDAO.create(book);
    }

    public Book getBookById(String id) {
        return bookDAO.get(id);  // Calls the get method from BookDaoImpl
    }

    public HashMap<String, Book> getAllBooks() {
        return bookDAO.getAll();
    }

    public void deleteBook(Book book) {
        bookDAO.delete(book);
    }

    public void updateBook(Book book) {
        bookDAO.update(book);
    }
}
