import java.util.*;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class ClaimController {
    private Claim claim;
    private ClaimView view;
    private ClaimService claimService;
    public ClaimController(ClaimView view){
        this.view = view;
        this.claimService = ClaimService.getInstance();
    }

    public void eventLoop(){
        Scanner sc = DataInput.getDataInput().getScanner();
        String choice;
        do{
            System.out.println("\nClaim Service Menu:");
            System.out.println("1. Add new claim");
            System.out.println("2. Update claim");
            System.out.println("3. Display all claims");
            System.out.println("4. Display claim by id");
            System.out.println("5. Delete claim by id");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            choice = sc.nextLine();
            switch (choice){
                case "1":
                    addNewClaim();
                    break;
                case "2":
                    updateClaim();
                    break;
                case "3":
                    List<Claim> claims = claimService.getAll();
                    for (Claim c : claims){
                        view.display(c);
                    }
                    break;
                case "4":
                case "5":
                    System.out.println("Enter claim id: ");
                    String claimId = sc.nextLine();
                    claim = claimService.getOne(claimId);
                    if(claim != null){
                        if(choice.equals("4")){
                            System.out.println("\nClaim found:\n");
                            view.display(claim);
                        } else {
                            claimService.delete(claimId);
                            System.out.println("Claim with ID " + claimId + " has been deleted");
                        }
                    }
                    else {
                        System.out.println("Claim with ID " + claimId + " not found");
                    }
                    break;
                case "6":
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        } while(!(choice.equals("6")));
    }

    public void addNewClaim(){
        Scanner sc = DataInput.getDataInput().getScanner();
        String answer = "Y";

        while(answer.equalsIgnoreCase("Y")){
            Map<String, String> claimData = view.displayNewClaimForm();
            if(claimData == null){
                continue;
            }

            String insuredPersonId = claimData.get(ClaimView.INSURED_PERSON_ID);
            Customer customer = Utilities.getById(CustomerService.getInstance().getAll(), insuredPersonId);
            int year = Integer.parseInt(claimData.get(ClaimView.CLAIM_YEAR));
            // Adjust month to 0-index for Calendar
            int month = Integer.parseInt(claimData.get(ClaimView.CLAIM_MONTH)) - 1;
            int day = Integer.parseInt(claimData.get(ClaimView.CLAIM_DAY));
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            Date claimDate = calendar.getTime();

            String documentName = claimData.get(ClaimView.DOCUMENT_NAME);
            String claimAmount = claimData.get(ClaimView.CLAIM_AMOUNT);

            String receiverBank = claimData.get(ClaimView.RECEIVER_BANK).trim();
            String receiverName = claimData.get(ClaimView.RECEIVER_NAME).trim();
            String receiverPhoneNumber = claimData.get(ClaimView.RECEIVER_PHONE_NUMBER).trim();
            String receiverBankingInfo = receiverBank + "-" + receiverName + "-" + receiverPhoneNumber;

            Claim claim = new Claim(claimDate, customer, claimAmount, receiverBankingInfo);
            claim.addDocument(documentName);
            claim.setReceiverBankingInfo(receiverBankingInfo);

            claimService.add(claim);
            ((PolicyHolder) customer).makeClaim(claim);

            System.out.println("Successfully added the claim!");
            System.out.println("Do you want to add another claim? (Y/N): ");
            answer = sc.nextLine();
        }
    }

    public void updateClaim() {
        Scanner sc = DataInput.getDataInput().getScanner();
        System.out.println("Enter claim id: ");
        String claimId = sc.nextLine();
        Claim claim = claimService.getOne(claimId);
        if (claim != null) {
            System.out.println("1. Update exam date");
            System.out.println("2. Change status to Done");
            System.out.println("3. Add a document to a claim");
            System.out.println("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    updateExamDate(claim);
                    break;
                case "2":
                    claim.closeClaim();
                    break;
                case "3":
                    addDocumentToClaim(claim);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2");
                    break;
            }
        } else {
            System.out.println("Claim with ID " + claimId + " not found");
        }
    }

    private void updateExamDate(Claim claim) {
        Scanner sc = DataInput.getDataInput().getScanner();
        Map<String, String> data = view.updateExamDateForm();
        int year = Integer.parseInt(data.get(ClaimView.EXAM_YEAR));
        int month = Integer.parseInt(data.get(ClaimView.EXAM_MONTH)) - 1; // Adjust month to 0-index for Calendar
        int day = Integer.parseInt(data.get(ClaimView.EXAM_DAY));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date newExamDate = calendar.getTime();
        claim.setExamDate(newExamDate);
        System.out.println("Exam date updated successfully");
    }

    public void addDocumentToClaim(Claim claim) {
        Scanner sc = DataInput.getDataInput().getScanner();
        System.out.println("Enter document name: ");
        String documentName = sc.nextLine();
        claim.addDocument(documentName);
        System.out.println("Document added successfully.");
    }
}
