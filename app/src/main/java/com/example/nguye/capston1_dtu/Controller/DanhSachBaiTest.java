package com.example.nguye.capston1_dtu.Controller;

import android.content.Intent;
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

public class DanhSachBaiTest extends AppCompatActivity implements setOnItemClick {

    public static final String NAME = "list";
    public static final String MY_NAME = "title";
    ArrayList<LoaiCT> loaiDE = new ArrayList<>();
    String name;
    RecyclerView recyclerView;
    LoaiCTAdapter loaiDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_test);
        Toolbar toolbar = findViewById(R.id.toolbarDe);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        name = intent.getStringExtra(NAME);

        recyclerView = findViewById(R.id.recycler_view_danh_sah_de_thi);
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
        if (name.equals("Kiểm tra Toán")){  ///////////// TOAN //////////////
            loaiDE.add(new LoaiCT(R.drawable.logodtu, "Bài kiểm tra số 1"));
            loaiDE.add(new LoaiCT(R.drawable.logodtu, "Bài kiểm tra số 2"));
            loaiDE.add(new LoaiCT(R.drawable.logodtu, "Bài kiểm tra số 3"));
            loaiDE.add(new LoaiCT(R.drawable.logodtu, "Bài kiểm tra số 4"));


            loaiDeAdapter = new LoaiCTAdapter(loaiDE, getApplicationContext());
            recyclerView.setAdapter(loaiDeAdapter);
            loaiDeAdapter.setSetOnItemClickListener(DanhSachBaiTest.this);

        }else if (name.equals("Kiểm tra Vật Lý")){
            loaiDE.add(new LoaiCT(R.drawable.logodtu, "Bài kiểm tra Ly số 1"));
           /* loaiDE.add(new LoaiCT(R.drawable.logodtu, "Bài kiểm tra Ly số 2"));
            loaiDE.add(new LoaiCT(R.drawable.logodtu, "Bài kiểm tra Ly số 3"));
            loaiDE.add(new LoaiCT(R.drawable.logodtu, "Bài kiểm tra Ly số 4"));*/


            loaiDeAdapter = new LoaiCTAdapter(loaiDE, getApplicationContext());
            recyclerView.setAdapter(loaiDeAdapter);
            loaiDeAdapter.setSetOnItemClickListener(DanhSachBaiTest.this);
        }else if (name.equals("Kiểm tra Hóa Học")){
            Toast.makeText(getApplicationContext(), "updating", Toast.LENGTH_LONG).show();
        }else if (name.equals("Kiểm tra Tiếng Anh")){
            Toast.makeText(getApplicationContext(), "updating", Toast.LENGTH_LONG).show();
        }else if (name.equals("Kiểm tra Sinh Học")){
            Toast.makeText(getApplicationContext(), "updating", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "co ma moi hien", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ScreenSlidePagerActivity.class);
        LoaiCT ct = loaiDE.get(position);
        intent.putExtra(MY_NAME, ct.getType());
        startActivity(intent);
    }
}
