package com.millsofmn.schoolplanner;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.millsofmn.schoolplanner.adapter.TermsListAdapter;
import com.millsofmn.schoolplanner.data.Term;
import com.millsofmn.schoolplanner.viewmodels.TermsViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Date;

public class TermsListActivity extends AppCompatActivity implements TermsListAdapter.OnTermListener {
    public static final String TAG = "TermsListActivity";

    public static final int ADD_TERM_REQUEST = 1;
    public static final int EDIT_TERM_REQUEST = 2;

    private TermsViewModel termViewModel;
    private RecyclerView recyclerView;

    private TermsListAdapter termAdapter;
    private FloatingActionButton fab;

    // vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "starting main process");
        setContentView(R.layout.activity_terms_list);


        termViewModel = ViewModelProviders.of(this).get(TermsViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // configure recycler view
        recyclerView = findViewById(R.id.terms_list_recycler_view);
        initRecyclerView();

        fab = findViewById(R.id.fab_new_term);
        initFloatingActionBar();
    }

    private void initRecyclerView() {
        termAdapter = new TermsListAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(layoutManager);

        termViewModel.getAllTerms().observe(this, terms -> termAdapter.setTerms(terms));
    }

    private void initFloatingActionBar() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewTerm();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void createNewTerm(){
        Intent intent = new Intent(this, TermActivity.class);
        startActivityForResult(intent, ADD_TERM_REQUEST);
    }

    @Override
    public void onTermClick(int position) {
        Intent intent = new Intent(this, TermActivity.class);
        intent.putExtra(TermActivity.EXTRA_TERM, termAdapter.getSelectedTerm(position));
        startActivityForResult(intent, EDIT_TERM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == ADD_TERM_REQUEST && resultCode == RESULT_OK){
            String termTitle = intent.getStringExtra(TermActivity.EXTRA_TERM_TITLE);
            Long start = intent.getLongExtra(TermActivity.EXTRA_TERM_START_DATE, 0);
            Long end = intent.getLongExtra(TermActivity.EXTRA_TERM_END_DATE, 0);

            Date startDate = new Date(start);
            Date endDate = new Date(end);

            Term newTerm = new Term(termTitle, startDate, endDate);
            termViewModel.insert(newTerm);
            Toast.makeText(this, "New term created.", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_TERM_REQUEST && resultCode == RESULT_OK){

            int id = intent.getIntExtra(TermActivity.EXTRA_TERM_ID, -1);

            if(id == -1){
                Toast.makeText(this, "Term cannot be updated.", Toast.LENGTH_SHORT).show();
                return;
            }
            String termTitle = intent.getStringExtra(TermActivity.EXTRA_TERM_TITLE);
            Long start = intent.getLongExtra(TermActivity.EXTRA_TERM_START_DATE, 0);
            Long end = intent.getLongExtra(TermActivity.EXTRA_TERM_END_DATE, 0);

            Date startDate = new Date(start);
            Date endDate = new Date(end);

            Term newTerm = new Term(id, termTitle, startDate, endDate);
            termViewModel.update(newTerm);
        } else {
            Toast.makeText(this, "Term not created.", Toast.LENGTH_SHORT).show();
        }
    }
}
