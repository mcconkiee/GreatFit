package com.ericmcconkie.watchface.settings;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.dinodevs.greatfitwatchface.R;


public class WithingsSettingsActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withings_settings);
        ListView root = findViewById(R.id.listview);


        WithingsSettingsAdapter adapter = new WithingsSettingsAdapter(this,0,Settings.getInstance().getSettingsItems());
        root.setAdapter(adapter);

        root.setBackgroundResource(R.drawable.settings_background);
        root.setPadding((int) getResources().getDimension(R.dimen.padding_round_small), 0, (int) getResources().getDimension(R.dimen.padding_round_small), (int) getResources().getDimension(R.dimen.padding_round_large));
        root.setClipToPadding(false);

    }
}
