package com.joysus.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.joysus.app.joyapp;
import com.joysus.util.FileUtils;

/**
 * 数据库工具类
 * 
 * @author howso
 */
public class DataBaseHelper extends SQLiteOpenHelper {
	// 数据库版本
	private final static int VERSION = 1;
	private final static String DATA_NAME = "city.db";
	// 定义静态类
	private static DataBaseHelper dbHelper;
	// 构造方法 参数上下文
	public DataBaseHelper(Context context) {
		this(context, getDataString(context), null, VERSION);
	}

	// 构造方法 参数 上下文 数据库名称 cursor工厂
	public DataBaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	// 获取数据库单例
	public static DataBaseHelper getIns(Context context) {
		if (dbHelper == null) {
			dbHelper = new DataBaseHelper(context);
		}
		return dbHelper;
	}

	// 创建方法
	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// 创建成功
	}

	/**
	 * <初始化数据库数据>
	 */
	public static void insertDb(Context context) {
		String DB_PATH = "data/data/" + joyapp.getIns().packageName + "/databases";
		String DB_NAME = "city.db";

		File targetFile = new File(DB_PATH);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		File file = new File(targetFile.toString(), DB_NAME);
		// 数据库转移
		if (!file.exists()) {
			try {
				file = FileUtils.getInstance().createNewFile(targetFile.toString() + File.separator + DB_NAME);

				InputStream is = context.getAssets().open(DB_NAME);
				OutputStream os = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				// 关闭文件流
				os.flush();
				os.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 获取assert文件夹下 数据库
	public static String getDataString(Context context) {
		insertDb(context);
		if (!new File("/data/data/" + context.getPackageName() + "/databases/city.db").exists()) {
			try {
				FileOutputStream out = new FileOutputStream("data/data/" + context.getPackageName() + "/databases/city.db");
				InputStream in = context.getAssets().open("city.db");

				byte[] buffer = new byte[1024];
				int readBytes = 0;

				while ((readBytes = in.read(buffer)) != -1)
					out.write(buffer, 0, readBytes);

				in.close();
				out.close();
			} catch (IOException e) {
			}
		}
		return "/data/data/" + context.getPackageName() + "/databases/city.db";
	}

	/**
	 * 插入数据
	 * 
	 * @param values
	 */
	public long insert(ContentValues values, String tableName) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long result = db.insert(tableName, null, values);
		return result;
	}

	/**
	 * 更新数据
	 * 
	 * @param values
	 * 
	 * @param tableName
	 * 
	 * @param whereClause
	 * 
	 * @param whereArgs
	 * 
	 */
	public long update(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long result = db.update(tableName, values, whereClause, whereArgs);
		return result;
	}

	/**
	 * sql语句
	 * 
	 * @param sql
	 */
	public void update(String sql) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.execSQL(sql);
	}

	/**
	 * 查询数据
	 * 
	 * @param dbHelper
	 * @return
	 */
	public synchronized Cursor select(String sql) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		return cursor;
	}

	/**
	 * 查询数据
	 * 
	 * @param dbHelper
	 * @return
	 */
	public Cursor selectArgs(String sql, String[] args) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, args);
		return cursor;
	}

	/**
	 * 删除数据
	 * 
	 * @param dbHelper
	 */
	public int delete(String tableName) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int result = db.delete(tableName, null, null);
		return result;
	}
	
	/**
	 * 删除数据
	 * 
	 * @param dbHelper
	 */
	public int delete(String tableName, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int result = db.delete(tableName, whereClause, whereArgs);
		return result;
	}
}