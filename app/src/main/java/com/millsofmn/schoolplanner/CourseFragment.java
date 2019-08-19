package com.millsofmn.schoolplanner;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.core.view.ScrollingView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.millsofmn.schoolplanner.data.Course;
import com.millsofmn.schoolplanner.data.Term;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class CourseFragment extends Fragment  implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = "++++ Course Fragment";
    private static final DateFormat fmtDate = new SimpleDateFormat("MMM d, yyyy");

    public static final String EXTRA_COURSE = "course";
    public static final String EXTRA_COURSE_ID = "course_id";
    public static final String EXTRA_COURSE_TITLE = "course_title";
    public static final String EXTRA_COURSE_START_DATE = "course_start_date";
    public static final String EXTRA_COURSE_END_DATE = "course_end_date";

    private Term thisTerm;
    private Course thisCourse;

    private EditText editTextCourseTitle;
    private Button buttonStartDate;
    private Button buttonEndDate;
    private Spinner courseStatus;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    private Button lastButtonPressed;
    public CourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        editTextCourseTitle = view.findViewById(R.id.et_course_title);
        buttonStartDate = view.findViewById(R.id.btn_course_start_date);
        buttonStartDate.setOnClickListener(cal -> {
            lastButtonPressed = buttonStartDate;
            showDatePickerDialog();
        });
        buttonEndDate = view.findViewById(R.id.btn_course_end_date);
        buttonEndDate.setOnClickListener(cal -> {
            lastButtonPressed = buttonEndDate;
            showDatePickerDialog();
        });

        courseStatus = view.findViewById(R.id.spinner_course_status);
        spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.course_statuses, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        courseStatus.setAdapter(spinnerAdapter);

        if (getIncomingIntent()) {
            setTermProperties();
        }
        return view;
    }

    private void setTermProperties() {
        editTextCourseTitle.setText(thisCourse.getTitle());
        buttonStartDate.setText(fmtDate.format(thisCourse.getStartDate()));
        buttonEndDate.setText(fmtDate.format(thisCourse.getEndDate()));
        Log.i(TAG, "Status = " + thisCourse.getStatus());
        courseStatus.setSelection(spinnerAdapter.getPosition(thisCourse.getStatus()));
    }

    private boolean getIncomingIntent() {
        boolean incomingIntent = false;
        if (getActivity().getIntent().hasExtra(EXTRA_COURSE)) {
            Log.i(TAG,"+++++ ");
            thisTerm = getActivity().getIntent().getParcelableExtra(TermFragment.EXTRA_TERM);
            thisCourse = getActivity().getIntent().getParcelableExtra(EXTRA_COURSE);
            incomingIntent = true;
        } else {

        }

        return incomingIntent;
    }


    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        lastButtonPressed.setText(fmtDate.format(date));
    }

}
