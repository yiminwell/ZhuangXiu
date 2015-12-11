package com.alib.listener;

/**
 * Created by jerry on 15/9/22.
 */
public interface NetworkCallBack<T>
{
    public void onSuccess(T t);
    public void onFailure(String error);
}
