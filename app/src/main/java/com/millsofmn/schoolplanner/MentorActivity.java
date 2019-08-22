package com.millsofmn.schoolplanner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.millsofmn.schoolplanner.db.entity.Mentor;
import com.millsofmn.schoolplanner.viewmodel.MentorViewModel;

public class MentorActivity extends AppCompatActivity {

    public static final String MENTOR_SELECTED_ID_EXTRA = "mentor_selected_id";
    public static final String MENTOR_SELECTED_EXTRA = "mentor_selected";

    public static final String MENTOR_ID_EXTRA = "mentor_id_extra";
    public static final String MENTOR_NAME_EXTRA = "mentor_name_extra";
    public static final String MENTOR_EMAIL_EXTRA = "mentor_email_extra";
    public static final String MENTOR_PHONE_EXTRA = "mentor_phone_extra";

    private MentorViewModel mentorViewModel;

    private Observer<Mentor> mentorObserver;

    private int id;
    private Mentor thisMentor;

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

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mentorViewModel = ViewModelProviders.of(this).get(MentorViewModel.class);

        etMentorName = findViewById(R.id.et_mentor_name);
        etEmailAddress = findViewById(R.id.et_mentor_emails);
        etPhoneNumber = findViewById(R.id.et_mentor_phone);

        btnDelete = findViewById(R.id.btn_mentor_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        btnSave = findViewById(R.id.btn_mentor_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        mentorObserver = mentor -> {
            thisMentor = mentor;
            if (mentor != null) {
                if (mentor.getName() != null && !mentor.getName().isEmpty()) {
                    etMentorName.setText(mentor.getName());
                }
                if (mentor.getEmailAddress() != null && !mentor.getEmailAddress().isEmpty()) {
                    etEmailAddress.setText(mentor.getEmailAddress());
                }
                if (mentor.getPhoneNumber() != null && !mentor.getPhoneNumber().isEmpty()) {
                    etPhoneNumber.setText(mentor.getPhoneNumber());
                }
            }
        };

        getIncomingIntent();
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra(MENTOR_SELECTED_ID_EXTRA)) {
            id = getIntent().getIntExtra(MENTOR_SELECTED_ID_EXTRA, -1);
            mentorViewModel.findById(id).observe(this, mentorObserver);
        } else {
            id = -1;
        }
    }

    private void save() {

        if (!TextUtils.isEmpty(etMentorName.getText())) {
            String name = etMentorName.getText().toString();
            String phone = etPhoneNumber.getText().toString();
            String email = etEmailAddress.getText().toString();

            Mentor mentor = new Mentor()
                    .name(name)
                    .phoneNumber(phone)
                    .emailAddress(email);

            if (id > -1) {
                mentor.id(id);
                mentorViewModel.update(mentor);
            } else {
                mentorViewModel.insert(mentor);
            }

            Intent intent = new Intent(this, MentorListActivity.class);
            startActivity(intent);
        } else {

            Toast.makeText(this, "Error Updating Mentor", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete() {
        mentorViewModel.delete(thisMentor);
        Intent intent = new Intent(this, MentorListActivity.class);
        startActivity(intent);
    }
}
