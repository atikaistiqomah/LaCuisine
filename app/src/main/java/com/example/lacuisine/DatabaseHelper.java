package com.example.lacuisine;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "daftar.db";
    private static String DB_PATH = "";
    private SQLiteDatabase mDatabase;
    private final Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if (Build.VERSION.SDK_INT >= 15) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = Environment.getDataDirectory() + "/data/" + context.getPackageName() + "/databases/";
        }

        this.mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void checkNCopyDatabase() {
        boolean dbExist = checkDatabase();
        if (dbExist) {
            Log.d("TAG", "database already exist");
        } else {
            this.getReadableDatabase();
        }

        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG", "Error copy database");
        }
    }

    public boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try{
            String mPath = DB_PATH+DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(mPath,null,SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLException e) {
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void copyDatabase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH+DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = mInput.read(buffer)) > 0) {
            mOutput.write(buffer,0,length);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public void openDatabase() {
        String mPath = DB_PATH+DB_NAME;
        mDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if (mDatabase != null) {
            mDatabase.close();
        }
        super.close();
    }

    public Cursor QueryFood() {
        return mDatabase.rawQuery("SELECT * FROM food",null);
    }

    public Cursor QueryDrink() {
        return mDatabase.rawQuery("SELECT * FROM drink",null);
    }

    public Cursor QueryDessert() {
        return mDatabase.rawQuery("SELECT * FROM dessert",null);
    }
}
