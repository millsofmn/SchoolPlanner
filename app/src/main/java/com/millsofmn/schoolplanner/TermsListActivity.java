package com.millsofmn.schoolplanner;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.millsofmn.schoolplanner.adapter.TermsListAdapter;
import com.millsofmn.schoolplanner.viewmodels.TermListViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class TermsListActivity extends AppCompatActivity implements TermsListAdapter.OnTermListener {

    public static final String TAG = "TermsListActivity";

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    // vars
    private TermListViewModel termViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "starting main process");
        setContentView(R.layout.activity_terms_list);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // configure recycler view
        recyclerView = findViewById(R.id.terms_list_recycler_view);
        initRecyclerView();

        fab = findViewById(R.id.fab_new_term);
        initFloatingActionBar();
    }

    private void initRecyclerView(){
        final TermsListAdapter termAdapter = new TermsListAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(layoutManager);

        termViewModel = ViewModelProviders.of(this).get(TermListViewModel.class);

        termViewModel.getAllTerms().observe(this, terms -> termAdapter.setTerms(terms));
    }

    private void initFloatingActionBar(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTermClick(int position) {
            Intent intent = new Intent(this, TermActivity.class);
            intent.putExtra(TermActivity.EXTRA_TERM_ID, position);
            startActivity(intent);
    }
}
