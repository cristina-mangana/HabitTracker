package com.example.android.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the subclass of SQLiteOpenHelper and pass the context to access the database
        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        // Insert random data into the database for testing purposes only. Show error message
        // if the insertion was unsuccessful
        long firstRowId = mDbHelper.insertHabit("Running", HabitEntry.TYPE_SPORT, "09-07-2017", 11,
                15, 11, 50, 1, HabitEntry.ACHIEVEMENT_GOOD);

        if (firstRowId == -1) {
            Log.e(LOG_TAG, "Error with saving first habit");
        }

        long secondRowId = mDbHelper.insertHabit("Learning Android", HabitEntry.TYPE_LEARNING,
                "09-07-2017", 19, 10, 21, 30, 1, HabitEntry.ACHIEVEMENT_OUTSTANDING);

        if (secondRowId == -1) {
            Log.e(LOG_TAG, "Error with saving second habit");
        }

        long thirdRowId = mDbHelper.insertHabit("Drink more water", HabitEntry.TYPE_FOOD_HEALTH,
                "09-07-2017", 0, 0, 0, 0, 12, HabitEntry.ACHIEVEMENT_AVERAGE);

        if (thirdRowId == -1) {
            Log.e(LOG_TAG, "Error with saving third habit");
        }

        // Read habits in the database and write to logcat
        Cursor cursor = mDbHelper.readAllHabits();

        try {
            // Create a header
            String header = "The table contains " + cursor.getCount() + " habits.\n" +
                    HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_TYPE + " - " +
                    HabitEntry.COLUMN_HABIT_DATE + " - " +
                    HabitEntry.COLUMN_HABIT_START_HOUR + " - " +
                    HabitEntry.COLUMN_HABIT_START_MINUTE + " - " +
                    HabitEntry.COLUMN_HABIT_END_HOUR + " - " +
                    HabitEntry.COLUMN_HABIT_END_MINUTE + " - " +
                    HabitEntry.COLUMN_HABIT_TIMES + " - " +
                    HabitEntry.COLUMN_HABIT_ACHIEVEMENT + "\n";

            Log.i(LOG_TAG, header);

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int typeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TYPE);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);
            int startHourColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_START_HOUR);
            int startMinuteColumnIndex =
                    cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_START_MINUTE);
            int endHourColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_END_HOUR);
            int endMinuteColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_END_MINUTE);
            int timesColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TIMES);
            int achievementColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_ACHIEVEMENT);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the value of the word at the current row the cursor
                // is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentType = cursor.getInt(typeColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                int currentStartHour = cursor.getInt(startHourColumnIndex);
                int currentStartMinute = cursor.getInt(startMinuteColumnIndex);
                int currentEndHour = cursor.getInt(endHourColumnIndex);
                int currentEndMinute = cursor.getInt(endMinuteColumnIndex);
                int currentTimes = cursor.getInt(timesColumnIndex);
                int currentAchievement = cursor.getInt(achievementColumnIndex);
                // Display the values from each column of the current row in the logcat
                String currentHabit = currentID + " - " +
                        currentName + " - " +
                        currentType + " - " +
                        currentDate + " - " +
                        currentStartHour + " - " +
                        currentStartMinute + " - " +
                        currentEndHour + " - " +
                        currentEndMinute + " - " +
                        currentTimes + " - " +
                        currentAchievement + "\n";
                Log.i(LOG_TAG, currentHabit);
            }
        } finally {
            // Always close the cursor when the reading process is finished
            cursor.close();
        }
    }
}
