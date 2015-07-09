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
 */
@ManagedBean(name = "adminManager")
@ApplicationScoped
@SessionScoped
public class AdminManager {
    UserManager userManager;

    public ArrayList<User> getUserList() {
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
        return list;
    }

    public void setStatus(String status, User user) {
        user.setStatus(status);
        userManager.updateStatus(user);
    }
}
