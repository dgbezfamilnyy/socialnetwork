package com.getjavajob.bezfamilnyydg.service.transactions;

import com.getjavajob.bezfamilnyydg.service.ServiceException;

import java.sql.Connection;

public interface TransactionManager {

    void startTransaction() throws ServiceException;

    void endTransaction() throws ServiceException;

    void commitTransaction() throws ServiceException;

    void closeConnections() throws ServiceException;
}
