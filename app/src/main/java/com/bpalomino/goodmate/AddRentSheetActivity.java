package com.bpalomino.goodmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/*  This Activity serves to do the following:
    Use listviews to create a rent sheet
*/

public class AddRentSheetActivity extends AppCompatActivity {
    @BindView(R.id.addRent_list_view)
    ExpandableListView rentList;
    private ArrayList<RentGroup> groupItems;
    private ArrayList<String> dataHeaders;
    private HashMap<String,ArrayList<RentGroup>> dataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rent_sheet);
        ButterKnife.bind(this);

        //data source list
//        groupItems = new ArrayList<>();

        //need one starter blank item
//        groupItems.add(new RentGroup(R.drawable.ic_add_circle_outline_24dp,0));

        //populate list with items
        populateList();

        // custom adapter for expandable listview
        RentSheetAdapter adapter = new RentSheetAdapter(this, dataHeaders,dataChild);

        rentList.setAdapter(adapter);
    }

    private void populateList() {
        dataHeaders = new ArrayList<>();
        dataChild = new HashMap<>();

        //adding header data
        dataHeaders.add("Group Name");

        //adding child data
        ArrayList<RentGroup> items = new ArrayList<>();
        items.add(new RentGroup(R.drawable.ic_add_circle_outline_24dp,0));
        dataChild.put(dataHeaders.get(0),items);

    }
}
