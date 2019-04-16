package com.example.nguye.capston1_dtu.Adapter_controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Model.City;
import com.example.nguye.capston1_dtu.R;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<City> {
    public CityAdapter(Context context, ArrayList<City> cityList){
        super(context,0,cityList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.city_spinner_row, parent, false
            );
        }
//       TextView imageViewFlag = convertView.findViewById(R.id.image_view_flag);
        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        City currentItem = getItem(position);
        if (currentItem != null) {
//            imageViewFlag.setText(currentItem.getmCity());
            textViewName.setText(currentItem.getName());
        }
        return convertView;
    }
}
