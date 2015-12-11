package com.alib.util;

import java.util.Collection;

public class CollectionsUtils
{

	/**
	 * 判断集合是否为空
	 * @param coll
	 * @return
	 */
	public static boolean isEmpty(Collection<?> datas)
	{
		if(datas != null && !datas.isEmpty())
		{
			return false;
		}
		return true;
	}
	
}
