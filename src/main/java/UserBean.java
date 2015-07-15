import javax.faces.bean.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class is the bean for the UserManager
 * @author Matt, Thanh
 * @version 1.1
 */

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {
    @ManagedProperty("#{userManager}")
    UserManager userManager;
    private String id;
    private String pass;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private boolean rejected;
    private User currentUser;
    private String loginMessage;
    private static int loginAttemp = 0;

    /**
     * this is a constructor
     */
    public UserBean() {
    }

    public User getUser() {
        return currentUser;
    }
    /**
     * This is for getting username
     * @return id the username
     */
    public String getId() {
        return id;
    }

    /**
     * This is for setting username
     * @param id username entered
     */
    public void setId(String id) {
        this.id = id;

    }

    /**
     * This is for getting password
     * @return pass the password
     */
    public String getPass() {
        return pass;
    }

    /**
     * This is for setting password
     * @param pass password entered
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * This is for getting firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This is for setting firstName
     * @param firstName firstName entered
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This is for getting lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This is for setting lastName
     * @param lastName lastName entered
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This is for getting email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This is for setting email
     * @param email email entered
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This is for getting rejected
     * @return whether the login is rejected
     */
    public boolean getRejected() {
        return rejected;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setLoginMessage(String msg) {
        loginMessage = msg;
    }

    public String getLoginMessage() {
        return loginMessage;
    }
    /**
     * this is for adding users
     */
    public String addUser() {
        boolean added = userManager.addUser(id, pass, firstName, lastName,
                email, major);
        if(added) {
            return "loggedin";
        } else {
            rejected = true;
            return "register";
        }
    }

    /**
     * This is for logging in
     */
    public String login() throws SQLException {

        currentUser = userManager.login(id, pass);
        if(currentUser != null) {
            if(currentUser.getStatus().equalsIgnoreCase("locked") || currentUser.getStatus().equalsIgnoreCase("banned")) {
                setLoginMessage("Your account has been " + currentUser.getStatus());
                rejected = true;
                return "index";
            } else {
                rejected = false;
                setFirstName(currentUser.getFirstName());
                setLastName(currentUser.getLastName());
                setEmail(currentUser.getEmail());
                setId(currentUser.getUsername());
                setPass(currentUser.getPassword());
                System.out.println(currentUser.getUsername());
                if(currentUser.getStatus().equalsIgnoreCase("admin")) {
                    return "admin";
                }
                return "loggedin";
            }
        }
        rejected = true;
        setLoginMessage("Wrong login credentials ");
        loginAttemp++;  // add one attemp when wrong password
        Connection con = null;
        Statement state = null;
        ResultSet result = null;
        if(loginAttemp > 3) {
            try {
                con = Database.makeConnection();
                try {
                    state = con.createStatement();
                    result = state.executeQuery("SELECT username FROM User");
                    boolean notFound = true;
                    while (result.next() && notFound) {
                        if (result.getString("username").equals(id)) {
                            User tempUser = new User(id, "");
                            tempUser.setStatus("locked");
                            userManager.updateStatus(tempUser);
                            loginAttemp = 0;
                        }
                    }
                }catch(SQLException e){
                    System.out.print(e.getMessage());
                } finally {
                    if (state != null) {
                        state.close();
                    }
                    if (state != null) {
                        result.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "index";
    }

    /**
     * Cancels current login attempt
     * @return the welcome page
     */
    public String cancel() {
        id = null;
        pass = null;
        rejected = false;
        return "welcomePage";
    }

    /**
     * This is for setting userManager
     * @param userManager
     */
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * This is for logout
     * @return name of the page that will be direct to after logging out
     */
    public String logout() {
        userManager.logout();
        return "loggedout";
    }

}