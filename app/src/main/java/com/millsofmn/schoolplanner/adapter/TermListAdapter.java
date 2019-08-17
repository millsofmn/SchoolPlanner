package com.millsofmn.schoolplanner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.R;
import com.millsofmn.schoolplanner.data.Term;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TermListAdapter extends RecyclerView.Adapter<TermListAdapter.TermViewHolder> {

    private static final DateFormat dateFormat = new SimpleDateFormat("MMM yyyy");

    public static class TermViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public TermViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }


    }

    private final LayoutInflater inflater;
    private List<Term> terms = new ArrayList<>();
    private Listener listener;

    public TermListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cardView = (CardView) inflater.inflate(R.layout.term_view_item, parent, false);

        return new TermViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView termTitle = cardView.findViewById(R.id.term_title);
        termTitle.setText(terms.get(position).getTitle());

        TextView termDates = cardView.findViewById(R.id.term_dates);
        termDates.setText(dateFormat.format(terms.get(position).getStartDate()) + " to " + dateFormat.format(terms.get(position).getEndDate()));

        cardView.setOnClickListener((v) -> {
            if(listener != null){
                listener.onClick(position);
            }
        });
    }

    @Override
    public  int getItemCount(){
        return terms.size();
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onClick(int position);
    }
}

