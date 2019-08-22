package com.millsofmn.schoolplanner;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class CourseActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        Toolbar toolbar = findViewById(R.id.content_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.term_pager);
        setupViewPager(viewPager);


        TabLayout tabLayout = findViewById(R.id.tab_term_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CourseFragment(), "Course Details");

        if(getIntent().hasExtra(CourseFragment.EXTRA_COURSE)){
            adapter.addFragment(new AsstListFragment(), "Assessments");
        }

        viewPager.setAdapter(adapter);
    }

}
