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
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course... courses);

    @Update
    int update(Course... courses);

    @Delete
    int delete(Course... courses);

    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAll();

    @Query("SELECT * FROM course WHERE id = :id")
    Course findById(int id);

    @Query("SELECT * FROM course WHERE term_id = :termId")
    LiveData<List<Course>> findByTermId(int termId);
}
