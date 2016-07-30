package com.joysus.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

/**
 * 
 * 拍照工具类
 */
public class PhotoUtils {
	private static PhotoUtils instance;

	public static PhotoUtils getInstance() {
		if (null == instance) {
			instance = new PhotoUtils();
		}
		return instance;
	}

	/**
	 * 提供拍照功能, 照片会自动保存在PHOTO_PATH中.
	 * 
	 * @param sender
	 *            请求拍照的控件id
	 * @param photoSavePath
	 *            拍照图片保存的路径
	 * 
	 */
	public void takePhoto(Context context, String photoSavePath) {
		// 执行拍照前，应该先判断SD卡是否存在
		String SDState = Environment.getExternalStorageState();

		if (SDState.equals(Environment.MEDIA_MOUNTED)) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			Uri photoUri = Uri.fromFile(new File(photoSavePath));
			// 这样就将文件的存储方式和uri指定到了Camera应用中
			intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
			((Activity) context).startActivityForResult(intent, 1);
		} else {
			Toast.makeText(context, "内存卡不存在,操作无法完成", Toast.LENGTH_LONG).show();
		}
	}
}