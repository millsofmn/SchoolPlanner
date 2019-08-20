package com.millsofmn.schoolplanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.millsofmn.schoolplanner.adapter.AssessmentAdapter;
import com.millsofmn.schoolplanner.db.entity.Course;
import com.millsofmn.schoolplanner.viewmodel.AssessmentViewModel;


public class AsstListFragment extends Fragment implements AssessmentAdapter.OnClickListener {
    public static final String TAG = "AsstListFragment +++";

    public static final int ADD_ASST_REQUEST = 1;
    public static final int EDIT_ASST_REQUEST = 2;

    private AssessmentAdapter assessmentAdapter;

    private AssessmentViewModel assessmentViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public static AsstListFragment newInstance() {
        return new AsstListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asst_list_fragment, container, false);


        assessmentAdapter = new AssessmentAdapter(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.ass_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = view.findViewById(R.id.fab_new_asst);
        fab.setOnClickListener(obj -> createNewAsst());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        assessmentViewModel = ViewModelProviders.of(this).get(AssessmentViewModel.class);


        Course thisCourse = getActivity().getIntent().getParcelableExtra(CourseFragment.EXTRA_COURSE);

        assessmentViewModel.findByCourseId(thisCourse.getId()).observe(this,
                assessments -> assessmentAdapter.setAssessmentList(assessments));
    }


    private void createNewAsst(){
        Course thisCourse = getActivity().getIntent().getParcelableExtra(CourseFragment.EXTRA_COURSE);
        Intent intent = new Intent(getActivity(), CourseActivity.class);
        intent.putExtra(TermFragment.EXTRA_TERM, thisCourse);
        startActivityForResult(intent, ADD_ASST_REQUEST);
    }

    @Override
    public void onClick(int position) {
        Log.i(TAG, "Fragment pos = " + position + " id=" + assessmentAdapter.getItemId(position));
        Course thisCourse = getActivity().getIntent().getParcelableExtra(CourseFragment.EXTRA_COURSE);

    }
}
