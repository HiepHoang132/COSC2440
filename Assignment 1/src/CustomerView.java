import java.util.Map;
import java.util.Scanner;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public abstract class CustomerView {
    public static final String FULL_NAME = "FULL_NAME";
    public static final String CUSTOMER_TYPE = "CUSTOMER_TYPE";
    public abstract void display(Customer customer);
    public abstract Map<String, String> displayNewCustomerForm();
}
