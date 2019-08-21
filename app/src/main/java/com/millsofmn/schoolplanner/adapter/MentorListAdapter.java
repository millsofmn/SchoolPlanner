package com.millsofmn.schoolplanner.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.R;
import com.millsofmn.schoolplanner.db.entity.MentorWithEmbedded;

import java.util.ArrayList;
import java.util.List;

public class MentorListAdapter extends RecyclerView.Adapter<MentorListAdapter.ViewHolder> {
    public static final String TAG = "++++++++MentorListAdapter";

    private List<MentorWithEmbedded> data = new ArrayList<>();

    private OnMentorListener onMentorListener;

    public MentorListAdapter(OnMentorListener onMentorListener) {
        this.onMentorListener = onMentorListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mentor_list_item, parent, false);

        return new ViewHolder(cardView, onMentorListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        Log.e(TAG, data.get(position).toString());
        TextView mentorName = cardView.findViewById(R.id.tv_mentor_name);
        mentorName.setText(data.get(position).mentor.getName());

        TextView phoneNumbers = cardView.findViewById(R.id.tv_mentor_phone);
        phoneNumbers.setText(TextUtils.join("\n", data.get(position).phoneNumbers));

        TextView emailAddresses = cardView.findViewById(R.id.tv_mentor_emails);
        emailAddresses.setText(TextUtils.join("\n", data.get(position).emails));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<MentorWithEmbedded> newData){
        if(data != null) {
            DataDiffCallBack dataDiffCallBack = new DataDiffCallBack(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(dataDiffCallBack);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data = newData;
        }
    }

    public MentorWithEmbedded getSelectedMentor(int position){
        if(!data.isEmpty()){
            if(data.size() > 0){
                return data.get(position);
            }
        }
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public OnMentorListener onMentorListener;

        public ViewHolder(@NonNull CardView cardView, OnMentorListener onMentorListener) {
            super(cardView);
            this.cardView = cardView;
            this.onMentorListener = onMentorListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMentorListener.onMentorClick(getAdapterPosition());
        }
    }

    public interface OnMentorListener {
        void onMentorClick(int position);
    }

    class DataDiffCallBack extends DiffUtil.Callback {
        private final List<MentorWithEmbedded> oldData, newData;

        public DataDiffCallBack(List<MentorWithEmbedded> oldData, List<MentorWithEmbedded> newData) {
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
            return oldData.get(oldItemPosition).mentor.getId() == newData.get(newItemPosition).mentor.getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
        }
    }
}
