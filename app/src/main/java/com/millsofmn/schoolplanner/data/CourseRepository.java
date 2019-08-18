package com.millsofmn.schoolplanner.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CourseRepository {
    public static final String TAG = "CourseRepository";

    private MutableLiveData<List<Course>> searchResult = new MutableLiveData<>();
    private LiveData<List<Course>> allCourses;

    private CourseDao courseDao;

    public CourseRepository(Context context){
        SchoolPlannerDatabase db = SchoolPlannerDatabase.getInstance(context);
        courseDao = db.courseDao();
        allCourses = courseDao.getAll();
    }

    public MutableLiveData<List<Course>> getSearchResult() {
        return searchResult;
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }


    public void insert(Course course) {
        new insertAsyncTask(courseDao).execute(course);
    }

    public void delete(Course course){
        new deleteAsyncTask(courseDao).execute(course);
    }

    public void update(Course course){
        new updateAsyncTask(courseDao).execute(course);
    }

    private static class insertAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao asyncTaskDao;

        public insertAsyncTask(CourseDao courseDao) {
            this.asyncTaskDao = courseDao;
        }

        @Override
        protected Void doInBackground(final Course... params){
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao asyncTaskDao;

        public deleteAsyncTask(CourseDao courseDao) {
            this.asyncTaskDao = courseDao;
        }

        @Override
        protected Void doInBackground(final Course... params){
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao asyncTaskDao;

        public updateAsyncTask(CourseDao courseDao) {
            this.asyncTaskDao = courseDao;
        }

        @Override
        protected Void doInBackground(final Course... params){
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
}
