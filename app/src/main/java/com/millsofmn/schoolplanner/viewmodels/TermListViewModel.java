package com.millsofmn.schoolplanner.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.millsofmn.schoolplanner.data.Term;
import com.millsofmn.schoolplanner.data.TermRepository;

import java.util.List;

public class TermListViewModel extends AndroidViewModel {

    private TermRepository repository;

    private LiveData<List<Term>> terms;

    public TermListViewModel(Application application){
        super(application);
        repository = new TermRepository(application);
        terms = repository.getAllTerms();
    }

    public void insert(Term term){
        repository.insert(term);
    }

    public void delete(Term term) {
        repository.delete(term);
    }

    public void update(Term term){
//        repository.update(term);
    }

    public LiveData<List<Term>> getAllTerms(){
        return terms;
    }
}
