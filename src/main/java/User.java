import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class User {
    private String username;
    private String password;
    private boolean rejected;

    public User() {
        System.out.println("Making user");
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        username = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String pass) {
        password = pass;
    }

    public boolean isRejected() {
        return rejected;
    }

    public String login() {
        if(username.equals("user") && password.equals("user")) {
            rejected = false;
            return "loggedin";
        }
        rejected = true;
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid login", "Please try again"));
        return "index";
    }
}

