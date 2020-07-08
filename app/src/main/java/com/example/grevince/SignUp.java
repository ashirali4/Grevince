package com.example.grevince;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import api.ApiClient;
import api.ApiInterface;
import api.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {


    private EditText emailTV, passwordTV,testmail,fullname;
    private Button regBtn;
    private ProgressBar progressBar;
    public static ApiInterface appp;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        appp = ApiClient.getApiClient().create(ApiInterface.class);
        initializeUI();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password, name, mymail;
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();
        name = fullname.getText().toString();
        mymail = testmail.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        Call<User> call =appp.register(email,name,mymail,password);
        call.enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("okay")){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Registertion Successful Thanks!", Toast.LENGTH_LONG).show();
                   // progressBar.setVisibility(View.GONE);
                }
                else if(response.body().getResponse().equals("failed")){
                    Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network failed! Please try again later"+t, Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeUI() {
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);
        testmail = findViewById(R.id.testemail);
        fullname = findViewById(R.id.fullname);
        regBtn = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar);
    }
}
