import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This class manages users
 * @author Matt, Thanh
 * @version 1.1
 */
@ManagedBean (name = "userManager")
@ApplicationScoped
public class UserManager {

    private User currUser;

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
        if (findUser(id)) {
            return false;
        }
        User newUser = new User(id, pass, fistName, lastName, email);
        currUser = newUser;
        Connection con = Database.makeConnection();
        try {
            String query = "INSERT INTO User(username, password, firstName, lastname, email, major, status)"
                    + " values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, newUser.getUsername());
            preparedStmt.setString(2, newUser.getPassword());
            preparedStmt.setString(3, newUser.getFirstName());
            preparedStmt.setString(4, newUser.getLastName());
            preparedStmt.setString(5, newUser.getEmail());
            preparedStmt.setString(6, newUser.getMajor());
            preparedStmt.setString(7, newUser.getStatus());
            preparedStmt.execute();
            return true;
        } catch (Exception exc) {
            System.out.printf("There is something wrong.");
        } finally {
            Database.makeClosed(con);
        }
        return false;
    }
    private boolean findUser(String id) {
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT username FROM User");
            while (result.next()) {
                if (result.getString("username").equals(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            Database.makeClosed(con);
        }
        return false;
    }


    private User searchUser(String id, String pass) {
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM User");
            while (result.next()) {
                if (result.getString("username").equals(id) && result.getString("password").equals(pass)) {
                    String ID = result.getString("username");
                    String passWord = result.getString("password");
                    User newUser = new User(ID, passWord);
                    newUser.setEmail(result.getString("email"));
                    newUser.setFirstName(result.getString("firstname"));
                    newUser.setLastName(result.getString("lastname"));
                    newUser.setMajor(result.getString("major"));
                    newUser.setStatus(result.getString("status"));
                    return newUser;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            Database.makeClosed(con);
        }
        return null;
    }
    /**
     * this is for logging in
     * @param id
     * @param pass
     * @return if the user is logged in
     */
    public User login(String id, String pass) {
        currUser = searchUser(id, pass);
        if (currUser == null) {
            return null;
        }
        return currUser;
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

    public static void updateUser(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET "
                    + "password = ?,"
                    + "firstname = ?,"
                    + "lastname = ?,"
                    + "email = ?,"
                    + "major = ?,"
                    + " WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            if (update.getPassword() != null && update.getPassword().length() >= 6) {
                preparedStmt.setString(1, update.getPassword());
            }
            if (update.getFirstName() != null) {
                preparedStmt.setString(2, update.getFirstName());
            }
            if (update.getLastName() != null) {
                preparedStmt.setString(3, update.getLastName());
            }
            if (update.getEmail() != null) {
                preparedStmt.setString(4, update.getEmail());
            }
            if (update.getMajor() != null) {
                preparedStmt.setString(5, update.getMajor());
            }
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            Database.makeClosed(con);
        }
    }
    public String updateProfile() {
        updateUser(currUser);
        return "profile";
    }
}