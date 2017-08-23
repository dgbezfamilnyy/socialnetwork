package com.getjavajob.bezfamilnyydg.dao.poolOfConnections;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConnections implements PoolOfConnections {
    private DataSource dataSource;
    private ThreadLocal<Connection> currentConnection = new ThreadLocal<>();

    public DataSourceConnections(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws DAOException {
        try {

            if (currentConnection.get() == null) {
                currentConnection.set(dataSource.getConnection());
                currentConnection.get().setAutoCommit(false);
            }
            return currentConnection.get();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void putConnection() throws DAOException {
        try {

            if (currentConnection.get() != null) {
                currentConnection.get().close();
                currentConnection.remove();
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean isAlive() {
        return dataSource != null;
    }


    @Override
    public void closeConnection() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shutDown() throws DAOException {
        throw new UnsupportedOperationException();
    }
}
