package com.millsofmn.schoolplanner.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.millsofmn.schoolplanner.data.SchoolPlannerDatabase;
import com.millsofmn.schoolplanner.data.dao.NoteDao;
import com.millsofmn.schoolplanner.data.domain.Note;

import java.util.List;

public class NoteRepository {
    public static final String TAG = "NoteRepository";

    private LiveData<List<Note>> all;

    private NoteDao dao;

    public NoteRepository(Context context){
        SchoolPlannerDatabase db = SchoolPlannerDatabase.getInstance(context);
        dao = db.noteDao();
        all = dao.getAll();
    }

    public void insert(Note entity) {
        new insertAsyncTask(dao).execute(entity);
    }

    public void delete(Note entity){
        new deleteAsyncTask(dao).execute(entity);
    }

    public void update(Note entity){
        new updateAsyncTask(dao).execute(entity);
    }

    public Note findById(int id){
        return dao.findById(id);
    }

    public LiveData<List<Note>> findAll() {
        return all;
    }

    public LiveData<List<Note>> findByCourseId(int courseId){
        return dao.findByCourseId(courseId);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao asyncTaskDao;

        public insertAsyncTask(NoteDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params){
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao asyncTaskDao;

        public deleteAsyncTask(NoteDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params){
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao asyncTaskDao;

        public updateAsyncTask(NoteDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params){
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
}
