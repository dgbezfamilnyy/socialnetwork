package com.getjavajob.bezfamilnyydg.dao.poolOfConnections;


import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface PoolOfConnections {
    String NUM_CONNECTIONS = "pool.connection.num";
    String DRIVER = "database.driver";
    String URL = "database.url";
    String USER = "database.user";
    String PASSWORD = "database.password";

    Connection getConnection() throws DAOException;

    /**
     * Realize for pool which will manage from outer
     * @throws DAOException
     */
    void putConnection() throws DAOException;

    /**
     * Realize for pool which will not manage from outer
     * @throws DAOException
     */
    void closeConnection() throws DAOException;

    void shutDown() throws DAOException;

    boolean isAlive();
}
