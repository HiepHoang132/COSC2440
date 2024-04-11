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

    public PolicyHolder() {
        super();
        dependents = null;
    }

    public PolicyHolder(String fullName) {
        super(fullName);
        this.dependents = new ArrayList<Dependent>();
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public String getDependentName(){
        StringBuilder sb = new StringBuilder();

        for (Dependent dependent : dependents) {
            String fullName = dependent.getFullName();
            sb.append(fullName).append(",");
        }

        // Remove the trailing commas
        if(!(sb.isEmpty())){
            sb.deleteCharAt(sb.length() - 1);
        }
        else {
            sb.append("None");
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

    public void makeClaim(Claim claim){
        this.getClaims().add(claim);
        for(Dependent dependent: this.getDependents()){
            dependent.getClaims().add(claim);
        }
    }

    @Override
    public String toString() {
        return "\nPolicy Holder ID: " + getId() +
                "\nFull Name: " + getFullName() + getInsuranceCard() +
                "\nDependents: " + getDependentName() +
                "\nClaims:\n " + getClaimsText()
                ;
    }
}
