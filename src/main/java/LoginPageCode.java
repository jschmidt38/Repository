import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by lingyi on 6/27/15.
 */
@ManagedBean (name ="loginPageCode")
@SessionScoped
public class LoginPageCode implements Serializable{
    private static final long serialVersionUID = -1611162265998907599L;
    UserManager userManager;
    public String getFacebookUrlAuth() {
        HttpSession session =
                (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String sessionId = session.getId();
        String userName = (String) session.getAttribute("email");
        String appId = "1627578390851985";
        String redirectUrl = "http://localhost:8080/Repository/loggedin.xhtml";
        String returnValue = "https://www.facebook.com/dialog/oauth?client_id="
                + appId + "&redirect_uri=" + redirectUrl
                + "&scope=email,user_birthday&state=" + sessionId;

        return returnValue;
    }

    public String getUserFromSession() {
        HttpSession session =
                (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String userName = (String) session.getAttribute("FACEBOOK_USER");
        String pass = (String) session.getAttribute("password");
//        userManager.addUser(userName, pass);
        if (userName != null) {
            return "Hello " + userName;
        }
        else {
            return "no user";
        }
    }
}
