package com.ericmcconkie.watchface.settings;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinodevs.greatfitwatchface.R;
import com.dinodevs.greatfitwatchface.resource.ResourceManager;
import com.ericmcconkie.utils.AppPaint;
import com.ericmcconkie.utils.StringsHelper;

import java.util.List;

public class WithingsSettingsAdapter extends ArrayAdapter<WithingsSettingsObject> {
    Context mContext;
    List<WithingsSettingsObject> mList;
    public WithingsSettingsAdapter(@NonNull Context context, int resource, @NonNull List<WithingsSettingsObject> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_preference_icon,parent,false);

        final WithingsSettingsObject object= mList.get(position);

        //todo icon
        Paint iconPaint = AppPaint.getIconPaint(mContext);
        Bitmap tempImage = StringsHelper.textAsBitmap(object.getIconName(mContext),iconPaint);
        ImageView imageView = listItem.findViewById(R.id.icon);
        imageView.setImageBitmap(tempImage);

        TextView name = (TextView) listItem.findViewById(R.id.title);
        name.setText(object.getTitle());

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,WithingSettingsItemActivity.class);
                intent.putExtra("SettingType",object.getSettingType().getValue());
                mContext.startActivity(intent);
            }
        });

        return  listItem;
    }

}
