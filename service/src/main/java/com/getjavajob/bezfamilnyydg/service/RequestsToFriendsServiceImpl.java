package com.getjavajob.bezfamilnyydg.service;

import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractRequestsToFriendsDAO;
import com.getjavajob.bezfamilnyydg.models.RequestToFriends;
import com.getjavajob.bezfamilnyydg.service.interfaces.AbstractService;
import com.getjavajob.bezfamilnyydg.service.interfaces.RequestsToFriendsService;
import com.sun.tracing.dtrace.Attributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestsToFriendsServiceImpl/* implements RequestsToFriendsService*/ {
    /*@Autowired
    private AbstractRequestsToFriendsDAO requestsToFriendsDAO;

    @Override
    public void setRequestsToFriendsDAO(AbstractRequestsToFriendsDAO requestsToFriendsDAO) {
        this.requestsToFriendsDAO = requestsToFriendsDAO;
    }

    @Override
    public RequestToFriends getRequestByAccIdAndFriendId(int accId, int friendId) throws ServiceException {
        return requestsToFriendsDAO.getRequestByAccIdAndFriendId(accId, friendId);
    }

    @Override
    public void sendRequestToFriends(RequestToFriends requestToFriends) throws ServiceException {
        requestsToFriendsDAO.insertInto(requestToFriends);
    }

    @Override
    public void deleteRequestToFriends(int accId, int accIdFriend) throws ServiceException {
        requestsToFriendsDAO.deleteRequestToFriends(accId, accIdFriend);
    }*/
}
