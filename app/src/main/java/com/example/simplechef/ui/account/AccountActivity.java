package com.example.simplechef.ui.account;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.simplechef.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.simplechef.R;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class AccountActivity extends AppCompatActivity {
    private static final String TAG = "AccountActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private FirebaseUser mCurrentUser;
    private FirebaseAuth mAuth;
    private TextView textViewUsername;
    private TextView textViewEmail;
    private ImageButton imageButtonPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        textViewUsername = findViewById(R.id.textViewUsername);
        textViewEmail = findViewById(R.id.textViewEmail);
        imageButtonPhoto = findViewById(R.id.imageButtonProfilePic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbarTitle = (TextView)findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        imageButtonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager())!= null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        setUserDataAndPhoto();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                imageButtonPhoto.setImageBitmap(bitmap);
                addProfilePictureToFirebase(bitmap);

        }
    }

    private void setUserDataAndPhoto() {
        String mUsername = mCurrentUser.getDisplayName();
        String mEmail = mCurrentUser.getEmail();
        String mPhotoURL;

        textViewUsername.setText(mUsername);
        textViewEmail.setText(mEmail);

        // sets photo from URL
        if (mCurrentUser.getPhotoUrl() != null) {
            mPhotoURL = mCurrentUser.getPhotoUrl().toString();
            Glide
                    .with(this)
                    .load(mPhotoURL)
                    .centerCrop()
                    .into(imageButtonPhoto);
        } else {
            Glide
                    .with(this)
                    .load(R.drawable.no_photo)
                    .centerCrop()
                    .into(imageButtonPhoto);

        }
    }

    private void addProfilePictureToFirebase(final Bitmap bitmap) {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference profilePicturesRef = storage.getReference().child("Users/" + mCurrentUser.getUid() + "/profile_pic.jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = profilePicturesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Failure uploading file");
                Toast.makeText(getApplicationContext(),"There was a problem uploading the image", Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d(TAG, "Success uploading file");

                Glide
                        .with(getApplicationContext())
                        .load(bitmap)
                        .centerCrop()
                        .into(imageButtonPhoto);


                profilePicturesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setPhotoUri(uri).build();

                        mCurrentUser.updateProfile(profileUpdates);
                    }
                });

            }
        });
    }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.action_signout:
            FirebaseAuth.getInstance().signOut();
            Intent signOutIntent = new Intent(AccountActivity.this, LoginActivity.class);
            startActivity(signOutIntent);
            break;
        default:
            break;
    }
    return true;
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }
}




