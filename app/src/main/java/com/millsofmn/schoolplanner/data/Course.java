package com.millsofmn.schoolplanner.data;

import java.util.Date;
import java.util.List;

public class Course {
    enum ProgressStatus{
        IN_PROEGESS, COMPLETED, DROPPED, PLAN_TO_TAKE
    };

    private String title;
    private Date startDate;
    private Date endDate;
    private ProgressStatus status;
    private List<String> notes;
    private List<Assessment> assessments;
    private List<Mentor> mentors;

}
