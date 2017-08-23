package com.getjavajob.bezfamilnyydg.dao.Utils;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    /*public static void printSQLExceptionAndCloseConnection(SQLException ex, Connection connection) {
        printSQLException(ex);
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace();
                System.err.println("SQLState: " +
                        ((SQLException) e).getSQLState());

                System.err.println("Error Code: " +
                        ((SQLException) e).getErrorCode());

                System.err.println("Message: " + e.getMessage());

                Throwable t = ex.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static void printSqlExceptionAndRollback(SQLException e, Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            printSQLException(e1);
        }

        printSQLException(e);
    }

    public static void printDAOException(DAOException e) {
        printSuppressed(e);
        printCause(e);
        System.err.println("PRINT STACK TRACE OF EXCEPTION BEGIN");
        e.printStackTrace();
        System.err.println("PRINT STACK TRACE OF EXCEPTION END");
    }

    public static void printSuppressed(Exception e) {
        Throwable[] suppressed = e.getSuppressed();
        int count = 0;
        for (Throwable ex : suppressed) {
            System.err.println("*** " + ++count + " PRINT SUPPRESSED BEGIN***");
            ex.printStackTrace();
            System.err.println("*** " + count + " PRINT SUPPRESSED END***");
        }
    }

    public static void printCause(Exception e) {
        Throwable cause = e.getCause();
        System.err.println("***PRINT CAUSE BEGIN***");
        if (cause != null) {
            if (cause instanceof SQLException) {
                printSQLException((SQLException) cause);
            } else {
                cause.printStackTrace();
            }
        }
        System.err.println("***PRINT CAUSE END***");
    }*/
}
