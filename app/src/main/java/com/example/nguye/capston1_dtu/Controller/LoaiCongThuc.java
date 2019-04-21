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

        Intent intent = getIntent();
        name = intent.getStringExtra(ME);


        recyclerView =findViewById(R.id.recycler_view_cong_thuc);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        inView();

    }

    private void inView() {
      if (name.equals("Công thức Toán")){
          arrayList.add(new LoaiCT(R.drawable.logodtu,"Đạo Hàm"));
          arrayList.add(new LoaiCT(R.drawable.logodtu,"Tích Phân"));
          arrayList.add(new LoaiCT(R.drawable.logodtu,"Lượng giác"));

          loaiCTAdapter = new LoaiCTAdapter(arrayList,getApplicationContext());
          recyclerView.setAdapter(loaiCTAdapter);
          loaiCTAdapter.setSetOnItemClickListener(LoaiCongThuc.this);
      }else if (name.equals("")){

      }else if (name.equals("")){

      }else if (name.equals("")){

      }else if (name.equals("")){

      }else if (name.equals("")){

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
