package main.accounts;

import main.DBException;
import main.DBService;

public class AccountService {
    DBService dbService;

    public AccountService(DBService dbService) {
        this.dbService = dbService;
    }

    public void addNewUser(UserProfile userProfile) throws DBException {
        String userId = dbService.addUser(userProfile);
        System.out.println("Added user name: " + userId);
    }

    public UserProfile getUserByLogin(String login) throws DBException {
        if (dbService.getUser(login) != null) {
            return dbService.getUser(login).getUserProfile();
        } else {
            return null;
        }
    }
}
