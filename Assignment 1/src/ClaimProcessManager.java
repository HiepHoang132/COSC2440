import java.util.List;

/**
 * This interface defines the operations that can be performed on a Claim.
 */
public interface ClaimProcessManager{

    /**
     * Adds a new claim.
     *
     * @param claim The claim to be added.
     */
    void add(Claim claim);

    /**
     * Updates the claim date of a specific claim.
     *
     * @param id The id of the claim to be updated.
     * @param claimDate The new claim date.
     */
    void updateClaimDate(String id, String claimDate);

    /**
     * Updates the exam date of a specific claim.
     *
     * @param id The id of the claim to be updated.
     * @param ExamDate The new exam date.
     */
    void updateExamDate(String id, String ExamDate);

    /**
     * Updates the status of a specific claim.
     *
     * @param id The id of the claim to be updated.
     * @param status The new status.
     */
    void updateStatus(String id, String status);

    /**
     * Deletes a specific claim.
     *
     * @param id The id of the claim to be deleted.
     */
    void delete(String id);

    /**
     * Retrieves a specific claim.
     *
     * @param id The id of the claim to be retrieved.
     * @return The claim with the given id.
     */
    Claim getOne(String id);

    /**
     * Retrieves all claims.
     *
     * @return A list of all claims.
     */
    List<Claim> getAll();
}