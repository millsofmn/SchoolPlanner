package com.millsofmn.schoolplanner;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.millsofmn.schoolplanner.db.entity.Term;
import com.millsofmn.schoolplanner.viewmodel.TermsViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class TermFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "TermFragment";

    public static final String EXTRA_TERM = "term";
    public static final String EXTRA_TERM_ID = "term_id";
    public static final String EXTRA_TERM_TITLE = "term_title";
    public static final String EXTRA_TERM_START_DATE = "term_start_date";
    public static final String EXTRA_TERM_END_DATE = "term_end_date";

    private static final DateFormat fmtDate = new SimpleDateFormat("MMM d, yyyy");

    private EditText editTermTitle;
    private Button editStartDate;
    private Button editEndDate;

    private Button lastButtonPressed;

    private Term termInitial;

    public TermFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_term, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

        editTermTitle = view.findViewById(R.id.edit_term_title);

        editStartDate = view.findViewById(R.id.term_start_date);
        editStartDate.setOnClickListener(cal -> {
            lastButtonPressed = editStartDate;
            showDatePickerDialog();
        });

        editEndDate = view.findViewById(R.id.term_end_date);
        editEndDate.setOnClickListener(cal -> {
            lastButtonPressed = editEndDate;
            showDatePickerDialog();
        });

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
        inflater.inflate(R.menu.menu_content, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setTermProperties() {
        editTermTitle.setText(termInitial.getTitle());
        editStartDate.setText(fmtDate.format(termInitial.getStartDate()));
        editEndDate.setText(fmtDate.format(termInitial.getEndDate()));
    }

    private boolean getIncomingIntent() {
        boolean incomingIntent = false;
        if (getActivity().getIntent().hasExtra(EXTRA_TERM)) {
            termInitial = getActivity().getIntent().getParcelableExtra(EXTRA_TERM);
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
                saveTerm();
                return true;
            case R.id.action_delete:
                deleteTerm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteTerm() {
        if (termInitial != null) {
            TermsViewModel termViewModel = ViewModelProviders.of(this).get(TermsViewModel.class);
            termViewModel.delete(termInitial);
            startActivity(new Intent(getActivity(), TermsListActivity.class));
        }
    }

    private void saveTerm() {
        if (!TextUtils.isEmpty(editTermTitle.getText()) ||
                !TextUtils.isEmpty(editStartDate.getText()) ||
                !TextUtils.isEmpty(editEndDate.getText())) {

            try {
                String termTitle = editTermTitle.getText().toString();
                Date startDate = fmtDate.parse(editStartDate.getText().toString());
                Date endDate = fmtDate.parse(editEndDate.getText().toString());

                Intent intent = new Intent();

                intent.putExtra(EXTRA_TERM_TITLE, termTitle);
                intent.putExtra(EXTRA_TERM_START_DATE, startDate.getTime());
                intent.putExtra(EXTRA_TERM_END_DATE, endDate.getTime());

                if (termInitial != null) {
                    intent.putExtra(EXTRA_TERM_ID, termInitial.getId());
                }

                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
                return;
            } catch (ParseException e) {
                e.printStackTrace();

            }
            Toast.makeText(getActivity(), "Please include a term name, start and end date.", Toast.LENGTH_LONG).show();
        }
    }
}
