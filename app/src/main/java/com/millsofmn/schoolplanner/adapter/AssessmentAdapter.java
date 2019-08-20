package com.millsofmn.schoolplanner.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.R;
import com.millsofmn.schoolplanner.db.entity.Assessment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private static final DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
    private OnClickListener onClickListener;

    private List<Assessment> assessmentList;

    public AssessmentAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setAssessmentList(List<Assessment> assessmentList){
        this.assessmentList = assessmentList;
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_asst_list_item, parent, false);

        return new AssessmentViewHolder(cardView, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView tvTitle = cardView.findViewById(R.id.tv_ass_title);
        tvTitle.setText(assessmentList.get(position).getTitle());

        TextView tvPerformanceType = cardView.findViewById(R.id.tv_ass_performance_type);
        tvPerformanceType.setText(assessmentList.get(position).getPerformanceType());


        TextView courseDates = cardView.findViewById(R.id.tv_ass_due_date);
        TextView alert = cardView.findViewById(R.id.tv_ass_alert);

        if(assessmentList.get(position).getDueDate() != null){
            courseDates.setText(dateFormat.format(assessmentList.get(position).getDueDate()));

            if(assessmentList.get(position).isAlertOnDueDate()){
                alert.setText("Alert On");
            } else {
                alert.setText("Alert Off");
            }

        } else {
            courseDates.setVisibility(View.GONE);
            alert.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return assessmentList == null ? 0 : assessmentList.size();
    }

    public Assessment getItem(int position){
        return assessmentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return assessmentList.get(position).getId();
    }

    static class AssessmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public OnClickListener onClickListener;

        public AssessmentViewHolder(CardView cardView, OnClickListener onClickListener) {
            super(cardView);
            this.cardView = cardView;
            this.onClickListener = onClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(getAdapterPosition());
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }
}
