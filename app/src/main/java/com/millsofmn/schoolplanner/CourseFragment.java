package com.millsofmn.schoolplanner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.millsofmn.schoolplanner.data.Course;
import com.millsofmn.schoolplanner.viewmodels.CoursesViewModel;

import java.util.Collection;
import java.util.List;


public class CourseFragment extends Fragment {

    public CourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_course, container, false);
    }
}
