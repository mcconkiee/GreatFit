package com.ericmcconkie.watchface.settings;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.util.Log;

import com.dinodevs.greatfitwatchface.R;
import com.dinodevs.greatfitwatchface.data.DataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class Settings {
    private static String TAG = "Settings";
    private Service service;
    public Paint paint;
    private SharedPreferences sharedPreferences;
    // static variable single_instance of type Singleton
    private static Settings single_instance = null;
    public Settings() {
        super();

    }

    public void setService(Service service){
        this.service = service;
        String settingsName = service.getApplication().getPackageName() +"_settings";
        this.sharedPreferences = service.getApplication().getSharedPreferences(settingsName,Context.MODE_PRIVATE);
        this.setupDefaults();
    }

    private void setupDefaults(){
        String categoryTach = service.getString(R.string.category_tach);
        if(sharedPreferences.getInt(categoryTach, -1) == -1) {
            sharedPreferences.edit().putInt(categoryTach, DataType.BATTERY.getDataType()).apply();
        }
        String categoryTop = service.getString(R.string.category_top);
        if(sharedPreferences.getInt(categoryTop, -1) == -1) {
            sharedPreferences.edit().putInt(categoryTop, DataType.HEART_RATE.getDataType()).apply();
        }
    }

    public String stringForDataType(DataType dataType){
        switch (dataType){
            case BATTERY:
                return service.getString(R.string.option_battery);
            case HEART_RATE:
                return service.getString(R.string.option_hear_rate);
            case STEPS:
                return service.getString(R.string.option_steps);
            case WEATHER:
                return service.getString(R.string.option_weather);
            case CUSTOM:
                return service.getString(R.string.option_sunrise_sunset);
            case DATE:
                return service.getString(R.string.option_date);
            default:
                return service.getString(R.string.option_battery);
        }
    }

    public int prefernceForCategory(String option){
        int pref = sharedPreferences.getInt(option,-1);
        return pref;
    }
    public DataType dataTypeForItem(String option){
        int pref = sharedPreferences.getInt(option,-1);
        DataType dataType = DataType.fromValue(pref);
        return dataType;
    }

    public  List<WithingsSettingsObject> getSettingsItems(){
        ArrayList<WithingsSettingsObject> list = new ArrayList<>();
        list.add(new WithingsSettingsObject(SettingType.TOP));
        list.add(new WithingsSettingsObject(SettingType.TACH));
        list.add(new WithingsSettingsObject(SettingType.DATE));
        return  list ;
    }

    public List<String> getSupportedSettingItems(){
        ArrayList<String> items = new ArrayList<>();
        items.add(service.getString(R.string.option_battery));
        items.add(service.getString(R.string.option_hear_rate));
        items.add(service.getString(R.string.option_steps));
        items.add(service.getString(R.string.option_weather));
        return items;
    }

    // static method to create instance of Singleton class
    public static Settings getInstance()
    {
        if (single_instance == null)
            single_instance = new Settings();

        return single_instance;
    }

}
