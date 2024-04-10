import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class InsuranceCardViewText extends InsuranceCardView{

    public Map<String, String> displayNewInsuranceCardForm() {
        Scanner sc = DataInput.getDataInput().getScanner();
        Map<String, String> data = new HashMap<>();
        System.out.println("Enter the policy owner: ");
        data.put(POLICY_OWNER, sc.nextLine());
        System.out.println("Enter the expiration date:");
        data.put(YEAR, getValidNumberInput(sc, "Year: ", 2024, 2200));
        data.put(MONTH, getValidNumberInput(sc, "Month: ",1,12));
        data.put(DAY, getValidNumberInput(sc, "Day: ",1,31));

        return data;
    }

    private String getValidNumberInput(Scanner sc, String prompt, int min, int max) {
        String input;
        while (true) {
            System.out.println(prompt);
            input = sc.nextLine();
            if (input.matches("\\d+")) {
                int num = Integer.parseInt(input);
                if(num >= min && num <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return input;
    }
}
