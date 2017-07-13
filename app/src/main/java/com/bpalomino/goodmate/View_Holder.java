package com.bpalomino.goodmate;

import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bpalomino on 7/10/17.
 */

public class View_Holder extends RecyclerView.ViewHolder{
    CardView cardView;
    TextView title;
    TextView description;
    ImageView imageView;

    public View_Holder(View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        imageView = itemView.findViewById(R.id.imageView);
    }
}
