package com.millsofmn.schoolplanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private ViewPager viewPager;
    private SectionsPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.main_container);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        adapter.addFragment(new HomeFragment(), "Home");

        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }
}
