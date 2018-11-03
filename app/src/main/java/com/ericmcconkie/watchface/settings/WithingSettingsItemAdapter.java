package com.ericmcconkie.watchface.settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinodevs.greatfitwatchface.R;
import com.dinodevs.greatfitwatchface.data.DataType;
import com.ericmcconkie.utils.AppPaint;
import com.ericmcconkie.utils.StringsHelper;

import java.util.List;

public class WithingSettingsItemAdapter extends ArrayAdapter {
    private static String TAG = "WithingSettingsItemActivity";
    Context mContext;
    List<String> mList;
    String mCategory;
    public WithingSettingsItemAdapter(@NonNull Context context, int resource, @NonNull List objects, String category) {
        super(context, resource, objects);
        mContext = context;
        mList = objects;
        mCategory = category;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String item = mList.get(position);
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_preference_icon,parent,false);
        TextView title = listItem.findViewById(R.id.title);
        title.setText(item);

        DataType dataType = Settings.getInstance().dataTypeForItem(mCategory);
        String stringForType = Settings.getInstance().stringForDataType(dataType);

        Boolean selected = stringForType.equals(item);

        Log.d(TAG, String.format("compareing %s %d for %s  %s",stringForType,dataType.getDataType(),mCategory,item));
        if(selected ){
            Log.d(TAG, "getView: MATCHED dataType ");
            String fill = mContext.getString(R.string.circle);
            Paint iconPaint = AppPaint.getIconPaint(mContext);
            Bitmap tempImage = StringsHelper.textAsBitmap(fill,iconPaint);
            ImageView imageView = listItem.findViewById(R.id.icon);
            imageView.setImageBitmap(tempImage);
        }

        TextView subtitle = listItem.findViewById(R.id.subtitle);
        subtitle.setText("");

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo - make this the selected item
            }
        });
        return listItem;
    }
}
