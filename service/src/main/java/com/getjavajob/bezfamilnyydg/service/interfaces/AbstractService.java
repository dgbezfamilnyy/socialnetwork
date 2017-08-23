package com.getjavajob.bezfamilnyydg.service.interfaces;

import com.getjavajob.bezfamilnyydg.service.ServiceException;
import com.getjavajob.bezfamilnyydg.service.transactions.TransactionManager;

import java.sql.Connection;

public abstract class AbstractService {
    protected TransactionManager transactionManager;

    protected void startTransaction() throws ServiceException {
        transactionManager.startTransaction();
    }

    protected void endTransaction() throws ServiceException {
        transactionManager.endTransaction();
    }

    protected void commitTransaction() throws ServiceException {
        transactionManager.commitTransaction();
    }

    @Override
    protected void finalize() {
        try {
            transactionManager.closeConnections();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
