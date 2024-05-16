package com.amitai.mathprojectasafdadon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MyGameActivity extends AppCompatActivity {
private RecyclerView rcShowCards1;
private RecyclerView rcShowCards2;
private RecyclerView rcShowCards3;
private MViewModel mViewModel;
CardAdapter cardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);
        mViewModel = new ViewModelProvider(this).get(MViewModel.class);
        rcShowCards1=findViewById(R.id.rcShowCards1);
        rcShowCards2=findViewById(R.id.rcShowCards2);
        rcShowCards3=findViewById(R.id.rcShowCards3);

        mViewModel.hand1.observe(this, new Observer<ArrayList<Card>>() {
            @Override
            public void onChanged(ArrayList<Card> cards) {
                cardAdapter=new CardAdapter(cards, new CardAdapter.OnitemClicklistener() {
                    @Override
                    public void onItemClick(Card item) {

                    }
                });
                rcShowCards1.setLayoutManager(new LinearLayoutManager(MyGameActivity.this,RecyclerView.HORIZONTAL,false));
                rcShowCards1.setAdapter(cardAdapter);
                rcShowCards1.setHasFixedSize(true);
            }
        });
        mViewModel.deck.observe(this, new Observer<ArrayList<Card>>() {
            @Override
            public void onChanged(ArrayList<Card> cards) {
                cardAdapter=new CardAdapter(cards, new CardAdapter.OnitemClicklistener() {
                    @Override
                    public void onItemClick(Card item) {

                    }
                });
                rcShowCards2.setLayoutManager(new LinearLayoutManager(MyGameActivity.this,RecyclerView.HORIZONTAL,false));
                rcShowCards2.setAdapter(cardAdapter);
                rcShowCards2.setHasFixedSize(true);
            }
        });

        mViewModel.hand2.observe(this, new Observer<ArrayList<Card>>() {
            @Override
            public void onChanged(ArrayList<Card> cards) {
                cardAdapter=new CardAdapter(cards, new CardAdapter.OnitemClicklistener() {
                    @Override
                    public void onItemClick(Card item) {

                    }
                });
                rcShowCards3.setLayoutManager(new LinearLayoutManager(MyGameActivity.this,RecyclerView.HORIZONTAL,false));
                rcShowCards3.setAdapter(cardAdapter);
                rcShowCards3.setHasFixedSize(true);
            }
        });





    }



}