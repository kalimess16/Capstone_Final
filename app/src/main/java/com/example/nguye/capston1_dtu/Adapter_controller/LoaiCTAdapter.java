package com.example.nguye.capston1_dtu.Adapter_controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Model.LoaiCT;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.setOnItemClick;

import java.util.ArrayList;

public class LoaiCTAdapter extends RecyclerView.Adapter<LoaiCTAdapter.ViewHolder> {
    ArrayList<LoaiCT> loaiCTS;
    Context context;
    private setOnItemClick setOnItemClick;


    public LoaiCTAdapter(ArrayList<LoaiCT> loaiCTS, Context context) {
        this.loaiCTS = loaiCTS;
        this.context = context;
    }

    public void setSetOnItemClickListener(setOnItemClick set){
        this.setOnItemClick = set;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_itemloaicongthuc,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.imageView.setImageResource(loaiCTS.get(i).getImage());
        viewHolder.textView.setText(loaiCTS.get(i).getType());
    }

    @Override
    public int getItemCount() {
        return loaiCTS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_congthuc);
            textView = itemView.findViewById(R.id.loaicongthuc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(setOnItemClick!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            setOnItemClick.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
