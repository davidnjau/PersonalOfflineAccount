package com.dave.personalofflineapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.dave.personalofflineapp.Adapter.PersonalInfoAdapter;
import com.dave.personalofflineapp.DatabaseHelper.DatabaseHelperClass;
import com.dave.personalofflineapp.Pojo.PersonalDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AllPersons extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayout myview;
    private DatabaseHelperClass databaseLandAdmin;
    private SQLiteDatabase db;
    private PersonalInfoAdapter personalInfoAdapter;
    private ArrayList<PersonalDetails> personalInfoAdapterArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_persons);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myview = findViewById(R.id.myview);
        databaseLandAdmin = new DatabaseHelperClass(this);
        db = databaseLandAdmin.getReadableDatabase();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personalInfoAdapterArrayList = databaseLandAdmin.getPersonalDetails();
        personalInfoAdapter = new PersonalInfoAdapter(this, personalInfoAdapterArrayList);

        recyclerView.setAdapter(personalInfoAdapter);
        if (personalInfoAdapter.getItemCount() == 0){

            myview.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }else {

            myview.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
