package com.millsofmn.schoolplanner.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.millsofmn.schoolplanner.db.entity.Term;
import com.millsofmn.schoolplanner.db.repository.TermRepository;

import java.util.List;

public class TermsViewModel extends AndroidViewModel {

    private TermRepository repository;

    private LiveData<List<Term>> terms;

    public TermsViewModel(Application application){
        super(application);
        repository = new TermRepository(application);
        terms = repository.findAll();
    }

    public void insert(Term term){
        repository.insert(term);
    }

    public void delete(Term term) {
        repository.delete(term);
    }

    public void update(Term term){
        repository.update(term);
    }

    public LiveData<List<Term>> getAllTerms(){
        return terms;
    }
}
