package com.millsofmn.schoolplanner.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.millsofmn.schoolplanner.data.Course;
import com.millsofmn.schoolplanner.data.CourseRepository;

import java.util.List;

public class CoursesViewModel extends AndroidViewModel {

    private CourseRepository repository;
    private LiveData<List<Course>> courses;

    public CoursesViewModel(@NonNull Application application) {
        super(application);
        repository = new CourseRepository(application);
        courses = repository.getAllCourses();
    }

    public void insert(Course course){
        repository.insert(course);
    }

    public void update(Course course){
        repository.update(course);
    }

    public void delete(Course course){
        repository.delete(course);
    }

    public LiveData<List<Course>> getCoursesByTermId(int termId){
        return repository.findByTermId(termId);
    }
}
