import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


/**
 * This class is the bean for the UserManager
 * @author Matt, Thanh
 * @version 1.1
 */

@ManagedBean
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
    private String role;
    private String status;
    private String sex;
    private boolean rejected;
    private User currentUser;

    /**
     * this is a constructor
     */
    public UserBean() {
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
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

    /**
     * this is for adding users
     */
    public String addUser() {
        boolean added = userManager.addUser(id, pass, firstName, lastName, email);
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
    public String login() {
        User loggedin = userManager.login(id, pass);
        if(loggedin != null) {
            rejected = false;
            setCurrentUser(loggedin);
            setFirstName(currentUser.getFirstName());
            setLastName(currentUser.getLastName());
            setEmail(currentUser.getEmail());
            setId(currentUser.getUsername());
            setPass(currentUser.getPassword());
            System.out.println(currentUser.getUsername());
            return "loggedin";
        }
// FacesContext.getCurrentInstance().addMessage(null,
        //new FacesMessage(FacesMessage.SEVERITY_WARN,
        //"Invalid login", "Please try again"));
        rejected = true;
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
    public String updateProfile() {
        currentUser.setPassword(pass);
        currentUser.setEmail(email);
        currentUser.setLastName(lastName);
        currentUser.setFirstName(firstName);
        UserManager.updateUser(currentUser);
        return "profile";
    }
}