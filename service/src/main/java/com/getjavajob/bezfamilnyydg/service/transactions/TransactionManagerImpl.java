package com.getjavajob.bezfamilnyydg.service.transactions;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;
import com.getjavajob.bezfamilnyydg.dao.poolOfConnections.PoolOfConnections;
import com.getjavajob.bezfamilnyydg.service.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager {
    private PoolOfConnections poolOfConnections;

    public TransactionManagerImpl(PoolOfConnections poolOfConnections) {
        this.poolOfConnections = poolOfConnections;
    }

    @Override
    public void startTransaction() throws ServiceException {
        try {

            poolOfConnections.getConnection();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void endTransaction() throws ServiceException {
        try {

                Connection connection = poolOfConnections.getConnection();
                if (connection != null) {
                    connection.rollback();
                    poolOfConnections.putConnection();
                }

        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void commitTransaction() throws ServiceException {
        try {

                Connection connection = poolOfConnections.getConnection();
                if (connection != null) {
                    connection.commit();
                }

        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void closeConnections() throws ServiceException {
        try {

            poolOfConnections.shutDown();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
