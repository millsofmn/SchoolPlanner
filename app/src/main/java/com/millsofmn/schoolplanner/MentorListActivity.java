package com.millsofmn.schoolplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MentorListActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SectionsPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab_new_mentor);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewCourse();
            }
        });

        adapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.main_container);
        setupViewPager(viewPager);
    }

    private void createNewCourse() {
        Intent intent = new Intent(this, MentorActivity.class);

        startActivity(intent);
    }

    private void setupViewPager(ViewPager viewPager){
        adapter.addFragment(new MentorListFragment(), "Mentor");

        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }

}
