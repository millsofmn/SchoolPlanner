package com.millsofmn.schoolplanner.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TermDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Term term);

    @Update
    void update(Term term);

    @Delete
    int delete(Term... terms);

    @Query("DELETE FROM term")
    void deleteAll();

    @Query("SELECT * FROM term ORDER BY title ASC")
    LiveData<List<Term>> getAllTerms();
}
