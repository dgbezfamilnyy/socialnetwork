package com.getjavajob.bezfamilnyydg.dao.utilsForTestDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.getjavajob.bezfamilnyydg.dao.Utils.ExceptionPrinter.printSQLException;

public class UtilsForTest {
    public static Connection getConnection(String fileName) {
        Connection conn = null;
        try {
            Properties props = new Properties();
            props.load((ClassLoader.getSystemResourceAsStream(fileName)));
            String driver = props.getProperty("database.driver");
            String url = props.getProperty("database.url");
            String user = props.getProperty("database.user");
            String password = props.getProperty("database.password");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
