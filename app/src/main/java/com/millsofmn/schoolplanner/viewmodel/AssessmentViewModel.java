package com.millsofmn.schoolplanner.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.millsofmn.schoolplanner.db.entity.Assessment;
import com.millsofmn.schoolplanner.db.repository.AssessmentRepository;

import java.util.List;

public class AssessmentViewModel extends AndroidViewModel {

    private final AssessmentRepository repository;
    private LiveData<List<Assessment>> assessments;

    public AssessmentViewModel(Application application) {
        super(application);

        repository = new AssessmentRepository(application);
        assessments = repository.findAll();
    }

    public LiveData<List<Assessment>> getAssessments(){
        return assessments;
    }
    public LiveData<List<Assessment>> findByCourseId(int courseId){
        return repository.findByCourseId(courseId);
    }
}