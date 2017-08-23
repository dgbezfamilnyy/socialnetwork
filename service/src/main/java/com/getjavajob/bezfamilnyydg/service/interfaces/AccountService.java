package com.getjavajob.bezfamilnyydg.service.interfaces;

import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractAccountDAO;
import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractPersonalPhonesDAO;
import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractWorkPhonesDAO;
import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.models.Friendship;
import com.getjavajob.bezfamilnyydg.models.RequestToFriends;
import com.getjavajob.bezfamilnyydg.service.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface AccountService {

    Account getById(int accId);

    Account getByEmail(String email);

    @Transactional
    Account createAccount(Account account);

    @Transactional
    void update(Account accountForUpdate);

    @Transactional
    void delete(Account account);

    List<Account> searchInNameSurname(String pattern);

    Set<Friendship> getFriendships(int id);

    Set<RequestToFriends> getTakenRequestsToFriends(int id);

    Set<RequestToFriends> getSentRequestsToFriends(int id);

    void setAccountDAO(AbstractAccountDAO accountDAO);

    long countOfSearchedAccounts(String pattern);

    List<Account> searchInNameSurnameForPagination(String pattern, int startPosition, int maxResult);

    boolean isExistRequestToFriends(int idAccFrom, int idAccTo);

    boolean isFriends(int accId, int accIdFriend);

    /**
     * @param accIdFrom id of account which has subscribe for id accIdTo
     * @param accIdTo id for which account with accIdFrom has subscribe
     */
    @Transactional
    void unsubscribeAccount(int accIdFrom, int accIdTo);

    @Transactional
    void subscribe(int accIdFrom, int accIdTo);

    @Transactional
    void deleteFriendships(int id, int idOfFriend);
}
