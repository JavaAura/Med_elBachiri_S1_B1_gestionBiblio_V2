package com.library.dao;

import com.library.model.Book;

import java.util.HashMap;

public interface BookDAO {
    public HashMap<String, Book> getAll();
    public Book get(String id);
    public void create(Book book);
    public void delete(Book book);
    public void update(Book book);
}
