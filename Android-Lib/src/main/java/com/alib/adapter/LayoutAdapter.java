package com.alib.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.alib.application.BaseApplication;
import com.lidroid.xutils.ViewUtils;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by jerry on 15/9/19.
 */
public abstract class LayoutAdapter<T, ViewHodler> extends CommAdapter<T>
{
    private int layoutResId = -1;
    private ViewHodler viewHodler;

    public LayoutAdapter(ArrayList<T> datas, int layoutResId,ViewHodler viewHodler)
    {
        super(datas);
        this.layoutResId = layoutResId;
        this.viewHodler = viewHodler;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(this.layoutResId,null);

            //initLayoutViews(convertView);

            ViewUtils.inject(viewHodler, convertView);

            convertView.setTag(viewHodler);
        }

        convertView.setTag(0,position);

        setViews(convertView,getItem(position));

        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = (int)v.getTag(0);

                onItemClicked(position,getItem(position));
            }
        });

        return convertView;
    }

    //public abstract void initLayoutViews(View convertView);

    public abstract void setViews(View convertView,T t);

    public abstract void onItemClicked(int poition,T t);

}
