package com.example.vvkh.aclient.app;

import android.app.ProgressDialog;
import android.os.AsyncTask;//@todo delete
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.String.*;
import static java.lang.Thread.*;


public class MainActivity extends BaseActivity {

    ListView videoList;
    //String[] videoArray = {"No Videos"};//@todo delete
    ArrayAdapter<String> videoAdapter;

    String taskUrl = "http://10.0.3.2:8083";//"http://gdata.youtube.com/feeds/api/users/twistedequations/uploads?v=2&alt=jsonc&start-index=1&max-results=20";

    ArrayList<String> videoArrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/

        videoList = (ListView) findViewById(R.id.videoList);
        videoAdapter = new ArrayAdapter<String>(this, R.layout.video_list_item, videoArrayList);
        videoList.setAdapter(videoAdapter);

        SomeRequest someRequest = new SomeRequest();
        someRequest.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    /**
     * @todo
     */
    public class SomeRequest  extends AsyncTask<Void, Void, Void> {//extends RestTask {//@todo inheri BaseRestActivity::RestTask

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            dialog.setTitle("Vremenniy title");
            dialog.show();

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient client = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(taskUrl);

            try {
                HttpResponse response = client.execute(getRequest);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();

                if (statusCode != 200) {
                    return null;
                }

                InputStream jsonStream = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(jsonStream));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                String jsonData = builder.toString();

                JSONObject json = new JSONObject(jsonData);
                JSONObject data = json.getJSONObject("data");
                JSONArray users = data.getJSONArray("users");
                for (int i = 0; i < users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    videoArrayList.add(user.getString("username"));
                }

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void resul) {
            dialog.dismiss();

            videoAdapter.notifyDataSetChanged();

            super.onPostExecute(resul);
        }
    }
}
