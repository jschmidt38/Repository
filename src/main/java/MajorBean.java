import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by thanh on 7/1/15.
 */
@ManagedBean
@ApplicationScoped
/**
 *
 */
public class MajorBean {

    public Major[] getMajors() {
        return Major.values();
    }
}
