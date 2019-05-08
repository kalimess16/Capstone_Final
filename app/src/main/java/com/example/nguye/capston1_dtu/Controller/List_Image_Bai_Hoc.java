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
import android.support.v7.widget.Toolbar;
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

        Toolbar toolbar = findViewById(R.id.toolbar_AnhBaiHoc);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
            if (name.equals("Đề mẫu Toán số 1")) { ////// toán
                deMauToanSo1();
            } else if (name.equals("Đề mẫu Toán số 2")) {
                deMauToanSo2();
            } else if (name.equals("Đề mẫu Toán số 3")) {
                deMauToanSo3();
            } else if (name.equals("Đề mẫu Anh Văn số 1")) {///// anh văn
                deMauAnhVan1();
            } else if (name.equals("Đề mẫu Anh Văn số 2")) {///// anh văn
                deMauAnhVan2();
            } else if (name.equals("Đề mẫu Anh Văn số 3")) {///// anh văn
                deMauAnhVan3();
            } else if (name.equals("Đề mẫu Anh Văn số 4")) {///// anh văn
                deMauAnhVan4();
            } else if (name.equals("Đề mẫu Anh Văn số 5")) {///// anh văn
                deMauAnhVan5();
            } else if (name.equals("Đề mẫu Văn số 1")) {///// Ngữ văn
                deMauNguVan1();
            } else if (name.equals("Đề mẫu Văn số 2")) {///// Ngữ văn
                deMauNguVan2();
            } else if (name.equals("Đề mẫu Lý năm 2017")) { /// Lý
                deMauLySo1();
            } else if (name.equals("Đề mẫu Lý năm 2018")) {
                deMauLySo2();
            } else if (name.equals("Đề mẫu Lý năm 2019_v1")) {
                deMauLySo3();
            } else if (name.equals("Đề mẫu Lý năm 2019_v2")) {
                deMauLySo4();
            } else if (name.equals("Đề mẫu Hóa số 1")) { //////// Hóa
                deMauHoaHoc1();
            } else if (name.equals("Đề mẫu Hóa số 2")) {
                deMauHoaHoc2();
            } else if (name.equals("Đề mẫu Hóa số 3")) {
                deMauHoaHoc3();
            } /*else if (name.equals("Đề mẫu Hóa số 4")) {
                deMauHoaHoc4();
            } else if (name.equals("Đề mẫu Hóa số 5")) {
                deMauHoaHoc5();
            }*/else if (name.equals("Đề mẫu Văn số 1")) {///// Ngữ văn
                deMauNguVan1();
            } else if (name.equals("Đề mẫu Văn số 2")) {
                deMauNguVan2();
            }else {
                Toast.makeText(getApplicationContext(), "dang cap nhat", Toast.LENGTH_LONG).show();
            }
        } else {
            mImageView_BH.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Vui lòng kết nối mạng", Toast.LENGTH_LONG).show();
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
    public void loadBaiHoc(){
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
                Log.d("Fail_BH", "Không kết nối được với firebase");
                Toast.makeText(getApplicationContext(), "Kết nối bị lỗi", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * cac bai mau mon toan
     */
    public void deMauToanSo1() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Toan").child("Bai Hoc").child("De so 1");
        loadBaiHoc();
    }

    public void deMauToanSo2() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Toan").child("Bai Hoc").child("đề số 2");
        loadBaiHoc();
    }

    public void deMauToanSo3() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Toan").child("Bai Hoc").child("đề số 3");
        loadBaiHoc();
    }


    /**
     * cac bai mau mon Ly
     */
    public void deMauLySo1() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Ly").child("De mau").child("De 2017");
        loadBaiHoc();
    }

    public void deMauLySo2() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Ly").child("De mau").child("De 2018");
        loadBaiHoc();
    }

    public void deMauLySo3() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Ly").child("De mau").child("De 2019_1");
        loadBaiHoc();
    }

    public void deMauLySo4() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Ly").child("De mau").child("De 2019_2");
        loadBaiHoc();
    }

    /**
     * Các đề mấu Tiếng ANh
     */
    public void deMauAnhVan1() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Anh Van").child("Bai Hoc").child("De so 1");
        loadBaiHoc();
    }
    public void deMauAnhVan2() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Anh Van").child("Bai Hoc").child("De so 2");
        loadBaiHoc();
    }
    public void deMauAnhVan3() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Anh Van").child("Bai Hoc").child("De so 3");
        loadBaiHoc();
    }
    public void deMauAnhVan4() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Anh Van").child("Bai Hoc").child("De so 4");
        loadBaiHoc();
    }
    public void deMauAnhVan5() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Anh Van").child("Bai Hoc").child("De so 5");
        loadBaiHoc();
    }
    public void deMauNguVan1() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Ngữ Văn").child("Bài Học").child("Đề số 1");
        loadBaiHoc();
    }
    public void deMauNguVan2() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Ngữ Văn").child("Bài Học").child("Đề số 2");
        loadBaiHoc();
    }
    public void deMauHoaHoc1() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Hoa Hoc").child("Bai Hoc").child("bài số 1");
        loadBaiHoc();
    }
    public void deMauHoaHoc2() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Hoa Hoc").child("Bai Hoc").child("bài số 2");
        loadBaiHoc();
    }
    public void deMauHoaHoc3() {
        mRefence = mDatabase.getReference("Mon Hoc").child("Hoa Hoc").child("Bai Hoc").child("bài số 3");
        loadBaiHoc();
    }
}
