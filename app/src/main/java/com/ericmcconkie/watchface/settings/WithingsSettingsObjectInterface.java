package com.ericmcconkie.watchface.settings;

import android.content.Context;
import android.graphics.Bitmap;

public interface WithingsSettingsObjectInterface {
    public String getTitle();
    public Boolean getEnabled();
    public String getIconName(Context context);
    public SettingType getSettingType();
}
