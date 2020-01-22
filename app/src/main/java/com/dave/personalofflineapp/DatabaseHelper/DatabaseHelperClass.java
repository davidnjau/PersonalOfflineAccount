package com.dave.personalofflineapp.DatabaseHelper;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dave.personalofflineapp.Pojo.PersonalDetails;

import java.util.ArrayList;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "person_details";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_REF_NO = "reference_number";

    private static final String TABLE_REF_IMAGES = "images";

    private static final String KEY_ID = "id";

    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_PHONE_NUMBER = "phone_number";

    private static final String KEY_PHOTO = "photo";

    ContentResolver mContentResolver;

    private static final String CREATE_TABLE_TABLE_REF_NO = "CREATE TABLE "
            + TABLE_REF_NO + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_FIRST_NAME + " TEXT, "
            + KEY_LAST_NAME + " TEXT, "
            + KEY_PHONE_NUMBER + " TEXT );";

    private static final String CREATE_TABLE_REF_IMAGES = "CREATE TABLE "
            + TABLE_REF_IMAGES + "(" + KEY_ID + " TEXT,"
            + KEY_PHOTO + " BLOB );";

    public DatabaseHelperClass(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mContentResolver = context.getContentResolver();
        Log.d("TABLE", CREATE_TABLE_REF_IMAGES);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_TABLE_REF_NO);
        sqLiteDatabase.execSQL(CREATE_TABLE_REF_IMAGES);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS '" + TABLE_REF_NO + "'");
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS '" + TABLE_REF_IMAGES + "'");

        onCreate(sqLiteDatabase);

    }

    public void AddPersonalinfo(String fName, String lName, String pNumber, byte[] image){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesRef = new ContentValues();
        ContentValues valuesRefImages = new ContentValues();
        valuesRef.put(KEY_FIRST_NAME, fName);
        valuesRef.put(KEY_LAST_NAME, lName);
        valuesRef.put(KEY_PHONE_NUMBER, pNumber);

        System.out.println("-*-* "+ "Done");

//        db.insert(TABLE_REF_NO, null, valuesRef);

        long id = db.insertWithOnConflict(TABLE_REF_NO,null, valuesRef,SQLiteDatabase.CONFLICT_IGNORE );


        valuesRefImages.put(KEY_ID, id);
        valuesRefImages.put(KEY_PHOTO, image);

        db.insert(TABLE_REF_IMAGES, null, valuesRefImages);
    }


    public ArrayList<PersonalDetails> getPersonalDetails() {

        ArrayList<PersonalDetails> addedInformationArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_REF_NO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                PersonalDetails consentCompleteTasks = new PersonalDetails();

                consentCompleteTasks.setmFirstName(c.getString(c.getColumnIndex(KEY_FIRST_NAME)));
                consentCompleteTasks.setmLastName(c.getString(c.getColumnIndex(KEY_LAST_NAME)));
                consentCompleteTasks.setmPhone(c.getString(c.getColumnIndex(KEY_PHONE_NUMBER)));

                consentCompleteTasks.setmImage(c.getBlob(1));

                addedInformationArrayList.add(consentCompleteTasks);

            } while (c.moveToNext());


        }

        return addedInformationArrayList;

    }


}
