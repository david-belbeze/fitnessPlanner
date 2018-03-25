package planner.fitness.com.fitnessplanner.filter;

/**
 * Filter for url
 */

public class URLFilter {

    private static final String REGEX_URL = "^https?://(www\\.)?[a-z0-9]+([-.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    /**
     * Verify if the given url is valid
     * @param url the url to test
     * @return true is the url is valid
     */
    public static boolean isValid(String url) {
        return url.matches(REGEX_URL);
    }

}
