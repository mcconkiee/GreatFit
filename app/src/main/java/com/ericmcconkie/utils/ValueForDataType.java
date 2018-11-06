package com.ericmcconkie.utils;

import android.util.Log;

import com.dinodevs.greatfitwatchface.data.Battery;
import com.dinodevs.greatfitwatchface.data.DataType;
import com.dinodevs.greatfitwatchface.data.HeartRate;
import com.dinodevs.greatfitwatchface.data.WeatherData;

public class ValueForDataType {
    final static String TAG = "ValueForDataType";
    public static float getValue(DataType dataType,Object value){
        DataType tpe = DataType.fromValue(dataType.getDataType());
        float val = 0f;
        switch (tpe){
            case BATTERY:
                int level = ((Battery)value).getLevel();
                int scale = ((Battery)value).getScale();
                val = (float)level / (float)scale;
                break;
            case HEART_RATE:
                val = (float)((HeartRate)value).getHeartRate();

            default:
                break;
        }
        Log.d(TAG, String.format("val = %f", val));
        return val;
    }
}
