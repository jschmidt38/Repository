import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User {
    private String username;
    private String password;
    private boolean isUser;

    public User() {
        System.out.println("Making user");
        isUser = false;
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
    public String getIsUser() {
        if (username.compareTo("Admin") == 1){
            isUser = true;
            return "Go Ahead";
        }
        else {
            isUser = false;
            return "nope";
        }
    }
}

