package com.bpalomino.goodmate;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
    private ArrayList<RentGroup> dataSource;

    @BindView(R.id.groupView) EditText editText;
    @BindView(R.id.groupAddButton) ImageButton addGroupButton;

    public RentSheetAdapter(Context context, ArrayList<RentGroup> dataSource) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        //all the implementation happens here
        View rowView = inflater.inflate(R.layout.list_rent_add_item, viewGroup, false);
        ButterKnife.bind(this, rowView);

        //get item from datasource
        RentGroup item = (RentGroup) getItem(i);

        //set edittext to its group name
        editText.setText(item.group);

        //listener to see when text is inputted by user
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //update data source on new group name
                dataSource.get(i).group = editable.toString().trim();
            }
        });

        //set image from data source
        addGroupButton.setImageResource(item.imageRes);
        addGroupButton.setTag(item.tag);

        //listener to see if button selected
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //identify which button it is - add/remove
                ImageButton button = (ImageButton) view;
                if((int)button.getTag() == 0)
                    dataSource.add(new RentGroup(R.drawable.ic_remove_circle_outline_24dp,1));
                else{
                    dataSource.remove(i);
                }
                //update user's screen with changes
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}
