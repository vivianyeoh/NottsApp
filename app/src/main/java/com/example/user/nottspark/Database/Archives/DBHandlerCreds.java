/*
package getresult.example.asus.nottspark.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandlerCreds extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 23;
    private static final String DATABASE_NAME =
            "UserCredentials";
    private static final String TABLE_CREDENTIALS =
            "credentials";
    private static final String KEY_ID =
            "id";
    private static final String KEY_USERNAME =
            "username";
    private static final String KEY_PW =
            "password";
    private static final String KEY_NAME =
            "name";
    private static final String KEY_PHONE =
            "phone";
    private static final String KEY_VEHICLE =
            "vehicle";
    private static final String KEY_PLATE =
            "plate";

    public DBHandlerCreds(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CREDENTIALS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_PW + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_VEHICLE + " TEXT,"
                + KEY_PLATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDENTIALS);
        onCreate(db);
    }

    public void addCredentials(Credentials cred) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, cred.getUsername()); //  Name
        values.put(KEY_PW, cred.getPassword()); // Password
        values.put(KEY_NAME, cred.getName()); //  Name
        values.put(KEY_PHONE, cred.getPhone()); // Phone
        values.put(KEY_VEHICLE, cred.getVehicle()); //  Vehicle
        values.put(KEY_PLATE, cred.getPlate()); // Plate

        db.insert(TABLE_CREDENTIALS, null, values);
        db.close(); // Closing database connection
    }

    public List<Credentials> getAllCredentials() {
        List<Credentials> credList = new ArrayList<Credentials>();
        String selectQuery = "SELECT * FROM " + TABLE_CREDENTIALS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Credentials cred = new Credentials();
                cred.setId(Integer.parseInt(cursor.getString(0)));
                cred.setUsername(cursor.getString(1));
                cred.setPassword(cursor.getString(2));
                cred.setName(cursor.getString(3));
                cred.setPhone(cursor.getString(4));
                cred.setVehicle(cursor.getString(5));
                cred.setPlate(cursor.getString(6));

                credList.add(cred);
            } while (cursor.moveToNext());
        }
        return credList;
    }

}
*/