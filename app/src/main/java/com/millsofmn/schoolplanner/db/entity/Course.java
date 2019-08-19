package com.millsofmn.schoolplanner.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "course",
        foreignKeys = @ForeignKey(
                entity = Term.class,
                parentColumns = "id",
                childColumns = "term_id",
                onDelete = ForeignKey.CASCADE))
public class Course implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "term_id")
    private int termId;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "alert_start")
    private boolean alertOnStartDate;

    @ColumnInfo(name = "start_date")
    private Date startDate;

    @ColumnInfo(name = "alert_end")
    private boolean alertOnEndDate;

    @ColumnInfo(name = "end_date")
    private Date endDate;

    public Course() {
    }

    public Course(int termId, @NonNull String title, @NonNull String status, boolean alertOnStartDate, Date startDate, boolean alertOnEndDate, Date endDate) {
        this.termId = termId;
        this.title = title;
        this.status = status;
        this.alertOnStartDate = alertOnStartDate;
        this.startDate = startDate;
        this.alertOnEndDate = alertOnEndDate;
        this.endDate = endDate;
    }

    public Course(int id, int termId, @NonNull String title, @NonNull String status, boolean alertOnStartDate, Date startDate, boolean alertOnEndDate, Date endDate) {
        this.id = id;
        this.termId = termId;
        this.title = title;
        this.status = status;
        this.alertOnStartDate = alertOnStartDate;
        this.startDate = startDate;
        this.alertOnEndDate = alertOnEndDate;
        this.endDate = endDate;
    }

    protected Course(Parcel in) {
        id = in.readInt();
        termId = in.readInt();
        title = in.readString();
        status = in.readString();
        alertOnStartDate = in.readByte() != 0;
        alertOnEndDate = in.readByte() != 0;
        if (in.readByte() == 0) {
            startDate = null;
        } else {
            startDate = new Date(in.readLong());
        }
        if (in.readByte() == 0) {
            endDate = null;
        } else {
            endDate = new Date(in.readLong());
        }
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(termId);
        parcel.writeString(title);
        parcel.writeString(status);
        parcel.writeByte((byte) (alertOnStartDate ? 1 : 0));
        parcel.writeByte((byte) (alertOnEndDate ? 1 : 0));
        if (startDate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(startDate.getTime());
        }
        if (endDate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(endDate.getTime());
        }
    }

    public Course id(int id) {
        this.id = id;
        return this;
    }

    public Course termId(int termId) {
        this.termId = termId;
        return this;
    }

    public Course title(@NonNull String title) {
        this.title = title;
        return this;
    }

    public Course status(@NonNull String status) {
        this.status = status;
        return this;
    }

    public Course alertOnStartDate(boolean alertOnStartDate) {
        this.alertOnStartDate = alertOnStartDate;
        return this;
    }

    public Course startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Course alertOnEndDate(boolean alertOnEndDate) {
        this.alertOnEndDate = alertOnEndDate;
        return this;
    }

    public Course endDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }

    public boolean isAlertOnStartDate() {
        return alertOnStartDate;
    }

    public void setAlertOnStartDate(boolean alertOnStartDate) {
        this.alertOnStartDate = alertOnStartDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isAlertOnEndDate() {
        return alertOnEndDate;
    }

    public void setAlertOnEndDate(boolean alertOnEndDate) {
        this.alertOnEndDate = alertOnEndDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
