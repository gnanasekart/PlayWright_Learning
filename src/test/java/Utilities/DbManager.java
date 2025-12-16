package Utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private static Connection connection = null;//SQL
    private static Connection connection2 = null;//MySQL

    //SQL Server
    public static void setDbConnection() {
        try {
            Class.forName(TestConfig.driver);
            connection = DriverManager.getConnection(TestConfig.dbConnectionUrl, TestConfig.dbUserName, TestConfig.dbPassword);

            if (!connection.isClosed())
                System.out.println("Successfully connected to SQL Server database!");
        } catch (Exception e) {
            System.err.println("Exceptions : "+e.getMessage());
        }
    }

    public static void setMySQLConnection() {
        try {
            Class.forName(TestConfig.mysqldriver);
            connection2 = DriverManager.getConnection(TestConfig.mysqlurl, TestConfig.mysqluserName, TestConfig.mysqlpassword);

            if (!connection2.isClosed())
                System.out.println("Successfully connected to MySQL database!");
        } catch (Exception e) {
            System.err.println("Exceptions : "+e.getMessage());
        }
    }

    public static List<String> getQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static List<String> getMySQLQuery(String query) throws SQLException {
        Statement statement = connection2.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static Connection getConnection() {
        return connection;
    }
}
