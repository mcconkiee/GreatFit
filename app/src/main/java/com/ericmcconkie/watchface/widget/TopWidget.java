package com.ericmcconkie.watchface.widget;

import android.app.Service;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.Log;

import com.dinodevs.greatfitwatchface.R;
import com.dinodevs.greatfitwatchface.data.DataType;
import com.dinodevs.greatfitwatchface.resource.ResourceManager;
import com.dinodevs.greatfitwatchface.widget.AbstractWidget;
import com.ericmcconkie.utils.StringsHelper;
import com.ericmcconkie.utils.ValueForDataType;
import com.ericmcconkie.watchface.settings.Settings;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopWidget extends AbstractWidget {
    static final String TAG = "TopWidget";

    SharedPreferences sharedPreferences;
    private DataType dataType = DataType.HEART_RATE;
    private Settings settings;
    private String mIcon; //font awesome icon for top
    private Bitmap mImage; //icon as an image
    Service service;
    private float widgetVal = 0f;
    private Paint textPaint;
    private Paint iconPaint;
    public TopWidget(Settings settings){
        this.settings = settings;
    }
    @Override
    public void init(Service service) {
        super.init(service);
        this.service = service;

        this.dataType = this.settings.dataTypeForItem(service.getString(R.string.category_top));

        // Font
        this.textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.textPaint.setColor(this.service.getResources().getColor(R.color.white));
        this.textPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.FONT_FILE));
        this.textPaint.setTextSize(50);
//        this.textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.textPaint.setTextAlign( Paint.Align.CENTER);

        // icon
        this.iconPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.iconPaint.setColor(this.service.getResources().getColor(R.color.white));
        //FIXME - heart vs heartbeat use different variants of fontawesome....set up a way to handle this from settings
        this.iconPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.FONTAWESOMESOLID));
        this.iconPaint.setTextSize(25);
        this.iconPaint.setTextAlign( Paint.Align.CENTER);

        mIcon = service.getString(R.string.heartbeat);
        mImage  = StringsHelper.textAsBitmap(mIcon,this.iconPaint);
    }

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        //value
        canvas.drawText(StringsHelper.widgetValAsString((int)this.widgetVal),centerX,centerY - 88,this.textPaint);

        //icon
        canvas.drawText(String.format("%s --",mIcon),centerX,centerY - 55,this.iconPaint);

//        Bitmap tempImage = StringsHelper.textAsBitmap(icon,this.iconPaint);
//        this.iconPaint.setColor(service.getResources().getColor(R.color.primary));
//        canvas.drawBitmap(tempImage,centerX,centerY,iconPaint);
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        return buildSlptViewComponent(service,false);
    }

    /**/
    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service, boolean better_resolution) {


        //todo - make this dynamic to the settings
        String icon = service.getString(R.string.heartbeat);
        Log.d(TAG, String.format("icon for this is %s",icon));
        Log.d(TAG, "buildSlptViewComponent: " + StringsHelper.widgetValAsString((int)this.widgetVal));
        List<SlptViewComponent> list = new ArrayList<>();

        //value
        SlptPictureView pictViewValue = new SlptPictureView();
        pictViewValue.setStringPicture(StringsHelper.widgetValAsString((int)this.widgetVal));
        int color = service.getResources().getColor(R.color.white);
        pictViewValue.setTextAttr(50,color,ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.FONT_FILE));
        pictViewValue.alignX = (byte) 2;
        pictViewValue.alignY = (byte) 2;
        pictViewValue.y = -102;
        pictViewValue.setRect(320,320);
        list.add(pictViewValue);


        //icon

        SlptPictureView pictView = new SlptPictureView();
        pictView.setStringPicture(icon);
        pictView.setTextAttr(30,color,ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.FONTAWESOMESOLID));
        pictView.alignX = (byte) 2;
        pictView.alignY = (byte) 2;
        pictView.y = -65;
        pictView.x = -10;
        pictView.setRect(320,320);
        list.add(pictView);

        //--

        SlptPictureView pictViewDash = new SlptPictureView();
        pictViewDash.setStringPicture("--");
        pictViewDash.setTextAttr(30,color,ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.FONT_FILE));
        pictViewDash.alignX = (byte) 2;
        pictViewDash.alignY = (byte) 2;
        pictViewDash.y = -65;
        pictViewDash.x = 10;
        pictViewDash.setRect(320,320);
        list.add(pictViewDash);

        return list;
    }

    @Override
    public void onDataUpdate(DataType type, Object value) {
        Log.d(TAG, String.format("onDataUpdate: value for datatype %f" , ValueForDataType.getValue(type,value)));
        widgetVal = ValueForDataType.getValue(type,value);
    }
    public List<DataType> getDataTypes() {
        return Collections.singletonList(this.dataType);
    }
}
