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
public interface Utilities {
   /**
    * Generates a unique identifier with a specified prefix and length.
    * The identifier is composed of the prefix followed by a sequence of random digits.
    * The number of digits is determined by the specified length.
    *
    * @param prefix The prefix of the identifier.
    * @param length The number of random digits in the identifier.
    * @return The generated unique identifier.
    */
   default String generateID(String prefix, int length){
      StringBuilder sb = new StringBuilder(prefix);
      Random random = new Random();
      for(int i = 0; i < length; i++){
         sb.append(random.nextInt(10));
      }
      return sb.toString();
   }
}
