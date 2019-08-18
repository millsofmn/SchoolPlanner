package com.millsofmn.schoolplanner.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TermRepository {
    public static final String TAG = "TermRepository";
    private TermDao termDao;
    private LiveData<List<Term>> terms;

    public TermRepository(Context context) {
        SchoolPlannerDatabase db = SchoolPlannerDatabase.getInstance(context);
        this.termDao = db.termDao();
        terms = this.termDao.getAllTerms();
    }

    public LiveData<List<Term>> getAllTerms(){
        return terms;
    }

    public void insert(Term term) {
        new insertAsyncTask(termDao).execute(term);
    }

    public void delete(Term term){
        new deleteAsyncTask(termDao).execute(term);
    }

    public void update(Term term){
        new updateAsyncTask(termDao).execute(term);
    }


    private static class insertAsyncTask extends AsyncTask<Term, Void, Void> {
        private TermDao asyncTaskDao;

        public insertAsyncTask(TermDao termDao) {
            this.asyncTaskDao = termDao;
        }

        @Override
        protected Void doInBackground(final Term... params){
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Term, Void, Void> {
        private TermDao asyncTaskDao;

        public deleteAsyncTask(TermDao termDao) {
            this.asyncTaskDao = termDao;
        }

        @Override
        protected Void doInBackground(final Term... params){
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Term, Void, Void> {
        private TermDao asyncTaskDao;

        public updateAsyncTask(TermDao termDao) {
            this.asyncTaskDao = termDao;
        }

        @Override
        protected Void doInBackground(final Term... params){
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
}
