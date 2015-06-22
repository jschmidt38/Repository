import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;

/**
 * This class manages users
 * @author Matt, Thanh
 * @version 1.1
 */
@ManagedBean (name = "userManager")
@ApplicationScoped
public class UserManager {

    private HashMap<String, User> users;
    private User currUser;

    /**
     * This is a constructor
     */
    public  UserManager() {
        users = new HashMap<String, User>();
    }

    /**
     * This is for adding new user
     * @param id username
     * @param pass password
     * @return if the user was added
     */
    public boolean addUser(String id, String pass) {
        return addUser(id, pass, "", "", "");
    }

    /**
     * This is for adding users with more fields
     * @param id username
     * @param pass password
     * @param fistName user's first name
     * @param lastName user's last name
     * @param email user's email
     * @return if the user was added
     */
    public boolean addUser(String id, String pass, String fistName, String lastName, String email) {
        if (users.get(id) != null) {
            return false;
        }
        User user = new User(id, pass, fistName, lastName, email);
        users.put(id, user);
        currUser = user;
        return true;
    }


    /**
     * this is for logging in
     * @param id
     * @param pass
     * @return if the user is logged in
     */
    public boolean login(String id, String pass) {
        User user = users.get(id);
        if (user == null) {
            return false;
        }
        if (!user.getPassword().equals(pass)) {
            return false;
        }
        currUser = user;
        return true;
    }

    /**
     * This is to get the current user
     * @return current user
     */
    public User getUser() {
        return currUser;
    }

    /**
     *This is for loggin out
     */
    public void logout() {
        currUser = null;
    }
}
