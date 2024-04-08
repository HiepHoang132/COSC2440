/**
 * @author <Hoang Hua Hiep - s3979137>
 */

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
            return "None";
        }
        return policyHolder.getFullName();
    }
    @Override
    public String toString() {
        return "\nDependent ID: " + getId() +
                "\nFullName: " + getFullName() +
                "\nPolicy Holder: " + getPolicyHolderName()
                ;
    }
}
