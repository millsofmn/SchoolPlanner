package com.millsofmn.schoolplanner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.millsofmn.schoolplanner.data.Term;
import com.millsofmn.schoolplanner.data.TermRepository;
import com.millsofmn.schoolplanner.viewmodels.TermListViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TermActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static final String EXTRA_TERM_ID = "termId";

    private static final DateFormat fmtDate = new SimpleDateFormat("MMM d, yyyy");

    private EditText editTermTitle;
    private Button editStartDate;
    private Button editEndDate;

    private Button lastButtonPressed;


    private TermListViewModel termViewModel;

    private Term termInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);


        Toolbar toolbar = findViewById(R.id.content_toolbar);
        setSupportActionBar(toolbar);

        termViewModel = ViewModelProviders.of(this).get(TermListViewModel.class);

        editTermTitle = findViewById(R.id.edit_term_title);

        editStartDate = findViewById(R.id.edit_start_date);
        editStartDate.setOnClickListener(view -> {
            lastButtonPressed = editStartDate;
            showDatePickerDialog();
        });

        editEndDate = findViewById(R.id.edit_end_date);
        editEndDate.setOnClickListener(view -> {
            lastButtonPressed = editEndDate;
            showDatePickerDialog();
        });

        if (isIncomingIntent()) {
            setTermProperties();
        }

        FloatingActionButton fab = findViewById(R.id.fab_new_course);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void setTermProperties() {
        editTermTitle.setText(termInitial.getTitle());

        editStartDate.setText(fmtDate.format(termInitial.getStartDate()));

        editEndDate.setText(fmtDate.format(termInitial.getEndDate()));
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

    private boolean isIncomingIntent() {
        boolean incomingIntent = false;
        if (getIntent().hasExtra(EXTRA_TERM_ID)) {

            int termId = (int) getIntent().getExtras().get(EXTRA_TERM_ID);

            termInitial = TermRepository.getTerms().get(termId);
            incomingIntent = true;
        }

        return incomingIntent;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        lastButtonPressed.setText(fmtDate.format(date));
    }
}
