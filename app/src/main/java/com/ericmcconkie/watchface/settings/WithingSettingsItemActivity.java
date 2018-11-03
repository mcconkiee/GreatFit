package com.ericmcconkie.watchface.settings;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;


import com.dinodevs.greatfitwatchface.R;

import java.util.List;


public class WithingSettingsItemActivity extends Activity {
    private static String TAG = "WithingSettingsItemActivity";
    private SettingType mSettingType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //unwrap the intent for which widget space
        Bundle extras = getIntent().getExtras();
        int settingTypeInt = extras.getInt("SettingType");
        mSettingType = SettingType.getSettingType(settingTypeInt);
        String category = "";
        switch (mSettingType){
            case TACH:
                Log.d(TAG, "onCreate: TACH category selected");
                category = this.getString(R.string.category_tach);
                break;
            case TOP:
                Log.d(TAG, "onCreate: Top category selected");
                category = this.getString(R.string.category_top);
                break;
            case DATE:
                Log.d(TAG, "onCreate: DATE category selected");
                category = this.getString(R.string.category_date);
                break;
        }

        Settings settings = Settings.getInstance();
        List<String> items = settings.getSupportedSettingItems();
        ListView listView = new ListView(this);
        listView.setAdapter(new WithingSettingsItemAdapter(this,0,items,category));
        setContentView(listView);

    }

}
