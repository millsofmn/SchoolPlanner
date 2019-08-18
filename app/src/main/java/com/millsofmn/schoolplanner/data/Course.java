package com.millsofmn.schoolplanner.data;

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
public class Course {
    enum ProgressStatus{
        PLAN_TO_TAKE(1),
        IN_PROGRESS(2),
        COMPLETED(3),
        DROPPED(4);

        private final Integer code;

        ProgressStatus(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

    }

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
    private ProgressStatus status;

//    private List<String> notes;
//    private List<Assessment> assessments;
//    private List<Mentor> mentors;


    public Course() {
    }

    public Course(int termId, String title, Date startDate, Date endDate, ProgressStatus status) {
        this.termId = termId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Course(int id, int termId, String title, Date startDate, Date endDate, ProgressStatus status) {
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

    public ProgressStatus getStatus() {
        return status;
    }

    public void setStatus(ProgressStatus status) {
        this.status = status;
    }
}
