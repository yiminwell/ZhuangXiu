package com.alib.network;

import com.alib.application.BaseApplication;
import com.alib.listener.NetworkCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.Map;
import java.util.Set;

/**
 * Created by jerry
 */
public class XUtilsNetwork<T>
{
    private static XUtilsNetwork utils;

    /**
     * 私有无参构造方法
     */
    private XUtilsNetwork() {
    }

    /**
     * 单例
     * @return
     */
    public static XUtilsNetwork getInstance()
    {
        if (utils == null)
        {
            synchronized (XUtilsNetwork.class){
                if (utils==null){
                    utils = new XUtilsNetwork();
                }
            }
        }
        return utils;
    }

    /**
     * Get请求
     * @param url
     * @param networkCallBack
     */
    public void doGetRequest(String url,
                              Map<String, Object> params,
                              final NetworkCallBack<T> networkCallBack)
    {
        // HttpUtils请求数据
        HttpUtils httpUtils = BaseApplication.getApplication().getHttpUtils();
//        HttpUtils httpUtils=new HttpUtils("UTF-8");
//        httpUtils = new HttpUtils("utf-8");
//        httpUtils.configRequestThreadPoolSize(5);
//        httpUtils.configSoTimeout(30000);
//        httpUtils.configResponseTextCharset("utf-8");
//        httpUtils.configRequestRetryCount(3);

        RequestParams requestParams = null;
        if (params != null && !params.isEmpty())
        {
            requestParams = new RequestParams();

            // 获取参数集合
            Set<Map.Entry<String,Object>> set = params.entrySet();

            // 循环参数，设置到RequestParams
            for (Map.Entry<String,Object> entry : set)
            {
                // post method - addQueryStringParameter
                requestParams.addQueryStringParameter(entry.getKey(), entry.getValue()+"");
            }
        }

        // 异步
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<T>()
        {
            @Override
            public void onSuccess(ResponseInfo<T> responseInfo)
            {
                networkCallBack.onSuccess(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s)
            {
                networkCallBack.onFailure(s);
            }
        });
    }

    /**
     * Post请求
     * @param url 请求的URL地址
     * @param params 是从外部传递的参数
     * @param networkCallBack
     */
    public void doPostRequest(String url,
                               Map<String, Object> params,
                               final NetworkCallBack<T> networkCallBack)
    {
        // HttpUtils请求数据
        HttpUtils httpUtils = BaseApplication.getApplication().getHttpUtils();

        RequestParams requestParams = null;
        if (params != null && !params.isEmpty())
        {
            requestParams = new RequestParams();

            // 获取参数集合
            Set<Map.Entry<String,Object>> set = params.entrySet();

            // 循环参数，设置到RequestParams
            for (Map.Entry<String,Object> entry : set)
            {
                // post method - addBodyParameter
                requestParams.addBodyParameter(entry.getKey(),entry.getValue()+"");
            }
        }

        httpUtils.send(
                HttpRequest.HttpMethod.POST, url, requestParams,new RequestCallBack<T>()
                {
                    @Override
                    public void onSuccess(ResponseInfo<T> responseInfo)
                    {
                        networkCallBack.onSuccess(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException e, String s)
                    {
                        networkCallBack.onFailure(s);
                    }
                });
    }
}
