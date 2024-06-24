package com.amitai.mathprojectasafdadon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyGameActivity extends AppCompatActivity {
private RecyclerView rcShowCards1;
private RecyclerView rcShowCards2;
private RecyclerView rcShowCards3;
private MViewModel mViewModel;
CardAdapter cardAdapter;
private TextView check;
private int b=0;









ArrayList<Card> tmp=new ArrayList<Card>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);



            mViewModel = new ViewModelProvider(this).get(MViewModel.class);
            check = findViewById(R.id.check);
            rcShowCards1 = findViewById(R.id.rcShowCards1);
            rcShowCards2 = findViewById(R.id.rcShowCards2);
            rcShowCards3 = findViewById(R.id.rcShowCards3);



                mViewModel.hand1.observe(this, new Observer<ArrayList<Card>>() {
                    @Override
                    public void onChanged(ArrayList<Card> cards) {
                        cardAdapter = new CardAdapter(cards, new CardAdapter.OnitemClicklistener() {
                            @Override
                            public void onItemClick(Card item) {

                            }
                        });
                        rcShowCards1.setLayoutManager(new LinearLayoutManager(MyGameActivity.this, RecyclerView.HORIZONTAL, false));
                        rcShowCards1.setAdapter(cardAdapter);
                        rcShowCards1.setHasFixedSize(true);
                    }
                });
            mViewModel.deck.observe(this, new Observer<ArrayList<Card>>() {
                @Override
                public void onChanged(ArrayList<Card> cards) {
//                if(b==0) {
                    cardAdapter = new CardAdapter(cards, new CardAdapter.OnitemClicklistener() {
                        @Override
                        public void onItemClick(Card item) {

                        }
                    });
                    rcShowCards2.setLayoutManager(new LinearLayoutManager(MyGameActivity.this, RecyclerView.HORIZONTAL, false));
                    rcShowCards2.setAdapter(cardAdapter);
                    rcShowCards2.setHasFixedSize(true);
                    b++;
                }
//                else {
//                    cardAdapter.setCards(cards);
//                    cardAdapter.notifyDataSetChanged();
//                }
//            }
            });

            mViewModel.hand2.observe(this, new Observer<ArrayList<Card>>() {
                @Override
                public void onChanged(ArrayList<Card> cards) {
                    cardAdapter = new CardAdapter(cards, new CardAdapter.OnitemClicklistener() {
                        @Override
                        public void onItemClick(Card item) {

                        }
                    });
                    rcShowCards3.setLayoutManager(new LinearLayoutManager(MyGameActivity.this, RecyclerView.HORIZONTAL, false));
                    rcShowCards3.setAdapter(cardAdapter);
                    rcShowCards3.setHasFixedSize(true);
                }
            });

            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tmp = cardAdapter.getCards();
                    mViewModel.isHide();

                }
            });


        }
    }




