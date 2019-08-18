package com.millsofmn.schoolplanner.data;

import android.util.Log;

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
        courses.add(new Course(1, 1,"Intro to Computers", getDate(-6), getDate(-5), Course.ProgressStatus.COMPLETED));
        courses.add(new Course(2, 1,"More About Computers", getDate(-5), getDate(-4), Course.ProgressStatus.COMPLETED));
        courses.add(new Course(3, 1,"Being Human", getDate(-4), getDate(-3), Course.ProgressStatus.COMPLETED));
        courses.add(new Course(4, 2,"Being Alien", getDate(-3), getDate(-2), Course.ProgressStatus.DROPPED));
        courses.add(new Course(5, 2,"Biology", getDate(-2), getDate(-1), Course.ProgressStatus.COMPLETED));
        courses.add(new Course(6, 2,"Dunking 101", getDate(-1), getDate(0), Course.ProgressStatus.IN_PROGRESS));
        courses.add(new Course(7, 3,"Android Development", getDate(0), getDate(1), Course.ProgressStatus.PLAN_TO_TAKE));
        courses.add(new Course(8, 3,"Drinking", getDate(1), getDate(2), Course.ProgressStatus.PLAN_TO_TAKE));
        courses.add(new Course(9, 3,"How to Pick Things", getDate(2), getDate(3), Course.ProgressStatus.PLAN_TO_TAKE));
        courses.add(new Course(10, 4,"Intro to Course Names", getDate(3), getDate(4), Course.ProgressStatus.PLAN_TO_TAKE));
        courses.add(new Course(11, 4,"Advance Computers", getDate(4), getDate(5), Course.ProgressStatus.PLAN_TO_TAKE));
        courses.add(new Course(12, 4,"Clapping on One and Three", getDate(5), getDate(6), Course.ProgressStatus.PLAN_TO_TAKE));

        return courses;
    }
}
