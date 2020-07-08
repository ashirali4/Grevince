package com.example.grevince.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.grevince.Dashboard;
import com.example.grevince.R;
import com.example.grevince.addcompalain;

public class Departments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
    }

    public void cs(View view) {
        Intent intent = new Intent(Departments.this, addcompalain.class);
        Bundle b=new Bundle();
        b.putString("type","cs");
        intent.putExtras(b);
        startActivity(intent);
    }
    public void exams(View view) {
        Intent intent = new Intent(Departments.this, addcompalain.class);
        Bundle b=new Bundle();
        b.putString("type","exams");
        intent.putExtras(b);
        startActivity(intent);
    }
    public void dsa(View view) {
        Intent intent = new Intent(Departments.this, addcompalain.class);
        Bundle b=new Bundle();
        b.putString("type","dsa");
        intent.putExtras(b);
        startActivity(intent);
    }
    public void addmission(View view) {
        Intent intent = new Intent(Departments.this, addcompalain.class);
        Bundle b=new Bundle();
        b.putString("type","add");
        intent.putExtras(b);
        startActivity(intent);
    }
    public void lib(View view) {
        Intent intent = new Intent(Departments.this, addcompalain.class);
        Bundle b=new Bundle();
        b.putString("type","lib");
        intent.putExtras(b);
        startActivity(intent);
    }
    public void accounts(View view) {
        Intent intent = new Intent(Departments.this, addcompalain.class);
        Bundle b=new Bundle();
        b.putString("type","ac");
        intent.putExtras(b);
        startActivity(intent);
    }
    public void others(View view) {
        Intent intent = new Intent(Departments.this, addcompalain.class);
        Bundle b=new Bundle();
        b.putString("type","others");
        intent.putExtras(b);
        startActivity(intent);
    }

}
