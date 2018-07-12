package planner.fitness.com.fitnessplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

public class SplashScreen extends AppCompatActivity {

    private static boolean LOGIN_RUN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setContentView(R.layout.activity_splash_screen);

        boolean verifiedIdentity = false;

        if (!SplashScreen.LOGIN_RUN && !verifiedIdentity && true) {
            SplashScreen.LOGIN_RUN = true;

            Intent intent = new Intent(this, LoginActivity.class);

            startActivity(intent);
        } else {
            // TODO: customize the view
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Load base parameters
     * @return true if parameters are set false otherwise
     */
    private boolean loadParameters() {
        // TODO: read server and user settings

        return false;
    }
}
