package com.alib.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class PackageUtils
{

	/**
	 * get app version
	 * @param context
	 * @return
	 */
	public static int getAppVersion(Context context)
	{
		try
		{
			PackageInfo info = 
					context.getPackageManager()
						.getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		}
		catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return 1;
	}
}
