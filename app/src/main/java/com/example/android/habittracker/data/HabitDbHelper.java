package com.example.android.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Cristina on 09/07/2017.
 * Database helper for the HabitTracker App. Manages database creation and version management.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    // Database version.
    private static final int DATABASE_VERSION = 1;

    // Name of the database file
    private static final String DATABASE_NAME = "habits.db";

    // SQL statement to create the habits table
    private static final String SQL_CREATE_HABITS_TABLE =
            "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                    HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL," +
                    HabitEntry.COLUMN_HABIT_TYPE + " INTEGER NOT NULL," +
                    HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL," +
                    HabitEntry.COLUMN_HABIT_START_HOUR + " INTEGER," +
                    HabitEntry.COLUMN_HABIT_START_MINUTE + " INTEGER," +
                    HabitEntry.COLUMN_HABIT_END_HOUR + " INTEGER," +
                    HabitEntry.COLUMN_HABIT_END_MINUTE + " INTEGER," +
                    HabitEntry.COLUMN_HABIT_TIMES + " INTEGER," +
                    HabitEntry.COLUMN_HABIT_ACHIEVEMENT + " INTEGER);";

    // SQL statement to delete the habits table
    private static final String SQL_DELETE_HABITS_TABLE =
            "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;

    /**
     * Constructs a new instance of {@link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    /**
     * This method is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Discard the data and start over
        db.execSQL(SQL_DELETE_HABITS_TABLE);
        onCreate(db);
    }

    /**
     * This method inserts data into the database table.
     *
     * @param habitName        is the name of the habit.
     * @param habitType        is the type of the habit.
     * @param habitDate        is the date when the habit takes place. (ex. 23-04-2017)
     * @param habitStartHour   is the initial hour for the habit. (ex. 20)
     * @param habitStartMinute is the initial minute por the habit. (ex. 30)
     * @param habitEndHour     is the hour when the habit ends.
     * @param habitEndMinute   is the minute when the habit ends.
     * @param habitTimes       is the times the habit has occurred in that date. (ex. 5)
     * @param habitAchievement is the habit achievement level.
     * @return the id of the inserted row. If it is -1, then there was an error with insertion.
     */
    public long insertHabit(String habitName, int habitType, String habitDate, int habitStartHour,
                            int habitStartMinute, int habitEndHour, int habitEndMinute,
                            int habitTimes, int habitAchievement) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, habitName);
        values.put(HabitEntry.COLUMN_HABIT_TYPE, habitType);
        values.put(HabitEntry.COLUMN_HABIT_DATE, habitDate);
        values.put(HabitEntry.COLUMN_HABIT_START_HOUR, habitStartHour);
        values.put(HabitEntry.COLUMN_HABIT_START_MINUTE, habitStartMinute);
        values.put(HabitEntry.COLUMN_HABIT_END_HOUR, habitEndHour);
        values.put(HabitEntry.COLUMN_HABIT_END_MINUTE, habitEndMinute);
        values.put(HabitEntry.COLUMN_HABIT_TIMES, habitTimes);
        values.put(HabitEntry.COLUMN_HABIT_ACHIEVEMENT, habitAchievement);

        // Insert the new row, returning the ID of the new row
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        return newRowId;
    }

    /**
     * This method reads all the available date in the database table.
     *
     * @return a Cursor object with the queried data.
     */
    public Cursor readAllHabits() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = getReadableDatabase();

        // Query information from the database
        // Define a projection that specifies which columns from the database you will actually use
        // after this query. In this case, all columns are queried.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_TYPE,
                HabitEntry.COLUMN_HABIT_DATE,
                HabitEntry.COLUMN_HABIT_START_HOUR,
                HabitEntry.COLUMN_HABIT_START_MINUTE,
                HabitEntry.COLUMN_HABIT_END_HOUR,
                HabitEntry.COLUMN_HABIT_END_MINUTE,
                HabitEntry.COLUMN_HABIT_TIMES,
                HabitEntry.COLUMN_HABIT_ACHIEVEMENT
        };

        // Create a {@link Cursor} object to retrieve the data.
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,                  // The table to query
                projection,                             // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );
        return cursor;
    }
}
