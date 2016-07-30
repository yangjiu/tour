package com.joysus.util;


import android.util.Log;

/**
 * 日志类
 */
public class Logger
{
    /**
     * 是否开启Debug日志
     */
    private static boolean DBG = true;
    
    /**
     * <输出LOGD级别，标签为CPS的日志>
     * @param 输出内容
     */
    public static void LOGD(String message)
    {
        LOGD("BASE", message);
    }
    
    /**
     * 输出LOGD级别的日志
     * @param 标签
     * @param 输出内容
     */
    public static void LOGD(final String tag, String message)
    {
        if (DBG)
        {
            Log.d(tag, message);
        }
    }
    
    /**
     * 输出LOGD级别的日志
     * @param 标签
     * @param 输出内容
     * @param 输出日志时的异常
     */
    public static void LOGD(final String tag, String message, Throwable cause)
    {
        if (DBG)
        {
            Log.d(tag, message, cause);
        }
    }
    
    /**
     * 输出LOGV级别的日志
     * @param 标签
     * @param 输出内容
     */
    public static void LOGV(final String tag, String message)
    {
        if (DBG)
        {
            Log.v(tag, message);
        }
    }
    
    /**
     * 输出LOGV级别的日志
     * @param 标签
     * @param 输出内容
     * @param 输出日志时的异常
     */
    public static void LOGV(final String tag, String message, Throwable cause)
    {
        if (DBG)
        {
            Log.v(tag, message, cause);
        }
    }
    
    /**
     * 输出LOGI级别的日志
     * @param 标签
     * @param 输出内容
     */
    public static void LOGI(final String tag, String message)
    {
        if (DBG)
        {
            Log.i(tag, message);
        }
    }
    
    /**
     * 输出LOGI级别的日志
     * @param 标签
     * @param 输出内容
     * @param 输出日志时的异常
     */
    public static void LOGI(final String tag, String message, Throwable cause)
    {
        if (DBG)
        {
            Log.i(tag, message, cause);
        }
    }
    
    /**
     * 输出LOGW级别的日志
     * @param 标签
     * @param 输出内容
     */
    public static void LOGW(final String tag, String message)
    {
        if (DBG)
        {
            Log.w(tag, message);
        }
    }
    
    /**
     * 输出LOGW级别的日志
     * @param 标签
     * @param 输出内容
     * @param 输出日志时的异常
     */
    public static void LOGW(final String tag, String message, Throwable cause)
    {
        if (DBG)
        {
            Log.w(tag, message, cause);
        }
    }
    
    /**
     * 输出LOGE级别的日志
     * @param 标签
     * @param 输出内容
     */
    public static void LOGE(final String tag, String message)
    {
        if (DBG)
        {
            Log.e(tag, message);
        }
    }
    
    /**
     * 输出LOGE级别的日志
     * @param 标签
     * @param 输出内容
     * @param 输出日志时的异常
     */
    public static void LOGE(final String tag, String message, Throwable cause)
    {
        if (DBG)
        {
            Log.e(tag, message, cause);
        }
    }
    
    private Logger()
    {
    }
}
