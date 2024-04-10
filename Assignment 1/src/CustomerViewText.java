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

        switch(customer_type){
            case "P":
                System.out.println("Enter policy holder's full name: ");
                data.put(POLICY_HOLDER_NAME, scanner.nextLine());
                System.out.println("Do you want to add a dependent to this policy holder? (Y/N): ");
                if(scanner.nextLine().equalsIgnoreCase("Y")){
                    System.out.println("Enter dependent's full name: ");
                    data.put(DEPENDENT_NAME, scanner.nextLine());
                }
                break;
            case "D":
                System.out.println("Enter dependent's full name: ");
                data.put(DEPENDENT_NAME, scanner.nextLine());
                System.out.println("Enter the policy holder ID for this dependent: ");
                data.put(POLICY_ID, scanner.nextLine());
                break;
        }

        return data;
    }
}
