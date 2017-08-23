package com.getjavajob.bezfamilnyydg.service;

import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractAccountDAO;
import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.models.Friendship;
import com.getjavajob.bezfamilnyydg.models.RequestToFriends;
import com.getjavajob.bezfamilnyydg.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AbstractAccountDAO accountDAO;

    @Override
    public void setAccountDAO(AbstractAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Account> searchInNameSurname(String pattern) {
        return accountDAO.searchInNameSurname(pattern);
    }

    @Override
    public Account getById(int accId) {
        Account account = accountDAO.getById(accId);
        return account;
    }

    @Override
    public Account getByEmail(String email) {
        Account account = accountDAO.getByEmail(email);
        return account;
    }

    @Override
    public Account createAccount(Account account) {
        return accountDAO.create(account);
    }

    @Override
    public void update(Account accountForUpdate) {
        accountDAO.update(accountForUpdate);
    }

    @Override
    public void delete(Account account) {
        accountDAO.delete(account);
    }

    @Override
    public long countOfSearchedAccounts(String pattern) {
        return accountDAO.countOfSearchedAccounts(pattern);
    }

    @Override
    public List<Account> searchInNameSurnameForPagination(String pattern, int startPosition, int maxResult) {
        return accountDAO.searchInNameSurnameForPagination(pattern, startPosition, maxResult);
    }

    @Override
    public Set<Friendship> getFriendships(int id) {
        return accountDAO.getFriendships(id);
    }

    @Override
    public Set<RequestToFriends> getTakenRequestsToFriends(int id) {
        return accountDAO.getTakenRequestsToFriends(id);
    }

    @Override
    public Set<RequestToFriends> getSentRequestsToFriends(int id) {
        return accountDAO.getSentRequestsToFriends(id);
    }

    @Override
    public boolean isExistRequestToFriends(int idAccFrom, int idAccTo) {
        return accountDAO.isExistRequestToFriends(idAccFrom, idAccTo);
    }

    @Override
    public boolean isFriends(int accId, int accIdFriend) {
        return accountDAO.isFriends(accId, accIdFriend);
    }

    @Override
    public void unsubscribeAccount(int id, int idForUnsubscribe) {
        accountDAO.unsubscribeAccount(id, idForUnsubscribe);
    }

    @Override
    public void subscribe(int accIdFrom, int accIdTo) {
        if (accountDAO.isExistRequestToFriends(accIdTo, accIdFrom)) {
            //delete subscribes because has response friend request and add friendships
            accountDAO.unsubscribeAccount(accIdFrom, accIdTo);
            accountDAO.unsubscribeAccount(accIdTo, accIdFrom);
            accountDAO.addFriendships(accIdFrom, accIdTo);
            accountDAO.addFriendships(accIdTo, accIdFrom);
        } else {
            accountDAO.subscribe(accIdFrom, accIdTo);
        }
    }

    @Override
    public void deleteFriendships(int id, int idOfFriend) {
        accountDAO.deleteFriendships(id, idOfFriend);
        accountDAO.deleteFriendships(idOfFriend, id);
        accountDAO.subscribe(idOfFriend, id);
    }
}
