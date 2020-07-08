package com.example.grevince;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.grevince.Activities.ApplicationTracking_Status;
import com.example.grevince.Adatpers.CustomAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import api.ApiClient;
import api.ApiInterface;
import api.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listview extends AppCompatActivity {
    public static ApiInterface applicationinterface;
    ListView listView;
    ArrayList<User> dataModels;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        applicationinterface = ApiClient.getApiClient().create(ApiInterface.class);
        listView = (ListView) findViewById(R.id.viewcomplains);
        dataModels= new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        username = prefs.getString("username", "defaul");


        Call<List<User>> call = applicationinterface.getHeroes(username);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getCompid();
                    Toast.makeText(listview.this, ""+   heroes[i], Toast.LENGTH_SHORT).show();
                    dataModels.add(heroList.get(i));
                }

                CustomAdapter adapter= new CustomAdapter(dataModels,getApplicationContext());
                listView.setAdapter(adapter);
                //displaying the string array into listview
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        User dataModel= dataModels.get(position);

                        Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getCompid()+" API: "+dataModel.getCategory(), Snackbar.LENGTH_LONG)
                                .setAction("No action", null).show();

                        Intent intent = new Intent(getApplicationContext(), ApplicationTracking_Status.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("compid", dataModel.getCompid());
                        bundle.putString("ctime", dataModel.getCtime());
                        bundle.putString("userid", dataModel.getUserid());
                        bundle.putString("status", dataModel.getStatus());
                        bundle.putString("name", dataModel.getName());
                        bundle.putString("reportid", dataModel.getReportid());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }


        });


    }

    }

