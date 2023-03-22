package dbService.dao;

import accounts.UserProfile;
import dbService.dataSets.UsersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public UsersDataSet get(String name) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, name);
    }

    public String insertUser(UserProfile userProfile) throws HibernateException {
        return (String) session.save(new UsersDataSet(userProfile));
    }
}
