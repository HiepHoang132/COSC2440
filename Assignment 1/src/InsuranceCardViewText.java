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
        data.put(YEAR, Utilities.getValidNumberInput(sc, "Year: ", 2024, 2200));
        data.put(MONTH, Utilities.getValidNumberInput(sc, "Month: ",1,12));
        data.put(DAY, Utilities.getValidNumberInput(sc, "Day: ",1,31));

        return data;
    }
}
