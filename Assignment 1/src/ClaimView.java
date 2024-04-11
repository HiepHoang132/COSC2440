import java.util.Map;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public abstract class ClaimView {
    public static final String INSURED_PERSON_ID = "INSURED_PERSON_ID";
    public static final String CLAIM_YEAR = "CLAIM_YEAR";
    public static final String CLAIM_MONTH = "CLAIM_MONTH";
    public static final String CLAIM_DAY = "CLAIM_DAY";
    public static final String EXAM_YEAR = "EXAM_YEAR";
    public static final String EXAM_MONTH = "EXAM_MONTH";
    public static final String EXAM_DAY = "EXAM_DAY";
    public static final String DOCUMENT_NAME = "DOCUMENT_NAME";
    public static final String CLAIM_AMOUNT = "CLAIM_AMOUNT";
    public static final String RECEIVER_BANK = "RECEIVER_BANK";
    public static final String RECEIVER_NAME = "RECEIVER_NAME";
    public static final String RECEIVER_PHONE_NUMBER = "RECEIVER_PHONE_NUMBER";
    public abstract void display(Claim claim);
    public abstract Map<String, String> displayNewClaimForm();
    public abstract Map<String, String> updateExamDateForm();
}
