package com.example.nguye.capston1_dtu.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.nguye.capston1_dtu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VideoLoad extends AppCompatActivity {
    public static final String MY_NAME = "Video";
    private String name;
    TextView mTextView;
    VideoView Video;
    TextView url;
    ProgressDialog pd;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference();
    private DatabaseReference mRef=reference.child("url");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Toolbar toolbar = findViewById(R.id.toolbar_Video);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        name = intent.getStringExtra(MY_NAME);
        mTextView = findViewById(R.id.de);
        mTextView.setText(name);
        Video=(VideoView)findViewById(R.id.video);
        pd= new ProgressDialog(VideoLoad.this);
        url=(TextView)findViewById(R.id.text);
        pd.setMessage("Đang tải");
        pd.show();
        inView();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void inView(){
        if(isOnline()){
           if(name.equals("Now You See Me")){
               nowYouSeeMe();
           }
        }
        else {
            Toast.makeText(getApplicationContext(), "Vui lòng kết nối mạng", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * check online
     */
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
    public void nowYouSeeMe(){
        mRef = firebaseDatabase.getReference("Video").child("Now You");
        loadVideo();

    }
    public void loadVideo(){
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String message= dataSnapshot.child("video").getValue(String.class);
                Uri uri = Uri.parse(message);
                Video.setVideoURI(uri);
                Video.start();
                Video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        pd.dismiss();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
