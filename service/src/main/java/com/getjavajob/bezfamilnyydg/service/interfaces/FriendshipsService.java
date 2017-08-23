package com.getjavajob.bezfamilnyydg.service.interfaces;

import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractFriendshipsDAO;
import com.getjavajob.bezfamilnyydg.models.Friendship;
import com.getjavajob.bezfamilnyydg.service.ServiceException;

import java.util.List;

public interface FriendshipsService {

    List<Friendship> getListOfFriendshipsByAccId(int accId) throws ServiceException;

    List<Integer> getListOfFriendsIdByAccId(int accId) throws ServiceException;

    void setFriendshipsDAO(AbstractFriendshipsDAO friendshipsDAO);

}
