package com.younesnaja.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.younesnaja.app.JDBCManager.insertCustomersID;

public class Main {
    public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
        Handler globalExceptionHandler = new Handler();
        Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler);

        PropertiesManager.loadPropertiesFile();

        insertCustomersID(new ArrayList<>());
    }
}
