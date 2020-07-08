package com.example.grevince;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.grevince.Adatpers.GalleryAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import api.ApiClient;
import api.ApiInterface;
import api.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addcompalain extends AppCompatActivity {
    String username,fullname,email,userid,department,category,subject,message;
    FirebaseDatabase mDatabase;
    DatabaseReference mDbRef;
    EditText subjecttt,messageee;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery;
    ArrayList<String> items=new ArrayList<String>();
    String type;
    private GalleryAdapter galleryAdapter;
    Spinner departmentttt,cate;
    private FirebaseAuth mAuth;
    public static ApiInterface myappiitoaddocm;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcompalain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        myappiitoaddocm = ApiClient.getApiClient().create(ApiInterface.class);
        subjecttt=findViewById(R.id.compsbuctt);
        messageee=findViewById(R.id.compmessage);
        cate=findViewById(R.id.compcateegory);
//        setSupportActionBar(toolbar);
        Intent i=getIntent();
        String cs="cs";
        String exams="exams";
        String dsa="dsa";
        String add="add";
        String lib="lib";
        String ac="ac";
        String others="others";


        type = getIntent().getExtras().getString("type");
        if(type.equals(cs)){
            items.add("General");
            items.add("Leave");
            items.add("Time Table");
            items.add("Enrollment");
            items.add("Quality Assurance");
            items.add("Fine");
            items.add("Ill Treatment");
            items.add("Lack of Facilites");
            items.add("Others");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);
            cate.setAdapter(adapter);
        }
        if(type.equals(exams)){
            items.add("General");
            items.add("ReCheck papers");
            items.add("Transcript");
            items.add("DMC");
            items.add("Others");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);
            cate.setAdapter(adapter);
        }
        if(type.equals(dsa)){
            items.add("General");
            items.add("Fee Concession");
            items.add("ScholarShips");
            items.add("Fine Issues");
            items.add("Others");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);
            cate.setAdapter(adapter);
        }
        if(type.equals(add)){
            items.add("General");
            items.add("New Admission");
            items.add("Others");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);
            cate.setAdapter(adapter);
        }
        if(type.equals(lib)){
            items.add("General");
            items.add("Issue Book");
            items.add("Book lost");
            items.add("Others");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);
            cate.setAdapter(adapter);
        }
        if(type.equals(ac)){
            items.add("General");
            items.add("Fee/Dues");
            items.add("Fee Vouchar");
            items.add("Installment Issues");
            items.add("Others");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);
            cate.setAdapter(adapter);
        }
        if(type.equals(others)){
            items.add("Hostel Issues");
            items.add("Cafeteria Issues");
            items.add("PhotoCopy Shop issue");
            items.add("Water and light Issues");
            items.add("Others");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);
            cate.setAdapter(adapter);
        }



        gvGallery = (GridView)findViewById(R.id.gv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setBackgroundDrawableResource(R.drawable.sd) ;
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Add a new Complain </font>"));

//        department=findViewById(R.id.department);

        mDatabase= FirebaseDatabase.getInstance();
        mDbRef= mDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();


    }


    public void chooseimage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();

                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(),mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter = new GalleryAdapter(getApplicationContext(),mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addcomplainto(View view) {

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        fullname = prefs.getString("name", "No name defined");
        username = prefs.getString("username", "No name defined");
        userid = prefs.getString("userid", "No name defined");
        email = prefs.getString("email", "No name defined");///"No name defined" is the default value.
        subject=subjecttt.getText().toString();
        message=messageee.getText().toString();
        category=cate.getSelectedItem().toString();

        department=type;


        Call<User> call =myappiitoaddocm.addcomplain(username,fullname,email,userid,department,category,subject,message,"No");
        call.enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("okay")){

                    Toast.makeText(getApplicationContext(), "Complains Added Successfully", Toast.LENGTH_LONG).show();

                }
                else if(response.body().getResponse().equals("failed")){
                    Toast.makeText(getApplicationContext(), "Failed to add complains", Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network failed! Please try again later"+t, Toast.LENGTH_LONG).show();


            }
        });
    }
}
