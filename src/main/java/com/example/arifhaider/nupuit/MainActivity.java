package com.example.arifhaider.nupuit;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static android.R.attr.button;
import static android.app.PendingIntent.getActivity;
import static com.example.arifhaider.nupuit.R.styleable.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private recHandler adapter ;
    private Button button;
    private List<Contacts> data;
    private DatabaseHandler db;
    private static  int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(this);
        db.deleteData();
        insertData(db);
        getData();
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    recyclerView = (RecyclerView) findViewById(R.id.recycler);
                    adapter = new recHandler(MainActivity.this.getApplicationContext(), get10Data());
                    LinearLayoutManager l = new LinearLayoutManager(MainActivity.this.getApplicationContext());
                    recyclerView.setLayoutManager(l);
                    recyclerView.setAdapter(adapter);

            }
        });

    }




    public List<Contacts> get10Data(){
        int j;
        List<Contacts> data2 = Collections.emptyList() ;//
        data2 = new ArrayList<Contacts>();

        for(j = MainActivity.i;j<data.size() && j<MainActivity.i+10 ;j++)
            data2.add(data.get(j));

        if(j>=data.size()){
            button.setText("No More Data");
            MainActivity.i = 0;
        }
    else
        MainActivity.i=j;

        return data2;
    }

    public void getData(){
        data = Collections.emptyList();
        data = new ArrayList<Contacts>();
        i = 0;
        data = db.getAllContacts();
        return;

    }

    public void insertData(DatabaseHandler db){
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while(cursor.moveToNext()){
            String phoneNumber ="";
            String id = cursor.getString(cursor.getColumnIndex((ContactsContract.Contacts._ID)));
            String name = cursor.getString(cursor.getColumnIndex((ContactsContract.Contacts.DISPLAY_NAME)));

            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?" +
                    "",new String[] {id},null);
            while(phoneCursor.moveToNext()){
                phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            }
            db.addContact(new Contacts(name,phoneNumber));
        }
    }
}
