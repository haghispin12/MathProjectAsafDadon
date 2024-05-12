package com.amitai.mathprojectasafdadon.mathproject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amitai.mathprojectasafdadon.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    public interface OnItemClickListener{
    void onItemClick(User item);
    }
    private ArrayList<User> users;
    private OnItemClickListener listener;

    public UserAdapter(ArrayList<User> users, UserAdapter.OnItemClickListener listener){
        this.users=users;
        this.listener=listener;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userview, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position){
        holder.bind(users.get(position),listener);
    }
    @Override
    public int getItemCount(){
        return users.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvUserName;
        ImageView ivUserImg;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            ivUserImg = itemView.findViewById(R.id.ivUserImg);
        }
        public void bind(final User item, final UserAdapter.OnItemClickListener listener){
            tvUserName.setText(item.getName());
            ivUserImg.setImageBitmap(item.getBitmap());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}


