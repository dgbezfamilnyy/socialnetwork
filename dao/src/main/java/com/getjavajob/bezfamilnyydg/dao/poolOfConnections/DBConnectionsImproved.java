package com.getjavajob.bezfamilnyydg.dao.poolOfConnections;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.getjavajob.bezfamilnyydg.models.Utils.Checker.checkNullWithExactMessage;

public class DBConnectionsImproved implements PoolOfConnections {
    private BlockingQueue<Connection> connections;
    private int numOfConnections;
    private Lock lock = new ReentrantLock();
    private Condition poolIsFull = lock.newCondition();
    private ThreadLocal<Connection> currentConnection = new ThreadLocal<>();

    public DBConnectionsImproved(String fileOfProperties) throws DAOException {

        try {

            Properties props = getProperties(fileOfProperties);
            numOfConnections = Integer.parseInt(props.getProperty(NUM_CONNECTIONS));
            connections = new LinkedBlockingQueue<>(numOfConnections);
            for (int i = 0; i < numOfConnections; i++) {
                connections.put(initConnection(props));
            }

        } catch (InterruptedException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public Connection getConnection() throws DAOException {

        try {

            checkNullWithExactMessage(connections, "Pool of connections is null");

            if (currentConnection.get() == null) {
                currentConnection.set(connections.take());
            }

            return currentConnection.get();

        } catch (InterruptedException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void putConnection() throws DAOException {

        try {

            checkNullWithExactMessage(connections, "Pool of connections is null");

            Connection connection = currentConnection.get();
            if (connection != null) {
                connections.put(connection);
                currentConnection.set(null);
            }

            if (numOfConnections == connections.size()) {
                try {
                    lock.lock();
                    poolIsFull.signalAll();
                } finally {
                    lock.unlock();
                }
            }

        } catch (InterruptedException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public void shutDown() throws DAOException {

        try {

            if (isAlive()) {
                // don't return from this method while count of connections in pool now no equals
                // initial number of connections
                checkCountOfConnections();

                for (Connection connection : connections) {
                    connection.close();
                }
                connections = null;
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public boolean isAlive() {
        return connections != null;
    }

    private Connection initConnection(Properties props) throws DAOException {
        try {

            String driver = props.getProperty(DRIVER);
            String url = props.getProperty(URL);
            String user = props.getProperty(USER);
            String password = props.getProperty(PASSWORD);
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            return conn;

        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void closeConnection() throws DAOException {
        throw new UnsupportedOperationException();
    }

    private Properties getProperties(String fileOfProperties) throws DAOException {
        try {

            Properties props = new Properties();
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileOfProperties);

            checkNullWithExactMessage(inputStream, "Class loader can not load file " + fileOfProperties);

            props.load(inputStream);
            return props;

        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    private void checkCountOfConnections() throws DAOException {

        try {

            lock.lock();
            while (numOfConnections != connections.size()) {
                poolIsFull.await();
            }

        } catch (InterruptedException e) {
            throw new DAOException(e);
        } finally {
            lock.unlock();
        }

    }
}
