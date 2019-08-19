package com.millsofmn.schoolplanner.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.millsofmn.schoolplanner.data.domain.Email;

import java.util.List;


@Dao
public interface EmailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Email... entity);

    @Update
    int update(Email... entity);

    @Delete
    int delete(Email... entity);

    @Query("SELECT * FROM mentor_email")
    LiveData<List<Email>> getAll();

    @Query("SELECT * FROM mentor_email WHERE id = :id")
    Email findById(int id);

    @Query("SELECT * FROM mentor_email WHERE mentor_id = :mentorId")
    LiveData<List<Email>> findByMentorId(int mentorId);
}
