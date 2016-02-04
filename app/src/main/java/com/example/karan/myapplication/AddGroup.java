package com.example.karan.myapplication;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.parse.ParseUser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Karan on 2/2/16.
 */
public class AddGroup extends AppCompatActivity {
public ListView lv;
public SimpleAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgroup);
        getContacts();
        ParseUser pu = ParseUser.getCurrentUser();
        ArrayList<String> names = (ArrayList<String>) pu.get("Names");
        ArrayList<String> lastmessage = (ArrayList<String>) pu.get("Numbers");
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        String[] from = new String[] {"rowid", "col_1"};
        int[] to = new int[] { R.id.message_tv, R.id.time_tv};

        for(int i = 0; i < names.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("rowid", "" + names.get(i));
            map.put("col_1", "" + lastmessage.get(i));
            fillMaps.add(map);
        }
        lv = (ListView)findViewById(android.R.id.list);
        adapter = new SimpleAdapter(this, fillMaps, R.layout.list_item, from, to);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox cx = (CheckBox) view.findViewById(R.id.check);
                cx.toggle();
            }
        });
    }

    private void getContacts() {
        ParseUser pu = ParseUser.getCurrentUser();
        ArrayList<String> contactname = new ArrayList<String>();
        ArrayList<String> contactnumber = new ArrayList<String>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor people = getContentResolver().query(uri, projection, null, null, null);

        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        people.moveToFirst();
        do {
            String name = people.getString(indexName);
            String number = people.getString(indexNumber);
            contactname.add(name);
            pu.put("Names", contactname);
            pu.saveInBackground();
            contactnumber.add(number);
            pu.put("Numbers", contactnumber);
            pu.saveInBackground();
        } while (people.moveToNext());

    }

}




