//package com.example.user.nottspark.Database.Archives;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.support.v4.app.FragmentActivity;
//
//import com.example.user.nottspark.Model.Leaver;
//import com.example.user.nottspark.Model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class NottsParkDatabase extends SQLiteOpenHelper {
//
//    // DATABASE AND TABLE DECLARATION //////////////////////////////////////////////////////////////////
//    // DATABASE NOTTSPARK
//    private static final int DATABASE_VERSION = 1;
//    private static final String DATABASE_NAME =
//            "NottsPark";
//
//    // TABLE LEAVER
//    private static final String TABLE_LEAVER =
//            "Leaver";
//    private static final String KEY_L_ID =
//            "leaver_id";
//    private static final String KEY_L_TIME =
//            "leaver_time";
//    private static final String KEY_L_USER_ID =
//            "leaver_user_id";
//    private static final String KEY_L_LOCATION =
//            "leaver_location";
//    private static final String KEY_L_STATUS =
//            "leaver_status";
//
//    // TABLE USER
//    private static final String TABLE_USER =
//            "User";
//    private static final String KEY_USER_ID =
//            "user_id";
//    private static final String KEY_USER_USERNAME =
//            "user_username";
//    private static final String KEY_USER_NAME =
//            "user_name";
//    private static final String KEY_USER_CONTACTNUM =
//            "user_contactNum";
//    private static final String KEY_USER_EMAIL =
//            "user_email";
//    private static final String KEY_USER_ACCOUNTTYPE =
//            "user_accType";
//    private static final String KEY_USER_PASSWORD =
//            "user_password";
//
//    // END /////////////////////////////////////////////////////////////////////////////////////////////
//
//    // For Fragments
//    public NottsParkDatabase(FragmentActivity activity, Object o, Context context, int i) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    // For Activity
//    public NottsParkDatabase(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    // Create Database with all the Tables /////////////////////////////////////////////////////////////
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        // CREATE TABLE LEAVER
//        String CREATE_LEAVER_TABLE = "CREATE TABLE " + TABLE_LEAVER + "("
//                + KEY_L_ID + " INTEGER PRIMARY KEY," + KEY_L_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
//                + KEY_L_USER_ID + " TEXT,"
//                + KEY_L_LOCATION + " TEXT,"
//                + KEY_L_STATUS + " TEXT,"
//                + " FOREIGN KEY (" + KEY_L_USER_ID + ") REFERENCES " + TABLE_USER + "(" + KEY_USER_ID + ")"
//                + ")";
//        db.execSQL(CREATE_LEAVER_TABLE);
//
//        // CREATE TABLE USER
//        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
//                + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_USER_USERNAME + " TEXT,"
//                + KEY_USER_NAME + " TEXT,"
//                + KEY_USER_CONTACTNUM + " TEXT,"
//                + KEY_USER_EMAIL + " TEXT,"
//                + KEY_USER_ACCOUNTTYPE + " TEXT,"
//                + KEY_USER_PASSWORD + " TEXT"
//                + ")";
//        db.execSQL(CREATE_USER_TABLE);
//    }
//    // END /////////////////////////////////////////////////////////////////////////////////////////////
//
//    // Delete Tables and Re-create Database with all the Tables
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAVER);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//        onCreate(db);
//    }
//
//    // USER ////////////////////////////////////////////////////////////////////////////////////////////
//    // Register a new user
//    public void addUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        //values.put(KEY_USER_ID, user.getUserID()); // User ID
//        values.put(KEY_USER_USERNAME, user.getUserUsername()); // Username
//        values.put(KEY_USER_NAME, user.getUserName()); // User's Name
//        values.put(KEY_USER_CONTACTNUM, user.getUserContactNum()); // User Contact Number
//        values.put(KEY_USER_EMAIL, user.getUserEmail()); // User Email
//        values.put(KEY_USER_ACCOUNTTYPE, user.getUserAccountType()); // User Account Type
//        values.put(KEY_USER_PASSWORD, user.getUserPassword()); // User Password
//
//        db.insert(TABLE_USER, null, values);
//        db.close();
//    }
//
//    // Get a Single User
//    public User getUser(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_USER, new String[]{KEY_USER_ID,
//                        KEY_USER_USERNAME, KEY_USER_NAME, KEY_USER_CONTACTNUM, KEY_USER_EMAIL, KEY_USER_ACCOUNTTYPE,
//                        KEY_USER_PASSWORD}, KEY_USER_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
////        User user = new User(Integer.parseInt(cursor.getString(0)),
////                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
////                cursor.getString(5), cursor.getString(6));
//        User user = null;//Vivian doing testing please delete this
//        return user;
//    }
//
//    // Getting All Users
//    public List<User> getAllUser() {
//        List<User> userList = new ArrayList<User>();
//        String selectQuery = "SELECT * FROM " + TABLE_USER;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                User user = new User();
//                user.setUserID(Integer.parseInt(cursor.getString(0)));
//                user.setUserUsername(cursor.getString(1));
//                user.setUserName(cursor.getString(2));
//                user.setUserContactNum(cursor.getString(3));
//                user.setUserEmail(cursor.getString(4));
//                user.setUserAccountType(cursor.getString(5));
//                user.setUserPassword(cursor.getString(6));
//
//                userList.add(user);
//            } while (cursor.moveToNext());
//        }
//        return userList;
//    }
//
//    // END /////////////////////////////////////////////////////////////////////////////////////////////
//
//    // LEAVER //////////////////////////////////////////////////////////////////////////////////////////
//    // Add a Leaver
//    public void addLeaver(Leaver leaver) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
////        ContentValues values = new ContentValues();
////        //values.put(KEY_L_TIME, leaver.getLeavingTime()); //  Time
////        values.put(KEY_L_USER_ID, leaver.getUserID()); // User ID
////        values.put(KEY_L_LOCATION, leaver.getLocation()); //  Location
////        values.put(KEY_L_STATUS, leaver.getStatus()); // Status
//
////        db.insert(TABLE_LEAVER, null, values);
//        //vivian doing testing please uncomment
//        db.close(); // Closing database connection
//    }
//
//    // END /////////////////////////////////////////////////////////////////////////////////////////////
//
//    public void deleteAll() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_USER, null, null);
//        db.delete(TABLE_LEAVER, null, null);
//    }
//}
