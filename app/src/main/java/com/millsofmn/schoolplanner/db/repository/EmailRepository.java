package com.millsofmn.schoolplanner.db.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.millsofmn.schoolplanner.db.SchoolPlannerDatabase;
import com.millsofmn.schoolplanner.db.dao.EmailDao;
import com.millsofmn.schoolplanner.db.entity.Email;

import java.util.List;

public class EmailRepository {
    public static final String TAG = "EmailRepository";

    private LiveData<List<Email>> all;

    private EmailDao dao;

    public EmailRepository(Context context){
        SchoolPlannerDatabase db = SchoolPlannerDatabase.getInstance(context);
        dao = db.emailDao();
        all = dao.getAll();
    }

    public void insert(Email entity) {
        new insertAsyncTask(dao).execute(entity);
    }

    public void delete(Email entity){
        new deleteAsyncTask(dao).execute(entity);
    }

    public void update(Email entity){
        new updateAsyncTask(dao).execute(entity);
    }

    public Email findById(int id){
        return dao.findById(id);
    }

    public LiveData<List<Email>> findAll() {
        return all;
    }

    public LiveData<List<Email>> findByTermId(int mentorId){
        return dao.findByMentorId(mentorId);
    }

    private static class insertAsyncTask extends AsyncTask<Email, Void, Void> {
        private EmailDao asyncTaskDao;

        public insertAsyncTask(EmailDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Email... params){
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Email, Void, Void> {
        private EmailDao asyncTaskDao;

        public deleteAsyncTask(EmailDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Email... params){
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Email, Void, Void> {
        private EmailDao asyncTaskDao;

        public updateAsyncTask(EmailDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Email... params){
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
}
