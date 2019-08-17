package com.millsofmn.schoolplanner.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.millsofmn.schoolplanner.data.Term;
import com.millsofmn.schoolplanner.data.TermRepository;

import java.util.List;

public class TermViewModel extends AndroidViewModel {

    private TermRepository repository;

    private LiveData<List<Term>> terms;

    public TermViewModel(Application application){
        super(application);
        repository = new TermRepository(application);
        terms = repository.getAllTerms();
    }

    public LiveData<List<Term>> getAllTerms(){
        return terms;
    }

    public void insert(Term term){
        repository.insert(term);
    }
}
