package com.millsofmn.schoolplanner;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.millsofmn.schoolplanner.adapter.CoursesListAdapter;
import com.millsofmn.schoolplanner.data.Course;
import com.millsofmn.schoolplanner.data.Term;
import com.millsofmn.schoolplanner.viewmodels.CoursesViewModel;

import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseListFragment extends Fragment implements CoursesListAdapter.OnCourseListener {

    public static final int ADD_COURSE_REQUEST = 1;
    public static final int EDIT_COURSE_REQUEST = 2;

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
        intent.putExtra(TermFragment.EXTRA_TERM, thisTerm);

        startActivityForResult(intent, EDIT_COURSE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK){

            int courseId = data.getIntExtra(CourseFragment.EXTRA_COURSE_ID, -1);
            int termId = data.getIntExtra(CourseFragment.EXTRA_COURSE_TERM_ID, -1);

            if( courseId == -1 || termId == -1){
                Toast.makeText(getActivity(), "Course cannot be updated.", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(CourseFragment.EXTRA_COURSE_TITLE);
            String status = data.getStringExtra(CourseFragment.EXTRA_COURSE_STATUS);
            Long start = data.getLongExtra(CourseFragment.EXTRA_COURSE_START_DATE, 0);
            Long end = data.getLongExtra(CourseFragment.EXTRA_COURSE_END_DATE, 0);

            Date startDate = new Date(start);
            Date endDate = new Date(end);

            Course newCourse = new Course(courseId, termId, title, startDate, endDate, status);
            coursesViewModel.update(newCourse);
        }
    }
}
