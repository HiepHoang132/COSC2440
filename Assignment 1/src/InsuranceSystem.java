import java.util.Scanner;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class InsuranceSystem {
    private static InsuranceSystem system;
    private CustomerController customerController;
    private ClaimController claimController;

    private InsuranceSystem(){
        customerController = new CustomerController(new CustomerViewText(), new InsuranceCardViewText());
        claimController = new ClaimController(new ClaimViewText());
    }

    public static InsuranceSystem getInstance(){
        if(system == null){
            system = new InsuranceSystem();
        }
        return system;
    }

    public void start() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        String choice;
        do {
            displayMenu();
            choice = scanner.nextLine();
            handleChoice(choice);
        } while (!choice.equals("3"));
    }

    private void displayMenu() {
        System.out.println("\nWelcome to the Insurance System!");
        System.out.println("What do you want to work on?");
        System.out.println("1. Customer");
        System.out.println("2. Claim");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void handleChoice(String choice) {
        switch (choice) {
            case "1":
                customerController.eventLoop();
                break;
            case "2":
                claimController.eventLoop();
                break;
            case "3":
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                break;
        }
    }
}