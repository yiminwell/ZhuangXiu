package com.alib.log;

import android.util.Log;

/**
 * @Title oLogCatLogger
 * @Package 
 * @Description LogCatLogger是打印到LogCat上面的日志类
 * @author 
 * @date 
 * @version 
 */
public class LogCatLogger implements ILogger
{
	@Override
	public void d(String tag, String message)
	{
		Log.d(tag, message);
	}

	@Override
	public void e(String tag, String message)
	{
		Log.e(tag, message);
	}

	@Override
	public void i(String tag, String message)
	{
		Log.i(tag, message);
	}

	@Override
	public void v(String tag, String message)
	{
		Log.v(tag, message);
	}

	@Override
	public void w(String tag, String message)
	{
		Log.w(tag, message);
	}

	@Override
	public void println(int priority, String tag, String message)
	{
		Log.println(priority, tag, message);
	}

	@Override
	public void open()
	{
		
	}

	@Override
	public void close()
	{
		
	}
}
