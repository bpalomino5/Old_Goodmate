package com.bpalomino.goodmate;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by bpalomino on 7/10/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<View_Holder> {
    private List<ShareItem> list = Collections.emptyList();
    private Context context;

    public RecyclerViewAdapter(List<ShareItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        // Use provided view holder on the oncreateviewholder method to populate the current row on the recyclerview
        holder.title.setText(list.get(position).title);
        holder.description.setText(list.get(position).description);
        holder.imageView.setImageResource(list.get(position).imageId);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert new item to the recycler view on a predefined position
    public void insert(int position, ShareItem item) {
        list.add(position,item);
        notifyItemInserted(position);
    }

    // Remove a recycler view item containing a specified share item object
    public void remove(ShareItem item){
        int position = list.indexOf(item);
        list.remove(position);
        notifyItemRemoved(position);
    }
}
