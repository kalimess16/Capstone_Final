package com.example.nguye.capston1_dtu.Adapter_controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Model.MonHoc;
import com.example.nguye.capston1_dtu.Model.Video;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.setOnItemClick;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{
    ArrayList<Video> listVideo;
    Context context;
    private setOnItemClick listener;

    public void setOnClickListener(setOnItemClick listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.l_video,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder viewHolder, int i) {
        viewHolder.imageView.setImageResource(listVideo.get(i).getView());
        viewHolder.textView.setText(listVideo.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return listVideo.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_video);
            textView = itemView.findViewById(R.id.tv_video);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public VideoAdapter(ArrayList<Video> listVIDEO, Context context) {
        this.listVideo = listVIDEO;
        this.context = context;
    }
}
