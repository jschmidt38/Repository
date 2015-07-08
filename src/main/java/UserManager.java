import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages users
 * @author Matt, Thanh
 * @version 1.1
 */
@ManagedBean (name = "userManager")
@ApplicationScoped
@SessionScoped
public class UserManager {

    private User currUser;
    //private static final long serialVersionUID = -1611162265998907599L;
    /**
     * This is for adding new user
     * @param id username
     * @param pass password
     * @return if the user was added
     */
    public boolean addUser(String id, String pass) {
        return addUser(id, pass, "", "", "","");
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
    public boolean addUser(String id, String pass, String fistName, String
            lastName, String email, String major) {
        if (findUser(id)) {
            return false;
        }
        User newUser = new User(id, pass, fistName, lastName, email, major);
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

    /**
     * update the password to database
     * @param update
     */
    public static void updatePassword(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "password = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getPassword());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update the firstname to database
     * @param update
     */
    public static void updateFirstName(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "firstname = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getFirstName());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update lastname into database
     * @param update
     */
    public static void updateLastName(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "lastname = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getLastName());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update email to database
     * @param update
     */
    public static void updateEmail(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "email = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getEmail());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update major into databse
     * @param update
     */
    public static void updateMajor(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "major = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getMajor());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update status into database
     * @param update
     */
    public static void updateStatus(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "status = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getStatus());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update the user's profile into database
     * @return profile
     */
    public String updateProfile() {
        if (currUser.getPassword() != null && currUser.getPassword().length() >= 6) {
            updatePassword(currUser);
        }
        if (currUser.getFirstName() != null) {
            updateFirstName(currUser);
        }
        if (currUser.getLastName() != null) {
            updateLastName(currUser);
        }
        if (currUser.getEmail() != null) {
            updateEmail(currUser);
        }
        if (currUser.getMajor() != null) {
            updateMajor(currUser);
        }
        return "profile";
    }

    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM User");
            while (result.next()) {
                userList.add(new User(result.getString("username"), result
                        .getString("password"), result.getString("firstname")
                        , result.getString("lastname"), result.getString
                        ("email"), result.getString("major")));
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            Database.makeClosed(con);
        }
        return userList;
    }

}