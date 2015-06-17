import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 * This class is used for login page
 * @author Matt Gordon
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * simple constructor for user class
     * @param id the username
     * @param pass the password
     */
    public User(String id, String pass) {
        this(id, pass, "", "", "");
    }

    /**
     * full constructor for user class
     * @param id the username
     * @param pass the password
     */
    public User(String id, String pass, String firstName, String lastName, String email) {
        username = id;
        password = pass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * This is for getting username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This is for setting username
     * @param name username entered
     */
    public void setUsername(String name) {
        username = name;
    }

    /**
     * This is for getting password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This is for setting password
     * @param pass password entered
     */
    public void setPassword(String pass) {
        password = pass;
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
}

