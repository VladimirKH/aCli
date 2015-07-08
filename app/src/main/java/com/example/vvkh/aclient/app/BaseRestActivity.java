package com.example.vvkh.aclient.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class BaseRestActivity extends ActionBarActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class RestTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        String progressDialogTitle = "Http loading ...";

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            dialog.setTitle(progressDialogTitle);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            dialog.dismiss();
            super.onPostExecute(result);
        }

        public void setProgressDialogTitle(String title) {
          progressDialogTitle = title;
        }
    }
}
