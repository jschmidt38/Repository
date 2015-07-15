//import org.brickred.socialauth.AuthProvider;
//import org.brickred.socialauth.Profile;
//import org.brickred.socialauth.SocialAuthConfig;
//import org.brickred.socialauth.SocialAuthManager;
//import org.brickred.socialauth.util.SocialAuthUtil;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * Created by lingyi on 6/28/15.
// */
//@ManagedBean(name = "userSession")
//@SessionScoped
//@RequestScoped
//public class UserSessionBean implements Serializable {
//    private SocialAuthManager manager;
//    private String            originalURL;
//    private String            providerID;
//    private Profile             profile;
//    public UserSessionBean() {
//
//    }
//    public void setOriginalURL(String originalURL) {
//        this.originalURL = originalURL;
//    }
//    public String getProviderID() {
//        return providerID;
//    }
//    public void setProviderID(String providerID) {
//        this.providerID = providerID;
//    }
//    public Profile getProfile() {
//        return profile;
//    }
//    public void socialConnect() throws Exception {
//        // Put your keys and secrets from the providers here
//        Properties props = System.getProperties();
//        props.put("graph.facebook.com.consumer_key", "1627578390851985");
//        props.put("graph.facebook.com.consumer_secret", "cfee9214b2a31280678daa97c92a5300");
//        // Define your custom permission if needed
//        props.put("graph.facebook.com.custom_permissions", "email,user_birthday");
//
//        // Initiate required components
//        SocialAuthConfig config = SocialAuthConfig.getDefault();
//        config.load(props);
//        manager = new SocialAuthManager();
//        manager.setSocialAuthConfig(config);
//        HttpSession session =
//                (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        String userName = (String) session.getAttribute("email");
//        // 'successURL' is the page you'll be redirected to on successful login
//        ExternalContext externalContext   = FacesContext.getCurrentInstance().getExternalContext();
//        String          successURL        = "http://localhost:8080/Repository/loggedin.xhtml";
//        String          authenticationURL = manager.getAuthenticationUrl(providerID, successURL);
//        FacesContext.getCurrentInstance().getExternalContext().redirect(authenticationURL);
//
//    }
//
//    public void pullUserInfo() {
//        try {
//            // Pull user's data from the provider
//            ExternalContext    externalContext = FacesContext.getCurrentInstance().getExternalContext();
//            HttpServletRequest request         = (HttpServletRequest) externalContext.getRequest();
//            Map map             = SocialAuthUtil.getRequestParametersMap(request);
//            if (this.manager != null) {
//                AuthProvider provider = manager.connect(map);
//                this.profile          = provider.getUserProfile();
//
//                // Do what you want with the data (e.g. persist to the database, etc.)
//                System.out.println("User's Social profile: " + profile);
//
//                // Redirect the user back to where they have been before logging in
//                FacesContext.getCurrentInstance().getExternalContext().redirect(originalURL);
//
//            } else FacesContext.getCurrentInstance().getExternalContext().redirect(externalContext.getRequestContextPath() + "home.xhtml");
//        } catch (Exception ex) {
//            System.out.println("UserSession - Exception: " + ex.toString());
//        }
//
//    }
//
//    public void logOut() {
//        try {
//            // Disconnect from the provider
//            manager.disconnectProvider(providerID);
//
//            // Invalidate session
//            ExternalContext    externalContext = FacesContext.getCurrentInstance().getExternalContext();
//            HttpServletRequest request         = (HttpServletRequest) externalContext.getRequest();
////            this.invalidateSession(request);
//
//            // Redirect to home page
//            FacesContext.getCurrentInstance().getExternalContext().redirect(externalContext.getRequestContextPath() + "home.xhtml");
//        } catch (IOException ex) {
//            System.out.println("UserSessionBean - IOException: " + ex.toString());
//        }
//    }
//
//    // Getters and Setters
//}
