import java.text.SimpleDateFormat;
import java.util.Date;

/**
  @author <Hoang Hua Hiep - s3979137>
 */

/**
 * The InsuranceCard class represents an insurance card in an insurance system.
 * It includes a card number, a reference to the cardholder,
 * the policy owner's name, and the expiration date of the card.
 */
public class InsuranceCard{
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
        this.cardNumber = Utilities.generateID("", 10);
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
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

    public String getExpirationDate() {
        return Utilities.formattedDate(expirationDate);
    }

    @Override
    public String toString() {
        return "\nInsuranceCard card number: " + getCardNumber() +
                "\nCard Holder: " + getCardHolderName() +
                "\nPolicy Owner: " + getPolicyOwner() +
                "\nExpiration Date: " + getExpirationDate()
                ;
    }
}
