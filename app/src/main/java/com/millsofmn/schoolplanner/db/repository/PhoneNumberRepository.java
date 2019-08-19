package com.millsofmn.schoolplanner.db.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.millsofmn.schoolplanner.db.SchoolPlannerDatabase;
import com.millsofmn.schoolplanner.db.dao.PhoneNumberDao;
import com.millsofmn.schoolplanner.db.entity.PhoneNumber;

import java.util.List;

public class PhoneNumberRepository {
    public static final String TAG = "PhoneNumberRepository";

    private LiveData<List<PhoneNumber>> all;

    private PhoneNumberDao dao;

    public PhoneNumberRepository(Context context){
        SchoolPlannerDatabase db = SchoolPlannerDatabase.getInstance(context);
        dao = db.phoneNumberDao();
        all = dao.getAll();
    }

    public void insert(PhoneNumber entity) {
        new insertAsyncTask(dao).execute(entity);
    }

    public void delete(PhoneNumber entity){
        new deleteAsyncTask(dao).execute(entity);
    }

    public void update(PhoneNumber entity){
        new updateAsyncTask(dao).execute(entity);
    }

    public PhoneNumber findById(int id){
        return dao.findById(id);
    }

    public LiveData<List<PhoneNumber>> findAll() {
        return all;
    }

    public LiveData<List<PhoneNumber>> findByMentorId(int mentorId){
        return dao.findByMentorId(mentorId);
    }

    private static class insertAsyncTask extends AsyncTask<PhoneNumber, Void, Void> {
        private PhoneNumberDao asyncTaskDao;

        public insertAsyncTask(PhoneNumberDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PhoneNumber... params){
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<PhoneNumber, Void, Void> {
        private PhoneNumberDao asyncTaskDao;

        public deleteAsyncTask(PhoneNumberDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PhoneNumber... params){
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<PhoneNumber, Void, Void> {
        private PhoneNumberDao asyncTaskDao;

        public updateAsyncTask(PhoneNumberDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PhoneNumber... params){
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
}
