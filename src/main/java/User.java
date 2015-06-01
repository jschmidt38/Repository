import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User {
    private String username;

    public User() {
        System.out.println("Making user");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        username = name;
    }
}
