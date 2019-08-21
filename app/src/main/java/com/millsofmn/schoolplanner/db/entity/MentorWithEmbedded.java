package com.millsofmn.schoolplanner.db.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MentorWithEmbedded {
    @Embedded
    public Mentor mentor;

    @Relation(parentColumn = "id", entityColumn = "mentor_id", entity = Email.class, projection = "email_address")
    public List<String> emails;

    @Relation(parentColumn = "id", entityColumn = "mentor_id", entity = PhoneNumber.class, projection = "phone_number")
    public List<String> phoneNumbers;
    @Override
    public String toString() {
        return "MentorWithEmbedded{" +
                "mentor=" + mentor +
                ", emails=" + emails +
                '}';
    }
}
