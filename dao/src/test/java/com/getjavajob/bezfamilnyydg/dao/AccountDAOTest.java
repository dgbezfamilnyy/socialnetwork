package com.getjavajob.bezfamilnyydg.dao;


import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;
import com.getjavajob.bezfamilnyydg.dao.hierarchy.AbstractAccountDAO;
import com.getjavajob.bezfamilnyydg.models.*;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:spring-dao-overrides.xml"})
public class AccountDAOTest {
    @Autowired
    private AbstractAccountDAO accountDAO;
    @Autowired
    private DataSource dataSource;

    @Before
    public void init() throws DAOException, InterruptedException, SQLException, FileNotFoundException {
        //initializingData();
    }

    @After
    public void after() throws DAOException, InterruptedException, SQLException, FileNotFoundException {
        //deleteAllAfterTest();
    }

    @Test
    public void getAll() {
        List<Account> accountsFromDB = accountDAO.getAll();
        assertEquals(2, accountsFromDB.size());
    }

    @Test
    public void updateAccount1() {
        Account accountForUpdate = accountDAO.getById(1);
        accountForUpdate.setName("updatedName");
        accountForUpdate.getPersonalPhoneNumber().add(new PersonalPhone("+7(222)222-22-22", accountForUpdate));
        accountForUpdate.getWorkPhoneNumber().add(new WorkPhone("+7(222)222-22-22", accountForUpdate));

        accountDAO.update(accountForUpdate);

        Account accountFromDB = accountDAO.getById(1);
        assertEquals(accountForUpdate, accountFromDB);
    }

    @Test
    public void updateAccount2() {
        Account accountForUpdate = accountDAO.getById(1);
        accountForUpdate.setName("updatedName");
        accountForUpdate.getPersonalPhoneNumber().removeAll(accountForUpdate.getPersonalPhoneNumber());
        accountForUpdate.getWorkPhoneNumber().removeAll(accountForUpdate.getWorkPhoneNumber());

        accountDAO.update(accountForUpdate);

        Account accountFromDB = accountDAO.getById(1);
        assertEquals(accountForUpdate, accountFromDB);
    }

/*    @Test
    public void updateAccount3() {
        //test delete
        Account accountForUpdate = accountDAO.getById(1);
        Account accountForUpdate2 = accountDAO.getById(2);
        accountForUpdate.getTakenRequestsToFriends().clear();
        accountForUpdate.getSentRequestsToFriends().clear();
        accountForUpdate2.getTakenRequestsToFriends().clear();
        accountForUpdate2.getSentRequestsToFriends().clear();

        accountDAO.update(accountForUpdate);
        accountDAO.update(accountForUpdate2);

        Account accountFromDB = accountDAO.getById(1);
        Account accountFromDB2 = accountDAO.getById(2);
        assertEquals(0, accountFromDB.getTakenRequestsToFriends().size());
        assertEquals(0, accountFromDB.getSentRequestsToFriends().size());
        assertEquals(0, accountFromDB2.getTakenRequestsToFriends().size());
        assertEquals(0, accountFromDB2.getSentRequestsToFriends().size());

        //test add
        accountForUpdate = accountDAO.getById(1);
        accountForUpdate2 = accountDAO.getById(2);
        accountForUpdate.getSentRequestsToFriends().add(new RequestToFriends(accountForUpdate, accountForUpdate2));
        accountForUpdate2.getTakenRequestsToFriends().add(new RequestToFriends(accountForUpdate, accountForUpdate2));
        accountForUpdate2.getSentRequestsToFriends().add(new RequestToFriends(accountForUpdate2, accountForUpdate));
        accountForUpdate.getTakenRequestsToFriends().add(new RequestToFriends(accountForUpdate2, accountForUpdate));

        accountDAO.update(accountForUpdate);
        accountDAO.update(accountForUpdate2);

        accountFromDB = accountDAO.getById(1);
        accountFromDB2 = accountDAO.getById(2);

        assertEquals(1, accountFromDB.getTakenRequestsToFriends().size());
        assertEquals(1, accountFromDB.getSentRequestsToFriends().size());
        assertEquals(1, accountFromDB2.getTakenRequestsToFriends().size());
        assertEquals(1, accountFromDB2.getSentRequestsToFriends().size());

    }*/

/*    @Test
    public void updateAccount4() {
        //test delete
        Account accountForUpdate = accountDAO.getById(1);
        Account accountForUpdate2 = accountDAO.getById(2);
        accountForUpdate.getFriendships().clear();
        accountForUpdate2.getFriendships().clear();

        accountDAO.update(accountForUpdate);
        accountDAO.update(accountForUpdate2);

        Account accountFromDB = accountDAO.getById(1);
        Account accountFromDB2 = accountDAO.getById(2);
        assertEquals(0, accountFromDB.getFriendships().size());
        assertEquals(0, accountFromDB2.getFriendships().size());

        //test add
        accountForUpdate = accountDAO.getById(1);
        accountForUpdate2 = accountDAO.getById(2);
        accountForUpdate.getFriendships().add(new Friendship(accountForUpdate, accountForUpdate2));
        accountForUpdate2.getFriendships().add(new Friendship(accountForUpdate2, accountForUpdate));

        accountDAO.update(accountForUpdate);
        accountDAO.update(accountForUpdate2);

        accountFromDB = accountDAO.getById(1);
        accountFromDB2 = accountDAO.getById(2);

        assertEquals(1, accountFromDB.getFriendships().size());
        assertEquals(1, accountFromDB2.getFriendships().size());
    }*/

    @Test
    public void countOfSearchedAccounts() {
        long countOfRecords = accountDAO.countOfSearchedAccounts("a");
        assertEquals(2, countOfRecords);
    }

    @Test
    public void searchInNameSurnameForPagination1() {
        List<Account> searchedAccounts = accountDAO.searchInNameSurnameForPagination("a", 0, 2);
        assertEquals(2, searchedAccounts.size());
    }

    @Test
    public void searchInNameSurnameForPagination2() {
        List<Account> searchedAccounts = accountDAO.searchInNameSurnameForPagination("blabla", 0, 2);
        assertEquals(0, searchedAccounts.size());
    }

    @Test
    public void getByEmail() {
        Account accountFromDB = accountDAO.getByEmail("sergey@mail.ru");
        assertEquals(initAccount(), accountFromDB);
    }

    @Test
    public void getById() {
        Account accountFromDB = accountDAO.getById(1);
        assertEquals(accountFromDB, initAccount());
    }

    @Test
    public void deleteById() {
        Account forRemove = accountDAO.getById(1);
        accountDAO.delete(forRemove);
        Account accountAfterRemove = accountDAO.getById(1);
        assertEquals(null, accountAfterRemove);
    }

    @Test
    public void getTakenRequestsToFriends() {
        Set<RequestToFriends> requestsToFriends = accountDAO.getTakenRequestsToFriends(1);
        assertEquals(1, requestsToFriends.size());
    }

    @Test
    public void getSentRequestsToFriends() {
        Set<RequestToFriends> requestsToFriends = accountDAO.getSentRequestsToFriends(1);
        assertEquals(1, requestsToFriends.size());
    }

    @Test
    public void getFriendships() {
        Set<Friendship> friendships = accountDAO.getFriendships(1);
        assertEquals(1, friendships.size());
    }

    @Test
    public void create() {
        Account forCreate = initAccount();
        String newEmail = "newEmail";
        forCreate.setEmail(newEmail);
        Account accountFromDB = accountDAO.create(forCreate);

        Account expected = initAccount();
        expected.setEmail(newEmail);
        assertEquals(expected, accountFromDB);
    }

    @Test
    public void searchInNameSurname() {
        List<Account> accounts = accountDAO.searchInNameSurname("Sergey");
        assertEquals(1, accounts.size());
    }

    @Test
    public void isExistRequestToFriends() {
        boolean isExistRequestToFriends = accountDAO.isExistRequestToFriends(1, 2);
        assertEquals(true, isExistRequestToFriends);
    }


    @Test
    public void isExistRequestToFriends2() {
        boolean isExistRequestToFriends = accountDAO.isExistRequestToFriends(123, 324);
        assertEquals(false, isExistRequestToFriends);
    }

    @Test
    public void isFriends() {
        boolean isFriends = accountDAO.isFriends(1, 2);
        assertEquals(true, isFriends);
    }

    @Test
    public void isFriends2() {
        boolean isFriends = accountDAO.isFriends(12132, 3424);
        assertEquals(false, isFriends);
    }

    @Test
    public void unsubscribeAccount() {
        accountDAO.unsubscribeAccount(1, 2);
        boolean isExistRequestToFriends = accountDAO.isExistRequestToFriends(1, 2);
        assertEquals(false, isExistRequestToFriends);
    }

    @Test
    public void deleteFriendships() {
        accountDAO.deleteFriendships(1, 2);
        boolean isExistFriendships = accountDAO.isFriends(1, 2);
        assertEquals(false, isExistFriendships);
    }

    @Test
    public void addFriendships() {
        String email1 = "newEmail1";
        String email2 = "newEmail2";

        Account forCreate = initAccount();
        forCreate.setEmail(email1);
        Account accountFromDB1 = accountDAO.create(forCreate);

        Account forCreate2 = initAccount();
        forCreate2.setEmail(email2);
        Account accountFromDB2 = accountDAO.create(forCreate2);

        accountDAO.addFriendships(accountFromDB1.getId(), accountFromDB2.getId());
        boolean isFriendships = accountDAO.isFriends(accountFromDB1.getId(), accountFromDB2.getId());
        assertEquals(true, isFriendships);
    }

    @Test
    public void subscribe() {
        String email1 = "newEmail1";
        String email2 = "newEmail2";

        Account forCreate = initAccount();
        forCreate.setEmail(email1);
        Account accountFromDB1 = accountDAO.create(forCreate);

        Account forCreate2 = initAccount();
        forCreate2.setEmail(email2);
        Account accountFromDB2 = accountDAO.create(forCreate2);

        accountDAO.subscribe(accountFromDB1.getId(), accountFromDB2.getId());
        boolean isExistRequestsToFriends = accountDAO.isExistRequestToFriends(accountFromDB1.getId(), accountFromDB2.getId());
        assertEquals(true, isExistRequestsToFriends);
    }

    public void initializingData() throws DAOException, SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("AccountInsertTo.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("PersonalPhonesInsertTo.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("WorkPhonesInsertTo.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("RequestsToFriendsInsert.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("FriendshipsInsert.sql")));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteAllAfterTest() throws InterruptedException, FileNotFoundException, SQLException, DAOException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("AccountDeleteAll.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("PersonalPhonesDeleteAll.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("WorkPhonesDeleteAll.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("RequestsToFriendsDeleteAll.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("FriendshipsDeleteAll.sql")));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static Account initAccount() {
        Account account = new Account("Sergey", "Rabotyaga", "Olegovich", LocalDate.parse("1991-03-12"),
                null, null, "Sergey home address",
                "Sergey work address", "sergey@mail.ru", "124423", "sergey@live.com",
                "like sun", "12345", null);
        Set<PersonalPhone> personalPhones = new HashSet<>(Arrays.asList(new PersonalPhone("+7(000)000-00-00", account),
                new PersonalPhone("+7(111)111-11-11", account)));

        Set<WorkPhone> workPhones = new HashSet<>(Arrays.asList(new WorkPhone("+7(000)000-00-00", account),
                new WorkPhone("+7(111)111-11-11", account)));
        account.setPersonalPhoneNumber(personalPhones);
        account.setWorkPhoneNumber(workPhones);
        return account;
    }
}
