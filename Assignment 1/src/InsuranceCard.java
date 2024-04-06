import java.util.Date;

/**
  @author <Hoang Hua Hiep - s3979137>
 */

/**
 * The InsuranceCard class represents an insurance card in an insurance system.
 * It includes a card number, a reference to the cardholder,
 * the policy owner's name, and the expiration date of the card.
 */
public class InsuranceCard implements Utilities{
    private String cardNumber;
    private Customer cardHolder;
    private String policyOwner;
    private Date expirationDate;

    public InsuranceCard(){
        cardNumber = null;
        cardHolder = null;
        policyOwner = null;
        expirationDate = null;
    }

    public InsuranceCard(String policyOwner, Date expirationDate) {
        this.cardNumber = generateID("", 10);
        this.policyOwner = policyOwner;
        this.expirationDate = new Date();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * This method is used to get the cardHolder of the insurance card.
     * @return Customer This returns the cardHolder of the insurance card.
     */
    public Customer getCardHolder() {
        return cardHolder;
    }

    /**
     * This method is used to get the full name of the cardHolder.
     * If no cardHolder is assigned, it returns a string stating "No cardHolder assigned".
     * @return String This returns the full name of the cardHolder or a string stating "No cardHolder assigned".
     */
    public String getCardHolderName(){
        if(cardHolder == null){
            return "No card holder assigned";
        }
        return cardHolder.getFullName();
    }

    public void setCardHolder(Customer cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "InsuranceCard{" +
                "cardNumber='" + getCardNumber() + '\'' +
                ", cardHolder='" + getCardHolderName() +
                ", policyOwner='" + getPolicyOwner() + '\'' +
                ", expirationDate=" + getExpirationDate() +
                '}';
    }
}
