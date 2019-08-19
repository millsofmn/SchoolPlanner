package com.millsofmn.schoolplanner.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "mentor_phone_number",
        foreignKeys = @ForeignKey(
                entity = Mentor.class,
                parentColumns = "id",
                childColumns = "mentor_id",
                onDelete = ForeignKey.CASCADE))
public class PhoneNumber implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "mentor_id")
    private int mentorId;

    @NonNull
    @ColumnInfo(name = "email_address")
    private String phoneNumber;

    public PhoneNumber() {
    }

    public PhoneNumber(int mentorId, @NonNull String phoneNumber) {
        this.mentorId = mentorId;
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber(int id, int mentorId, @NonNull String phoneNumber) {
        this.id = id;
        this.mentorId = mentorId;
        this.phoneNumber = phoneNumber;
    }

    protected PhoneNumber(Parcel in) {
        id = in.readInt();
        mentorId = in.readInt();
        phoneNumber = in.readString();
    }

    public static final Creator<PhoneNumber> CREATOR = new Creator<PhoneNumber>() {
        @Override
        public PhoneNumber createFromParcel(Parcel in) {
            return new PhoneNumber(in);
        }

        @Override
        public PhoneNumber[] newArray(int size) {
            return new PhoneNumber[size];
        }
    };

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
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(mentorId);
        parcel.writeString(phoneNumber);
    }
}
