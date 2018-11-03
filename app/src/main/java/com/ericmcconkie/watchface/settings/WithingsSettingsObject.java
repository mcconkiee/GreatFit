package com.ericmcconkie.watchface.settings;

import android.content.Context;
import android.util.Log;

import com.dinodevs.greatfitwatchface.R;


public class WithingsSettingsObject implements  WithingsSettingsObjectInterface{
    private static final String TAG = "WithingsSettingsObject";
    public SettingType settingType;
    private String mTitle;

    public WithingsSettingsObject(SettingType settingType) {
        super();
        this.settingType = settingType;

        switch (this.settingType){
            case TACH:
                mTitle =  "Tach";
                break;
            case TOP:
                mTitle =  "Top Widget";
                break;
            case DATE:
                mTitle =  "Date";
                break;
            default:
                Log.d(TAG, "getTitle: unsupported Type");
        }

    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public Boolean getEnabled() {
        return true;
    }

    @Override
    public SettingType getSettingType() {
        return settingType;
    }

    @Override
    public String getIconName(Context context) {
        switch (settingType){
            case TACH:
                return context.getString(R.string.tachometer);
            case TOP:
                return context.getString(R.string.circle);
            case DATE:
                return context.getString(R.string.calendar);
            default:
                return context.getString(R.string.circle);
        }
    }
}
