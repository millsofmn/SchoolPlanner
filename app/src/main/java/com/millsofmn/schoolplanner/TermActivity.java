package com.millsofmn.schoolplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.millsofmn.schoolplanner.data.Term;
import com.millsofmn.schoolplanner.data.TermRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TermActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static final String EXTRA_TERM_ID = "termId";

    private static final DateFormat fmtDate = new SimpleDateFormat("MMM d yyyy");

    private static final int EDIT_MODE_ENABLED = 1;
    private static final int EDIT_MODE_DISABLED = 0;

    private EditText editTermTitle;
    private Button editStartDate;
    private Button editEndDate;

    private Button lastButtonPressed;

//    private LinearLayout layoutDateDisplay;
//    private TextView textStartDate;
//    private TextView textEndDate;

    private boolean isTermNew;
    private Term termInitial;

    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        editTermTitle = findViewById(R.id.edit_term_title);
        editStartDate = findViewById(R.id.edit_start_date);
        editEndDate = findViewById(R.id.edit_end_date);

//        textTitle = findViewById(R.id.term_text_title);
//        editTitle = findViewById(R.id.term_edit_title);

//        layoutDateDisplay = findViewById(R.id.term_date_display);
//        textStartDate = findViewById(R.id.term_text_start);
//        textEndDate = findViewById(R.id.term_text_end);

        if (getIncomingIntent()) {
            enableEditMode();
        } else {
            setTermProperties();
            disableEditMode();
        }

        FloatingActionButton fab = findViewById(R.id.fab_new_course);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void setTermProperties() {
//        textTitle.setText(termInitial.getTitle());
//        editTitle.setText(termInitial.getTitle());

        editTermTitle.setText(termInitial.getTitle());

        editStartDate.setText(fmtDate.format(termInitial.getStartDate()));
        editStartDate.setOnClickListener(view -> {
            lastButtonPressed = editStartDate;
            showDatePickerDialog();
        });

        editEndDate.setText(fmtDate.format(termInitial.getEndDate()));
        editEndDate.setOnClickListener(view -> {
            lastButtonPressed = editEndDate;
            showDatePickerDialog();
        });
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private boolean getIncomingIntent() {
        if (getIntent().hasExtra(EXTRA_TERM_ID)) {

            int termId = (int) getIntent().getExtras().get(EXTRA_TERM_ID);

            termInitial = TermRepository.getTerms().get(termId);

            mode = EDIT_MODE_DISABLED;

            isTermNew = false;
        } else {
            mode = EDIT_MODE_ENABLED;
            isTermNew = true;
        }
        return isTermNew;
    }

    private void enableEditMode() {
//        textTitle.setVisibility(View.GONE);
//        editTitle.setVisibility(View.VISIBLE);
//
//        layoutDateDisplay.setVisibility(View.GONE);
//        textStartDate.setVisibility(View.GONE);
//        textEndDate.setVisibility(View.GONE);
    }

    private void disableEditMode() {
//        textTitle.setVisibility(View.VISIBLE);
//        editTitle.setVisibility(View.GONE);
//
//        layoutDateDisplay.setVisibility(View.VISIBLE);
//        textStartDate.setVisibility(View.VISIBLE);
//        textEndDate.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        lastButtonPressed.setText(fmtDate.format(date));
    }
}
