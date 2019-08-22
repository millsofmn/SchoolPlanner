package com.millsofmn.schoolplanner;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.adapter.MentorListAdapter;
import com.millsofmn.schoolplanner.viewmodel.MentorViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MentorListFragment extends Fragment implements MentorListAdapter.OnMentorListener {
    public static final int ADD_MENTOR_REQUEST = 1;
    public static final int EDIT_MENTOR_REQUEST = 2;

    private MentorViewModel mentorViewModel;
    private MentorListAdapter mentorListAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mentor_list, container, false);

        mentorListAdapter = new MentorListAdapter(this);

        recyclerView = view.findViewById(R.id.mentor_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setAdapter(mentorListAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mentorViewModel = ViewModelProviders.of(this).get(MentorViewModel.class);

        mentorViewModel.getMentors().observe(this, mentors -> mentorListAdapter.setData(mentors));
    }

    @Override
    public void onMentorClick(int position) {
        Intent intent = new Intent(getActivity(), MentorActivity.class);
        intent.putExtra(MentorActivity.MENTOR_SELECTED_ID_EXTRA, mentorListAdapter.getSelectedMentor(position).getId());

        startActivityForResult(intent, EDIT_MENTOR_REQUEST);
        startActivity(intent);

    }

}
