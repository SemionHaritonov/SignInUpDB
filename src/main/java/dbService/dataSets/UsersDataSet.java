package dbService.dataSets;

import accounts.UserProfile;
import org.h2.engine.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Id
    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "pass", unique = false, updatable = false)
    private String pass;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(String name, String pass, long id) {
        this.setName(name);
        this.setPass(pass);
        this.setId(id);
    }
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(UserProfile userProfile) {
        this.setName(userProfile.getLogin());
        this.setPass(userProfile.getPass());
    }

    public UserProfile getUserProfile() {
        return new UserProfile(getName(), getPass());
    }


    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}