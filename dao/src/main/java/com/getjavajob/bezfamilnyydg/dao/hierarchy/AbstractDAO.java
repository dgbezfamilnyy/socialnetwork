package com.getjavajob.bezfamilnyydg.dao.hierarchy;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;
import com.getjavajob.bezfamilnyydg.dao.poolOfConnections.PoolOfConnections;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.getjavajob.bezfamilnyydg.models.Utils.Checker.checkNull;

public abstract class AbstractDAO<E> implements DAO<E> {
    // vars
    @PersistenceContext
    protected EntityManager entityManager;
    //constants
    //for logging
    protected static final String START_METHOD = "Start %s method";
    protected static final String END_METHOD = "End %s method";
    protected static final String GET_OBJECT = "Get %s from DB %s";
    protected static final String DELETE_OBJECT = "Delete from DB %s = %s";

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
