import java.util.Map;
import java.util.Scanner;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class CustomerController {
    private Customer customer;
    private CustomerView view;
    private CustomerService customerService;

    public CustomerController(CustomerView view) {
        this.view = view;
        this.customerService = CustomerService.getInstance();
    }

    public void eventLoop() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        String customerId;
        String choice;
        Customer customer;
        do {
            System.out.println("\nCustomer Service Menu:");
            System.out.println("1. Add a new customer");
            System.out.println("2. View all customers");
            System.out.println("3. Search for a customer");
            System.out.println("4. Delete a customer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewCustomer();
                    break;
                case "2":
                    System.out.print("\nAll customers: ");
                    customerService.getAll().forEach(view::display);
                    break;
                case "3":
                case "4":
                    System.out.print("Enter the customer ID to delete: ");
                    customerId = scanner.nextLine();
                    customer = customerService.getOne(customerId);
                    if (customer != null) {
                        if (choice.equals("3")) {
                            System.out.print("\nCustomer found: ");
                            view.display(customer);
                        } else {
                            customerService.delete(customerId);

                            System.out.println("Customer with ID " + customerId + " has been deleted.");
                        }
                    } else {
                        System.out.println("No customer found with the provided ID.");
                    }
                    break;
                case "5":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        } while (!choice.equals("5"));
    }

    public void addNewCustomer(){
        Scanner scanner = DataInput.getDataInput().getScanner();
        String answer = "Y";
        while(answer.equalsIgnoreCase("Y")){
            Map<String, String> data = view.displayNewCustomerForm();
            String customer_type = data.get(CustomerView.CUSTOMER_TYPE);
            String full_name = data.get(CustomerView.FULL_NAME);

            switch (customer_type) {
                case "P":
                    customer = new PolicyHolder(full_name);
                    customerService.add(customer);
                    System.out.println("Do you want to add a dependent to this policy holder? (Y/N): ");
                    answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("Y")) {
                        System.out.println("Enter dependent's full name: ");
                        Dependent dependent = new Dependent(scanner.nextLine());
                        ((PolicyHolder) customer).addDependent(dependent);
                        customerService.add(dependent);
                    }
                    break;
                case "D":
                    Dependent dependent = new Dependent(full_name);
                    System.out.println("Enter the policy holder ID for this dependent: ");
                    String policyHolderId = scanner.nextLine();
                    Customer customer = Utilities.getById(customerService.getAll(), policyHolderId);
                    dependent.setPolicyHolder((PolicyHolder) customer);
                    customerService.add(dependent);
                    break;
                default:
                    System.out.println("Invalid customer type. Please enter P for Policy Holder or D for Dependent.");
                    continue;
            }

            System.out.println("Successfully added the customer");
            System.out.println("Do you want to add another customer? (Y/N): ");
            answer = scanner.nextLine();
        }

    }
}
