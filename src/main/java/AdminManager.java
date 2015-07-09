import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by lingyi on 7/2/15.
 */
@ManagedBean(name = "adminManager")
@ApplicationScoped
@SessionScoped
public class AdminManager {
    private UserManager userManager;
    private ArrayList<User> users;
    private boolean isBanned = false;
    private boolean isLock = false;


    public void getUserList() {
        ArrayList<User> list = new ArrayList<User>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT username, status, firstname, lastname, email, major FROM User");
            while (result.next()) {
                    String ID = result.getString("username");
                    User newUser = new User(ID, "");
                    newUser.setEmail(result.getString("email"));
                    newUser.setFirstName(result.getString("firstname"));
                    newUser.setLastName(result.getString("lastname"));
                    newUser.setMajor(result.getString("major"));
                    newUser.setStatus(result.getString("status"));

                    list.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Database.makeClosed(con);
        }
        users = list;
    }

    public void setStatus(String status, User user) {
        user.setStatus(status);
        userManager.updateStatus(user);
    }

    public void ban(User user) {
        user.setStatus("banned");
        userManager.updateStatus(user);
    }

    public void unban(User user) {
        user.setStatus("unbanned");
        userManager.updateStatus(user);
    }

    public void unlock(User user) {
        user.setStatus("active");
        userManager.updateStatus(user);
    }

    public ArrayList<User> getUsers() {
        getUserList();
        return users;
    }

    public boolean getBanned(User user) {
        if(user.getStatus().equalsIgnoreCase("banned")) {
            return true;
        } else {
            return false;
        }
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean getLock(User user) {
        if(user.getStatus().equalsIgnoreCase("locked")) {
            return true;
        } else {
            return false;
        }
    }

    public void setLocked(boolean locked) {
        isLock = locked;
    }
}
