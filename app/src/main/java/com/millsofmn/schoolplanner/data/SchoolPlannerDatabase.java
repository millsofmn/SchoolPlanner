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

@Database(entities = {Term.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SchoolPlannerDatabase extends RoomDatabase {
    public static final String TAG = "SchoolPlannerDatabase";

    public abstract TermDao termDao();

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

        public PopulateDbAsync(SchoolPlannerDatabase database){
            this.termDao = database.termDao();
        }

        @Override
        protected Void doInBackground(final Void... params){
            Log.i(TAG, "initialize database with data");
            termDao.deleteAll();

            for(Term term : TermRepository.getTerms()){
                termDao.insert(term);
            }

            return null;
        }
    }
}
