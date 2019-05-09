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
import android.support.v7.widget.Toolbar;
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

        Toolbar toolbar = findViewById(R.id.toolbar_AnhCongThuc);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * show cong thuc
     */
    private void inView() {
        if (isOnline()) {
            if (name.equals("Đạo Hàm")) { //// toan
                congThucToan1();
            } else if (name.equals("Tích Phân")) {
                congThucToan2();
            } else if (name.equals("Lượng giác")) {
                congThucToan3();
            } else if (name.equals("Câu tường thuật")) { ///// anh van
                congthucAnhvan1();
            } else if (name.equals("Câu điều kiện")) {
                congThucAnhVan2();
            } else if (name.equals("Thì")) {
                congThucAnhVan3();
            } else if (name.equals("Ngữ Pháp")) {
                congThucAnhVan4();
            } else if (name.equals("Loại từ")) {
                congThucAnhVan5();
            } else if (name.equals("Nguyên tắc trọng âm")) {
                congThucAnhVan6();
            } else if (name.equals("Các từ định lượng")) {
                congThucAnhVan7();
            } else if (name.equals("Tổng hợp công thức Lý lớp 10")) { ////////// ly
                congThucVatLy1();
            } else if (name.equals("Tổng hợp công thức Lý lớp 11")) {
                congThucVatLy2();
            } else if (name.equals("Tổng hợp công thức Lý lớp 12")) {
                congThucVatLy3();
            } else if (name.equals("Chất điện li")) { ///////// Môn Hóa////////////
                congThucHoaHoc1();
            } else if (name.equals("Tổng hợp công thức kim loại")) {
                congThucHoaHoc2();
            } else if (name.equals("Tổng hợp công thức POLYME")) {
                congThucHoaHoc3();
            } else if (name.equals("Cơ chế tổng hợp ARN")) {//// Môn Sinh//////////
                congThucSinhHoc1();
            } else if (name.equals("Cơ chế tổng hợp protein")) {
                congThucSinhHoc2();
            } else if (name.equals("Cấu trúc ADN")) {
                congThucSinhHoc3();
            } else if (name.equals("Di truyền và biến dị")) {
                congThucSinhHoc4();

            } else {
                Toast.makeText(getApplicationContext(), "Dữ liệu chưa được cập nhật", Toast.LENGTH_LONG).show();
            }
        } else {
            mImageView.setVisibility(View.VISIBLE);
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

    /**
     * cac cong thuc toan hoc
     */
    public void loadCongThuc(){
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
                Log.d("Fail", "Không kết nối được với firebase");
                Toast.makeText(getApplicationContext(), "Kết nối bị lỗi", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void congThucToan1() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Toan").child("Cong Thuc").child("Đạo Hàm");
        loadCongThuc();
    }

    public void congThucToan2() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Toan").child("Cong Thuc").child("Tích Phân");
        loadCongThuc();
    }

    public void congThucToan3() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Toan").child("Cong Thuc").child("Lượng Giác");
        loadCongThuc();
    }

    /**
     * Cac cong thuc ly hoc
     */
    public void congThucVatLy1() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Ly").child("Cong Thuc").child("Lop 10");
        loadCongThuc();
    }

    public void congThucVatLy2() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Ly").child("Cong Thuc").child("Lop 11");
        loadCongThuc();
    }

    public void congThucVatLy3() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Ly").child("Cong Thuc").child("Lop 12");
        loadCongThuc();
    }

    /**
     * Các Công Thức ANh văn
     */
    public void congthucAnhvan1() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Anh Van").child("Cong Thuc").child("Câu tường thuật");
        loadCongThuc();
    }
    public void congThucAnhVan2() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Anh Van").child("Cong Thuc").child("Câu điều kiện");
        loadCongThuc();
    }
    public void congThucAnhVan3() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Anh Van").child("Cong Thuc").child("Thi");
        loadCongThuc();
    }
    public void congThucAnhVan4(){
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Anh Van").child("Cong Thuc").child("Ngữ pháp");
        loadCongThuc();
    }
    public void congThucAnhVan5(){
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Anh Van").child("Cong Thuc").child("Loại từ");
        loadCongThuc();
    }
    public void congThucAnhVan6(){
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Anh Van").child("Cong Thuc").child("Nguyên tắc trọng âm");
        loadCongThuc();
    }
    public void congThucAnhVan7(){
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Anh Van").child("Cong Thuc").child("Các từ định lượng");
        loadCongThuc();
    }

    /**
     * Các Công Thức Hóa Học
     */
    public void congThucHoaHoc1() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Hoa Hoc").child("Cong thuc").child("Chat dien li");
        loadCongThuc();
    }
    public void congThucHoaHoc2() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Hoa Hoc").child("Cong thuc").child("Kim Loai");
        loadCongThuc();
    }
    public void congThucHoaHoc3() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Hoa Hoc").child("Cong thuc").child("POLYME");
        loadCongThuc();
    }
    /**
     * Các công thức Sinh Học
     */
    public void congThucSinhHoc1() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Sinh Học").child("Công Thức").child("Cơ chế tổng hợp ARN");
        loadCongThuc();
    }
    public void congThucSinhHoc2() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Sinh Học").child("Công Thức").child("Cơ chế tổng hợp protetin");
        loadCongThuc();
    }
    public void congThucSinhHoc3() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Sinh Học").child("Công Thức").child("Cấu trúc ADN");
        loadCongThuc();
    }
    public void congThucSinhHoc4() {
        myRef = mFirebaseDatabase.getReference("Mon Hoc").child("Sinh Học").child("Công Thức").child("Di truyền và biến dị");
        loadCongThuc();
    }

}



