package com.getjavajob.bezfamilnyydg.service;

import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractFriendshipsDAO;
import com.getjavajob.bezfamilnyydg.models.Friendship;
import com.getjavajob.bezfamilnyydg.service.interfaces.AbstractService;
import com.getjavajob.bezfamilnyydg.service.interfaces.FriendshipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipsServiceImpl /*implements FriendshipsService*/ {
    /*@Autowired
    private AbstractFriendshipsDAO friendshipsDAO;

    @Override
    public void setFriendshipsDAO(AbstractFriendshipsDAO friendshipsDAO) {
        this.friendshipsDAO = friendshipsDAO;
    }

    @Override
    public List<Friendship> getListOfFriendshipsByAccId(int accId) throws ServiceException {
        return friendshipsDAO.getListOfFriendshipsByAccId(accId);
    }

    @Override
    public List<Integer> getListOfFriendsIdByAccId(int accId) throws ServiceException {
        return friendshipsDAO.getListOfFriendsIdByAccId(accId);
    }*/
}
