package planner.fitness.com.fitnessplanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import planner.fitness.com.fitnessplanner.data.FPOrganisation;
import planner.fitness.com.fitnessplanner.network.URLReader;

public class ServerSettingActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_setting);

        getSupportLoaderManager().initLoader(DownloadUsersDataLoader.LOADER_ID, null, this);
        showProgress(true);
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

    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new DownloadUsersDataLoader(this, getString(R.string.users_url));
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, String data) {
        showProgress(false);

        ArrayList<FPOrganisation> organisations = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            int length = array.length();
            for (int i = 0; i < length; i++) {
                organisations.add(FPOrganisation.createOrganisation(array.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("SERVER", "onLoadFinished: " + data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }


    /**
     * Represents an asynchronous task used to download users.json data
     */
    static class DownloadUsersDataLoader extends AsyncTaskLoader<String> {

        static final int LOADER_ID = 0;

        private String mUrl;

        DownloadUsersDataLoader(@NonNull Context context, String url) {
            super(context);

            mUrl = url;

            onContentChanged();
        }

        @Override
        protected void onStartLoading() {
            if (takeContentChanged()) forceLoad();
        }

        @Override
        protected void onReset() {
            super.onReset();
            onStopLoading();
        }

        @Nullable
        @Override
        public String loadInBackground() {
            String json = URLReader.read(mUrl);
            return json == null ? "[]" : json;
        }
    }
}
