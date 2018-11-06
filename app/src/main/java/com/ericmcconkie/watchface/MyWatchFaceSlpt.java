package com.ericmcconkie.watchface;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dinodevs.greatfitwatchface.AbstractWatchFaceSlpt;
import com.dinodevs.greatfitwatchface.widget.Widget;
import com.ericmcconkie.watchface.settings.Settings;
import com.ericmcconkie.watchface.widget.MainClock;
import com.ericmcconkie.watchface.widget.TachWidget;
import com.ericmcconkie.watchface.widget.TopWidget;
import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptAbsoluteLayout;
import com.ingenic.iwds.slpt.view.core.SlptLayout;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

public class MyWatchFaceSlpt extends AbstractWatchFaceSlpt {
    Context context;



    @Override
    protected SlptLayout createClockLayout26WC() {
        SlptAbsoluteLayout result = new SlptAbsoluteLayout();
        for (SlptViewComponent component : clock.buildSlptViewComponent(this, true)) {
            result.add(component);
        }
        for (Widget widget : widgets) {
            for (SlptViewComponent component : widget.buildSlptViewComponent(this, true)) {
                result.add(component);
            }
        }

        return result;
    }

    @Override
    protected SlptLayout createClockLayout8C() {
        SlptAbsoluteLayout result = new SlptAbsoluteLayout();
        for (SlptViewComponent component : clock.buildSlptViewComponent(this)) {
            result.add(component);
        }
        for (Widget widget : widgets) {
            for (SlptViewComponent component : widget.buildSlptViewComponent(this)) {
                result.add(component);
            }
        }

        return result;
    }



    protected void initWatchFaceConfig() {
        Log.w("DinoDevs-GreatFit", "Initiating watchface");

        //this.getResources().getBoolean(R.bool.seconds)

        Context context = this.getApplicationContext();
        boolean needRefreshSecond = Util.needSlptRefreshSecond(context);
        if (needRefreshSecond) {
            this.setClockPeriodSecond(true);
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = this.getApplicationContext();

        // Load settings
        Settings settings = Settings.getInstance();
        settings.setService(this);



        this.clock = new MainClock(settings);
        this.widgets.add(new TachWidget(settings));
        this.widgets.add(new TopWidget(settings));

        return super.onStartCommand(intent, flags, startId);
    }
}
