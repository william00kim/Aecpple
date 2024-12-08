package com.aecpple.aecpple;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

        private List<FitnessInfodata> dataList;


        public MyAdapter(List<FitnessInfodata> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_sportlist, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            FitnessInfodata data = dataList.get(position);
            holder.iv_icon.setImageResource(data.getIv_icon());
            holder.tv_gymname.setText(data.getTv_gymname());
            holder.tv_gymcall.setText(data.getTv_gymcall());
            holder.tv_gymadress.setText(data.getTv_gymadress());
            holder.tv_actname.setText(data.getTv_actname());

            holder.itemView.setOnClickListener(v -> {
                String curName = holder.tv_gymname.getText().toString();
                Toast.makeText(v.getContext(), curName, Toast.LENGTH_SHORT).show();
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public static class CustomViewHolder extends RecyclerView.ViewHolder {
            protected ImageView iv_icon;
            protected TextView tv_gymname;
            protected TextView tv_gymcall;
            protected TextView tv_gymadress;
            protected TextView tv_actname;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_icon = itemView.findViewById(R.id.iv_icon);
                tv_gymname = itemView.findViewById(R.id.tv_gymname);
                tv_gymcall = itemView.findViewById(R.id.tv_gymcall);
                tv_gymadress = itemView.findViewById(R.id.tv_gymadress);
                tv_actname = itemView.findViewById(R.id.tv_actname);
            }
        }
    }