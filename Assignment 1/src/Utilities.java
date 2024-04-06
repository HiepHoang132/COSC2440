import java.util.List;
import java.util.Random;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

/**
 * The Utilities interface provides a method for generating a unique identifier.
 *
 * This interface is implemented by classes that
 * require the generation of unique identifiers.
 */
public class Utilities {
   /**
    * Generates a unique identifier with a specified prefix and length.
    * The identifier is composed of the prefix followed by a sequence of random digits.
    * The number of digits is determined by the specified length.
    *
    * @param prefix The prefix of the identifier.
    * @param length The number of random digits in the identifier.
    * @return The generated unique identifier.
    */
   public static String generateID(String prefix, int length){
      StringBuilder sb = new StringBuilder(prefix);
      Random random = new Random();
      for(int i = 0; i < length; i++){
         sb.append(random.nextInt(10));
      }
      return sb.toString();
   }

   /**
    * This method is used to find and return a Claim object from a list of claims based on its ID.
    *
    * @param claims This is the list of Claim objects from which to find the claim.
    * @param id This is the ID of the claim to find.
    * @return Claim This returns the Claim object with the matching ID. If no claim with the matching ID is found, it returns null.
    */
   public static Claim getClaimById(List<Claim> claims, String id) {
      for (Claim claim : claims) {
         if (claim.getId().equals(id)) {
            return claim;
         }
      }
      return null;
   }
}
