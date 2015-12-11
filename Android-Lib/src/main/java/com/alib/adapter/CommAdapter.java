package com.alib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.alib.application.BaseApplication;

import java.util.ArrayList;

/**
 * Created by jerry on 15/9/19.
 */
public abstract class CommAdapter<T> extends BaseAdapter
{
    protected Context mContext;
    protected ArrayList<T> datas;
    protected LayoutInflater layoutInflater;

    public CommAdapter(ArrayList<T> datas)
    {
        this.datas = datas;
        this.mContext = BaseApplication.getApplication();
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount()
    {
        return datas.size();
    }

    @Override
    public T getItem(int position)
    {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
