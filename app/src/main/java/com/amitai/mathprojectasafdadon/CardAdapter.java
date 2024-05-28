package com.amitai.mathprojectasafdadon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
 public interface OnitemClicklistener {
     void onItemClick(Card item);
 }
     private ArrayList<Card> cards;
     private OnitemClicklistener listener;

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public CardAdapter(ArrayList<Card> cards, CardAdapter.OnitemClicklistener listener){
         this.cards=cards;
         this.listener=listener;
 }

    @NonNull
    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
         return new CardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyViewHolder holder, int position) {
        holder.bind(cards.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
         ImageView ivCardImg;
         public MyViewHolder(@NonNull View itemView){
             super(itemView);
             ivCardImg=itemView.findViewById(R.id.ivCardImg);
         }
         public void bind(final Card item, final CardAdapter.OnitemClicklistener listener){
             if (!item.getIsHide())
                 ivCardImg.setImageResource(item.getBitmap());
             else
                 ivCardImg.setImageResource(R.drawable.background1);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.onItemClick(item);
                 }
             });
         }

    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}


