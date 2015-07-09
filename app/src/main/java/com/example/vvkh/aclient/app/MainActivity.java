package com.example.vvkh.aclient.app;

import android.app.ProgressDialog;
import android.os.AsyncTask;//@todo delete
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static java.lang.Thread.*;


public class MainActivity extends BaseActivity {

    ListView videoList;
    String[] videoArray = {"No Videos"};
    ArrayAdapter<String> videoAdapter;

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
        videoAdapter = new ArrayAdapter<String>(this, R.layout.video_list_item, videoArray);
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
    public class SomeRequest  extends AsyncTask<Void, Void, Void> {//extends RestTask {//@todo унаследовать от BaseRestActivity::RestTask

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
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void resul) {
            dialog.dismiss();

            super.onPostExecute(resul);
        }
    }
}
