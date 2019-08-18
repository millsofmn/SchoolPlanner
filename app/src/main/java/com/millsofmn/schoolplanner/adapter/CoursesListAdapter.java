package com.millsofmn.schoolplanner.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.data.Course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CoursesListAdapter extends RecyclerView.Adapter {

    private static final DateFormat dateFormat = new SimpleDateFormat("MMM yyyy");

    private List<Course> courses = new ArrayList<>();
    private OnCourseListener onCourseListener;

    public CoursesListAdapter(OnCourseListener onCourseListener) {
        this.onCourseListener = onCourseListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        CardView cardView = (CardView)
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public OnCourseListener onCourseListener;

        public ViewHolder(@NonNull View itemView, CardView cardView, OnCourseListener onCourseListener) {
            super(itemView);
            this.cardView = cardView;
            this.onCourseListener = onCourseListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCourseListener.onCourseClick(getAdapterPosition());
        }
    }

    public interface OnCourseListener {
        void onCourseClick(int position);
    }
}
