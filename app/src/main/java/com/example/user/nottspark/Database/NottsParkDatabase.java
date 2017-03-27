package com.example.user.nottspark.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

import com.example.user.nottspark.Model.Car;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.Transaction;
import com.example.user.nottspark.Model.User;

import java.util.ArrayList;
import java.util.List;

public class NottsParkDatabase extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = NottsParkDatabase.class.getName();
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NottsPark";
    // Table Names
    private static final String TABLE_CAR = "CarController";
    private static final String TABLE_LEAVER = "Leaver";
    private static final String TABLE_TRANSACTION = "Transaction";
    private static final String TABLE_USER = "User";
    //TABLE CAR
    private static final String KEY_CAR_ID = "car_id";
    private static final String KEY_CAR_MAKE = "car_make";
    private static final String KEY_CAR_MODEL = "car_model";
    private static final String KEY_CAR_PLATE = "car_plate";
    // TABLE LEAVER
    private static final String KEY_L_ID = "leaver_id";
    private static final String KEY_L_USER_ID = "leaver_user_id";
    private static final String KEY_L_LOCATION = "leaver_location";
    private static final String KEY_L_DESC = "leaver_desc";
    private static final String KEY_L_PARINGSTATUS = "leaver_paring_status";
    private static final String KEY_L_NOWOFAFTER10 = "leaver_now_or_after10";
    private static final String KEY_L_DATE = "leaver_date";
    private static final String KEY_L_TIME = "leaver_time";
    //TABLE TRANSACTION
    private static final String KEY_TRANSID = "transID";
    private static final String KEY_PARKERID = "parkerID";
    private static final String KEY_LEAVERID = "leaverID";
    private static final String KEY_EXCHANGESTATUS = "exchange_status";
    private static final String KEY_EXCHANGETIME = "exchange_time";
    // TABLE USER
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_USERNAME = "user_username";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_CONTACTNUM = "user_contact_num";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_CAR = "user_car";
    private static final String KEY_REGISTERDATE = "user_reg_date";
    private static final String KEY_USER_ACCOUNTTYPE = "user_acctype";
    private static final String KEY_USER_PASSWORD = "user_password";
    // CREATE TABLE CAR
    String CREATE_CAR_TABLE = "CREATE TABLE " + TABLE_CAR + "("
            + KEY_CAR_ID + " INTEGER PRIMARY KEY,"
            + KEY_CAR_MAKE + " TEXT,"
            + KEY_CAR_MODEL + " TEXT,"
            + KEY_CAR_PLATE + " TEXT"
            + ")";
    // CREATE TABLE LEAVER- need to be updated
    String CREATE_LEAVER_TABLE = "CREATE TABLE " + TABLE_LEAVER + "("
            + KEY_L_ID + " INTEGER PRIMARY KEY,"
            + KEY_L_USER_ID + " INTEGER,"
            + KEY_L_LOCATION + " TEXT,"
            + KEY_L_DESC + " TEXT,"
            + KEY_L_PARINGSTATUS + " INTEGER,"
            + KEY_L_NOWOFAFTER10 + " INTEGER,"
            + KEY_L_DATE + " TEXT,"
            + KEY_L_TIME + " TEXT,"
            + " FOREIGN KEY (" + KEY_L_USER_ID + ") REFERENCES " + TABLE_USER + "(" + KEY_USER_ID + ")"
            + ")";
    // CREATE TABLE TRANSACTION
    String CREATE_TRANSACTION_TABLE = "CREATE TABLE " + TABLE_TRANSACTION + "("
            + KEY_TRANSID + " INTEGER PRIMARY KEY,"
            + KEY_PARKERID + " INTEGER,"
            + KEY_LEAVERID + " INTEGER,"
            + KEY_EXCHANGESTATUS + " TEXT,"
            + KEY_EXCHANGETIME + " TEXT,"
            + " FOREIGN KEY (" + KEY_PARKERID + ") REFERENCES " + TABLE_USER + "(" + KEY_USER_ID + "),"
            + " FOREIGN KEY (" + KEY_LEAVERID + ") REFERENCES " + TABLE_LEAVER + "(" + KEY_L_ID + ")"
            + ")";
    // CREATE TABLE USER
    String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + KEY_USER_ID + " INTEGER PRIMARY KEY,"
            + KEY_USER_USERNAME + " TEXT,"
            + KEY_USER_NAME + " TEXT,"
            + KEY_USER_CONTACTNUM + " TEXT,"
            + KEY_USER_EMAIL + " TEXT,"
            + KEY_USER_EMAIL + " TEXT,"
            + KEY_CAR + " INTEGER,"
            + KEY_REGISTERDATE + " TEXT,"
            + KEY_USER_ACCOUNTTYPE + " TEXT,"
            + KEY_USER_PASSWORD + " TEXT,"
            + " FOREIGN KEY (" + KEY_CAR + ") REFERENCES " + TABLE_CAR + "(" + KEY_CAR_ID + ")"
            + ")";

    // For Fragments
    public NottsParkDatabase(FragmentActivity activity, Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // For Activity
    public NottsParkDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Table Create Statements
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CAR_TABLE);
        db.execSQL(CREATE_LEAVER_TABLE);
        db.execSQL(CREATE_TRANSACTION_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_CAR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_LEAVER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TRANSACTION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_USER_TABLE);
        onCreate(db);
    }

    /**
     * Creating a car
     */
    public void addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_CAR_ID, car.getCarID()); << don't put ID, it is auto-incremented
        values.put(KEY_CAR_MAKE, car.getCarMake());
        values.put(KEY_CAR_MODEL, car.getCarModel());
        values.put(KEY_CAR_PLATE, car.getCarPlate());

        db.insert(TABLE_CAR, null, values);
        db.close();
    }

    /**
     * Creating a leaver
     */
    public void addLeaver(Leaver leaver) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_L_ID, leaver.getLeaverID()); << don't put ID, it is auto-incremented
        values.put(KEY_L_USER_ID, leaver.getUserID().getUserID());
        values.put(KEY_L_LOCATION, leaver.getLocation());
        values.put(KEY_L_DESC, leaver.getLeaverDesc());
        values.put(KEY_L_PARINGSTATUS, leaver.isPairingStatus() ? 1 : 0);
        values.put(KEY_L_NOWOFAFTER10, leaver.isNowOrAfter10Min() ? 1 : 0);
        values.put(KEY_L_DATE, leaver.getLeavingDate());
        values.put(KEY_L_TIME, leaver.getLeavingTime());

        db.insert(TABLE_LEAVER, null, values);
        db.close();
    }

    /**
     * Creating a transaction
     */
    public void addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_TRANSID, transaction.getTransID()); << don't put ID, it is auto-incremented
        values.put(KEY_PARKERID, transaction.getParkerID().getUserID());
        values.put(KEY_LEAVERID, transaction.getLeaverID().getLeaverID());
        values.put(KEY_EXCHANGESTATUS, transaction.getExchangeStatus());
        values.put(KEY_EXCHANGETIME, transaction.getExchangeTime());

        db.insert(TABLE_TRANSACTION, null, values);
        db.close();
    }

    /**
     * Creating a user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_USER_ID, user.getUserID()); << don't put ID, it is auto-incremented
        values.put(KEY_USER_USERNAME, user.getUserUsername());
        values.put(KEY_USER_NAME, user.getUserName());
        values.put(KEY_USER_CONTACTNUM, user.getUserContactNum());
        values.put(KEY_USER_EMAIL, user.getUserEmail());
        values.put(KEY_CAR, user.getCar().getCarID());  // you might get an error here coz you can only 'values.put' a String
        values.put(KEY_REGISTERDATE, user.getRegisterDate());
        values.put(KEY_USER_ACCOUNTTYPE, user.getUserAccountType());
        values.put(KEY_USER_PASSWORD, user.getUserPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * get single car
     */
    public Car getCar(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_CAR + " WHERE " + KEY_CAR_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        Car car = new Car(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return car;
    }

    /**
     * get single leaver
     */
    public Leaver getLeaver(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_LEAVER + " WHERE " + KEY_L_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        User userGetLeaver = getUser(Integer.parseInt(cursor.getString(1)));
        Leaver leaver = new Leaver(Integer.parseInt(cursor.getString(0)), userGetLeaver, cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)) == 1,
                Integer.parseInt(cursor.getString(5)) == 1, cursor.getString(6), cursor.getString(7));
        return leaver;
    }

    /**
     * get single transaction
     */
    public Transaction getTransaction(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTION + " WHERE " + KEY_TRANSID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        User userGetTransaction = getUser(Integer.parseInt(cursor.getString(1)));
        Leaver leaverGetTransaction = getLeaver(Integer.parseInt(cursor.getString(2)));
        Transaction transaction = new Transaction(Integer.parseInt(cursor.getString(0)), userGetTransaction, leaverGetTransaction, cursor.getString(3), cursor.getString(4));
        return transaction;
    }

    /**
     * get single user
     */
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE " + KEY_USER_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        Car carGetUser = getCar(Integer.parseInt(cursor.getString(5)));
        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), carGetUser, cursor.getString(6), cursor.getString(7), cursor.getString(8));
        return user;
    }

    /**
     * getting all car
     */
    public List<Car> getAllCar() {
        List<Car> carList = new ArrayList<Car>();
        String selectQuery = "SELECT * FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                carList.add(getCar(Integer.parseInt(cursor.getString(0))));
            } while (cursor.moveToNext());
        }
        return carList;
    }

    /**
     * getting all leaver
     */
    public List<Leaver> getAllLeaver() {
        List<Leaver> leaverList = new ArrayList<Leaver>();
        String selectQuery = "SELECT * FROM " + TABLE_LEAVER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                leaverList.add(getLeaver(Integer.parseInt(cursor.getString(0))));
            } while (cursor.moveToNext());
        }
        return leaverList;
    }

    /**
     * getting all transaction
     */
    public List<Transaction> getAllTransaction() {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                transactionList.add(getTransaction(Integer.parseInt(cursor.getString(0))));
            } while (cursor.moveToNext());
        }
        return transactionList;
    }

    /**
     * getting all user
     */
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                userList.add(getUser(Integer.parseInt(cursor.getString(0))));
            } while (cursor.moveToNext());
        }
        return userList;
    }


    /**
     * getting all cars under single tag
     */
    public List<Car> getAllCarsByID(int id) {
        List<Car> cars = new ArrayList<Car>();
        String selectQuery = "SELECT * FROM " + TABLE_CAR + " WHERE " + KEY_CAR_ID + " = '" + id + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Car car = new Car(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                cars.add(car);
            } while (cursor.moveToNext());
        }
        return cars;
    }

    /**
     * getting all leavers under single tag
     */
    public List<Leaver> getAllLeaversByID(int id) {
        List<Leaver> leavers = new ArrayList<Leaver>();
        String selectQuery = "SELECT * FROM " + TABLE_LEAVER + " WHERE " + KEY_L_ID + " = '" + id + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User userGetLeaver = getUser(Integer.parseInt(cursor.getString(1)));

                Leaver leaver = new Leaver(Integer.parseInt(cursor.getString(0)), userGetLeaver, cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)) == 1,
                        Integer.parseInt(cursor.getString(5)) == 1, cursor.getString(6), cursor.getString(7));
                leavers.add(leaver);
            } while (cursor.moveToNext());
        }
        return leavers;
    }

    /**
     * getting all transactions under single tag
     */
    public List<Transaction> getAllTransactionsByID(int id) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION + " WHERE " + KEY_TRANSID + " = '" + id + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User userGetTransaction = getUser(Integer.parseInt(cursor.getString(1)));
                Leaver leaverGetTransaction = getLeaver(Integer.parseInt(cursor.getString(2)));
                Transaction transaction = new Transaction(Integer.parseInt(cursor.getString(0)), userGetTransaction, leaverGetTransaction, cursor.getString(3), cursor.getString(4));
                transactions.add(transaction);
            } while (cursor.moveToNext());
        }
        return transactions;
    }

    /**
     * getting all users under single tag
     */
    public List<User> getAllUsersByID(int id) {
        List<User> users = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USER_ID + " = '" + id + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Car carGetUser = getCar(Integer.parseInt(cursor.getString(5)));
                User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), carGetUser, cursor.getString(6), cursor.getString(7), cursor.getString(8));
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }

    /**
     * getting count
     */
    public int getCount(String tablename) {
        String countQuery = "SELECT  * FROM " + tablename;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a car
     */
    public int updateCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CAR_ID, car.getCarID());
        values.put(KEY_CAR_MAKE, car.getCarMake());
        values.put(KEY_CAR_MODEL, car.getCarModel());
        values.put(KEY_CAR_PLATE, car.getCarPlate());

        // updating row
        return db.update(TABLE_CAR, values, KEY_CAR_ID + " = ?",
                new String[]{String.valueOf(car.getCarID())});
    }

    /**
     * Updating a leaver
     */
    public int updateLeaver(Leaver leaver) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_L_ID, leaver.getLeaverID());
        values.put(KEY_L_USER_ID, leaver.getUserID().getUserID());
        values.put(KEY_L_LOCATION, leaver.getLocation());
        values.put(KEY_L_DESC, leaver.getLeaverDesc());
        values.put(KEY_L_PARINGSTATUS, leaver.isPairingStatus() ? 1 : 0);
        values.put(KEY_L_NOWOFAFTER10, leaver.isNowOrAfter10Min() ? 1 : 0);
        values.put(KEY_L_DATE, leaver.getLeavingDate());
        values.put(KEY_L_TIME, leaver.getLeavingTime());

        // updating row
        return db.update(TABLE_LEAVER, values, KEY_L_ID + " = ?",
                new String[]{String.valueOf(leaver.getLeaverID())});
    }

    /**
     * Updating a transaction
     */
    public int updateTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TRANSID, transaction.getTransID());
        values.put(KEY_PARKERID, transaction.getParkerID().getUserID());
        values.put(KEY_LEAVERID, transaction.getLeaverID().getLeaverID());
        values.put(KEY_EXCHANGESTATUS, transaction.getExchangeStatus());
        values.put(KEY_EXCHANGETIME, transaction.getExchangeTime());

        // updating row
        return db.update(TABLE_TRANSACTION, values, KEY_TRANSID + " = ?",
                new String[]{String.valueOf(transaction.getTransID())});
    }

    /**
     * Updating a user
     */
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getUserID());
        values.put(KEY_USER_USERNAME, user.getUserUsername());
        values.put(KEY_USER_NAME, user.getUserName());
        values.put(KEY_USER_CONTACTNUM, user.getUserContactNum());
        values.put(KEY_USER_EMAIL, user.getUserEmail());
        values.put(KEY_CAR, user.getCar().getCarID());
        values.put(KEY_REGISTERDATE, user.getRegisterDate());
        values.put(KEY_USER_ACCOUNTTYPE, user.getUserAccountType());
        values.put(KEY_USER_PASSWORD, user.getUserPassword());

        // updating row
        return db.update(TABLE_USER, values, KEY_USER_ID + " = ?",
                new String[]{String.valueOf(user.getUserID())});
    }

    /**
     * Deleting a CarController
     */
    public void deleteCar(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAR, KEY_CAR_ID + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * Deleting a Leaver
     */
    public void deleteLeaver(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LEAVER, KEY_L_ID + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * Deleting a Transaction
     */
    public void deleteTransaction(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSACTION, KEY_TRANSID + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * Deleting a User
     */
    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_USER_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAR, null, null);
        db.delete(TABLE_LEAVER, null, null);
        db.delete(TABLE_TRANSACTION, null, null);
        db.delete(TABLE_USER, null, null);
    }
}