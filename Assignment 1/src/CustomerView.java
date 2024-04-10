import java.util.Map;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public abstract class CustomerView {
    public static final String POLICY_HOLDER_NAME = "POLICY_HOLDER_NAME";
    public static final String DEPENDENT_NAME = "DEPENDENT_NAME";
    public static final String POLICY_ID = "POLICY_ID";
    public static final String DEPENDENT_ID = "DEPENDENT_ID";
    public static final String CUSTOMER_TYPE = "CUSTOMER_TYPE";
    public abstract void display(Customer customer);
    public abstract Map<String, String> displayNewCustomerForm();
}
