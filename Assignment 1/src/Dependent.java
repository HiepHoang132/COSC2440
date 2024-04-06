/**
 * @author <Hoang Hua Hiep - s3979137>
 */

import java.util.List;

/**
 * The Dependent class represents a dependent in an insurance system.
 * It extends the Customer class and includes a reference to the policyholder.
 */
public class Dependent extends Customer{
    private PolicyHolder policyHolder;

    public Dependent() {
        super();
        policyHolder = null;
    }

    public Dependent(String fullName) {
        super(fullName);
        policyHolder = null;
    }

    /**
     * Sets the policyholder of the dependent.
     *
     * @param policyHolder The policyholder to be set.
     */
    public void setPolicyHolder(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }

    /**
     * Returns the policyholder of the dependent.
     *
     * @return The policyholder of the dependent.
     */
    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public String getPolicyHolderName(){
        if(policyHolder == null){
            return "No policy holder assigned";
        }
        return policyHolder.getFullName();
    }
    @Override
    public String toString() {
        return "Dependent{" +
                "id='" + getId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", policyHolder='" + getPolicyHolderName() +
                '}';
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void update(String id, Claim claim) {
        for(int i = 0; i < claims.size(); i++){
            if(claims.get(i).getId().equals(id)){
                claims.set(i, claim);
                break;
            }
        }
    }

    @Override
    public void delete(String id) {
        for(int i = 0; i < claims.size(); i++){
            if(claims.get(i).getId().equals(id)){
                claims.remove(i);
                break;
            }
        }
    }

    @Override
    public Claim getOne(String id) {
        for(Claim claim: claims){
            if(claim.getId().equals(id)){
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return claims;
    }
}
