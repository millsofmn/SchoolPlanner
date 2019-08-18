package com.millsofmn.schoolplanner.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Term.class, Course.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SchoolPlannerDatabase extends RoomDatabase {
    public static final String TAG = "SchoolPlannerDatabase";

    public abstract CourseDao courseDao();
    public abstract TermDao termDao();

//    public abstract AssessmentDao assessmentDao();

    private static volatile  SchoolPlannerDatabase INSTANCE;

    public static SchoolPlannerDatabase getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (SchoolPlannerDatabase.class) {
                if(INSTANCE == null){
                    INSTANCE = Room
                            .databaseBuilder(
                                    context.getApplicationContext(),
                                    SchoolPlannerDatabase.class,
                                    "school_planner_database")
                            .addCallback(schoolPlannerDatabaseCallback) // todo remove
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static SchoolPlannerDatabase.Callback schoolPlannerDatabaseCallback = new SchoolPlannerDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase database){
            super.onOpen(database);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final TermDao termDao;
        private final CourseDao courseDao;

        public PopulateDbAsync(SchoolPlannerDatabase database){
            termDao = database.termDao();
            courseDao = database.courseDao();
        }

        @Override
        protected Void doInBackground(final Void... params){
            Log.i(TAG, "initialize database with data");
            termDao.deleteAll();

            for(Term term : DatabaseSeed.getTerms()){
                termDao.insert(term);
            }

            for(Course course : DatabaseSeed.getCourses()){
                courseDao.insert(course);
            }

            return null;
        }
    }
}
