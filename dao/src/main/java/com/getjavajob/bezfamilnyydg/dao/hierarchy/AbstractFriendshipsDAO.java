package com.getjavajob.bezfamilnyydg.dao.hierarchy;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;
import com.getjavajob.bezfamilnyydg.models.Friendship;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractFriendshipsDAO extends AbstractDAO<Friendship> {
    // name of vars in Account model class
    public static final String ID = "id";
    public static final String ACC_ID = "accId";
    public static final String ACC_ID_FRIEND = "accIdFriend";

}
