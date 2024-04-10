import java.util.Calendar;
import java.util.Map;
import java.util.Scanner;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class CustomerController {
    private CustomerView customerView;
    private InsuranceCardView insuranceCardView;
    private CustomerService customerService;
    private InsuranceCardService insuranceCardService;

    public CustomerController(CustomerView customerView, InsuranceCardView insuranceCardView){
        this.customerView = customerView;
        this.insuranceCardView = insuranceCardView;
        this.customerService = CustomerService.getInstance();
        this.insuranceCardService = InsuranceCardService.getInstance();
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
                    customerService.getAll().forEach(customerView::display);
                    break;
                case "3":
                case "4":
                    System.out.print("Enter the customer ID: ");
                    customerId = scanner.nextLine();
                    customer = customerService.getOne(customerId);
                    if (customer != null) {
                        if (choice.equals("3")) {
                            System.out.print("\nCustomer found: ");
                            customerView.display(customer);
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
            Map<String, String> customerData = customerView.displayNewCustomerForm();
            String customerType = customerData.get(CustomerView.CUSTOMER_TYPE);

            switch(customerType){
                case "P":
                    String policyName = customerData.get(CustomerView.POLICY_HOLDER_NAME);
                    PolicyHolder policyHolder = new PolicyHolder(policyName);
                    customerService.add(policyHolder);
                    addInsuranceCard(policyHolder);

                    customerView.display(policyHolder);
                    System.out.println("Successfully added the customer");
                    break;
                case "D":
                    String dependentName = customerData.get(CustomerView.DEPENDENT_NAME);
                    Dependent dependent = new Dependent(dependentName);
                    addInsuranceCard(dependent);
                    customerService.add(dependent);

                    String policyId = customerData.get(CustomerView.POLICY_ID);
                    Customer customer = customerService.getOne(policyId);
                    if(customer instanceof PolicyHolder){
                        ((PolicyHolder) customer).addDependent(dependent);
                        dependent.setPolicyHolder((PolicyHolder) customer);
                    } else if (customer instanceof Dependent) {
                        System.out.println("This is a dependent ID. Cannot add a dependent to another dependent.");
                    }
                    else{
                        System.out.println("No policy holder found with the provided ID.");
                    }

                    customerView.display(dependent);
                    System.out.println("Successfully added the customer");
                    break;
            }

        System.out.println("\nDo you want to add another customer? (Y/N): ");
        answer = scanner.nextLine();
        }
    }

    public void addInsuranceCard(Customer customer){
        Map<String, String> data = insuranceCardView.displayNewInsuranceCardForm();
        String policyOwner = data.get(InsuranceCardView.POLICY_OWNER);
        int year = Integer.parseInt(data.get(InsuranceCardView.YEAR));

        // adjust month value for Calendar
        int month = Integer.parseInt(data.get(InsuranceCardView.MONTH)) - 1;
        int day = Integer.parseInt(data.get(InsuranceCardView.DAY));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        InsuranceCard insuranceCard = new InsuranceCard(policyOwner, calendar.getTime());
        insuranceCardService.add(insuranceCard);
        customer.setInsuranceCard(insuranceCard);
    }
}
