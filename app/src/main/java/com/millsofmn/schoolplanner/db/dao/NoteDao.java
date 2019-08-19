package com.millsofmn.schoolplanner.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.millsofmn.schoolplanner.db.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note... entity);

    @Update
    int update(Note... entity);

    @Delete
    int delete(Note... entity);

    @Query("SELECT * FROM course_note")
    LiveData<List<Note>> getAll();

    @Query("SELECT * FROM course_note WHERE id = :id")
    Note findById(int id);

    @Query("SELECT * FROM course_note WHERE course_id = :courseId")
    LiveData<List<Note>> findByCourseId(int courseId);
}
