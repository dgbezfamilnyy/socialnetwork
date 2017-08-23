package com.getjavajob.bezfamilnyydg.dao.hierarchy;

import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.models.Friendship;
import com.getjavajob.bezfamilnyydg.models.RequestToFriends;

import java.util.List;
import java.util.Set;


public abstract class AbstractAccountDAO extends AbstractDAO<Account> {
    // name of table and columns
    public static final String ACCOUNT_TBL = "ACCOUNT_TBL";
    public static final String ACC_ID = "ACC_ID_TO";
    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String MIDDLE_NAME = "MIDDLE_NAME";
    public static final String DATE_OF_BIRTH = "DATE_OF_BIRTH";
    public static final String HOME_ADDRESS = "HOME_ADDRESS";
    public static final String WORK_ADDRESS = "WORK_ADDRESS";
    public static final String EMAIL = "EMAIL";
    public static final String ICQ = "ICQ";
    public static final String SKYPE = "SKYPE";
    public static final String ADD_INFO = "ADD_INFO";
    public static final String PASSWORD = "PASSWORD";
    public static final String AVATAR = "IMAGE";
    // name of vars in Account model class
    public static final String EMAIL_FIELD = "email";
    public static final String NAME_FIELD = "name";
    public static final String SURNAME_FIELD = "surname";
    //name of METHODS
    protected static final String GET_BY_ID_METHOD = "getById";
    protected static final String CREATE_METHOD = "create";
    protected static final String ADD_FRIENDSHIPS = "addFriendships";
    protected static final String SUBSCRIBE = "subscribe";
    protected static final String UPDATE_METHOD = "update";
    protected static final String DELETE_METHOD = "delete";
    protected static final String DELETE_FRIENDSHIPS = "deleteFriendships";
    protected static final String UNSUBSCRIBE = "unsubscribeAccount";
    protected static final String GET_ALL_METHOD = "getAll";
    protected static final String GET_BY_EMAIL_METHOD = "getByEmail";
    protected static final String GET_BY_EMAIL_METHOD_NoResultException = "getByEmail method ended with NoResultException and return null";
    protected static final String SEARCH_IN_NAME_SURNAME_METHOD = "searchInNameSurname";
    protected static final String COUNT_OF_SEARCHED_ACCOUNTS_METHOD = "countOfSearchedAccounts";
    protected static final String SEARCH_IN_NAME_SURNAME_FOR_PAGINATION_METHOD = "searchInNameSurnameForPagination";
    protected static final String GET_TAKEN_REQUESTS_TO_FRIENDS = "getTakenRequestsToFriends";
    protected static final String GET_SENT_REQUESTS_TO_FRIENDS = "getSentRequestsToFriends";
    protected static final String GET_FRIENDSHIPS = "getFriendships";
    protected static final String IS_EXIST_REQUEST_TO_FRIENDS= "isExistRequestToFriends";
    protected static final String GET_REQUEST_TO_FRIENDS_LIGHT = "getRequestToFriendsLight";
    protected static final String IS_FRIENDS= "isFriends";
    protected static final String GET_FRIENDSHIPS_LIGHT= "getFriendshipLight";

    /**
     * @param idOfAcc id of account for which we want to set requests
     * @return set taken requests
     */
    public abstract Set<RequestToFriends> getTakenRequestsToFriends(int idOfAcc);

    /**
     * @param idOfAcc id of account, which sent requests
     * @return set of sent requests
     */
    public abstract Set<RequestToFriends> getSentRequestsToFriends(int idOfAcc);

    public abstract Set<Friendship> getFriendships(int id);

    /**
     * @param email for search
     * @return entity or null if no one entity was not found
     */
    public abstract Account getByEmail(String email);

    public abstract Account getById(int id);

    public abstract List<Account> searchInNameSurname(String pattern);

    public abstract long countOfSearchedAccounts(String pattern);

    public abstract List<Account> searchInNameSurnameForPagination(String pattern, int startPosition, int maxResult);

    public abstract boolean isExistRequestToFriends(int idAccFrom, int idAccTo);

    public abstract boolean isFriends(int accId, int accIdFriend);

    /**
     * @param accIdFrom id of account which has subscribe for id accIdTo
     * @param accIdTo id for which account with accIdFrom has subscribe
     */
    public abstract void unsubscribeAccount(int accIdFrom, int accIdTo);

    public abstract void subscribe(int accIdFrom, int accIdTo);

    public abstract void deleteFriendships(int id, int idOfFriend);

    public abstract void addFriendships(int id, int idOfFriend);

}
