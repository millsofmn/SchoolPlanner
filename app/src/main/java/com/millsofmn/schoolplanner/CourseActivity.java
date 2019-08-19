package com.millsofmn.schoolplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.millsofmn.schoolplanner.data.Course;
import com.millsofmn.schoolplanner.viewmodels.CoursesViewModel;

import java.util.Date;

public class CourseActivity extends AppCompatActivity {



    private ViewPager viewPager;
    private CoursesViewModel coursesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        Toolbar toolbar = findViewById(R.id.content_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        coursesViewModel = ViewModelProviders.of(this).get(CoursesViewModel.class);

        viewPager = findViewById(R.id.term_pager);
        setupViewPager(viewPager);


        TabLayout tabLayout = findViewById(R.id.tab_term_layout);
        tabLayout.setupWithViewPager(viewPager);

//        FloatingActionButton fab = findViewById(R.id.fab_new_course);
//        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show());
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CourseFragment(), "Course");
        adapter.addFragment(new Fragment(), "Assessments");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_content, menu);
        return true;
    }


}
