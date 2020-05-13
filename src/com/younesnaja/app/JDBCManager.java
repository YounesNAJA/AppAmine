package com.younesnaja.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public abstract class JDBCManager {

    private final static Logger LOGGER = Logger.getLogger(Handler.class.getName());
    private static final String TRUNCATE_TABLE_TEMPTABLE = "com.younesnaja.table.truncate";
    private static final String INSERT_INTO_TEMPTABLE = "com.younesnaja.table.insert";

    public static Connection getConnectToDatabase() throws ClassNotFoundException, SQLException {

        LOGGER.info("JDBC Oracle Driver loading...");

        Class.forName(PropertiesManager.getProperty(Constants.ORACLE_JDBC_DRIVER));

        LOGGER.info("Connecting to database...");
        return DriverManager.getConnection(
                PropertiesManager.getProperty(Constants.URL), PropertiesManager.getProperty(Constants.USERNAME), PropertiesManager.getProperty(Constants.PASSWORD));

    }

    public static void getCustomersID() throws ClassNotFoundException, SQLException {
        Connection con = getConnectToDatabase();
        LOGGER.info("Creating statement to execute the request...");
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM TEMPTABLE");
        LOGGER.info("Query executed on database.");
        while (rs.next())
            LOGGER.info(rs.getString(1));

        con.close();
    }

    public static void insertCustomersID(List<String> customersID) throws ClassNotFoundException, SQLException {
        truncateTable();

        Connection con = getConnectToDatabase();

        PreparedStatement statement = con.prepareStatement(PropertiesManager.getProperty(INSERT_INTO_TEMPTABLE));
        LOGGER.info("PreparedStatement ready to execute the query.");

        customersID = Arrays.asList(new String[]{"ABCDE1", "GFJK2"});

        int i = 0;

        for (String customerID : customersID) {
            statement.setString(1, customerID);
            statement.addBatch();

            i++;
        }

        statement.executeBatch();
        LOGGER.info("Query executed.");

        Integer clientsInserted = i;
        LOGGER.info(clientsInserted + " clients inserted.");

        con.close();
    }

    private static void truncateTable() throws SQLException, ClassNotFoundException {
        Statement stmt = getConnectToDatabase().createStatement();
        LOGGER.info("Creating statement to execute the request...");

        ResultSet rs = stmt.executeQuery(PropertiesManager.getProperty(TRUNCATE_TABLE_TEMPTABLE));
        LOGGER.info("Temporary table truncated successfully.");
    }

}
