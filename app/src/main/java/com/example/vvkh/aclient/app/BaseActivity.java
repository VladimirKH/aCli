package com.example.vvkh.aclient.app;

import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends BaseRestActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isAuthenticated()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    protected boolean isAuthenticated() {
        return true;
    }
}
