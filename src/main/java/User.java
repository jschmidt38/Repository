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
    private boolean rejected;
    /**
     * This is a constructor
     */
    public User() {
        System.out.println("Making user");
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
     * This is for checking whether is rejected
     * @return if it is rejected
     */
    public boolean isRejected() {
        return rejected;
    }
    /**
     * This is for check login functionality
     * @return login status
     */
    public String login() {
        if (username.equals("user") && password.equals("user")) {
            rejected = false;
            return "loggedin";
        }
        rejected = true;
// FacesContext.getCurrentInstance().addMessage(null,
        //new FacesMessage(FacesMessage.SEVERITY_WARN,
        //"Invalid login", "Please try again"));
        return "index";
    }
}

