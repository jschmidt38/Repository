import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by lingyi on 7/2/15.
 * need javadoc
 */
@ManagedBean(name = "adminManager")
@ApplicationScoped
@SessionScoped
public class AdminManager {
    private UserManager userManager = new UserManager();
    private ArrayList<User> users;
    private boolean isBanned = false;
    private boolean isLock = false;


    /**
     * Get the list of all admins
     */
    public void getUserList() {
        ArrayList<User> list = new ArrayList<User>();
        Connection con = null;
        Statement state = null;
        ResultSet result = null;
        try {
            con = Database.makeConnection();
            try {
                state = con.createStatement();
                result = state.executeQuery("SELECT username, status, firstname, lastname, email, major FROM User");
                while (result.next()) {
                    String iD = result.getString("username");
                    User newUser = new User(iD, "");
                    newUser.setEmail(result.getString("email"));
                    newUser.setFirstName(result.getString("firstname"));
                    newUser.setLastName(result.getString("lastname"));
                    newUser.setMajor(result.getString("major"));
                    newUser.setStatus(result.getString("status"));
                    if (!newUser.getStatus().equalsIgnoreCase("admin")) {
                        list.add(newUser);
                    }
                }
            } catch (SQLException e) {
                System.out.print(e.getMessage());
            }finally {
                if (state != null) {
                    state.close();
                }
                if (result != null) {
                    result.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        users = list;
    }

    /**
     * setter for status
     * @param status new status
     * @param user the user that need update
     */
    public void setStatus(String status, User user) {
        user.setStatus(status);
        userManager.updateStatus(user);
    }

    /**
     * this method uses to ban a user
     * @param user the user need to be banned
     */
    public void ban(User user) {
        user.setStatus("banned");
        userManager.updateStatus(user);
    }

    /**
     * this method is for unbanning users
     * @param user the user that need to be unbanned
     */
    public void unban(User user) {
        user.setStatus("active");
        userManager.updateStatus(user);
    }

    /**
     * unlock a user
     * @param user
     */
    public void unlock(User user) {
        user.setStatus("active");
        userManager.updateStatus(user);
    }

    /**
     * getter for users
     * @return a list of all users
     */
    public ArrayList<User> getUsers() {
        getUserList();
        return users;
    }

    /**
     * getter for isbanned
     * @param user
     * @return
     */
    public boolean getBanned(User user) {
        return user.getStatus().equalsIgnoreCase("banned");
    }

    /**
     * setter for unbanned
     * @param banned new status
     */
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * getter for lock
     * @param user the user
     * @return status of the user
     */
    public boolean getLock(User user) {
        return  user.getStatus().equalsIgnoreCase("locked");
    }

    /**
     * set a user to lock
     * @param locked lock
     */
    public void setLocked(boolean locked) {
        isLock = locked;
    }
    public boolean getBanned() {
        return isBanned;
    }
    public boolean getLocked() {
        return isLock;
    }
}

