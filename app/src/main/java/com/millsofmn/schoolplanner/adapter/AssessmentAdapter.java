package com.millsofmn.schoolplanner.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.R;
import com.millsofmn.schoolplanner.db.entity.Assessment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private static final DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
    private OnClickListener onClickListener;

    private List<Assessment> data;

    public AssessmentAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setData(List<Assessment> newData){
        if (data != null) {
            DataDiffCallBack dataDiffCallBack = new DataDiffCallBack(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(dataDiffCallBack);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data = newData;
        }
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
        tvTitle.setText(data.get(position).getTitle());

        TextView tvPerformanceType = cardView.findViewById(R.id.tv_ass_performance_type);
        tvPerformanceType.setText(data.get(position).getPerformanceType());


        TextView courseDates = cardView.findViewById(R.id.tv_ass_due_date);
        TextView alert = cardView.findViewById(R.id.tv_ass_alert);

        if(data.get(position).getDueDate() != null){
            courseDates.setText(dateFormat.format(data.get(position).getDueDate()));

            if(data.get(position).isAlertOnDueDate()){
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
        return data == null ? 0 : data.size();
    }

    public Assessment getItem(int position){
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
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

    class DataDiffCallBack extends DiffUtil.Callback {
        private final List<Assessment> oldData, newData;

        public DataDiffCallBack(List<Assessment> oldData, List<Assessment> newData) {
            this.oldData = oldData;
            this.newData = newData;
        }

        @Override
        public int getOldListSize() {
            return oldData.size();
        }

        @Override
        public int getNewListSize() {
            return newData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).getId() == newData.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
        }
    }
}
