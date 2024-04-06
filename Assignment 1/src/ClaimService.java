import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

/**
 * This class provides the implementation for the ClaimProcessManager interface.
 * It manages a list of claims and provides methods to manipulate and retrieve these claims.
 * It follows the Singleton design pattern.
 */

 public class ClaimService implements ClaimProcessManager{
    private static List<Claim> claims;
    private static ClaimService instance;
    public ClaimService(){
        claims = new ArrayList<>();
    }

    public static ClaimService getInstance(){
        if(instance == null){
            instance = new ClaimService();
        }
        return instance;
    }
    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void updateClaimDate(String id, String claimDate) {
        Claim claim = Utilities.getById(claims, id);
        if(claim != null){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                claim.setClaimDate(formatter.parse(claimDate));
            } catch (Exception e) {
                System.out.println("Invalid date format.");
            }
        }
    }

    @Override
    public void updateExamDate(String id, String examDate) {
        Claim claim = Utilities.getById(claims, id);
        if(claim != null){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                claim.setClaimDate(formatter.parse(examDate));
            } catch (Exception e) {
                System.out.println("Invalid date format.");
            }
        }
    }

    @Override
    public void updateStatus(String id, String status){
        Claim claim = Utilities.getById(claims, id);
        if(claim != null){
            claim.setStatus(status);
        }
    }

    @Override
    public void delete(String id) {
        Claim claim = Utilities.getById(claims, id);
        if(claim != null){
            claims.remove(claim);
        }
    }

    @Override
    public Claim getOne(String id) {
        return Utilities.getById(claims, id);
    }

    @Override
    public List<Claim> getAll() {
        return claims;
    }
}
