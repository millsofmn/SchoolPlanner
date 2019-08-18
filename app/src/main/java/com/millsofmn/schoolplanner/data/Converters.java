package com.millsofmn.schoolplanner.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? null : date.getTime();
    }


    @TypeConverter
    public static Course.ProgressStatus getProgressStatus(Integer code){
        for(Course.ProgressStatus ps : Course.ProgressStatus.values()){
            if(ps.getCode() == code){
                return ps;
            }
        }
        return null;
    }

    @TypeConverter
    public static Integer getProgressStatusCode(Course.ProgressStatus progressStatus){
        if(progressStatus != null){
            return progressStatus.getCode();
        }
        return null;
    }
}
