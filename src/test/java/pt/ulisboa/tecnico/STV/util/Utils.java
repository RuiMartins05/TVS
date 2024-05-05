package pt.ulisboa.tecnico.STV.util;

import java.security.SecureRandom;
import java.util.Random;


/*
* For simplicity, this utils Class does not validate the domain requirements explicitly, meaning that if the requirements
* are not those of the project, the validity of the data generated is not guaranteed (i.e., minimum length of a topic may vary)
 */
public class Utils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new SecureRandom();


    /**
     * Generates a random topic that obeys to the domain constraints.
     * <p>
     * A topic must be a non-empty string, with at least 6 characters.
     * A topic's length must be less or equal than [Integer.MAX_VALUE].
     * </p>
     * @return a valid topic.
     */
    public static String generateRandomValidTopic() {
        int length = RANDOM.nextInt(6, 10);

        return RANDOM.ints(length, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * Generates a random topic that does NOT obey to the domain constraints.
     * <p>
     * A topic must be a non-empty string.
     * A topic's length must be less or equal than [Integer.MAX_VALUE].
     * </p>
     * @return an invalid topic.
     */
    public static String generateRandomInvalidTopic() {
        int length = RANDOM.nextInt(0, 5);

        return RANDOM.ints(length, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
