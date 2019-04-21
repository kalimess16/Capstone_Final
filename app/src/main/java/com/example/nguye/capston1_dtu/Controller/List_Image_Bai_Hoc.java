package com.example.nguye.capston1_dtu.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguye.capston1_dtu.Adapter_controller.BaiHocAdapter;
import com.example.nguye.capston1_dtu.Model.Hoc;
import com.example.nguye.capston1_dtu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class List_Image_Bai_Hoc extends AppCompatActivity {

    public static final String MY_NAME = "Capstone";
    private String name;
    ImageView mImageView_BH;
    TextView mTextView;
    RecyclerView mRev;
    FirebaseDatabase mDatabase;
    DatabaseReference mRefence;
    ArrayList<Hoc> listHoc;
    BaiHocAdapter mBaiHocAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_anh_bai_hoc);
        // Anh Xa
        AnhXA();
        // get ten
        Intent intent = getIntent();
        name = intent.getStringExtra(MY_NAME);
        mTextView.setText(name);
        // Recycler View
        mRev.setHasFixedSize(true);
        listHoc = new ArrayList<>();
        mRev.setLayoutManager(new LinearLayoutManager(this));
        // send Query to Firebase
        mDatabase = FirebaseDatabase.getInstance();
        inView(); // load bai hoc len activity
    }

    /**
     * lấy thông tin từ firebase và bỏ vào adapter
     */
    private void inView() {
        getDeToan();
    }

    /**
     * lấy thông tin đề số 1 môn toán từ firebase
     */
    private void getDeToan() {
        if (isOnline()) {
            if (name.equals("Đề mẫu Toán số 1")) {
                deMauToanSo1();
            } else if (name.equals("Đề mẫu Toán số 2")) {
                deMauToanSo2();
            } else if (name.equals("Đề mẫu Toán số 3")) {
                deMauToanSo3();
            } else {
                Toast.makeText(getApplicationContext(), "dang cap nhat", Toast.LENGTH_LONG).show();
            }
        } else {
            mImageView_BH.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Vui long Ket noi InterNet", Toast.LENGTH_LONG).show();
        }

    }


    /**
     * kiểm tra có đang sử sụng mạng hay ko?
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


    private void AnhXA() {
        mImageView_BH = findViewById(R.id.m_DisconnectV);
        mTextView = findViewById(R.id.demo_bai_hoc);
        mRev = findViewById(R.id.m_ReV_bai_hoc);
    }

    /**
     * cac bai mau mon toan
     */
    public void deMauToanSo1() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Toan").child("Bai Hoc").child("De so 1");
        final Hoc[] hocs = {null};
        mRefence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String image = (String) snapshot.child("image").getValue();
                    hocs[0] = new Hoc(image);
                    listHoc.add(hocs[0]);
                }
                mBaiHocAdapter = new BaiHocAdapter(List_Image_Bai_Hoc.this, listHoc);
                mRev.setAdapter(mBaiHocAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Fail_BH", "ko connect dc vs Firebase");
                Toast.makeText(getApplicationContext(), "Fail connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deMauToanSo2() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Toan").child("Bai Hoc").child("đề số 2");
        final Hoc[] hocs = {null};
        mRefence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String image = (String) snapshot.child("image").getValue();
                    hocs[0] = new Hoc(image);
                    listHoc.add(hocs[0]);
                }
                mBaiHocAdapter = new BaiHocAdapter(List_Image_Bai_Hoc.this, listHoc);
                mRev.setAdapter(mBaiHocAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Fail_BH", "ko connect dc vs Firebase");
                Toast.makeText(getApplicationContext(), "Fail connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deMauToanSo3() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Toan").child("Bai Hoc").child("đề số 3");
        final Hoc[] hocs = {null};
        mRefence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String image = (String) snapshot.child("image").getValue();
                    hocs[0] = new Hoc(image);
                    listHoc.add(hocs[0]);
                }
                mBaiHocAdapter = new BaiHocAdapter(List_Image_Bai_Hoc.this, listHoc);
                mRev.setAdapter(mBaiHocAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Fail_BH", "ko connect dc vs Firebase");
                Toast.makeText(getApplicationContext(), "Fail connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deMauToanSo4() {

    }

    public void deMauToanSo5() {

    }

    /**
     * cac bai mau mon Ly
     */
    public void deMauLySo1() {

    }

    public void deMauLySo2() {

    }

}
