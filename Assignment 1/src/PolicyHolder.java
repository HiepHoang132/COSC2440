import java.util.List;
import java.util.ArrayList;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

/**
 * The PolicyHolder class represents a policyholder in an insurance system.
 * It extends the Customer class and includes a list of dependents.
 */
public class PolicyHolder extends Customer{
    private List<Dependent> dependents;
    private ClaimService claimService;

    public PolicyHolder() {
        super();
        dependents = null;
        this.claimService = ClaimService.getInstance();
    }

    public PolicyHolder(String fullName) {
        super(fullName);
        this.dependents = new ArrayList<Dependent>();
        this.claimService = ClaimService.getInstance();
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public String getDependentName(){
        StringBuilder sb = new StringBuilder();
        for(Dependent dependent: dependents){
            sb.append(dependent.getFullName()).append(", ");
        }

        // Remove the trailing comma and space
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("No dependents assigned");
        }
        return sb.toString();
    }

    /**
     * Adds a dependent to the policyHolder.
     *
     * The dependent can only be added if
     * the dependent does not have a policyHolder.
     *
     * @param dependent The dependent to be added.
     * @return True if the dependent was successfully added, false otherwise.
     */
    public boolean addDependent(Dependent dependent) {
        if (dependent.getPolicyHolder() == null) {
            this.dependents.add(dependent);
            dependent.setPolicyHolder(this);
            return true;
        }
        return false;
    }

    /**
     * This method is used to make a claim on behalf of the policyHolder and its dependents.
     *
     * @param insuredPerson The customer who is insured and making the claim.
     * @param claimAmount The amount that the insured person is claiming.
     * @param receiverBankingInfo The banking information of the receiver.
     */
    public void makeClaim(Customer insuredPerson, double claimAmount, String receiverBankingInfo) {
        // Create a new claim with the provided parameters
        Claim newClaim = new Claim(insuredPerson, claimAmount, receiverBankingInfo);

        // Add the new claim to the list of claims of the policyHolder
        this.getClaims().add(newClaim);

        // Add the new claim to the list of claims of each dependent of the policyHolder
        for (Dependent dependent : this.getDependents()) {
            dependent.getClaims().add(newClaim);
        }

        // Add the new claim to the claimService
        this.claimService.add(newClaim);
    }

    @Override
    public String toString() {
        return "PolicyHolder{" +
                "id='" + getId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + getInsuranceCard() +
                ", dependents=" + getDependentName() +
                '}';
    }
}
