import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created by thanh on 6/10/15.
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
    private boolean rejected;

    public UserBean() {
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

    public boolean getRejected() {
        return rejected;
    }

    public String addUser() {
        boolean added = userManager.addUser(id, pass, firstName, lastName, email);
        if(added) {
            return "loggedin";
        } else {
            return "register";
        }
    }

    public String login() {
        Boolean loggedin = userManager.login(id, pass);
        if(loggedin) {
            rejected = false;
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

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public String logout() {
        userManager.logout();
        return "loggedout";
    }
}
