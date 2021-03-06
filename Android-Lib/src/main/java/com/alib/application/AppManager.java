package com.alib.application;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.Toast;

/**
 * @author Jerry.Zou
 */
public class AppManager
{
    private Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager()
    {
    }

    /**
     * 单例，获取对应应用管理类
     */
    public static AppManager getAppManager()
    {
        if (instance == null)
        {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity)
    {
        if (activityStack == null)
        {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity()
    {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity()
    {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity)
    {
        if (activity != null)
        {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 移除指定的Activity
     */
    public void removeActivity(Activity activity)
    {
        if (activity != null)
        {
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls)
    {
        for (Activity activity : activityStack)
        {
            if (activity.getClass().equals(cls))
            {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity()
    {
        for (int i = 0, size = activityStack.size(); i < size; i++)
        {
            if (null != activityStack.get(i))
            {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     *
     * @param context      上下文
     * @param isBackground 是否开开启后台运行
     */
    public void AppExit(Context context, Boolean isBackground)
    {
        try
        {
            if (isBackground)
            {
                PackageManager pm = context.getPackageManager();
                ResolveInfo homeInfo =
                        pm.resolveActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME), 0);

                ActivityInfo ai = homeInfo.activityInfo;
                Intent startIntent = new Intent(Intent.ACTION_MAIN);
                startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                startIntent.setComponent(new ComponentName(ai.packageName, ai.name));
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try
                {
                    context.startActivity(startIntent);
                }
                catch (ActivityNotFoundException e)
                {
                    Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
                }
                catch (SecurityException e)
                {
                    Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
                }
            } else
            {
                finishAllActivity();

                ActivityManager activityMgr = (ActivityManager) context
                        .getSystemService(Context.ACTIVITY_SERVICE);
                //activityMgr.restartPackage(context.getPackageName());

                activityMgr.killBackgroundProcesses(context.getPackageName());

                // 注意，如果您有后台程序运行，请不要支持此句子
                System.exit(0);
            }

        } catch (Exception e)
        {

        }
    }
}