package com.millsofmn.schoolplanner;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.millsofmn.schoolplanner.domain.Term;

import java.util.List;

class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    private List<Term> terms;

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

        TextView textView = (TextView)cardView.findViewById(R.id.term_title);
        textView.setText(terms.get(position).getTitle());
    }

    @Override
    public  int getItemCount(){
        return terms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}

