import java.util.HashMap;

/**
 * Created by thanh on 6/10/15.
 */
public class UserManager {

    private static UserManager instance;

    private HashMap<String, User> users;
    private User currUser;

    private  UserManager() {
        users = new HashMap<>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public boolean addUser(String id, String pass) {
        if (users.get(id) != null) {
            return false;
        }
        User user = new User(id, pass);
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



}
