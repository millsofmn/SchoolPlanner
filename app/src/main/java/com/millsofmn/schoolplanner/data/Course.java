package com.millsofmn.schoolplanner.data;

import android.os.Parcel;
import android.os.Parcelable;

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

    @ColumnInfo(name = "term_id")
    private int termId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "start_date")
    private Date startDate;

    @ColumnInfo(name = "end_date")
    private Date endDate;

    @ColumnInfo(name = "progress_status")
    private String status;

//    private List<String> notes;
//    private List<Assessment> assessments;
//    private List<Mentor> mentors;


    public Course() {
    }

    public Course(int termId, String title, Date startDate, Date endDate, String status) {
        this.termId = termId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Course(int id, int termId, String title, Date startDate, Date endDate, String status) {
        this.id = id;
        this.termId = termId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    protected Course(Parcel in) {
        id = in.readInt();
        termId = in.readInt();
        title = in.readString();
        status = in.readString();
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
}
