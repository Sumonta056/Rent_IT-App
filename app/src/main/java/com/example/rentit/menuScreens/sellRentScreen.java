package com.example.rentit.menuScreens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rentit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class sellRentScreen extends AppCompatActivity {

    // image uploading and loading
    Button upload;
    ImageView img;
    Uri imageUri;
    int IMAGE_REQ = 2;
    // image uploading and loading

    // product info and database
    EditText name;
    EditText description;
    EditText rating;
    EditText address;
    EditText price;
    Button post;
    FirebaseFirestore firebaseFirestore;
    Uri test;
    // product info and database

    String emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        // hide the title bar

        // login info passing
        Intent intents = getIntent();
        emails = intents.getStringExtra("emails");
        // login info passing

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sell_rent);

        // image uploading and loading
        upload = findViewById(R.id.select_img);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
                //uploadImage();
            }
        });
        // image uploading and loading

        // posting info database
        name = findViewById(R.id.rent_name);
        description = findViewById(R.id.rent_des);
        rating = findViewById(R.id.rent_rating);
        address = findViewById(R.id.rent_address);
        price = findViewById(R.id.rent_price);

        firebaseFirestore = FirebaseFirestore.getInstance();

        post = findViewById(R.id.post_it);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                Intent intent = new Intent(sellRentScreen.this , menu.class );
                // pass login data to menu
                intent.putExtra("emails" ,emails);
                // pass login data to menu
                startActivity(intent); // go to sell screen
            }
        });
        // posting info into database

    }

    private void insertData() {

        // uploading progress
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();
        // uploading progress


        // adding to database
        Map<String,String > items = new HashMap<>();
        items.put("name", name.getText().toString());
        items.put("description", description.getText().toString());
        items.put("rating", rating.getText().toString());
        items.put("price",price.getText().toString());
        items.put("image",test.toString());

        firebaseFirestore.collection("Products").add(items)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        pd.dismiss();
                        Toast.makeText(sellRentScreen.this,"Post Successful", Toast.LENGTH_SHORT).show();
                    }
                });

        //pushing status
        Map<String,String > item = new HashMap<>();
        item.put("name", name.getText().toString());
        item.put("description", description.getText().toString());
        item.put("rating", rating.getText().toString());
        item.put("address",price.getText().toString());
        item.put("image",test.toString());
        item.put("type","Paid");
        item.put("head","Rented By You");

        firebaseFirestore.collection(emails).add(item);

        // adding to database
    }

    // Getting the image from gallery
    private void openImage() {

        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQ);
        // request for image selection
    }
    // check if any request for image has come or not
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQ && resultCode == RESULT_OK)
        {
            imageUri = data.getData();

            // loading img to IMage view
            img = findViewById(R.id.rent_img);
            Glide.with(sellRentScreen.this).load(imageUri).into(img);
            // loading  img to IMage view

            uploadImage();
            //  got the image data now upload it to fire - store
        }
    }
    // check if any request for image has come or not

    // Getting the image from gallery

    // getting the image extension type
    private String getFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
    // getting the image extension type

    // Uploading the image to fire storage
    private void uploadImage() {

        // uploading progress
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();
        // uploading progress


        if (imageUri != null) // check null string
        {
            // creating a folder name upload and its reference
            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("uploads").child(System.currentTimeMillis() + "."+ getFileExtension(imageUri));

            // pushing the image to upload folder
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    // getting the url of image
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            // upload done confirmation
                            String url = uri.toString();
                            Log.d("Download" ,url);
                            test = uri;
                            pd.dismiss();
                            Toast.makeText(sellRentScreen.this,"Image Upload Successful", Toast.LENGTH_SHORT).show();
                            // upload done confirmation

                        }
                    });
                    // getting the url of image

                }
            });
            // pushing the image to upload folder

        }
    }
    // Uploading the image to fire storage


    public void onBackPressed() {

        Intent intent = new Intent(sellRentScreen.this, menu.class);
        // pass login data to menu
        intent.putExtra("emails", emails);
        // pass login data to menu
        startActivity(intent); // go to sell screen

        super.onBackPressed();
    }
}