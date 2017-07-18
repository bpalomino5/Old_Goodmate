package com.bpalomino.goodmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bpalomino on 7/17/17.
 */

public class RentSheetAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> dataSource;

    @BindView(R.id.groupView) EditText editText;
    @BindView(R.id.groupAddButton) ImageButton addGroupButton;

    public RentSheetAdapter(Context context, ArrayList<String> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //all the implementation happens here
        View rowView = inflater.inflate(R.layout.list_rent_add_item, viewGroup, false);
        ButterKnife.bind(this, rowView);

        String item = (String) getItem(i);
        editText.setText(item);

        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSource.add("");
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}
