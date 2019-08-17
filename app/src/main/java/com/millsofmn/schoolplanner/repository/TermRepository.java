package com.millsofmn.schoolplanner.repository;

import com.millsofmn.schoolplanner.domain.Term;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TermRepository {

    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MONTH, diff);
        return cal.getTime();
    }
    //public Term(int id, String title, Date startDate, Date endDate) {
    public static List<Term> getTerms(){
        List<Term> terms = new ArrayList<>();
        terms.add(new Term(1, "Term 1", getDate(-3), getDate(-2)));
        terms.add(new Term(2, "Term 2", getDate(-2), getDate(-1)));
        terms.add(new Term(3, "Term 3", getDate(-1), getDate(0)));
        terms.add(new Term(4, "Term 4", getDate(0), getDate(1)));

        return terms;
    }
}
