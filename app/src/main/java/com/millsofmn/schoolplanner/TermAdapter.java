package com.millsofmn.schoolplanner;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.domain.Term;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    DateFormat dateFormat = new SimpleDateFormat("MMM yyyy");
    
    private List<Term> terms;
    private Listener listener;

    public TermAdapter(List<Term> terms) {
        this.terms = terms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.term_view, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView termTitle = (TextView)cardView.findViewById(R.id.term_title);
        termTitle.setText(terms.get(position).getTitle());

        TextView termDates = (TextView)cardView.findViewById(R.id.term_dates);
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

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    interface Listener {
        void onClick(int position);
    }
}

