package com.ericmcconkie.watchface.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.dinodevs.greatfitwatchface.CustomDataUpdater;
import com.dinodevs.greatfitwatchface.GreatFitSlpt;
import com.dinodevs.greatfitwatchface.R;
import com.dinodevs.greatfitwatchface.settings.Settings;
import com.ericmcconkie.watchface.settings.BaseSetting;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends FragmentActivity {
    RecyclerView root = new RecyclerView(this);
    //Add header to a list of settings
    List<BaseSetting> settings = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Button btn = new Button();
//        btn.setText(R.string.steps);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                sharedPreferences.edit().clear().apply();
//                Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
//                // Restart watchface
//                SettingsActivity.this.sendBroadcast(new Intent("com.huami.intent.action.WATCHFACE_CONFIG_CHANGED"));
//                // Start CustomDataUpdater service
//                startService(new Intent(getApplicationContext(), CustomDataUpdater.class));
//                // Slpt some times doesn't run
//                startService(new Intent(getApplicationContext(), GreatFitSlpt.class));
//                // Kill this
//                SettingsActivity.this.setResult(-1);
//                SettingsActivity.this.finish();
//            }
//        });
    }
}
