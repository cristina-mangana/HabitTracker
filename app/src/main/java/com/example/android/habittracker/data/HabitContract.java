package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Cristina on 09/07/2017.
 * Contract for the HabitTracker App
 */

public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static final class HabitEntry implements BaseColumns {

        /**
         * Name of the database table for habits
         */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the habit.
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_NAME = "habit_name";

        /**
         * Type of the habit.
         * The only possible values are {@link #TYPE_FOOD_HEALTH}, {@link #TYPE_GOOD_ACTIONS},
         * {@link #TYPE_LEARNING}, {@link #TYPE_REST} or {@link #TYPE_SPORT}.
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_TYPE = "habit_type";

        /**
         * Date when of the habit occurs.
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_DATE = "habit_date";

        /**
         * Hour when the habit starts.
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_START_HOUR = "habit_start_hour";

        /**
         * Minutes when the habit starts.
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_START_MINUTE = "habit_start_minute";

        /**
         * Hour when the habit ends.
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_END_HOUR = "habit_end_hour";

        /**
         * Minutes when the habit ends.
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_END_MINUTE = "habit_end_minute";

        /**
         * Times that the habit has occurred in the habit date.
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_TIMES = "habit_times";

        /**
         * Achievement level of the habit. The only possible values are {@link #ACHIEVEMENT_POOR},
         * {@link #ACHIEVEMENT_AVERAGE}, {@link #ACHIEVEMENT_GOOD} or
         * {@link #ACHIEVEMENT_OUTSTANDING}.
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_ACHIEVEMENT = "habit_achievement";

        /**
         * Possible values for the type of the habit.
         */
        public static final int TYPE_FOOD_HEALTH = 0;
        public static final int TYPE_GOOD_ACTIONS = 1;
        public static final int TYPE_LEARNING = 2;
        public static final int TYPE_REST = 3;
        public static final int TYPE_SPORT = 4;

        /**
         * Possible values for the achievement level of the habit.
         */
        public static final int ACHIEVEMENT_POOR = 0;
        public static final int ACHIEVEMENT_AVERAGE = 1;
        public static final int ACHIEVEMENT_GOOD = 2;
        public static final int ACHIEVEMENT_OUTSTANDING = 3;
    }
}
