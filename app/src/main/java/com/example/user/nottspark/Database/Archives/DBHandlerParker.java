/*
package getresult.example.asus.nottspark.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandlerParker extends SQLiteOpenHelper {

    private static final int DATABASE_P_VERSION = 1;
    private static final String DATABASE_P_NAME =
            "LeaverInfo";
    private static final String TABLE_PARKER =
            "credentials";
    private static final String KEY_P_ID =
            "id";
    private static final String KEY_P_LOCATION =
            "location";
    private static final String KEY_P_VEHICLE =
            "time";
    private static final String KEY_P_STATUS =
            "status";

    public DBHandlerParker(Context context, Object o, Object o1, int i) {
        super(context, DATABASE_P_NAME, null, DATABASE_P_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXCHANGE_TABLE = "CREATE TABLE " + TABLE_PARKER + "("
                + KEY_P_ID + " INTEGER PRIMARY KEY," + KEY_P_LOCATION + " TEXT,"
                + KEY_P_VEHICLE + " TEXT,"
                + KEY_P_STATUS + " INTEGER" + ")";
        db.execSQL(CREATE_EXCHANGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARKER);
        onCreate(db);
    }

    public void addParker(ParkerInfo info) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_P_LOCATION, info.getLocation()); // Location
        values.put(KEY_P_VEHICLE, info.getVehicle()); // Vehicle
        values.put(KEY_P_STATUS, info.getStatus()); //  Status

        db.insert(TABLE_PARKER, null, values);
        db.close(); // Closing database connection
    }

    public ParkerInfo getParker(int id) {
        SQLiteDatabase db_parker = this.getReadableDatabase();

        Cursor cursor = db_parker.query(TABLE_PARKER, new String[]{KEY_P_ID,
                        KEY_P_LOCATION, KEY_P_VEHICLE, KEY_P_STATUS}, KEY_P_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ParkerInfo cred = new ParkerInfo(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        return cred;
    }
}
*/