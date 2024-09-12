package com.library.utils.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {
    // create all tables at once
    public static void create(Connection connection) {
        create_documents_table(connection);
        create_books_table(connection);
        create_magazines_table(connection);
        create_scientific_journals_table(connection);
        create_university_thesis_table(connection);
        create_users_table(connection);
        create_students_table(connection);
        create_professors_table(connection);
    }

    private static void dynamic_create_table(Connection con, String tableName, String columns) {
        Statement stmt = null;
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                    + "id SERIAL PRIMARY KEY, "
                    + columns + ");";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("[+] \"" + tableName + "\" table has been created successfully!");
        } catch (SQLException e) {
            System.err.println("[-] Error creating " + tableName + ": " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("[-] Error closing statement: " + e.getMessage());
                }
            }
        }
    }

    private static void create_documents_table(Connection cn) {
        dynamic_create_table(cn,
                "documents",
                "title VARCHAR(255), author VARCHAR(60), pub_date DATE, num_pages INT, borrowed BOOLEAN DEFAULT false"
        );
    }

    private static void create_books_table(Connection cn) {
        dynamic_create_table(cn,
                "books",
                "isbn TEXT, document_id INT REFERENCES documents(id)) INHERITS (documents"
        );
    }

    private static void create_magazines_table(Connection cn) {
        dynamic_create_table(cn,
                "magazines",
                "number INT, document_id INT REFERENCES documents(id)) INHERITS (documents"
        );
    }

    private static void create_scientific_journals_table(Connection cn) {
        dynamic_create_table(cn,
                "scientific_journals",
                "impact_factor BIGINT, document_id INT REFERENCES documents(id)) INHERITS (documents"
        );
    }

    private static void create_university_thesis_table(Connection cn) {
        dynamic_create_table(cn,
                "university_thesis",
                "degree_program TEXT, document_id INT REFERENCES documents(id)) INHERITS (documents"
        );
    }

    private static void create_users_table(Connection cn) {
        dynamic_create_table(cn,
                "users",
                "name VARCHAR(60), email VARCHAR(255), age INT"
        );
    }

    private static void create_students_table(Connection cn) {
        dynamic_create_table(cn,
                "students",
                "integration_date DATE, user_id INT REFERENCES users(id)) INHERITS (users"
        );
    }

    private static void create_professors_table(Connection cn) {
        dynamic_create_table(cn,
                "professors",
                "department TEXT, user_id INT REFERENCES users(id)) INHERITS (users"
        );
    }
}
