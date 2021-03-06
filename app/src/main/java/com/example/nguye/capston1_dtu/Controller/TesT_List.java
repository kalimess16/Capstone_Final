package com.example.nguye.capston1_dtu.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.nguye.capston1_dtu.Adapter_controller.ExamAdapter;
import com.example.nguye.capston1_dtu.Model.Exam;
import com.example.nguye.capston1_dtu.R;

import java.util.ArrayList;

public class TesT_List extends Fragment {

    GridView gridView;
    ArrayList<Exam> listExam = new ArrayList<>();
    ExamAdapter examAdapter;
    public static final String NAME = "list";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView = getActivity().findViewById(R.id.gvSubject);
        listExam.add(new Exam("Kiểm tra Toán"));
        listExam.add(new Exam("Kiểm tra Vật Lý"));
        /*listExam.add(new Exam("Kiểm tra Hóa Học"));
        listExam.add(new Exam("Kiểm tra Tiếng Anh"));
        listExam.add(new Exam("Kiểm tra Sinh Học"));*/


        examAdapter = new ExamAdapter(getActivity(), listExam);
        gridView.setAdapter(examAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DanhSachBaiTest.class);
                Exam exam = listExam.get(position);
                intent.putExtra(NAME,exam.getName());
                startActivity(intent);
            }
        });
    }
}
