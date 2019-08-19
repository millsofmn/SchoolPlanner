package com.millsofmn.schoolplanner.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "mentor_email",
        foreignKeys = @ForeignKey(
                entity = Mentor.class,
                parentColumns = "id",
                childColumns = "mentor_id",
                onDelete = ForeignKey.CASCADE))
public class Email implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "mentor_id")
    private int mentorId;

    @NonNull
    @ColumnInfo(name = "email_address")
    private String emailAddress;

    public Email() {
    }

    public Email(int mentorId, @NonNull String emailAddress) {
        this.mentorId = mentorId;
        this.emailAddress = emailAddress;
    }

    public Email(int id, int mentorId, @NonNull String emailAddress) {
        this.id = id;
        this.mentorId = mentorId;
        this.emailAddress = emailAddress;
    }

    protected Email(Parcel in) {
        id = in.readInt();
        mentorId = in.readInt();
        emailAddress = in.readString();
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        @Override
        public Email createFromParcel(Parcel in) {
            return new Email(in);
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(mentorId);
        parcel.writeString(emailAddress);
    }

    public Email id(int id) {
        this.id = id;
        return this;
    }

    public Email mentorId(int mentorId) {
        this.mentorId = mentorId;
        return this;
    }

    public Email emailAddress(@NonNull String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    @NonNull
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@NonNull String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
