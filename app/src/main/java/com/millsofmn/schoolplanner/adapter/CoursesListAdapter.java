package com.millsofmn.schoolplanner.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.CourseFragment;
import com.millsofmn.schoolplanner.CoursesListActivity;
import com.millsofmn.schoolplanner.R;
import com.millsofmn.schoolplanner.data.Course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CoursesListAdapter extends RecyclerView.Adapter<CoursesListAdapter.ViewHolder> {

    private static final DateFormat dateFormat = new SimpleDateFormat("MMM yyyy");

    private List<Course> courses = new ArrayList<>();

    private OnCourseListener onCourseListener;

    public CoursesListAdapter(OnCourseListener onCourseListener) {
        this.onCourseListener = onCourseListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_course_item, parent, false);

        return new ViewHolder(cardView, onCourseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView courseTitle = cardView.findViewById(R.id.course_title);
        courseTitle.setText(courses.get(position).getTitle());

        TextView courseDates = cardView.findViewById(R.id.course_dates);
        if(courses.get(position).getStartDate() != null){
            courseDates.setText(dateFormat.format(courses.get(position).getStartDate()) + " to " + dateFormat.format(courses.get(position).getEndDate()));
        }

        TextView courseStatus = cardView.findViewById(R.id.course_status);
        courseStatus.setText(courses.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourses(List<Course> courses){
        this.courses = courses;
        notifyDataSetChanged();
    }

    public Course getSelectedCourse(int position){
        if(!courses.isEmpty()){
            if(courses.size() > 0){
                return courses.get(position);
            }
        }
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public OnCourseListener onCourseListener;

        public ViewHolder(@NonNull CardView cardView, OnCourseListener onCourseListener) {
            super(cardView);
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
