package com.millsofmn.schoolplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.millsofmn.schoolplanner.domain.Term;
import com.millsofmn.schoolplanner.repository.TermRepository;

public class TermDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TERM_ID = "termId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        int termId = (int) getIntent().getExtras().get(EXTRA_TERM_ID);

        Term term = TermRepository.getTerms().get(termId);

        TextView termTitle = findViewById(R.id.term_title_edit);
        termTitle.setText(term.getTitle());

        TextView startDate = findViewById(R.id.start_date_edit);
        startDate.setText("test int");

        FloatingActionButton fab = findViewById(R.id.fab_new_course);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }
}
