package com.bpalomino.goodmate;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

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
    @BindView(R.id.toolbarRent)
    Toolbar toolbarRent;

    private ArrayList<String> dataHeaders;
    private HashMap<String,ArrayList<RentItem>> dataChild;
    private RentSheetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rent_sheet);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarRent);
        getSupportActionBar().setTitle("Rent Sheet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //populate list with items
        populateList();

        // custom adapter for expandable listview
        adapter = new RentSheetAdapter(this, dataHeaders,dataChild);

        rentList.setAdapter(adapter);

        promptDialog();
    }

    private void promptDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        Button done = dialog.findViewById(R.id.dialogButtonDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = dialog.findViewById(R.id.dialog_input_view);

                //once group name finalized with done
                //init dataheaders and datachild
                dataHeaders.add(text.getText().toString());
                ArrayList<RentItem> items = new ArrayList<>();
                items.add(new RentItem(R.drawable.ic_add_circle_outline_24dp,0));
                dataChild.put(dataHeaders.get(dataHeaders.size()-1),items);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.tiAdd){
            promptDialog();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void populateList() {
        dataHeaders = new ArrayList<>();
        dataChild = new HashMap<>();
    }
}
