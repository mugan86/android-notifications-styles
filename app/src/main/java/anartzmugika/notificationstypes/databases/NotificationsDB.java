package anartzmugika.notificationstypes.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

import anartzmugika.notificationstypes.Notification;
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

    public void addNotification(String id, String title, String icon)
    {
        String sql = "INSERT OR REPLACE INTO " + ConstantValues.NOTIFICATION_TABLE +
                " (" + ConstantValues.COLUMN_ID + ", " +
                ConstantValues.COLUMN_TITLE + ", "+
                ConstantValues.COLUMN_ICON + " ) " +
                "VALUES ( ?, ?, ? )";

        System.out.println("SQL: " + sql);

        SQLiteDatabase db = this.getWritableDatabase();

        /*
         * According to the docs http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
         * Writers should use beginTransactionNonExclusive() or beginTransactionWithListenerNonExclusive(SQLiteTransactionListener)
         * to start a transaction. Non-exclusive mode allows database file to be in readable by other threads executing queries.
         */
        db.beginTransactionNonExclusive();
        // db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);

        stmt.bindString(1,  id);
        stmt.bindString(2, title);
        stmt.bindString(3,  icon);

        stmt.execute();
        stmt.clearBindings();


        db.setTransactionSuccessful();
        db.endTransaction();
    }

    //Notifications
    public ArrayList<Notification> getNotification() {
        // 1. build the query
        String query = "SELECT * FROM " + ConstantValues.NOTIFICATION_TABLE;

        System.out.println("QUERY: " + query);

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Notification> notifications = new ArrayList<>();
        // 3. go over each row, build book and add it to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    System.out.println(cursor.getString(0));
                    System.out.println(cursor.getString(1));
                    System.out.println(cursor.getString(2));
                    System.out.println("Notification add");
                    notifications.add(new Notification(cursor.getString(0), cursor.getString(1), cursor.getString(2))); //Exist
                } while (cursor.moveToNext());
            }
        } finally {
            closeCursorDb(cursor, db);
            if (notifications.size() > 0) return notifications;
        }
        return null;
    }
    private void closeCursorDb(Cursor cursor, SQLiteDatabase db)
    {
        if (!cursor.isClosed()) cursor.close();
        if (db.isOpen()) db.close();
    }
}
