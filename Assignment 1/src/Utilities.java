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
    * This method is used to get an item from a list by its ID. The method accepts a list of items and an ID as parameters.
    * It iterates over the list and checks if the item is an instance of Claim, Customer, or InsuranceCard.
    * If the item is an instance of Claim or Customer, it compares the item's ID with the provided ID.
    * If the item is an instance of InsuranceCard, it compares the item's card number with the provided ID.
    * If a match is found, the item is returned. If no match is found after iterating over the entire list, the method returns null.
    *
    * @param list The list of items to search.
    * @param id The ID to search for.
    * @return The item with the matching ID, or null if no match is found.
    */
   public static <Type> Type getById(List<Type> list, String id) {
      for (Type item : list) {
         if (item instanceof Claim) {
            if (((Claim) item).getId().equals(id)) {
               return item;
            }
         } else if (item instanceof Customer) {
            if (((Customer) item).getId().equals(id)) {
               return item;
            }
         } else if (item instanceof InsuranceCard) {
            if (((InsuranceCard) item).getCardNumber().equals(id)) {
               return item;
            }
         }
      }
      return null;
   }
}
