package com.millsofmn.schoolplanner;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.millsofmn.schoolplanner.adapter.CoursesListAdapter;
import com.millsofmn.schoolplanner.data.Term;
import com.millsofmn.schoolplanner.viewmodels.CoursesViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseListFragment extends Fragment  implements CoursesListAdapter.OnCourseListener {


    private RecyclerView recyclerView;
    private CoursesViewModel coursesViewModel;
    private CoursesListAdapter courseAdapter;
    private Term thisTerm;

    public CourseListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);

        coursesViewModel = ViewModelProviders.of(this).get(CoursesViewModel.class);


        if (getActivity().getIntent().hasExtra(TermFragment.EXTRA_TERM)) {
            thisTerm = getActivity().getIntent().getParcelableExtra(TermFragment.EXTRA_TERM);
            recyclerView = view.findViewById(R.id.course_list_recycler_view);
            initRecyclerView();
        }

        return view;
    }

    private void initRecyclerView() {
        courseAdapter = new CoursesListAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(layoutManager);

        coursesViewModel.getCoursesByTermId(thisTerm.getId()).observe(this, courses -> courseAdapter.setCourses(courses));
    }

    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(getActivity(), CourseActivity.class);
        intent.putExtra(CourseFragment.EXTRA_COURSE, courseAdapter.getSelectedCourse(position));
        startActivityForResult(intent, CourseActivity.EDIT_COURSE_REQUEST);
    }
}
