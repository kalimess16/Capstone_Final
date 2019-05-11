package com.example.nguye.capston1_dtu.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguye.capston1_dtu.Adapter_controller.MonHocAdapter;
import com.example.nguye.capston1_dtu.Adapter_controller.VideoAdapter;
import com.example.nguye.capston1_dtu.Model.MonHoc;
import com.example.nguye.capston1_dtu.Model.Video;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.setOnItemClick;

import java.util.ArrayList;

public class List_Video extends Fragment implements setOnItemClick {
    RecyclerView recyclerView;
    VideoAdapter viDeoAdapter;

    public static final String ME = "Video";

    final ArrayList<Video> arrVideo = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_video,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_video);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        inView();
    }

    private void inView() {
        arrVideo.add(new Video(R.drawable.logodtu,"Luyện nghe tiếng anh"));
        arrVideo.add(new Video(R.drawable.logodtu,"Nguyên hàm lượng giác"));

        viDeoAdapter = new VideoAdapter(arrVideo,getActivity());
        recyclerView.setAdapter(viDeoAdapter);
        viDeoAdapter.setOnClickListener(List_Video.this);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(),VideoLoad.class);
        Video monHoc = arrVideo.get(position);
        intent.putExtra(ME,monHoc.getName());
        startActivity(intent);
    }
}
