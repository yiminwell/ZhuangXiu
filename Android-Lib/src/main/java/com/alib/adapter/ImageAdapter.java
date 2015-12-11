package com.alib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.alib.application.BaseApplication;

import java.util.ArrayList;

/**
 * Created by jerry on 15/9/19.
 */
public class ImageAdapter<T> extends CommAdapter<T>
{
    public ImageAdapter(ArrayList<T> datas)
    {
        super(datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView = null;
        if (convertView == null)
        {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            convertView = imageView;
        }

        T item = getItem(position);

        if (item instanceof Integer)
        {
            imageView.setImageResource((Integer)item);
        }
        else if (item instanceof String)
        {
            BaseApplication.getApplication()
                    .getImageLoader().displayImage((String)item,imageView);
        }

        return convertView;
    }


}
