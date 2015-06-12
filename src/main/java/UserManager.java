import java.util.ArrayList;

/**
 * Created by thanh on 6/10/15.
 */
public class UserManager {

    private ArrayList<User> userList;

    public  UserManager() {
        userList = new ArrayList<>();
    }

    public boolean addUser(String id, String pass) {
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUsername().equals(id)) {
                return false;
            }
        }
        userList.add(new User(id, pass));
        return true;
    }

    public boolean login(String id, String pass) {
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUsername().equals(id) && userList.get(i).getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }



}
