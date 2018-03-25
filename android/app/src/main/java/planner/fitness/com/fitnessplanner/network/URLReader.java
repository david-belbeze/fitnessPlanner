package planner.fitness.com.fitnessplanner.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import planner.fitness.com.fitnessplanner.filter.URLFilter;

/**
 * This class allows to download data from url
 */

public class URLReader {

    /**
     * get the content of the file at the given host url
     * @param url the url to download
     * @return the content of the downloaded file or null if error occurred
     */
    public static String read(String url) {
        String content = null;

        try {
            if (URLFilter.isValid(url)) {
                URL host = new URL(url);
                BufferedReader in = new BufferedReader(
                            new InputStreamReader(host.openStream()));

                content = "";

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content = content.concat(inputLine);
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}
