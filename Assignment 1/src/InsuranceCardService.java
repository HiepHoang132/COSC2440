import java.util.ArrayList;
import java.util.List;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class InsuranceCardService implements ProcessManager<InsuranceCard, String>{
    private static List<InsuranceCard> insuranceCards;
    private static InsuranceCardService instance;
    public InsuranceCardService() {
        insuranceCards = new ArrayList<>();
    }
    public static InsuranceCardService getInstance() {
        if (instance == null) {
            instance = new InsuranceCardService();
        }
        return instance;
    }
    @Override
    public void add(InsuranceCard insuranceCard) {
        insuranceCards.add(insuranceCard);
    }

    @Override
    public void delete(String id) {
        InsuranceCard insuranceCard = Utilities.getById(insuranceCards, id);
        if (insuranceCard != null) {
            insuranceCards.remove(insuranceCard);
        }
    }
    @Override
    public InsuranceCard getOne(String id) {
        return Utilities.getById(insuranceCards, id);
    }
    @Override
    public List<InsuranceCard> getAll() {
        return insuranceCards;
    }
}
