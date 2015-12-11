package com.alib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jerry on 15/9/22.
 */
public class BaseFragment extends Fragment
{
    private int resLayoutId = -1;

    public BaseFragment()
    {
    }

    public BaseFragment(int resLayoutId)
    {
        this.resLayoutId = resLayoutId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(resLayoutId, container, false);
        return view;
    }
}
