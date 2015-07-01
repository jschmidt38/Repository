/**
 * Created by thanh on 6/30/15.
 */
public enum Major {
    CS("CS"), CM("CM"), BME("BME"), AE("AE"), BA("BA"), EE("EE"), CmpE("CmpE");

    private String major;

    private Major(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }
}
