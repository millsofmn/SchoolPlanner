package com.millsofmn.schoolplanner.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.millsofmn.schoolplanner.data.SchoolPlannerDatabase;
import com.millsofmn.schoolplanner.data.dao.MentorDao;
import com.millsofmn.schoolplanner.data.domain.Mentor;

import java.util.List;

public class MentorRepository {
    public static final String TAG = "MentorRepository";

    private LiveData<List<Mentor>> all;

    private MentorDao dao;

    public MentorRepository(Context context){
        SchoolPlannerDatabase db = SchoolPlannerDatabase.getInstance(context);
        dao = db.mentorDao();
        all = dao.getAll();
    }

    public void insert(Mentor entity) {
        new insertAsyncTask(dao).execute(entity);
    }

    public void delete(Mentor entity){
        new deleteAsyncTask(dao).execute(entity);
    }

    public void update(Mentor entity){
        new updateAsyncTask(dao).execute(entity);
    }

    public Mentor findById(int id){
        return dao.findById(id);
    }

    public LiveData<List<Mentor>> findAll() {
        return all;
    }

    private static class insertAsyncTask extends AsyncTask<Mentor, Void, Void> {
        private MentorDao asyncTaskDao;

        public insertAsyncTask(MentorDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Mentor... params){
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Mentor, Void, Void> {
        private MentorDao asyncTaskDao;

        public deleteAsyncTask(MentorDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Mentor... params){
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Mentor, Void, Void> {
        private MentorDao asyncTaskDao;

        public updateAsyncTask(MentorDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Mentor... params){
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
}
