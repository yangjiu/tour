package com.joysus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.joysus.app.joyapp;
import com.joysus.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by howso on 2016.06.15.
 */

public class ChatManager {
    // 上下文
    private Context mContext;
    // 数据库对象
    private SQLiteDatabase db;
    // 数据库工具类
    private DBHelper helper;

    public ChatManager(Context context)
    {
        this.mContext = context;
        // 实例化DataBaseHelper
        helper = new DBHelper(mContext);
    }
    // 写入
    public void writeDB() {
        if (null != helper) {
            db = helper.getWritableDatabase();
        }
    }

    // 读取
    public void readDB() {
        if (null != helper) {
            db = helper.getReadableDatabase();
        }

    }

    // <关闭数据库对象>
    public void closeDB() {
        if (null != db) {
            db.close();
        }
    }


    public void initCellDB() {
        String DB_PATH = "data/data/" + joyapp.getIns().packageName + "/databases";
        String DB_NAME = "chatdb.db";

        File targetFile = new File(DB_PATH);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        File file = new File(targetFile.toString(), DB_NAME);

        if (!file.exists()) {
            try {
                file = FileUtils.getInstance().createNewFile(targetFile.toString() + File.separator + DB_NAME);

                InputStream is = mContext.getAssets().open(DB_NAME);
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
}
