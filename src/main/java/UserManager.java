import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;

/**
 * Created by thanh on 6/10/15.
 */
@ManagedBean (name = "userManager")
@ApplicationScoped
public class UserManager {

    private HashMap<String, User> users;
    private User currUser;

    public  UserManager() {
        users = new HashMap<String, User>();
    }

    public boolean addUser(String id, String pass) {
        return addUser(id, pass, "", "", "");
    }
    public boolean addUser(String id, String pass, String fistName, String lastName, String email) {
        if (users.get(id) != null) {
            return false;
        }
        User user = new User(id, pass, fistName, lastName, email);
        users.put(id, user);
        currUser = user;
        return true;
    }


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

    public User getUser() {
        return currUser;
    }

    public void logout() {
        currUser = null;
    }
}
