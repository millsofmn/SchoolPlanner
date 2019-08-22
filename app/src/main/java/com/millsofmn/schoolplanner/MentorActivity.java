package com.millsofmn.schoolplanner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.millsofmn.schoolplanner.db.entity.Mentor;
import com.millsofmn.schoolplanner.viewmodel.MentorViewModel;

public class MentorActivity extends AppCompatActivity {

    public static final String MENTOR_SELECTED_ID_EXTRA = "mentor_selected_id";
    public static final String MENTOR_SELECTED_EXTRA = "mentor_selected";

    private MentorViewModel mentorViewModel;

    private Observer<Mentor> mentorObserver;

    private int id;
    private EditText etMentorName;
    private EditText etEmailAddress;
    private EditText etPhoneNumber;
    private Button btnSave;
    private Button btnDelete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mentorViewModel = ViewModelProviders.of(this).get(MentorViewModel.class);

        etMentorName = findViewById(R.id.et_mentor_name);
        etEmailAddress = findViewById(R.id.et_mentor_emails);
        etPhoneNumber = findViewById(R.id.et_mentor_phone);

        btnDelete = findViewById(R.id.btn_mentor_delete);

        btnSave = findViewById(R.id.btn_mentor_save);

        mentorObserver = mentor -> {
            if(mentor.getName() != null && !mentor.getName().isEmpty()){
                etMentorName.setText(mentor.getName());
            }
            if(mentor.getEmailAddress() != null && !mentor.getEmailAddress().isEmpty()){
                etEmailAddress.setText(mentor.getEmailAddress());
            }
            if(mentor.getPhoneNumber() != null && !mentor.getPhoneNumber().isEmpty()){
                etPhoneNumber.setText(mentor.getPhoneNumber());
            }
        };

        getIncomingIntent();
    }

    private void getIncomingIntent() {

        if(getIntent().hasExtra(MENTOR_SELECTED_ID_EXTRA)){
            id = getIntent().getIntExtra(MENTOR_SELECTED_ID_EXTRA, -1);
            mentorViewModel.findById(id).observe(this, mentorObserver);

        }
    }

    private void save(){

    }

    private void delete(){

    }
}
