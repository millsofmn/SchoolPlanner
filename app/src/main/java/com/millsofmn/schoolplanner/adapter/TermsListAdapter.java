package com.millsofmn.schoolplanner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.R;
import com.millsofmn.schoolplanner.db.entity.Term;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TermsListAdapter extends RecyclerView.Adapter<TermsListAdapter.ViewHolder> {

    private static final DateFormat dateFormat = new SimpleDateFormat("MMM yyyy");

    private List<Term> terms = new ArrayList<>();

    private OnTermListener onTermListener;
    public TermsListAdapter(OnTermListener onTermListener) {
        this.onTermListener = onTermListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_term_list_item, parent, false);

        return new ViewHolder(cardView, onTermListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView termTitle = cardView.findViewById(R.id.term_title);
        termTitle.setText(terms.get(position).getTitle());

        TextView termDates = cardView.findViewById(R.id.term_dates);
        if(terms.get(position).getStartDate() != null){
            termDates.setText(dateFormat.format(terms.get(position).getStartDate()) + " to " + dateFormat.format(terms.get(position).getEndDate()));
        }

    }

    @Override
    public  int getItemCount(){
        return terms.size();
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
        notifyDataSetChanged();
    }

    public Term getSelectedTerm(int position){
        if(!terms.isEmpty()){
            if(terms.size() > 0){
                return terms.get(position);
            }
        }
        return null;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView cardView;
        public OnTermListener onTermListener;
        public ViewHolder(@NonNull CardView cardView, OnTermListener onTermListener) {
            super(cardView);
            this.cardView = cardView;
            this.onTermListener = onTermListener;

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTermListener.onTermClick(getAdapterPosition());
        }
    }

    public interface OnTermListener {
        void onTermClick(int position);
    }
}
