package com.example.grevince.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grevince.Adatpers.CustomAdapter;
import com.example.grevince.R;
import com.example.grevince.listview;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import api.ApiClient;
import api.ApiInterface;
import api.Responses;
import api.User;
import classes.Contact;
import classes.ContactsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApplicationTracking_Status extends AppCompatActivity {
    ArrayList<Responses> dataModels;
    TextView compnumber,compdate,rollname;
    MaterialButton statuscom;
    ListView listView;
    public static ApiInterface apppinsds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_tracking__status);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.mylist);
        dataModels= new ArrayList<>();

        apppinsds = ApiClient.getApiClient().create(ApiInterface.class);
        dataModels= new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        String compid = bundle.getString("compid");
        String ctime = bundle.getString("ctime");
        String userid = bundle.getString("userid");
        String status = bundle.getString("status");
        String name = bundle.getString("name");
        String reportid = bundle.getString("reportid");
        compnumber=findViewById(R.id.compidcode);
        compdate=findViewById(R.id.compdate);
        rollname=findViewById(R.id.compnameroll);
        statuscom=findViewById(R.id.statusbutton);
        String compfullid="Complain ID - #000"+compid;
        compnumber.setText(compfullid);
        compdate.setText(ctime);
        String fulluser="R-FSD-"+userid+" | "+name;
        rollname.setText(fulluser);
        if(status.equals("1")){
            status="SOLVED";
        }else{
            status="UNSOLVED";
        }
        statuscom.setText(status);
        // Initialize contacts





        Call<List<Responses>> call = apppinsds.getresponses(reportid);

        call.enqueue(new Callback<List<Responses>>() {
            @Override
            public void onResponse(Call<List<Responses>> call, Response<List<Responses>> response) {
                List<Responses> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getDfrom();
                    Toast.makeText(getApplicationContext(),""+   heroes[i], Toast.LENGTH_SHORT).show();
                    dataModels.add(heroList.get(i));

                }
                ContactsAdapter adapter = new ContactsAdapter(dataModels);
                // Attach the adapter to the recyclerview to populate items
                rvContacts.setAdapter(adapter);
                // Set layout manager to position the items





            }

            @Override
            public void onFailure(Call<List<Responses>> call, Throwable t) {

            }


        });
        rvContacts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // That's all!

    }


}
