package com.bpalomino.goodmate;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bpalomino on 7/17/17.
 */

public class RentSheetAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> dataHeaders;
    private HashMap<String, ArrayList<RentGroup>> dataChild;

//    private ArrayList<RentGroup> dataSource;

    public class ChildViewHolder {
        @BindView(R.id.groupView)
        EditText editText;
        @BindView(R.id.groupAddButton)
        ImageButton addGroupButton;
    }
    @BindView(R.id.listHeaderView)
    TextView listHeaderView;

    private ChildViewHolder childViewHolder;
    public RentSheetAdapter(Context context, ArrayList<String> dataHeaders, HashMap<String,ArrayList<RentGroup>> dataChild) {
        this.context = context;
        this.dataHeaders = dataHeaders;
        this.dataChild = dataChild;
        childViewHolder = new ChildViewHolder();
    }

    @Override
    public int getGroupCount() {
        return dataHeaders.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return dataChild.get(dataHeaders.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return dataHeaders.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataChild.get(dataHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String title = (String) getGroup(i);
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group,null);
        }
        ButterKnife.bind(this,view);


//        listHeaderView.setTypeface(null, Typeface.BOLD);

        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_rent_add_item, viewGroup, false);
        }
        ButterKnife.bind(childViewHolder, view);

        //get item from datasource
            RentGroup item = (RentGroup) getChild(i,i1);


            //set edittext to its group name
            childViewHolder.editText.setText(item.group);

            //listener to see when text is inputted by user
            childViewHolder.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    //update data source on new group name
                    dataChild.get(dataHeaders.get(i)).get(i1).group = editable.toString().trim();
                }
            });

            //set image from data source
            childViewHolder.addGroupButton.setImageResource(item.imageRes);
            childViewHolder.addGroupButton.setTag(item.tag);

            //listener to see if button selected
            childViewHolder.addGroupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //identify which button it is - add/remove
                    ImageButton button = (ImageButton) view;
                    if ((int) button.getTag() == 0)
                        dataChild.get(dataHeaders.get(i)).add(new RentGroup(R.drawable.ic_remove_circle_outline_24dp, 1));
                    else
                        dataChild.get(dataHeaders.get(i)).remove(i1);
                    //update user's screen with changes
                    notifyDataSetChanged();
                }
            });


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
