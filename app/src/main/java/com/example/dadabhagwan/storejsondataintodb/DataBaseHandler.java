package com.example.dadabhagwan.storejsondataintodb;

/**
 * Created by dadabhagwan on 11/16/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agile on 24-Oct-16.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION =4;


    // Database Name
    public static final String DATABASE_NAME = "actorDatabase";

    // Contacts table name
    public static final String ACTOR_TABLE = "Actor";


    public static final String ACTOR_ID = "_id";
    public static final String ACTOR_NAME = "name";
    public static final String ACTOR_DESC = "desc";
    public static final String ACTOR_DOB = "dob";
    public static final String ACTOR_COUNTRY = "country";
    public static final String ACTOR_HEIGHT = "height";
    public static final String ACTOR_SPOUSE = "spouse";
    public static final String ACTOR_CHILD = "child";
    public static final String ACTOR_IMAGE = "image";
    //ArrayList<Actors> actors=new ArrayList<Actors>();
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + ACTOR_TABLE + "( "
                + ACTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ACTOR_NAME + " TEXT,"
                + ACTOR_DESC + " TEXT," + ACTOR_DOB + " TEXT," + ACTOR_COUNTRY + " TEXT," + ACTOR_HEIGHT + " TEXT," + ACTOR_SPOUSE + " TEXT," + ACTOR_CHILD + " TEXT," + ACTOR_IMAGE + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ACTOR_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void addActor(Actors actors) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ACTOR_NAME, actors.getName());
        values.put(ACTOR_DESC, actors.getDescription());
        values.put(ACTOR_DOB, actors.getDob());
        values.put(ACTOR_COUNTRY, actors.getCountry());
        values.put(ACTOR_HEIGHT,actors.getHeight());
        values.put(ACTOR_SPOUSE, actors.getSpouse());
        values.put(ACTOR_CHILD, actors.getChildren());
        values.put(ACTOR_IMAGE, actors.getImage());

        // Inserting Row
        db.insert(ACTOR_TABLE, null, values);
        db.close(); // Closing database connection
    }


    public List<Actors> getAllContacts() {
    List<Actors> contactList = new ArrayList<Actors>();
//        // Select All Query
       String selectQuery = "SELECT  * FROM " + ACTOR_TABLE;
//
       SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
            do {
                Actors actor = new Actors();
                actor.setName(cursor.getString(1));
                actor.setDescription(cursor.getString(2));
                actor.setDob(cursor.getString(3));
                actor.setCountry(cursor.getString(4));
                actor.setHeight(cursor.getString(5));
                actor.setSpouse(cursor.getString(6));
                actor.setChildren(cursor.getString(7));
                actor.setImage(cursor.getString(8));

                contactList.add(actor);
            } while (cursor.moveToNext());
        }
//
//        // return contact list
        return contactList;
    }

//    public Cursor getAllData()
//    {
//        SQLiteDatabase db1=this.getReadableDatabase();
//        Cursor cursor=db1.query(ACTOR_TABLE,new String[] {ACTOR_NAME,ACTOR_DESC,ACTOR_DOB,ACTOR_COUNTRY,ACTOR_HEIGHT,ACTOR_SPOUSE,ACTOR_CHILD,ACTOR_IMAGE},null,null,null,null,null);
//        if(cursor!=null)
//        {
//            cursor.moveToFirst();
//            return cursor;
//        }
//        else
//        {
//            return null;
//        }
//
//    }

}