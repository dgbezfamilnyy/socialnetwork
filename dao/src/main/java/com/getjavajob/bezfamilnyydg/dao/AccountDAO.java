package com.getjavajob.bezfamilnyydg.dao;

import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractAccountDAO;
import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractFriendshipsDAO;
import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractRequestsToFriendsDAO;
import com.getjavajob.bezfamilnyydg.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

@Repository
public class AccountDAO extends AbstractAccountDAO {
    private static final Logger logger = LoggerFactory.getLogger(AccountDAO.class);

    @Override
    public List<Account> getAll() {
        logger.info(format(START_METHOD, GET_ALL_METHOD));
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> from = cq.from(Account.class);
        cq.select(from);

        TypedQuery<Account> q = entityManager.createQuery(cq);
        List<Account> accounts = q.getResultList();
        logger.debug(format(GET_OBJECT, "accounts", accounts));
        logger.info(format(END_METHOD, DELETE_METHOD));
        return accounts;
    }

    @Override
    public Account getById(int id) {
        logger.info(format(START_METHOD, GET_BY_ID_METHOD));
        Account account = entityManager.find(Account.class, id);
        logger.debug(format(GET_OBJECT, "account", account));
        logger.info(format(END_METHOD, GET_BY_ID_METHOD));
        return account;
    }

    @Override
    public void delete(Account account) {
        logger.info(format(START_METHOD, DELETE_METHOD));
        logger.debug(format(DELETE_OBJECT, "account", account));
        entityManager.remove(account);
        logger.info(format(END_METHOD, DELETE_METHOD));
    }

    @Override
    public List<Account> searchInNameSurname(String pattern) {
        logger.info(format(START_METHOD, SEARCH_IN_NAME_SURNAME_METHOD));
        String reqEx = "%" + pattern + "%";
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> from = cq.from(Account.class);
        cq.select(from).where(cb.or(cb.like(from.get(NAME_FIELD), reqEx),
                cb.like(from.get(SURNAME_FIELD), reqEx)));

        TypedQuery<Account> query = entityManager.createQuery(cq);
        logger.debug("Get accounts by reqEx = " + reqEx);
        List<Account> accounts = query.getResultList();
        logger.debug(format(GET_OBJECT, "accounts", accounts));
        logger.info(format(END_METHOD, SEARCH_IN_NAME_SURNAME_METHOD));
        return accounts;
    }

    @Override
    public long countOfSearchedAccounts(String pattern) {
        logger.info(format(START_METHOD, COUNT_OF_SEARCHED_ACCOUNTS_METHOD));
        String reqEx = "%" + pattern + "%";
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Account> from = cq.from(Account.class);
        cq.select(cb.count(from)).where(cb.or(cb.like(from.get(NAME_FIELD), reqEx),
                cb.like(from.get(SURNAME_FIELD), reqEx)));

        TypedQuery<Long> query = entityManager.createQuery(cq);
        logger.debug("Get accounts by reqEx = " + reqEx);
        long countAccounts = query.getSingleResult();
        logger.debug("Count of searched accounts is " + countAccounts);
        logger.info(format(END_METHOD, COUNT_OF_SEARCHED_ACCOUNTS_METHOD));
        return countAccounts;
    }

    @Override
    public List<Account> searchInNameSurnameForPagination(String pattern, int startPosition, int maxResult) {
        logger.info(format(START_METHOD, SEARCH_IN_NAME_SURNAME_FOR_PAGINATION_METHOD));
        String reqEx = "%" + pattern + "%";
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> from = cq.from(Account.class);
        cq.select(from).where(cb.or(cb.like(from.get(NAME_FIELD), reqEx),
                cb.like(from.get(SURNAME_FIELD), reqEx)));

        TypedQuery<Account> query = entityManager.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResult);
        logger.debug(format("Get accounts by reqEx = %s start position = %s maxResult = %s", reqEx, startPosition, maxResult));
        List<Account> accounts = query.getResultList();
        logger.debug(format(GET_OBJECT, "accounts", accounts));
        logger.info(format(END_METHOD, SEARCH_IN_NAME_SURNAME_FOR_PAGINATION_METHOD));
        return accounts;
    }

    @Override
    public Account create(Account account) {
        logger.info(format(START_METHOD, CREATE_METHOD));
        logger.debug("Create account = " + account);
        entityManager.persist(account);
        logger.debug("Created account = " + account);
        logger.info(format(END_METHOD, CREATE_METHOD));
        return account;
    }

    @Override
    public void update(Account accountForUpdate) {
        logger.info(format(START_METHOD, UPDATE_METHOD));
        logger.debug("Update account = " + accountForUpdate);
        entityManager.merge(accountForUpdate);
        logger.debug("Updated account = " + accountForUpdate);
        logger.info(format(END_METHOD, UPDATE_METHOD));
    }

    @Override
    public Account getByEmail(String email) {
        try {

            logger.info(format(START_METHOD, GET_BY_EMAIL_METHOD));

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
            Root<Account> from = criteriaQuery.from(Account.class);
            criteriaQuery.where(criteriaBuilder.equal(from.get(EMAIL_FIELD), email));
            criteriaQuery.select(from);
            TypedQuery<Account> typedQuery = entityManager.createQuery(criteriaQuery);

            logger.debug("email by get = " + email);
            Account account = typedQuery.getSingleResult();
            logger.debug(format(GET_OBJECT, "account", account));
            logger.info(format(END_METHOD, GET_BY_EMAIL_METHOD));

            return account;

        } catch (NoResultException e) {
            logger.info(format(END_METHOD, GET_BY_EMAIL_METHOD));
            return null;
        }
    }

    @Override
    public Set<RequestToFriends> getTakenRequestsToFriends(int idOfAcc) {
        logger.info(format(START_METHOD, GET_TAKEN_REQUESTS_TO_FRIENDS));
        Account account = entityManager.find(Account.class, idOfAcc);
        Set<RequestToFriends> requestsToFriends = account.getTakenRequestsToFriends();
        logger.debug(format(GET_OBJECT, "takenRequestsToFriends", requestsToFriends));
        logger.info(format(END_METHOD, GET_TAKEN_REQUESTS_TO_FRIENDS));
        return requestsToFriends;
    }

    @Override
    public Set<RequestToFriends> getSentRequestsToFriends(int idOfAcc) {
        logger.info(format(START_METHOD, GET_SENT_REQUESTS_TO_FRIENDS));
        Account account = entityManager.find(Account.class, idOfAcc);
        Set<RequestToFriends> requestsToFriends = account.getSentRequestsToFriends();
        logger.debug(format(GET_OBJECT, "sentRequestsToFriends", requestsToFriends));
        logger.info(format(END_METHOD, GET_SENT_REQUESTS_TO_FRIENDS));
        return requestsToFriends;
    }

    @Override
    public Set<Friendship> getFriendships(int id) {
        logger.info(format(START_METHOD, GET_FRIENDSHIPS));
        Account account = entityManager.find(Account.class, id);
        Set<Friendship> friendships = account.getFriendships();
        logger.debug(format(GET_OBJECT, "friendships", friendships));
        logger.info(format(END_METHOD, GET_FRIENDSHIPS));
        return friendships;
    }

    @Override
    public boolean isExistRequestToFriends(int idAccFrom, int idAccTo) {
        try {
            logger.info(format(START_METHOD, IS_EXIST_REQUEST_TO_FRIENDS));

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<RequestToFriendsLight> cq = cb.createQuery(RequestToFriendsLight.class);
            Root<RequestToFriendsLight> from = cq.from(RequestToFriendsLight.class);
            cq.select(from).where(cb.and(cb.equal(from.get(AbstractRequestsToFriendsDAO.ACC_ID_TO), idAccTo),
                    cb.equal(from.get(AbstractRequestsToFriendsDAO.ACC_ID_FROM), idAccFrom)));
            TypedQuery<RequestToFriendsLight> typedQuery = entityManager.createQuery(cq);

            RequestToFriendsLight requestToFriendsLight =  typedQuery.getSingleResult();
            logger.debug(format(GET_OBJECT, "requestToFriendsLight", requestToFriendsLight));

            logger.info(format(END_METHOD, IS_EXIST_REQUEST_TO_FRIENDS));
            return requestToFriendsLight != null;

        } catch (NoResultException e) {
            logger.info(format(END_METHOD, IS_EXIST_REQUEST_TO_FRIENDS));
            return false;
        }
    }

    @Override
    public boolean isFriends(int accId, int accIdFriend) {
        try {
            logger.info(format(START_METHOD, IS_FRIENDS));

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<FriendshipLight> cq = cb.createQuery(FriendshipLight.class);
            Root<FriendshipLight> from = cq.from(FriendshipLight.class);
            cq.select(from).where(cb.and(cb.equal(from.get(AbstractFriendshipsDAO.ACC_ID), accId),
                    cb.equal(from.get(AbstractFriendshipsDAO.ACC_ID_FRIEND), accIdFriend)));
            TypedQuery<FriendshipLight> typedQuery = entityManager.createQuery(cq);

            FriendshipLight friendshipLight =  typedQuery.getSingleResult();
            logger.debug(format(GET_OBJECT, "friendshipLight", friendshipLight));

            logger.info(format(END_METHOD, IS_FRIENDS));
            return friendshipLight != null;

        } catch (NoResultException e) {
            logger.info(format(END_METHOD, IS_FRIENDS));
            return false;
        }
    }

    public  void unsubscribeAccount(int accIdFrom, int accIdTo) {
        logger.info(format(START_METHOD, UNSUBSCRIBE));
        RequestToFriendsLight requestToFriendsLight = getRequestToFriendsLight(accIdFrom, accIdTo);
        logger.debug(format(DELETE_OBJECT, "requestToFriendsLight", requestToFriendsLight));
        if (requestToFriendsLight != null) {
            entityManager.remove(requestToFriendsLight);
        }
        logger.info(format(END_METHOD, UNSUBSCRIBE));
    }

    public void subscribe(int accIdFrom, int accIdTo) {
        logger.info(format(START_METHOD, SUBSCRIBE));
        RequestToFriendsLight requestToFriendsLight = new RequestToFriendsLight(accIdFrom, accIdTo);
        logger.debug("Create requestToFriendsLight = " + requestToFriendsLight);
        entityManager.persist(requestToFriendsLight);
        logger.info(format(END_METHOD, SUBSCRIBE));
    }

    public  void deleteFriendships(int id, int idOfFriend) {
        logger.info(format(START_METHOD, DELETE_FRIENDSHIPS));
        FriendshipLight friendshipLight = getFriendshipLight(id, idOfFriend);
        logger.debug(format(DELETE_OBJECT, "friendshipLight", friendshipLight));
        if (friendshipLight != null) {
            entityManager.remove(friendshipLight);
        }
        logger.info(format(END_METHOD, DELETE_FRIENDSHIPS));
    }

    public  void addFriendships(int id, int idOfFriend) {
        logger.info(format(START_METHOD, ADD_FRIENDSHIPS));
        FriendshipLight friendshipLight = new FriendshipLight(id, idOfFriend);
        logger.debug("Create friendshipLight = " + friendshipLight);
        entityManager.persist(friendshipLight);
        logger.info(format(END_METHOD, ADD_FRIENDSHIPS));
    }

    private RequestToFriendsLight getRequestToFriendsLight(int idAccFrom, int idAccTo) {
        try {
            logger.info(format(START_METHOD, GET_REQUEST_TO_FRIENDS_LIGHT));

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<RequestToFriendsLight> cq = cb.createQuery(RequestToFriendsLight.class);
            Root<RequestToFriendsLight> from = cq.from(RequestToFriendsLight.class);
            cq.select(from).where(cb.and(cb.equal(from.get(AbstractRequestsToFriendsDAO.ACC_ID_TO), idAccTo),
                    cb.equal(from.get(AbstractRequestsToFriendsDAO.ACC_ID_FROM), idAccFrom)));
            TypedQuery<RequestToFriendsLight> typedQuery = entityManager.createQuery(cq);

            RequestToFriendsLight requestToFriendsLight =  typedQuery.getSingleResult();
            logger.debug(format(GET_OBJECT, "requestToFriendsLight", requestToFriendsLight));

            logger.info(format(END_METHOD, GET_REQUEST_TO_FRIENDS_LIGHT));
            return requestToFriendsLight;

        } catch (NoResultException e) {
            logger.info(format(END_METHOD, GET_REQUEST_TO_FRIENDS_LIGHT));
            return null;
        }
    }

    private FriendshipLight getFriendshipLight(int accId, int accIdFriend) {
        try {
            logger.info(format(START_METHOD, GET_FRIENDSHIPS_LIGHT));

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<FriendshipLight> cq = cb.createQuery(FriendshipLight.class);
            Root<FriendshipLight> from = cq.from(FriendshipLight.class);
            cq.select(from).where(cb.and(cb.equal(from.get(AbstractFriendshipsDAO.ACC_ID), accId),
                    cb.equal(from.get(AbstractFriendshipsDAO.ACC_ID_FRIEND), accIdFriend)));
            TypedQuery<FriendshipLight> typedQuery = entityManager.createQuery(cq);

            FriendshipLight friendshipLight =  typedQuery.getSingleResult();
            logger.debug(format(GET_OBJECT, "friendshipLight", friendshipLight));

            logger.info(format(END_METHOD, GET_FRIENDSHIPS_LIGHT));
            return friendshipLight;

        } catch (NoResultException e) {
            logger.info(format(END_METHOD, GET_FRIENDSHIPS_LIGHT));
            return null;
        }
    }
}
