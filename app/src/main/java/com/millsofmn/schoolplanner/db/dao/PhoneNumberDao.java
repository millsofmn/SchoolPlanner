package com.millsofmn.schoolplanner.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.millsofmn.schoolplanner.db.entity.PhoneNumber;

import java.util.List;

@Dao
public interface PhoneNumberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PhoneNumber... entity);

    @Update
    int update(PhoneNumber... entity);

    @Delete
    int delete(PhoneNumber... entity);

    @Query("SELECT * FROM mentor_phone_number")
    LiveData<List<PhoneNumber>> getAll();

    @Query("SELECT * FROM mentor_phone_number WHERE id = :id")
    PhoneNumber findById(int id);

    @Query("SELECT * FROM mentor_phone_number WHERE mentor_id = :mentorId")
    LiveData<List<PhoneNumber>> findByMentorId(int mentorId);
}
