package com.millsofmn.schoolplanner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.millsofmn.schoolplanner.data.domain.Course;
import com.millsofmn.schoolplanner.data.domain.Term;
import com.millsofmn.schoolplanner.viewmodels.CoursesViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.app.Activity.RESULT_OK;


public class CourseFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = "++++ Course Fragment";
    private static final DateFormat fmtDate = new SimpleDateFormat("MMM d, yyyy");

    public static final String EXTRA_COURSE = "course";
    public static final String EXTRA_COURSE_ID = "course_id";
    public static final String EXTRA_COURSE_TITLE = "course_title";
    public static final String EXTRA_COURSE_START_DATE = "course_start_date";
    public static final String EXTRA_COURSE_END_DATE = "course_end_date";
    public static final String EXTRA_COURSE_TERM_ID = "course_term_id";
    public static final String EXTRA_COURSE_STATUS = "course_status";

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

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
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
        thisTerm = getActivity().getIntent().getParcelableExtra(TermFragment.EXTRA_TERM);

        if (getActivity().getIntent().hasExtra(EXTRA_COURSE)) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveCourse();
                return true;
            case R.id.action_delete:
                deleteCourse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void deleteCourse() {
        if (thisCourse != null) {
            CoursesViewModel coursesViewModel = ViewModelProviders.of(this).get(CoursesViewModel.class);
            coursesViewModel.delete(thisCourse);
            NavUtils.navigateUpFromSameTask(getActivity());
        }
    }

    private void saveCourse() {
        Log.i(TAG, "title=" + editTextCourseTitle.getText() +
                " start=" + buttonStartDate.getText() +
                " end=" + buttonEndDate.getText() +
                " term=" + thisTerm);

        if (!TextUtils.isEmpty(editTextCourseTitle.getText()) &&
                !TextUtils.isEmpty(buttonStartDate.getText()) &&
                !TextUtils.isEmpty(buttonEndDate.getText()) &&
                thisTerm != null) {

            try {
                String courseTitle = editTextCourseTitle.getText().toString();
                Date startDate = fmtDate.parse(buttonStartDate.getText().toString());
                Date endDate = fmtDate.parse(buttonEndDate.getText().toString());
                String status = courseStatus.getSelectedItem().toString();

                Intent intent = new Intent();

                intent.putExtra(EXTRA_COURSE_TERM_ID, thisTerm.getId());
                intent.putExtra(EXTRA_COURSE_TITLE, courseTitle);
                intent.putExtra(EXTRA_COURSE_START_DATE, startDate.getTime());
                intent.putExtra(EXTRA_COURSE_END_DATE, endDate.getTime());
                intent.putExtra(EXTRA_COURSE_STATUS, status);

                if (thisCourse != null) {
                    intent.putExtra(EXTRA_COURSE_ID, thisCourse.getId());
                }

                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();

                return;
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }
        Toast.makeText(getActivity(), "Please include a course name, start and end date.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getActivity(), "Please fdsfdsdfsdfs a term fdsdfsdfs, start and end date.", Toast.LENGTH_LONG).show();
    }
}
