import java.util.List;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(String id, Claim claim);
    void delete(String id);
    Claim getOne(String id);
    List<Claim> getAll();
}
