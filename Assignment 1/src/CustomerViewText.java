import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class CustomerViewText extends CustomerView {
    @Override
    public void display(Customer customer) {
        if(customer instanceof PolicyHolder policyHolder) {
            System.out.println(policyHolder);
        } else if(customer instanceof Dependent dependent) {
            System.out.println(dependent);
        }
    }

    @Override
    public Map<String, String> displayNewCustomerForm() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        Map<String, String> data = new HashMap<>();
        String customer_type;
        boolean isValid;

        do {
            System.out.println("\nYou are adding a new customer");
            System.out.println("-----------------------------");
            System.out.println("Enter customer type (P for Policy Holder, D for Dependent): ");
            customer_type = scanner.nextLine();
            isValid = !customer_type.equalsIgnoreCase("P") && !customer_type.equalsIgnoreCase("D");
            if (isValid) {
                System.out.println("Invalid customer type. Please enter P for Policy Holder or D for Dependent.");
            }
        } while (isValid);

        data.put(CUSTOMER_TYPE, customer_type);
        System.out.println("Enter full name: ");
        data.put(FULL_NAME, scanner.nextLine());
        return data;
    }
}
