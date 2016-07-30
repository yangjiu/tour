package com.joysus.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库工具类
 * 
 * @author howso
 */
public class DBHelper extends SQLiteOpenHelper {
	// 数据库版本
	private final static int VERSION = 11;
	private final static String DATA_NAME = "yyh.db";

	// 定义静态类
	private static DBHelper dbHelper;
	/**
	 * 程序上下文
	 */
	private Context context;

	// 构造方法 参数上下文
	public DBHelper(Context context) {
		this(context, DATA_NAME, null, VERSION);
		this.context = context;
	}

	// 构造方法 参数 上下文 数据库名称 cursor工厂
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	// 获取数据库单例
	public static DBHelper getIns(Context context) {
		if (dbHelper == null) {
			dbHelper = new DBHelper(context);
		}
		return dbHelper;
	}

	// 创建方法
	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * 判断数据是否存在
	 * @return
	 */
	private boolean isExistForData(SQLiteDatabase db,String tableName,String columnName,String whereArgs )
	{
		boolean result=false;
		String sql="select * from "+tableName+"  where  "+columnName+"='"+whereArgs+"'";
		Cursor cursor = null;
		try {
			cursor=db.rawQuery(sql, null);
			if(null!=cursor&&cursor.moveToNext())
			{
				//如果进来就证明有  
				result= true;
			}
		} 
		finally
		{
			  if(null != cursor && !cursor.isClosed()){
		            cursor.close() ;
		        }
		}
		return result;
	}
	/**
	* 方法1：检查某表列是否存在
	* @param db
	* @param tableName 表名
	* @param columnName 列名
	* @return
	*/
	private boolean checkColumnExist(SQLiteDatabase db, String tableName
	        , String columnName) {
	    boolean result = false ;
	    Cursor cursor = null ;
	    try{
	        //查询一行
	        cursor = db.rawQuery( "SELECT * FROM " + tableName + " LIMIT 0"
	            , null );
	        result = cursor != null && cursor.getColumnIndex(columnName) != -1 ;
	    }
	    finally{
	        if(null != cursor && !cursor.isClosed()){
	            cursor.close() ;
	        }
	    }

	    return result ;
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
}