import java.util.Scanner;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class DataInput {
    private static DataInput input;
    private Scanner sc;
    public DataInput() {
        sc = new Scanner(System.in);
    }
    public static DataInput getDataInput() {
        if (input == null) {
            input = new DataInput();
        }
        return input;
    }
    public Scanner getScanner() {
        return sc;
    }
}
