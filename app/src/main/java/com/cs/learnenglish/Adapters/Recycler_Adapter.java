package com.cs.learnenglish.Adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.learnenglish.R;
import com.cs.learnenglish.Word;

import java.util.Collections;
import java.util.List;


/**
 * Created by Qais Rasuli on 9/17/2019.
 */
public class Recycler_Adapter extends RecyclerView.Adapter<View_Holder>{
    private List<Word> list;
    private Context context;

    public Recycler_Adapter(List<Word> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new View_Holder(v);

    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.english.setText(list.get(position).getmEnglishTranslation());
        holder.farsi.setText(list.get(position).getmFarsiTranslation());
        holder.imageView.setImageResource(list.get(position).getImageResourceId());
        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Word data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Word data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

}

class View_Holder extends RecyclerView.ViewHolder {

     CardView cv;
     TextView english;
     TextView farsi ;
     ImageView imageView;

    View_Holder(View itemView) {
        super(itemView);
        cv = itemView.findViewById(R.id.cardView);
        english = itemView.findViewById(R.id.english_text_view);
        farsi =  itemView.findViewById(R.id.farsi_text_view);
        imageView =  itemView.findViewById(R.id.imageView);
    }
}