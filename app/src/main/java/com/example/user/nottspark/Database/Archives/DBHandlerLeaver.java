/*
package getresult.example.asus.nottspark.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

public class DBHandlerLeaver extends SQLiteOpenHelper {

    private static final int DATABASE_L_VERSION = 1;
    private static final String DATABASE_L_NAME =
            "LeaverInfo";
    private static final String TABLE_LEAVER =
            "credentials";
    private static final String KEY_L_ID =
            "id";
    private static final String KEY_L_LOCATION =
            "location";
    private static final String KEY_L_TIME =
            "time";
    private static final String KEY_L_VEHICLE =
            "vehicle";
    private static final String KEY_L_DESC =
            "desc";
    private static final String KEY_L_AVAILABLE =
            "available";

    public DBHandlerLeaver(FragmentActivity activity, Object o, Context context, int i) {
        super(context, DATABASE_L_NAME, null, DATABASE_L_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXCHANGE_TABLE = "CREATE TABLE " + TABLE_LEAVER + "("
                + KEY_L_ID + " INTEGER PRIMARY KEY," + KEY_L_LOCATION + " TEXT,"
                + KEY_L_TIME + " TEXT,"
                + KEY_L_VEHICLE + " TEXT,"
                + KEY_L_DESC + " TEXT,"
                + KEY_L_AVAILABLE + " INTEGER" + ")";
        db.execSQL(CREATE_EXCHANGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAVER);
        onCreate(db);
    }

    public void addLeaver(LeaverInfo info) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_L_LOCATION, info.getLocation()); // Location
        values.put(KEY_L_TIME, info.getTime()); //  Time
        values.put(KEY_L_VEHICLE, info.getVehicle()); // Vehicle
        values.put(KEY_L_DESC, info.getDesc()); //  Desc
        values.put(KEY_L_AVAILABLE, info.getAvailable()); //  Availability

        db.insert(TABLE_LEAVER, null, values);
        db.close(); // Closing database connection
    }
}
*/