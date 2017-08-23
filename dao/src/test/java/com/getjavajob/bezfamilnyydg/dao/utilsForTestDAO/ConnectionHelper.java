package com.getjavajob.bezfamilnyydg.dao.utilsForTestDAO;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;
import com.getjavajob.bezfamilnyydg.dao.poolOfConnections.PoolOfConnections;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHelper {
    PoolOfConnections poolOfConnections;

    public ConnectionHelper(PoolOfConnections poolOfConnections) {
        this.poolOfConnections = poolOfConnections;
    }


    public Connection getConnection() throws DAOException {
        return poolOfConnections.getConnection();
    }

    public void commitConnection(Connection connection) throws DAOException {
        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void closeConnections() throws DAOException {
        poolOfConnections.shutDown();
    }

    public void putConnection() throws DAOException {
        try {
            poolOfConnections.getConnection().rollback();
            poolOfConnections.putConnection();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
