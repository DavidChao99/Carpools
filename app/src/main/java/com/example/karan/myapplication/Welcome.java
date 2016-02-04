package com.example.karan.myapplication;

import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.ActionBar;

import java.util.ArrayList;

/**
 * Created by Karan on 10/8/15.
 */
public class Welcome extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ParseUser currentUser = ParseUser.getCurrentUser();
        ArrayList<String> al = new ArrayList<String>();
        EditText et = (EditText) findViewById(R.id.grouptext);
        ListView lv = (ListView)findViewById(android.R.id.list);
        String time;
        if(currentUser.containsKey("Time") && currentUser.containsKey("Names") && currentUser.containsKey("Numbers"))
        {
            time = (String) currentUser.get("Time");
            al = (ArrayList<String>) currentUser.get("Names");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    al);
            lv.setAdapter(arrayAdapter);
        }
        else
        {
            et.setText("Add a Group with the Plus Button ↑!");
        }





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.plus:
            {
                Intent intent = new Intent(Welcome.this, AddGroup.class);
                startActivity(intent);
                finish();
            }
                break;
            default:
                break;
        }

        return true;
    }

}