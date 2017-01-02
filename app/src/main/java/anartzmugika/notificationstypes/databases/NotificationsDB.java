package anartzmugika.notificationstypes.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import anartzmugika.notificationstypes.data.ConstantValues;

/*********************************************
 * Created by anartzmugika on 2/1/17.
 * Class to manage notifications info
 */

public class NotificationsDB extends SQLiteOpenHelper{

    private static int version = 1;
    private static String name = "NotificationsDB";
    private static SQLiteDatabase.CursorFactory factory = null;
    private Cursor cursor;
    NotificationsDB dbHelper;
    private Context context;
    SQLiteDatabase db;

    public NotificationsDB(Context context) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Create Data Base");


        db.execSQL("CREATE TABLE IF NOT EXISTS " + ConstantValues.NOTIFICATION_TABLE + "(" +
                " " + ConstantValues.COLUMN_ID + " TEXT PRIMARY KEY," +
                " " + ConstantValues.COLUMN_TITLE + " TEXT," +
                " " + ConstantValues.COLUMN_ICON + " TEXT)");

        Log.i(this.getClass().toString(), "Notifications table create");

        String msg_table = "CREATE TABLE IF NOT EXISTS " + ConstantValues.CONTENT_TABLE + "(" +
                " " + ConstantValues.COLUMN_ID + " INTEGER PRIMARY KEY," +
                " " + ConstantValues.COLUMN_MSG_DATE + " TEXT, "+
                " " + ConstantValues.COLUMN_MSG_IMAGE + " TEXT," +
                " " + ConstantValues.COLUMN_MSG_AUTHOR + " TEXT," +
                " " + ConstantValues.COLUMN_MSG_AUTHOR_IMG + " TEXT," +
                " " + ConstantValues.COLUMN_MSG_SOURCE + " TEXT," +
                " " + ConstantValues.COLUMN_MSG_NOTIFICATION_ID + " TEXT," +
                " " + ConstantValues.COLUMN_MSG_BODY + " TEXT)";

        db.execSQL(msg_table);

        Log.i(this.getClass().toString(), "Messages table create");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + ConstantValues.NOTIFICATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantValues.CONTENT_TABLE);
        //System.out.println("Update to database version in 2016/12/14: Version " + newVersion);
        this.onCreate(db);
    }
}
