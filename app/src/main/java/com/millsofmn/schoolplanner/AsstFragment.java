package com.millsofmn.schoolplanner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.millsofmn.schoolplanner.db.entity.Assessment;
import com.millsofmn.schoolplanner.db.entity.Course;
import com.millsofmn.schoolplanner.viewmodel.AssessmentViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.app.Activity.RESULT_OK;

public class AsstFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "++++++AssessmentFragment";
    private static final DateFormat fmtDate = new SimpleDateFormat("MMM d, yyyy");

    public static final String EXTRA_ASST_COURSE = "asst_course";
    public static final String EXTRA_ASST = "asst";
    public static final String EXTRA_ASST_ID = "asst_id";
    public static final String EXTRA_ASST_COURSE_ID = "asst_course_id";
    public static final String EXTRA_ASST_TITLE = "asst_title";
    public static final String EXTRA_ASST_TYPE = "asst_type";
    public static final String EXTRA_ASST_DUE_DATE = "asst_due_date";
    public static final String EXTRA_ASST_ALERT = "asst_alert";

    private Assessment thisAsst;
    private Course thisCourse;

    private EditText editTextAsstTitle;
    private Spinner spinAsstType;
    private Button buttonAsstDueDate;
    private Button buttonClearDate;
    private CheckBox checkBoxAsstAlert;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    public AsstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asst, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

        editTextAsstTitle = view.findViewById(R.id.et_asst_title);
        checkBoxAsstAlert = view.findViewById(R.id.cb_asst_alert);

        buttonAsstDueDate = view.findViewById(R.id.btn_asst_due_date);
        buttonAsstDueDate.setOnClickListener(cal -> {
            showDatePickerDialog();
        });

        buttonClearDate = view.findViewById(R.id.btn_asst_clear_date);
        buttonClearDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                buttonAsstDueDate.setText("NOT SCHEDULEd");
            }
        });

        spinAsstType = view.findViewById(R.id.sp_asst_type);

        spinnerAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.assessment_types,
                R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ;
        spinAsstType.setAdapter(spinnerAdapter);

        if (getIncomingIntent()) {
            setProperties();
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_content, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setProperties() {
        editTextAsstTitle.setText(thisAsst.getTitle());

        if (thisAsst.getDueDate() != null) {
            buttonAsstDueDate.setText(fmtDate.format(thisAsst.getDueDate()));
        } else {
            buttonAsstDueDate.setText("Not Scheduled");
        }
        checkBoxAsstAlert.setChecked(thisAsst.isAlertOnDueDate());
        spinAsstType.setSelection(spinnerAdapter.getPosition(thisAsst.getPerformanceType()));
    }

    private boolean getIncomingIntent() {

        thisCourse = getActivity().getIntent().getParcelableExtra(AsstFragment.EXTRA_ASST_COURSE);

        if (getActivity().getIntent().hasExtra(EXTRA_ASST)) {
            thisAsst = getActivity().getIntent().getParcelableExtra(EXTRA_ASST);
        }

        return thisAsst == null ? false : true;
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
        buttonAsstDueDate.setText(fmtDate.format(date));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveAsst();
                return true;
            case R.id.action_delete:
                deleteAsst();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void deleteAsst() {
        if (thisCourse != null) {
            AssessmentViewModel viewModel = ViewModelProviders.of(this).get(AssessmentViewModel.class);
            viewModel.delete(thisAsst);
            Intent intent = new Intent(getActivity(), CourseActivity.class);

            intent.putExtra(CourseFragment.EXTRA_COURSE, thisCourse);
            startActivity(intent);

        }
    }

    private void saveAsst() {
        Log.i(TAG, "saving");

        if (!TextUtils.isEmpty(editTextAsstTitle.getText()) && thisCourse != null) {
            Log.i(TAG, "I'm in here");

            String asstTitle = editTextAsstTitle.getText().toString();
            String asstType = spinAsstType.getSelectedItem().toString();
            boolean asstAlert = checkBoxAsstAlert.isSelected();

            Date dueDate;
            try {
                dueDate = fmtDate.parse(buttonAsstDueDate.getText().toString());
            } catch (ParseException e) {
                dueDate = null;
            }

            Intent intent = new Intent();
            intent.putExtra(EXTRA_ASST_COURSE_ID, thisCourse.getId());
            intent.putExtra(EXTRA_ASST_TITLE, asstTitle);
            intent.putExtra(EXTRA_ASST_TYPE, asstType);
            intent.putExtra(EXTRA_ASST_DUE_DATE, dueDate);
            intent.putExtra(EXTRA_ASST_ALERT, asstAlert);

            if (thisAsst != null) {
                intent.putExtra(EXTRA_ASST_ID, thisAsst.getId());
            }

            getActivity().setResult(RESULT_OK, intent);
            getActivity().finish();
        } else {
            Toast.makeText(getActivity(), "Unable to Save", Toast.LENGTH_SHORT).show();
        }
    }
}
