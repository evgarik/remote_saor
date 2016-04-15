package ru.mts.remote_saor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

/**
 * Created by alex.sosov on 13.04.2016.
 */
public class Database {
    private static final String DATABASE_NAME = "saor.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MESSAGES = "Messages";

    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_MESSAGES_NUMBER = "message_number";
    public static final String COLUMN_MESSAGES_MESSAGE = "message_text";
    public static final String COLUMN_MESSAGES_TIME = "message_time";
    public static final String COLUMN_MESSAGES_DIRECTION = "message_direction";

    private static final String MESSAGES_CREATE = "create table " + TABLE_MESSAGES + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_MESSAGES_NUMBER + " text not null, "
            + COLUMN_MESSAGES_MESSAGE + " text, "
            + COLUMN_MESSAGES_TIME + " integer not null, "
            + COLUMN_MESSAGES_DIRECTION + " text not null);";

    SQLiteDatabase mDatabase;
    SQLiteHelper mSqlHelper;


    public Database(Context context){
        mSqlHelper = new SQLiteHelper(context);
        mDatabase = mSqlHelper.getWritableDatabase();
    }


    public long insertMessage(String number, String text, long time_ms, String direction)
    {
        ContentValues cvalues = new ContentValues();
        cvalues.put(COLUMN_MESSAGES_NUMBER, number);
        cvalues.put(COLUMN_MESSAGES_MESSAGE, text);
        cvalues.put(COLUMN_MESSAGES_TIME, time_ms);
        cvalues.put(COLUMN_MESSAGES_DIRECTION, direction);
        return mDatabase.insert(TABLE_MESSAGES, null,cvalues);
    }

    public void closeDb()
    {
        mDatabase.close();
    }


    public class SQLiteHelper extends SQLiteOpenHelper {

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(MESSAGES_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
        public SQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
    }
}
