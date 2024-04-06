import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class Claim{
    private String id;
    private Date claimDate;
    private Customer insuredPerson;
    private String cardNumber;
    private Date examDate;
    private List<String> documents;
    private double claimAmount;
    private String status;
    private String receiverBankingInfo;

    public Claim() {
        this.id = null;
        this.claimDate = null;
        this.insuredPerson = null;
        this.cardNumber = null;
        this.examDate = null;
        this.documents = null;
        this.claimAmount = 0;
        this.status = null;
        this.receiverBankingInfo = null;
    }

    /**
     * This is the constructor for the Claim class.
     *
     * @param insuredPerson The customer who is insured and making the claim.
     * @param claimAmount The amount that the insured person is claiming.
     * @param receiverBankingInfo The banking information of the receiver.
     */
    public Claim(Customer insuredPerson, double claimAmount, String receiverBankingInfo) {
        this.id = Utilities.generateID("f-", 10);
        this.claimDate = null;
        this.insuredPerson = insuredPerson;
        this.cardNumber = insuredPerson.getInsuranceCard();
        this.examDate = null;
        this.documents = new ArrayList<String>();
        this.claimAmount = claimAmount;
        this.status = "New"; // Set status to "New" when a claim is created
        this.receiverBankingInfo = receiverBankingInfo;
    }

    public String getId() {
        return id;
    }

    public String getClaimDate() {
        if(claimDate == null){
            return "The customer has not claimed yet.";
        }
        return claimDate.toString();
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public String getInsuredPersonName() {
        if(insuredPerson == null){
            return "No insured person assigned";
        }
        return insuredPerson.getFullName();
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getCardNumber() {
        if(cardNumber == null){
            return "N/A";
        }
        return cardNumber;
    }

    public String getExamDate() {
        if(examDate == null){
            return "This claim has not been examined yet.";
        }
        return examDate.toString();
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public String getDocumentText(){
        StringBuilder sb = new StringBuilder();
        for(String document: documents){
            sb.append(document).append(", ");
        }

        // Remove the trailing comma and space
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("No documents attached");
        }
        return sb.toString();
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    public void setReceiverBankingInfo(String receiverBankingInfo) {
        this.receiverBankingInfo = receiverBankingInfo;
    }

    public void addDocument(String documentName){
        this.documents.add(this.id + "_" + this.cardNumber + "_" + documentName + ".pdf");
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id='" + getId() + '\'' +
                ", claimDate='" + getClaimDate() + '\'' +
                ", insuredPerson='" + getInsuredPersonName() + '\'' +
                ", cardNumber='" + getCardNumber() + '\'' +
                ", examDate='" + getExamDate() + '\'' +
                ", documents=" + getDocumentText() +
                ", claimAmount=" + getClaimAmount()+
                ", status='" + getStatus() + '\'' +
                ", receiverBankingInfo='" + receiverBankingInfo + '\'';
    }
}
