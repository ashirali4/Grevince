package com.example.grevince;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import api.ApiClient;
import api.ApiInterface;
import api.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private EditText emailTV, passwordTV;
    private Button loginBtn;
    private ProgressBar progressBar;
    public static ApiInterface apiInterface;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        mAuth = FirebaseAuth.getInstance();

        initializeUI();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }
        Call<User> call =apiInterface.performLogin(email,password);
        call.enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("okay")){
                    progressBar.setVisibility(View.GONE);
                    SharedPreferences sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("username",response.body().getUsername() );
                    editor.putString("name",response.body().getName());
                    editor.putString("email", response.body().getEmail());
                    editor.putString("userid", response.body().getUserid());
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                }
                else if(response.body().getResponse().equals("failed")){
                    Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Login failed! Please try again later"+t, Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.GONE);
            }
        });


    }

    private void initializeUI() {
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);

        loginBtn = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
    }

    public void Chnage(View view) {
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);

    }

    public void camer(View view) {

    }

}
