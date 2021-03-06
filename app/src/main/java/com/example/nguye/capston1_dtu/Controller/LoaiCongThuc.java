package com.example.nguye.capston1_dtu.Controller;


import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.nguye.capston1_dtu.Adapter_controller.LoaiCTAdapter;
import com.example.nguye.capston1_dtu.Model.LoaiCT;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.setOnItemClick;

import java.util.ArrayList;


public class LoaiCongThuc extends AppCompatActivity implements setOnItemClick {

    public static final String ME = "congthuc";
    public static final String MY_NAME = "capstone";
    String name;
    RecyclerView recyclerView;
    LoaiCTAdapter loaiCTAdapter;
    final ArrayList<LoaiCT> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_loai_congthuc);
        Toolbar toolbar = findViewById(R.id.toolbar_loaiCongTHuc);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        name = intent.getStringExtra(ME);


        recyclerView =findViewById(R.id.recycler_view_cong_thuc);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        inView();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void inView() {
      if (name.equals("Môn Toán")){
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Đạo Hàm"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Tích Phân"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Lượng giác"));

          loaiCTAdapter = new LoaiCTAdapter(arrayList,getApplicationContext());
          recyclerView.setAdapter(loaiCTAdapter);
          loaiCTAdapter.setSetOnItemClickListener(LoaiCongThuc.this);
      }else if (name.equals("Môn Lý")){
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Tổng hợp công thức Lý lớp 10"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Tổng hợp công thức Lý lớp 11"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Tổng hợp công thức Lý lớp 12"));

          loaiCTAdapter = new LoaiCTAdapter(arrayList,getApplicationContext());
          recyclerView.setAdapter(loaiCTAdapter);
          loaiCTAdapter.setSetOnItemClickListener(LoaiCongThuc.this);
      }else if (name.equals("Môn Hóa")){
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Chất điện li"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Tổng hợp công thức kim loại"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Tổng hợp công thức POLYME"));

          loaiCTAdapter = new LoaiCTAdapter(arrayList,getApplicationContext());
          recyclerView.setAdapter(loaiCTAdapter);
          loaiCTAdapter.setSetOnItemClickListener(LoaiCongThuc.this);
      }else if (name.equals("Môn Anh Văn")){
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Câu tường thuật"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Câu điều kiện"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Thì"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Ngữ Pháp"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Các từ định lượng"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Nguyên tắc trọng âm"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Loại từ"));
          loaiCTAdapter = new LoaiCTAdapter(arrayList,getApplicationContext());
          recyclerView.setAdapter(loaiCTAdapter);
          loaiCTAdapter.setSetOnItemClickListener(LoaiCongThuc.this);

      } else if (name.equals("Môn Sinh Học")){
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Cơ chế tổng hợp ARN"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Cơ chế tổng hợp protein"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Cấu trúc ADN"));
          arrayList.add(new LoaiCT(R.drawable.cuckoo,"Di truyền và biến dị"));
          loaiCTAdapter = new LoaiCTAdapter(arrayList,getApplicationContext());
          recyclerView.setAdapter(loaiCTAdapter);
          loaiCTAdapter.setSetOnItemClickListener(LoaiCongThuc.this);
      }else {
          Toast.makeText(getApplicationContext(),"Updating", Toast.LENGTH_LONG).show();
      }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(LoaiCongThuc.this,List_Image_cong_thuc.class);
        LoaiCT monHoc = arrayList.get(position);
        intent.putExtra(MY_NAME,monHoc.getType());
        startActivity(intent);
    }
}
