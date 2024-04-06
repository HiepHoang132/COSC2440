import java.util.List;
import java.util.ArrayList;

/**
  @author <Hoang Hua Hiep - s3979137>
 */


/**
 * The Customer class represents a customer in an insurance system.
 *
 * It implements the Utilities interface and
 * includes fields for id, fullName, insuranceCard, and claims.
 */
public abstract class Customer implements Utilities, ClaimProcessManager{
    private String id;
    private String fullName;
    private InsuranceCard insuranceCard;
    protected List<Claim> claims;

    /**
     * Default constructor for the Customer class.
     * Initializes all fields to null.
     */
    public Customer(){
        id = null;
        fullName = null;
        insuranceCard = null;
        claims = null;
    }

    /**
     * Constructor for the Customer class.
     * Initializes the id, fullName, and claims fields.
     *
     * @param fullName The full name of the customer.
     */
    public Customer(String fullName) {
        this.id = generateID("c-", 7);
        this.fullName = fullName;
        this.insuranceCard = null;
        this.claims = new ArrayList<Claim>();
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInsuranceCard() {
        if(insuranceCard == null){
            return "No insurance card assigned";
        }
        return insuranceCard.getCardNumber();
    }

    /**
     * This method is used to assign an insurance card to a customer.
     *
     * @param insuranceCard The insurance card to be assigned to the customer.
     * @return boolean Returns true if the insurance card was successfully assigned, false otherwise.
     *
     * The method first checks if the insurance card is already assigned to another customer or if the current customer
     * already has an insurance card. If either of these conditions is true, the method returns false and does not
     * assign the card.
     *
     * If the conditions are met, the method assigns the insurance card to the customer and sets the customer as the cardHolder.
     */
    public boolean setInsuranceCard(InsuranceCard insuranceCard){
        // Check if the insurance card is already assigned or if the customer already has a card
        if(insuranceCard.getCardHolder() != null || this.getInsuranceCard() != null){
            return false;
        }

        // Assign the insurance card and set the customer as the cardHolder
        this.insuranceCard = insuranceCard;
        insuranceCard.setCardHolder(this);
        return true;
    }

    public String getClaimsText(){
        StringBuilder sb = new StringBuilder();
        for(Claim claim: claims){
            sb.append(claim).append(", ");
        }

        // Remove the trailing comma and space
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("No claims been made");
        }
        return sb.toString();
    }
}
