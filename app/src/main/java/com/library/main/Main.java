package com.library.main;


import com.library.ui.ConsoleUI;
import com.library.utils.InputValidator;
import com.library.utils.db.DbConnection;
import com.library.utils.db.Tables;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // create tables by uncommenting
//        Connection cn = DbConnection.connect();
//        Tables.create(cn);
        ConsoleUI.start();
    }
}