package com.millsofmn.schoolplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    private Button btnNavMentors;
    private Button btmNavMangeTerm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnNavMentors = view.findViewById(R.id.btn_nav_mentor);
        btmNavMangeTerm = view.findViewById(R.id.btn_nav_terms);

        btnNavMentors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MentorActivity.class);
                startActivity(intent);
            }
        });

        btmNavMangeTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TermsListActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
