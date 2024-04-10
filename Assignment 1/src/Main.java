/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class Main {
    public static void main(String[] args) {
        CustomerController controller = new CustomerController(new CustomerViewText(), new InsuranceCardViewText());
        controller.eventLoop();
    }
}
