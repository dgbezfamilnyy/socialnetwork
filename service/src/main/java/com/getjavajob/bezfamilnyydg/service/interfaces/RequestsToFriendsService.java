package com.getjavajob.bezfamilnyydg.service.interfaces;

import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractRequestsToFriendsDAO;
import com.getjavajob.bezfamilnyydg.models.RequestToFriends;
import com.getjavajob.bezfamilnyydg.service.ServiceException;
import org.springframework.transaction.annotation.Transactional;

public interface RequestsToFriendsService {

    RequestToFriends getRequestByAccIdAndFriendId(int accId, int friendId) throws ServiceException;

    @Transactional
    void sendRequestToFriends(RequestToFriends requestToFriends) throws ServiceException;

    @Transactional
    void deleteRequestToFriends(int accId, int accIdFriend) throws ServiceException;

    void setRequestsToFriendsDAO(AbstractRequestsToFriendsDAO requestToFriends);

}
