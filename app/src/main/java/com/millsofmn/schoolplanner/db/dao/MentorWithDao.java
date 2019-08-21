package com.millsofmn.schoolplanner.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.millsofmn.schoolplanner.db.entity.MentorWithEmbedded;

import java.util.List;

@Dao
public interface MentorWithDao {


    @Query("SELECT * FROM mentor")
    LiveData<List<MentorWithEmbedded>> loadMentorWithEmbedded();
}
