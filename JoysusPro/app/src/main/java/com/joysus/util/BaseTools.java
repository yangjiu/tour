package com.joysus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Point;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;

public class BaseTools {
	// 定义自己的静态变量
	private static BaseTools tools;

	// 获取单例
	public static BaseTools getInstance() {
		if (null == tools) {
			tools = new BaseTools();
		}
		return tools;
	}

	/**
	 * 获取IMSI
	 * 
	 * @return
	 */
	public String getIMSI(Context context) {
		TelephonyManager mTelephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = mTelephonyManager.getSimSerialNumber();

		return imsi;
	}

	/**
	 * 获得当前MCC MNC
	 * 
	 * @param context
	 * @return
	 */
	public String getMCCMNC(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getNetworkOperator();
	}

	/**
	 * 
	 * 动态获取MCC MNC并格式化
	 * 
	 * @param context
	 * @return
	 */
	public String showMCCMNC(Context context) {
		String mccmnc = getMCCMNC(context);
		if (null != mccmnc && mccmnc.length() == 5) {
			return mccmnc.substring(0, 3) + "/" + mccmnc.substring(3);
		}
		return Config.DEFAULT_VALUE;
	}

	/**
	 * 
	 * 格式化字符串对象，去掉null
	 * 
	 * @param content
	 * @return
	 */
	public String formatString(String content) {
		if (TextUtils.isEmpty(content) || "null".equalsIgnoreCase(content)
				|| "".equals(content.trim())) {
			return Config.DEFAULT_EMPTY_VALUE;
		} else {
			return content;
		}
	}

	/**
	 * 获取当前设备宽度
	 * 
	 * @return
	 */
	public int getWidth(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		return width;
	}

	/**
	 * 获取当前设备高度
	 * 
	 * @return
	 */
	public int getHeight(Context context) {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		Point size=new Point();
		display.getSize(size);
		int height = size.y;
		return height;
	}

	/**
	 * <格式化时间>
	 * 
	 * @param long 格式：yyyy-MM-dd HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public String formatTime(long time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(time);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * <格式化时间> 格式：HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public String formatShowTimeToHMS(long time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			return sdf.format(time);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * <格式化时间>
	 * 
	 * @param String
	 *            格式：yyyy-MM-dd HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public long formatTimeByMill(String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss.SSS");
			return format.parse(time).getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 
	 * <格式化时间>
	 * 
	 * @param time
	 * @return yyyy-MM-dd HH:mm:ss SSS
	 */
	@SuppressLint("SimpleDateFormat")
	public String formatTimes(long time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss SSS");
			return sdf.format(time);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 把毫秒转化成日期
	 * 
	 * @param dateFormat
	 *            (日期格式，例如：MM/ dd/yyyy HH:mm:ss)
	 * @param millSec
	 *            (毫秒数)
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public String transferLongToDate(String dateFormat, Long millSec) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date date = new Date(millSec);
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * <格式化时间>
	 * 
	 * @param String
	 *            格式：yyyy-MM-dd HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public long formatTime(String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return format.parse(time).getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * <格式化时间>
	 * 
	 * @param long 格式：yyyyMMdd_HHmmssSSS
	 */
	@SuppressLint("SimpleDateFormat")
	public String formatTimeToZipName(long time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			return sdf.format(time);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * <获取本软件的版本名>
	 */
	public String getVersionName(Context context) {
		PackageInfo packageInfo = null;

		String versionName = "1.0";

		try {
			packageInfo = context.getPackageManager().getPackageInfo(
					context.getPackageName().toString(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != packageInfo) {
			versionName = packageInfo.versionName;
		}
		return versionName;
	}

	/**
	 * <获取本软件的版本号>
	 */
	public int getVersionCode(Context context) {
		PackageInfo packageInfo = null;

		int versionCode = 0;

		try {
			packageInfo = context.getPackageManager().getPackageInfo(
					context.getPackageName().toString(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != packageInfo) {
			versionCode = packageInfo.versionCode;
		}
		return versionCode;
	}
}
