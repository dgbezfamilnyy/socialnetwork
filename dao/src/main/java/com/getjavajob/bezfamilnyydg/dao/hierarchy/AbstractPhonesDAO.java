package com.getjavajob.bezfamilnyydg.dao.hierarchy;

import com.getjavajob.bezfamilnyydg.models.PersonalPhone;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractPhonesDAO extends AbstractDAO<PersonalPhone> {

    /*public abstract void insertIntoPhonesByAccId(int id, List<String> phones);

    protected void insertIntoPhonesByAccId(int id, List<String> phones, String sqlCommand) {
        if (phones != null && phones.size() > 0) {
            this.jdbcTemplate.batchUpdate(sqlCommand, new PhonesBatchPreparedStatementSetter(id, phones));
        }
    }

    protected void deleteByAccId(int id, String sqlCommand) {
        this.jdbcTemplate.update(sqlCommand, id);
    }

    private static final class PhonesBatchPreparedStatementSetter implements BatchPreparedStatementSetter {
        private List<String> listPhones;
        private int accId;

        PhonesBatchPreparedStatementSetter(Integer id, List<String> phones) {
            listPhones = phones;
            accId = id;
        }

        @Override
        public void setValues(PreparedStatement ps, int i) throws SQLException {
            ps.setInt(1, accId);
            ps.setString(2, listPhones.get(i));
        }

        @Override
        public int getBatchSize() {
            return listPhones.size();
        }
    }*/
}
