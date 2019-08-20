package com.millsofmn.schoolplanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.millsofmn.schoolplanner.adapter.AssessmentAdapter;
import com.millsofmn.schoolplanner.db.entity.Assessment;
import com.millsofmn.schoolplanner.db.entity.Course;
import com.millsofmn.schoolplanner.viewmodel.AssessmentViewModel;

import java.util.Date;

import static android.app.Activity.RESULT_OK;


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

    private Course thisCourse;

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


        thisCourse = getActivity().getIntent().getParcelableExtra(CourseFragment.EXTRA_COURSE);

        assessmentViewModel.findByCourseId(thisCourse.getId()).observe(this,
                assessments -> assessmentAdapter.setData(assessments));
    }


    private void createNewAsst(){
        Intent intent = new Intent(getActivity(), AsstActivity.class);
        intent.putExtra(AsstFragment.EXTRA_ASST_COURSE, thisCourse);

        startActivityForResult(intent, ADD_ASST_REQUEST);
    }

    @Override
    public void onClick(int position) {
        Log.i(TAG, "Fragment pos = " + position + " id=" + assessmentAdapter.getItemId(position));
        Intent intent = new Intent(getActivity(), AsstActivity.class);
        intent.putExtra(AsstFragment.EXTRA_ASST, assessmentAdapter.getItem(position));
        intent.putExtra(AsstFragment.EXTRA_ASST_COURSE, thisCourse);

        startActivityForResult(intent, EDIT_ASST_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode == EDIT_ASST_REQUEST || requestCode == ADD_ASST_REQUEST) && resultCode == RESULT_OK){

            int courseId = data.getIntExtra(AsstFragment.EXTRA_ASST_COURSE_ID, -1);

            if(courseId == -1){
                Toast.makeText(getActivity(), "Assessment Can Not Be Created", Toast.LENGTH_SHORT).show();
                return;
            }

            int asstId = data.getIntExtra(AsstFragment.EXTRA_ASST_ID, -1);
            String title = data.getStringExtra(AsstFragment.EXTRA_ASST_TITLE);
            String type = data.getStringExtra(AsstFragment.EXTRA_ASST_TYPE);
            Long date = data.getLongExtra(AsstFragment.EXTRA_ASST_DUE_DATE, -1);
            boolean alert = data.getBooleanExtra(AsstFragment.EXTRA_ASST_ALERT, false);

            Date alertDate = null;
            if(date > 0){
                alertDate = new Date(date);
            }

            Assessment assessment = new Assessment()
                    .title(title)
                    .performanceType(type)
                    .courseId(courseId)
                    .dueDate(alertDate)
                    .alertOnDueDate(alert);

            String msg = "";

            if(asstId != -1){
                assessment.setId(asstId);
                assessmentViewModel.update(assessment);
                msg = "Assessment Created";
            } else {
                assessmentViewModel.insert(assessment);
                msg = "Assessment Created";
            }
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Oh No!", Toast.LENGTH_SHORT).show();

        }
    }
}
