package planner.fitness.com.fitnessplanner;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import planner.fitness.com.fitnessplanner.network.URLReader;

public class ServerSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_setting);

        DownloadUsersDataTask task = new DownloadUsersDataTask();

        showProgress(true);
        task.execute(getString(R.string.users_url));
    }

    private void showProgress(boolean show) {
        findViewById(R.id.progress_bar).setVisibility(
                show ? View.VISIBLE : View.GONE
        );
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * Represents an asynchronous task used to download users.json data
     */
    public class DownloadUsersDataTask extends AsyncTask<String, Void, Boolean> {

        private String json;

        DownloadUsersDataTask() {
            json = "[]";
        }

        /**
         * Download the content of the file
         * @param params
         * @return
         */
        @Override
        protected Boolean doInBackground(String... params) {
            json = URLReader.read(params[0]);

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false);
            ((TextView) findViewById(R.id.text_view)).setText(json);
        }

        @Override
        protected void onCancelled() {
            json = "[]";
            showProgress(false);
        }

        /**
         * get the json content downloaded
         * @return the json content
         */
        public String getJson() {
            return json;
        }
    }
}
