package com.millsofmn.schoolplanner;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

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

    private TextView mentorName;
    private EditText etMentorName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mentorViewModel = ViewModelProviders.of(this).get(MentorViewModel.class);

        mentorName = findViewById(R.id.tv_mentor_name);
        etMentorName = findViewById(R.id.et_mentor_name);

        mentorObserver = mentor -> {
            if(mentor.getName() != null && !mentor.getName().isEmpty()){
                mentorName.setText(mentor.getName());
                etMentorName.setText(mentor.getName());
            }
        };

        getIncomingIntent();
    }

    private void getIncomingIntent() {

        if(getIntent().hasExtra(MENTOR_SELECTED_ID_EXTRA)){
            int id = getIntent().getIntExtra(MENTOR_SELECTED_ID_EXTRA, -1);
            mentorViewModel.findById(id).observe(this, mentorObserver);

        }
    }
}
