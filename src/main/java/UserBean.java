import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by thanh on 6/10/15.
 */

@ManagedBean
@RequestScoped
public class UserBean {

    private String id;
    private String pass;
    private boolean rejected;
    private UserManager manager;

    public UserBean() {
        manager = UserManager.getInstance();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean getRejected() {
        return rejected;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public String addUser() {
        boolean added = manager.addUser(id, pass);
        if(added) {
            return "loggedin";
        } else {
            return "register";
        }
    }

    public String login() {
        Boolean loggedin = manager.login(id, pass);
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
}
