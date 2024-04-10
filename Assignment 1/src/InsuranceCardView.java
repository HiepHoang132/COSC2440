import java.util.Map;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public abstract class InsuranceCardView {
    public static final String POLICY_OWNER = "POLICY_OWNER";
    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String DAY = "DAY";
    public abstract Map<String, String> displayNewInsuranceCardForm();
}
