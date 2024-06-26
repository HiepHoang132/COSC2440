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
    private String claimAmount;
    private String status;
    private String receiverBankingInfo;

    public Claim() {
        this.id = null;
        this.claimDate = null;
        this.insuredPerson = null;
        this.cardNumber = null;
        this.examDate = null;
        this.documents = null;
        this.claimAmount = null;
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
    public Claim(Date claimDate, Customer insuredPerson, String claimAmount, String receiverBankingInfo) {
        this.id = Utilities.generateID("f-", 10);
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = insuredPerson.getInsuranceCard().getCardNumber();
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
        return Utilities.formattedDate(claimDate);
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
            return "This claim has not been examined yet";
        }
        return Utilities.formattedDate(examDate);
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
        this.status = "Processing";
    }

    public void closeClaim(){
        if(this.status.equals("Processing")){
            this.status = "Done";
            System.out.println("Claim status updated to 'Done'");
        } else {
            System.out.println("Invalid status update. Can only change the status to 'Done' when the claim is in 'Processing' status");
        }
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

    public String getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(String claimAmount) {
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
        return "Claim ID: " + getId() + "\n" +
                "Claim Date: " + getClaimDate() + "\n" +
                "Insured Person: " + getInsuredPersonName() + "\n" +
                "Card Number: " + getCardNumber() + "\n" +
                "Exam Date: " + getExamDate() + "\n" +
                "Documents: ClaimId_CardNumber_DocumentName.pdf \n" + getDocumentText() + "\n" +
                "Claim Amount: " + getClaimAmount() + "\n" +
                "Status: " + getStatus() + "\n" +
                "Receiver Banking Info: " + getReceiverBankingInfo() + "\n";
    }
}
