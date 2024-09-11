package com.library.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static java.sql.Connection cn = null;

    private static final String url = System.getenv("DB_URL");
    private static final String usr = System.getenv("DB_USERNAME");
    private static final String pwd = System.getenv("DB_PASSWORD");

    public static Connection connect() {
        if (cn == null) {
            try {
                cn = DriverManager.getConnection(url, usr, pwd);
                System.out.println("Connected to the database successfully.");
            } catch (SQLException e) {
                System.err.println("Connection failed: " + e.getMessage());
            }
        }
        return cn;
    }
}
