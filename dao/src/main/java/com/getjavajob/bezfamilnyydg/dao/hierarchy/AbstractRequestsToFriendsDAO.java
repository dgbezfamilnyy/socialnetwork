package com.getjavajob.bezfamilnyydg.dao.hierarchy;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;
import com.getjavajob.bezfamilnyydg.models.RequestToFriends;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractRequestsToFriendsDAO extends AbstractDAO<RequestToFriends> {
    // name of vars in RequestToFriendsLight model class
    public static final String ID = "id";
    public static final String ACC_ID_TO = "accIdTo";
    public static final String ACC_ID_FROM = "accIdFrom";
}
