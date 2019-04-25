package com.example.nguye.capston1_dtu.Controller;

import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nguye.capston1_dtu.Adapter_controller.AnhAdapter;
import com.example.nguye.capston1_dtu.Model.Anh;
import com.example.nguye.capston1_dtu.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.InetAddress;
import java.util.ArrayList;


public class List_Image_cong_thuc extends AppCompatActivity {
    public static final String MY_NAME = "capstone";
    private String name;
    TextView mTextView;
    RecyclerView mRecyclerView;
    ImageView mImageView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;
    ArrayList<Anh> listArray;
    AnhAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_anh_cong_thuc);
        Intent intent = getIntent();
        name = intent.getStringExtra(MY_NAME);
        mTextView = findViewById(R.id.de);
        mImageView = findViewById(R.id.mDisconnectV);
        // RecyclerView
        mRecyclerView = findViewById(R.id.mReV);
        mRecyclerView.setHasFixedSize(true);
        // set text
        mTextView.setText(name);
        // array
        listArray = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // send to Query Fire base
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        inView();
    }

    /**
     * show cong thuc
     */
    private void inView() {
        if (isOnline()) {
            if (name.equals("Đạo Hàm")) {
                congThucToan1();
            } else if (name.equals("Tích Phân")) {
                congThucToan2();
            }else if (name.equals("Lượng giác")){
                congThucToan3();
            }else if(name.equals("1")){
                congThucHoaHoc1();
            }else if(name.equals("2")){
                congthucAnhvan1();
            }
            else {
                Toast.makeText(getApplicationContext(), "Cang Cap nhat!!", Toast.LENGTH_LONG).show();
            }
        } else {
            mImageView.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Vui long Ket noi InterNet", Toast.LENGTH_LONG).show();
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

    /**
     * cac cong thuc toan hoc
     */
    public void congThucToan1() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Toan").child("Cong Thuc").child("Đạo Hàm");
        final Anh[] anh = {null};
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String image = (String) snapshot.child("image").getValue();
                    anh[0] = new Anh(image);
                    listArray.add(anh[0]);
                }
                adapter = new AnhAdapter(List_Image_cong_thuc.this, listArray);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Fail", "ko connect dc vs Firebase");
                Toast.makeText(getApplicationContext(), "Fail connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void congThucToan2() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Toan").child("Cong Thuc").child("Tích Phân");
        final Anh[] anh = {null};
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String image = (String) snapshot.child("image").getValue();
                    anh[0] = new Anh(image);
                    listArray.add(anh[0]);
                }
                adapter = new AnhAdapter(List_Image_cong_thuc.this, listArray);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Fail", "ko connect dc vs Firebase");
                Toast.makeText(getApplicationContext(), "Fail connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void congThucToan3() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Toan").child("Cong Thuc").child("Lượng Giác");
        final Anh[] anh = {null};
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String image = (String) snapshot.child("image").getValue();
                    anh[0] = new Anh(image);
                    listArray.add(anh[0]);
                }
                adapter = new AnhAdapter(List_Image_cong_thuc.this, listArray);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Fail", "ko connect dc vs Firebase");
                Toast.makeText(getApplicationContext(), "Fail connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Cac cong thuc ly hoc
     */
    public void congThucVatLy1() {

    }

    public void congThucVatLy2() {

    }

    public void congThucVatLy3() {

    }
    public void congthucAnhvan1(){
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Anh Van").child("Cong Thuc").child("Thi");
        final Anh[] anh = {null};
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String image = (String) snapshot.child("image").getValue();
                    anh[0] = new Anh(image);
                    listArray.add(anh[0]);
                }
                adapter = new AnhAdapter(List_Image_cong_thuc.this, listArray);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Fail", "ko connect dc vs Firebase");
                Toast.makeText(getApplicationContext(), "Fail connection", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void congThucHoaHoc1(){
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Hoa Hoc").child("Cong Thuc").child("lop 10");
        final Anh[] anh = {null};
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String image = (String) snapshot.child("image").getValue();
                    anh[0] = new Anh(image);
                    listArray.add(anh[0]);
                }
                adapter = new AnhAdapter(List_Image_cong_thuc.this, listArray);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Fail", "ko connect dc vs Firebase");
                Toast.makeText(getApplicationContext(), "Fail connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}



