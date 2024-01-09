package com.amitai.mathprojectasafdadon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amitai.mathprojectasafdadon.R;

import java.util.ArrayList;

public class MyFruitAdapter extends RecyclerView.Adapter<MyFruitAdapter.MyViewHolder> {
    public interface OnItemClickListener{
        void onItemClick(Fruit item);
    }

    private ArrayList<Fruit> fruits;
    private OnItemClickListener listener;

    public MyFruitAdapter(ArrayList<Fruit> fruits, OnItemClickListener listener){
        this.fruits=fruits;
        this.listener=listener;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        holder.bind(fruits.get(position),listener);
    }

    @Override
    public int getItemCount(){
        return fruits.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvFruitName;
        ImageView ivFruitImg;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tvFruitName = itemView.findViewById(R.id.tvFruitName);
            ivFruitImg = itemView.findViewById(R.id.ivFruitImg);
        }

        public void bind(final Fruit item, final OnItemClickListener listener){
            tvFruitName.setText(item.getFruitName());
            ivFruitImg.setImageResource(item.getDrawable());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }


    }




}
