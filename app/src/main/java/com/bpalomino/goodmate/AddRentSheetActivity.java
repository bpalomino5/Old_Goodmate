package com.bpalomino.goodmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddRentSheetActivity extends AppCompatActivity {
    @BindView(R.id.addRent_list_view) ListView rentList;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rent_sheet);
        ButterKnife.bind(this);

        //data source list
        items = new ArrayList<>();

        //need one starter blank item
        items.add("");

        // custom adapter for listview
        RentSheetAdapter adapter = new RentSheetAdapter(this, items);
        rentList.setAdapter(adapter);
    }
}
