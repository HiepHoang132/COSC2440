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
                    System.out.print("Enter the customer ID: ");
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
            String customerType = data.get(CustomerView.CUSTOMER_TYPE);
            switch(customerType){
                case "P":
                    String policyName = data.get(CustomerView.POLICY_HOLDER_NAME);
                    PolicyHolder policyHolder = new PolicyHolder(policyName);
                    customerService.add(policyHolder);

                    String dependentName = data.get(CustomerView.DEPENDENT_NAME);
                    Dependent dependent = new Dependent(dependentName);
                    customerService.add(dependent);

                    policyHolder.addDependent(dependent);
                    dependent.setPolicyHolder(policyHolder);
                    break;
                case "D":
                    String dependentName2 = data.get(CustomerView.DEPENDENT_NAME);
                    Dependent dependent2 = new Dependent(dependentName2);
                    customerService.add(dependent2);

                    String policyId = data.get(CustomerView.POLICY_ID);
                    Customer customer = customerService.getOne(policyId);
                    if(customer instanceof PolicyHolder){
                        ((PolicyHolder) customer).addDependent(dependent2);
                        dependent2.setPolicyHolder((PolicyHolder) customer);
                    } else if (customer instanceof Dependent) {
                        System.out.println("This is a dependent ID. Cannot add a dependent to another dependent.");
                    }
                    else{
                        System.out.println("No policy holder found with the provided ID.");
                    }
                    break;
            }
        System.out.println("Successfully added the customer");
        System.out.println("Do you want to add another customer? (Y/N): ");
        answer = scanner.nextLine();
        }
    }
}
