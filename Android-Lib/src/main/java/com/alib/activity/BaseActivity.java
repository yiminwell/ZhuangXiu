package com.alib.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.alib.application.AppManager;
import com.lidroid.xutils.ViewUtils;

/**
 * Created by jerry on 15/9/19.
 */
public abstract class BaseActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ViewUtils.inject(this);

        initViews();

        initDatas(getIntent());

        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        AppManager.getAppManager().finishActivity(this);
    }

    protected void showToast(CharSequence tip)
    {
        if (TextUtils.isEmpty(tip))
        {
            return;
        }
        Toast.makeText(
                getApplicationContext(), tip, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int resId)
    {
        if (resId == -1)
        {
            return;
        }
        this.showToast(getString(resId));
    }

    protected abstract void initViews();

    protected abstract void initDatas(Intent intent);


}
