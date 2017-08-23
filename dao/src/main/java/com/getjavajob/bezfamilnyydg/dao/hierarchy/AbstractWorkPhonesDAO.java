package com.getjavajob.bezfamilnyydg.dao.hierarchy;

import com.getjavajob.bezfamilnyydg.models.PersonalPhone;

import java.util.List;

public abstract class AbstractWorkPhonesDAO extends AbstractPhonesDAO {
    // name of table and columns
    public static final String WORK_PHONE_TBL = "WORK_PHONE_TBL";
    public static final String ACC_ID = "ACC_ID_TO";
    public static final String PHONE = "PHONE";
    // SQL statements
    protected static final String SELECT_ALL = "SELECT * FROM " + WORK_PHONE_TBL;
    protected static final String SELECT_BY_ID = SELECT_ALL + " WHERE " + ACC_ID + " = ? ORDER BY PHONE;";
    protected static final String INSERT_INTO = "INSERT INTO " + WORK_PHONE_TBL + "(" + ACC_ID + ", " + PHONE +
            ")" + " VALUES (?, ?);";
    protected static final String DELETE_ALL = "DELETE FROM " + WORK_PHONE_TBL;
    protected static final String DELETE_BY_ACC_ID = "DELETE FROM " + WORK_PHONE_TBL + " WHERE " + ACC_ID + " = ?;";
    protected static final String UPDATE_ACC_ID_BY_ACC_ID = "UPDATE " + WORK_PHONE_TBL + " SET " + ACC_ID + "=? " +
            "WHERE " + ACC_ID + "= ?;";

    public abstract void deleteByAccId(int accId);

    public abstract List<PersonalPhone> getByAccId(int accId);
}
