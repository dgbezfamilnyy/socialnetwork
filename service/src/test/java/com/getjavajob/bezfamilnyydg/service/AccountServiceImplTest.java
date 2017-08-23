package com.getjavajob.bezfamilnyydg.service;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;
import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractAccountDAO;
import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.models.Friendship;
import com.getjavajob.bezfamilnyydg.models.RequestToFriends;
import com.getjavajob.bezfamilnyydg.service.interfaces.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {
    private static AbstractAccountDAO accountDAOMock;
    private static AccountService accountServiceInstance;

    @BeforeClass
    public static void beforeClass() throws DAOException {
        accountDAOMock = mock(AbstractAccountDAO.class);
        accountServiceInstance = new AccountServiceImpl();
    }

    @Before
    public void before() {
        accountServiceInstance.setAccountDAO(accountDAOMock);
    }

    @After
    public void after() {
        reset(accountDAOMock);
    }

    @Test
    public void getAccountById() {

        int accId = 1;
        Account account = new Account();

        when(accountDAOMock.getById(accId)).thenReturn(account);

        Account accountFromDB = accountServiceInstance.getById(accId);

        verify(accountDAOMock).getById(accId);
        assertEquals(account, accountFromDB);

    }

    @Test
    public void getTakenRequestsToFriends() {
        int id = 1;
        Set<RequestToFriends> requestsToFriends = new HashSet<>();

        when(accountDAOMock.getTakenRequestsToFriends(id)).thenReturn(requestsToFriends);

        Set<RequestToFriends> requestsToFriendsFromDB = accountServiceInstance.getTakenRequestsToFriends(id);

        verify(accountDAOMock).getTakenRequestsToFriends(id);
        assertEquals(0, requestsToFriendsFromDB.size());

    }

    @Test
    public void getSentRequestsToFriends() {
        int id = 1;
        Set<RequestToFriends> requestsToFriends = new HashSet<>();

        when(accountDAOMock.getSentRequestsToFriends(id)).thenReturn(requestsToFriends);

        Set<RequestToFriends> requestsToFriendsFromDB = accountServiceInstance.getSentRequestsToFriends(id);

        verify(accountDAOMock).getSentRequestsToFriends(id);
        assertEquals(0, requestsToFriendsFromDB.size());
    }

    @Test
    public void getByEmail() {
        String email = "email";
        Account account = new Account();

        when(accountDAOMock.getByEmail(email)).thenReturn(account);

        Account accountFromDB = accountServiceInstance.getByEmail(email);

        verify(accountDAOMock).getByEmail(email);
        assertEquals(account, accountFromDB);
    }

    @Test
    public void createAccount() {

        Account account = initAccount();

        when(accountDAOMock.create(account)).thenReturn(account);

        Account accountFromDB = accountServiceInstance.createAccount(account);

        verify(accountDAOMock).create(account);
        assertEquals(account, accountFromDB);

    }

    @Test
    public void isExistRequestToFriends() {
        boolean expectedRes = false;
        int accIdFrom = 1;
        int accIdTo = 2;

        when(accountDAOMock.isExistRequestToFriends(accIdFrom, accIdTo)).thenReturn(expectedRes);

        boolean res =  accountServiceInstance.isExistRequestToFriends(accIdFrom, accIdTo);

        verify(accountDAOMock).isExistRequestToFriends(accIdFrom, accIdTo);
        assertEquals(expectedRes, res);
    }

    @Test
    public void isFriends() {
        boolean expectedRes = true;
        int accId = 1;
        int accIdFriend = 2;

        when(accountDAOMock.isFriends(accId, accIdFriend)).thenReturn(expectedRes);

        boolean res = accountServiceInstance.isFriends(accId, accIdFriend);

        verify(accountDAOMock).isFriends(accId, accIdFriend);
        assertEquals(expectedRes, res);
    }

    @Test
    public void editAccount() {

        Account account = initAccount();

        accountServiceInstance.update(account);

        verify(accountDAOMock).update(account);

    }

    @Test
    public void unsubscribeAccount() {
        int accIdFrom = 1;
        int accIdTo = 2;

        accountServiceInstance.unsubscribeAccount(accIdFrom, accIdTo);

        verify(accountDAOMock).unsubscribeAccount(accIdFrom, accIdTo);
    }

    @Test
    public void subscribe() {
        int accIdFrom = 1;
        int accIdTo = 2;

        accountServiceInstance.subscribe(accIdFrom, accIdTo);

        verify(accountDAOMock).subscribe(accIdFrom, accIdTo);
    }

    @Test
    public void subscribe2() {
        int accIdFrom = 1;
        int accIdTo = 2;

        when(accountDAOMock.isExistRequestToFriends(accIdTo, accIdFrom)).thenReturn(true);

        accountServiceInstance.subscribe(accIdFrom, accIdTo);

        verify(accountDAOMock).unsubscribeAccount(accIdFrom, accIdTo);
        verify(accountDAOMock).unsubscribeAccount(accIdTo, accIdFrom);
        verify(accountDAOMock).addFriendships(accIdTo, accIdFrom);
        verify(accountDAOMock).addFriendships(accIdFrom, accIdTo);
    }

    @Test
    public void searchInNameSurnameForPagination() {

        String pattern = "a";
        int start = 0;
        int max = 2;
        List<Account> expectedAccounts = new ArrayList<>();

        when(accountDAOMock.searchInNameSurnameForPagination(pattern, start, max)).thenReturn(expectedAccounts);

        List<Account> actualAccounts = accountServiceInstance.searchInNameSurnameForPagination(pattern, start, max);

        verify(accountDAOMock).searchInNameSurnameForPagination(pattern, start, max);
        assertEquals(expectedAccounts, actualAccounts);

    }

    @Test
    public void getFriendships() {
        int id = 1;
        Set<Friendship> friendships = new HashSet<>();

        when(accountDAOMock.getFriendships(id)).thenReturn(friendships);

        Set<Friendship> friendshipsFromDB = accountServiceInstance.getFriendships(id);

        verify(accountDAOMock).getFriendships(id);
        assertEquals(0, friendshipsFromDB.size());
    }

    @Test
    public void countOfSearchedAccounts() {
        String pattern = "a";
        long expectedResult = 2;

        when(accountDAOMock.countOfSearchedAccounts(pattern)).thenReturn(expectedResult);

        long actualResult = accountServiceInstance.countOfSearchedAccounts(pattern);

        verify(accountDAOMock).countOfSearchedAccounts(pattern);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteAccount() {

        Account account = initAccount();

        accountServiceInstance.delete(account);

        verify(accountDAOMock).delete(account);

    }

    @Test
    public void searchInNameSurname() {

        String pattern = "pattern";
        List<Account> accounts = Arrays.asList(new Account());

        when(accountDAOMock.searchInNameSurname(pattern)).thenReturn(accounts);

        List<Account> accountsFromDB = accountServiceInstance.searchInNameSurname(pattern);

        verify(accountDAOMock).searchInNameSurname(pattern);
        assertEquals(accounts, accountsFromDB);

    }

    private static Account initAccount() {
        Account account = new Account("Sergey", "Rabotyaga", "Olegovich",
                LocalDate.of(1991, 3, 12), new HashSet<>(), new HashSet<>(), "Sergey home address", "Sergey work address",
                "insert@mail.ru", "124423", "sergey@live.com", "like sun", "12345", null);

        return account;
    }
}
