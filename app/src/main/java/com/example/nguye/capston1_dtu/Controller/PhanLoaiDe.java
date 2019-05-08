package com.example.nguye.capston1_dtu.Controller;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.nguye.capston1_dtu.Adapter_controller.LoaiCTAdapter;
import com.example.nguye.capston1_dtu.Model.LoaiCT;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.setOnItemClick;

import java.util.ArrayList;

public class PhanLoaiDe extends AppCompatActivity implements setOnItemClick {

    public static final String NAME = "de thi";
    public static final String MY_NAME = "Capstone";
    ArrayList<LoaiCT> loaiCTS = new ArrayList<>();
    String name;
    RecyclerView recyclerView;
    LoaiCTAdapter loaiCTAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_loai_de);
        Toolbar toolbar = findViewById(R.id.toolbar_loaiDe);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        name = intent.getStringExtra(NAME);

        recyclerView = findViewById(R.id.recycler_view_De_Thi);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        inView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void inView() {
        if (name.equals("Môn Toán")) {
            /////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~`///////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Toán số 1"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Toán số 2"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Toán số 3"));
            /*loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Toán số 4"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Toán số 5"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Toán số 6"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Toán số 7"));
*/
            loaiCTAdapter = new LoaiCTAdapter(loaiCTS, getApplicationContext());
            recyclerView.setAdapter(loaiCTAdapter);
            loaiCTAdapter.setSetOnItemClickListener(PhanLoaiDe.this);

        } else if (name.equals("Môn Văn")) {
            /////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~`///////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Văn số 1"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Văn số 2"));
            /*loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Văn số 3"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Văn số 4"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Văn số 5"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Văn số 6"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Văn số 7"));
*/
            loaiCTAdapter = new LoaiCTAdapter(loaiCTS, getApplicationContext());
            recyclerView.setAdapter(loaiCTAdapter);
            loaiCTAdapter.setSetOnItemClickListener(PhanLoaiDe.this);
        } else if (name.equals("Môn Lý")) {
            /////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~`///////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lý năm 2017"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lý năm 2018"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lý năm 2019_v1"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lý năm 2019_v2"));
            loaiCTAdapter = new LoaiCTAdapter(loaiCTS, getApplicationContext());
            recyclerView.setAdapter(loaiCTAdapter);
            loaiCTAdapter.setSetOnItemClickListener(PhanLoaiDe.this);
        } else if (name.equals("Môn Hóa")) {
            /////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~`///////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Hóa số 1"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Hóa số 2"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Hóa số 3"));
            /*loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Hóa số 4"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Hóa số 5"));*/

            loaiCTAdapter = new LoaiCTAdapter(loaiCTS, getApplicationContext());
            recyclerView.setAdapter(loaiCTAdapter);
            loaiCTAdapter.setSetOnItemClickListener(PhanLoaiDe.this);
        } else if (name.equals("Môn Anh Văn")) {
            /////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~`///////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Anh Văn số 1"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Anh Văn số 2"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Anh Văn số 3"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Anh Văn số 4"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Anh Văn số 5"));
            /*loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Anh Văn số 6"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Anh Văn số 7"));*/

            loaiCTAdapter = new LoaiCTAdapter(loaiCTS, getApplicationContext());
            recyclerView.setAdapter(loaiCTAdapter);
            loaiCTAdapter.setSetOnItemClickListener(PhanLoaiDe.this);
        } /*else if (name.equals("Môn Lịch sử")) {
            /////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~`///////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lịch sử số 1"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lịch sử số 2"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lịch sử số 3"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lịch sử số 4"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lịch sử số 5"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lịch sử số 6"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Lịch sử số 7"));

            loaiCTAdapter = new LoaiCTAdapter(loaiCTS, getApplicationContext());
            recyclerView.setAdapter(loaiCTAdapter);
            loaiCTAdapter.setSetOnItemClickListener(PhanLoaiDe.this);
        }*//* else if (name.equals("Môn Sinh học")) {
            /////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~`///////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Sinh học số 1"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Sinh học số 2"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Sinh học số 3"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Sinh học số 4"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Sinh học số 5"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Sinh học số 6"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Sinh học số 7"));

            loaiCTAdapter = new LoaiCTAdapter(loaiCTS, getApplicationContext());
            recyclerView.setAdapter(loaiCTAdapter);
            loaiCTAdapter.setSetOnItemClickListener(PhanLoaiDe.this);
        }*//* else if (name.equals("Môn Địa lý")) {
            /////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~`///////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Địa lý số 1"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Địa lý số 2"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Địa lý số 3"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Địa lý số 4"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Địa lý số 5"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Địa lý số 6"));
            loaiCTS.add(new LoaiCT(R.drawable.logodtu, "Đề mẫu Địa lý số 7"));

            loaiCTAdapter = new LoaiCTAdapter(loaiCTS, getApplicationContext());
            recyclerView.setAdapter(loaiCTAdapter);
            loaiCTAdapter.setSetOnItemClickListener(PhanLoaiDe.this);
        } */else {
            Toast.makeText(getApplicationContext(), "co ma moi hien", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, List_Image_Bai_Hoc.class);
        LoaiCT ct = loaiCTS.get(position);
        intent.putExtra(MY_NAME, ct.getType());
        startActivity(intent);
    }
}
