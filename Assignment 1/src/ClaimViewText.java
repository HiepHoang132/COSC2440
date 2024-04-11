import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class ClaimViewText extends ClaimView {
    @Override
    public void display(Claim claim) {
        System.out.println(claim);
    }

    @Override
    public Map<String, String> displayNewClaimForm() {
        Scanner sc = DataInput.getDataInput().getScanner();
        Map<String, String> data = new HashMap<>();
        System.out.println("\nNew Claim Form");
        System.out.println("Enter insured person id: ");
        String customerID = sc.nextLine();

        if(!isCustomerPolicyHolder(customerID)){
            return null;
        }

        data.put(INSURED_PERSON_ID, customerID);

        System.out.println("Enter the claim date:");
        data.put(CLAIM_YEAR, Utilities.getValidNumberInput(sc, "Year: ", 2024, 2200));
        data.put(CLAIM_MONTH, Utilities.getValidNumberInput(sc, "Month: ",1,12));
        data.put(CLAIM_DAY, Utilities.getValidNumberInput(sc, "Day: ",1,31));

        System.out.println("Enter document name: ");
        data.put(DOCUMENT_NAME, sc.nextLine());
        System.out.println("Enter claim amount: ");
        data.put(CLAIM_AMOUNT, sc.nextLine());
        System.out.println("Enter receiver's bank: ");
        data.put("RECEIVER_BANK", sc.nextLine());
        System.out.println("Enter receiver's name: ");
        data.put("RECEIVER_NAME", sc.nextLine());

        // Validate phone number
        while (true) {
            System.out.println("Enter receiver's phone number: ");
            String phoneNumber = sc.nextLine();
            if (phoneNumber.matches("\\d{10}")) {
                data.put(RECEIVER_PHONE_NUMBER, phoneNumber);
                break;
            } else {
                System.out.println("Invalid phone number. Please enter a 10-digit phone number.");
            }
        }

        return data;
    }

    private boolean isCustomerPolicyHolder(String id) {
        Customer customer = Utilities.getById(CustomerService.getInstance().getAll(), id);
        if (customer == null) {
            System.out.println("No customer found with the provided ID.");
            return false;
        }

        if(!(customer instanceof PolicyHolder)){
            System.out.println("The customer is not a policy holder and cannot make a claim");
            return false;
        }
        return true;
    }

    public Map<String, String> updateExamDateForm() {
        Scanner sc = DataInput.getDataInput().getScanner();
        Map<String, String> data = new HashMap<>();
        System.out.println("Enter the new exam date:");
        data.put(EXAM_YEAR, Utilities.getValidNumberInput(sc, "Year: ", 2024, 2200));
        data.put(EXAM_MONTH, Utilities.getValidNumberInput(sc, "Month: ",1,12));
        data.put(EXAM_DAY, Utilities.getValidNumberInput(sc, "Day: ",1,31));
        return data;
    }
}
