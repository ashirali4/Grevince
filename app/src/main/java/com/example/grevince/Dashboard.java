package com.example.grevince;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.grevince.Activities.Departments;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        String name = prefs.getString("name", "No name defined");
        String userid = prefs.getString("userid", "No name defined");///"No name defined" is the default value.
        Toast.makeText(this, "User "+name+ " has been logged In.ID-"+userid, Toast.LENGTH_SHORT).show();
    }

    public void addcompalin(View view) {
        Intent intent = new Intent(Dashboard.this, Departments.class);
        startActivity(intent);
    }
    public void viewcomplains(View view) {
        Intent intent = new Intent(Dashboard.this, listview.class);
        startActivity(intent);
    }
}
