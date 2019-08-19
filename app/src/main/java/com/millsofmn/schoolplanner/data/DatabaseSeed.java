package com.millsofmn.schoolplanner.data;

import com.millsofmn.schoolplanner.data.domain.Assessment;
import com.millsofmn.schoolplanner.data.domain.Course;
import com.millsofmn.schoolplanner.data.domain.CourseMentor;
import com.millsofmn.schoolplanner.data.domain.Email;
import com.millsofmn.schoolplanner.data.domain.Mentor;
import com.millsofmn.schoolplanner.data.domain.Note;
import com.millsofmn.schoolplanner.data.domain.PhoneNumber;
import com.millsofmn.schoolplanner.data.domain.Term;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DatabaseSeed {


    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MONTH, diff);
        return cal.getTime();
    }

    //public Term(int id, String title, Date startDate, Date endDate) {
    public static List<Term> getTerms(){
        List<Term> terms = new ArrayList<>();
        terms.add(new Term(1, "Term 1", getDate(-6), getDate(-3)));
        terms.add(new Term(2, "Term 2", getDate(-3), getDate(0)));
        terms.add(new Term(3, "Term 3", getDate(0), getDate(3)));
        terms.add(new Term(4, "Term 4", getDate(3), getDate(6)));

        return terms;
    }

    public static List<Course> getCourses(){
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1, 1,"Intro to Computers", "COMPLETED", false, getDate(-6), false, getDate(-5)));
        courses.add(new Course(2, 1,"More About Computers", "COMPLETED", false, getDate(-5), false, getDate(-4)));
        courses.add(new Course(3, 1,"Being Human", "COMPLETED", false, getDate(-4), false, getDate(-3)));
        courses.add(new Course(4, 2,"Being Alien", "DROPPED", false, getDate(-3), false, getDate(-2)));
        courses.add(new Course(5, 2,"Biology", "COMPLETED", false, getDate(-2), false, getDate(-1)));
        courses.add(new Course(6, 2,"Dunking 101", "IN_PROGRESS", false, getDate(-1), false, getDate(0)));
        courses.add(new Course(7, 3,"Android Development", "PLAN_TO_TAKE", false, getDate(0), false, getDate(1)));
        courses.add(new Course(8, 3,"Drinking", "PLAN_TO_TAKE", false, getDate(1), false, getDate(2)));
        courses.add(new Course(9, 3,"How to Pick Things", "PLAN_TO_TAKE", false, getDate(2), false, getDate(3)));
        courses.add(new Course(10, 4,"Intro to Course Names", "PLAN_TO_TAKE", false, getDate(3), false, getDate(4)));
        courses.add(new Course(11, 4,"Advance Computers", "PLAN_TO_TAKE", false, getDate(4), false, getDate(5)));
        courses.add(new Course(12, 4,"Clapping on One and Three", "PLAN_TO_TAKE", false, getDate(5), false, getDate(6)));

        return courses;
    }

    public static List<Note> getNotes(){
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, 1, "Intro was a good course."));
        notes.add(new Note(2, 1, "Intro was a hard course"));
        notes.add(new Note(3, 2, "More about comp"));
        notes.add(new Note(4, 3, "Being human"));
        notes.add(new Note(5, 4, "Being Alien"));
        notes.add(new Note(6, 4, "Almost alien"));
        notes.add(new Note(7, 4, "Keeping it alien"));
        notes.add(new Note(8, 6, "101 with donuts"));
        notes.add(new Note(9, 7, "android"));
        notes.add(new Note(10, 8, "Drinking"));
        notes.add(new Note(11, 8, "Hangover"));
        notes.add(new Note(12, 9, "pick things"));
        notes.add(new Note(13, 10, "intro course"));
        notes.add(new Note(14, 10, "intra course"));
        notes.add(new Note(15, 10, "course intro"));
        return notes;
    }

    public static List<Assessment> getAssessments(){
        List<Assessment> assessments = new ArrayList<>();
        assessments.add(new Assessment(1, 1, "Assessment", "OBJECTIVE", getDate(-5), false));
        assessments.add(new Assessment(2, 2, "Assessment", "OBJECTIVE", getDate(-4), false));
        assessments.add(new Assessment(3, 3, "Assessment", "PERFORMANCE", getDate(-3), false));
        assessments.add(new Assessment(4, 4, "Assessment", "PERFORMANCE", getDate(-2), false));
        assessments.add(new Assessment(5, 5, "Assessment", "OBJECTIVE", getDate(-1), false));
        assessments.add(new Assessment(6, 6, "Assessment", "PERFORMANCE", getDate(0), false));
        assessments.add(new Assessment(7, 7, "Assessment", "OBJECTIVE", getDate(1), false));
        assessments.add(new Assessment(8, 7, "Assessment", "PERFORMANCE", getDate(1), false));
        assessments.add(new Assessment(9, 8, "Assessment", "OBJECTIVE", getDate(2), false));
        assessments.add(new Assessment(10, 9, "Assessment", "PERFORMANCE", getDate(3), false));
        assessments.add(new Assessment(11, 10, "Assessment", "OBJECTIVE", getDate(4), false));
        assessments.add(new Assessment(12, 11, "Assessment", "OBJECTIVE", getDate(5), false));
        assessments.add(new Assessment(13, 12, "Assessment", "OBJECTIVE", getDate(6), false));
        assessments.add(new Assessment(14, 12, "Assessment", "PERFORMANCE", getDate(6), false));
        assessments.add(new Assessment(15, 12, "Assessment", "PERFORMANCE", getDate(6), false));

        return assessments;
    }

    public static List<Mentor> getMentors(){
        List<Mentor> mentors = new ArrayList<>();
        mentors.add(new Mentor(1, "Sally June"));
        mentors.add(new Mentor(2, "Sven Updohal"));
        mentors.add(new Mentor(3, "James Smith"));
        mentors.add(new Mentor(4, "Saul Paul"));
        mentors.add(new Mentor(5, "Negan Gull"));

        return mentors;
    }

    public static List<CourseMentor> getCourseMentors(){
        List<CourseMentor> courseMentors = new ArrayList<>();
        courseMentors.add(new CourseMentor(1, 1));
        courseMentors.add(new CourseMentor(2, 2));
        courseMentors.add(new CourseMentor(3, 3));
        courseMentors.add(new CourseMentor(4, 4));
        courseMentors.add(new CourseMentor(5, 5));
        courseMentors.add(new CourseMentor(6, 2));
        courseMentors.add(new CourseMentor(7, 3));
        courseMentors.add(new CourseMentor(8, 4));
        courseMentors.add(new CourseMentor(9, 5));
        courseMentors.add(new CourseMentor(10, 2));
        courseMentors.add(new CourseMentor(11, 3));
        courseMentors.add(new CourseMentor(12, 5));

        return courseMentors;
    }

    public static List<Email> getEmail(){
        List<Email> emails = new ArrayList<>();
        emails.add(new Email(1, 1,"june.sally@exmple.com"));
        emails.add(new Email(2, 2,"updahal.sven@exmple.com"));
        emails.add(new Email(3, 3,"smith.jamesy@exmple.com"));
        emails.add(new Email(4, 4,"paul.saul@exmple.com"));
        emails.add(new Email(5, 4,"bible@exmple.com"));
        emails.add(new Email(6, 5,"gull.negany@exmple.com"));

        return emails;
    }

    public static List<PhoneNumber> getPhoneNumbers(){
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(1, 1, "507-555-1234"));
        phoneNumbers.add(new PhoneNumber(2, 1, "507-555-4567"));
        phoneNumbers.add(new PhoneNumber(3, 2, "507-555-7899"));
        phoneNumbers.add(new PhoneNumber(4, 3, "507-555-7534"));
        phoneNumbers.add(new PhoneNumber(5, 4, "507-555-1599"));
        phoneNumbers.add(new PhoneNumber(6, 5, "507-555-8525"));
        phoneNumbers.add(new PhoneNumber(7, 5, "507-555-9173"));
        phoneNumbers.add(new PhoneNumber(8, 5, "507-555-4002"));

        return phoneNumbers;
    }
}
