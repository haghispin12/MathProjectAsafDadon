package com.amitai.mathprojectasafdadon;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Map;

public class MyGameActivity extends AppCompatActivity {
private RecyclerView rcShowCards1;
private RecyclerView rcShowCards2;
private RecyclerView rcShowCards3;
private MViewModel mViewModel;
CardAdapter cardAdapter;
    private TextView check;
    private TextView bet;
    EditText enterMoney;
    private TextView fold;
    private TextView money;
    private TextView opponentMoney;
    private TextView opponentBet;
    private boolean isYourTurn;








    ArrayList<Card> tmp=new ArrayList<Card>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);
        check=findViewById(R.id.check);
        bet=findViewById(R.id.bet);
        enterMoney=findViewById(R.id.enterMoney);
        fold=findViewById(R.id.fold);
        money=findViewById(R.id.money);
        opponentMoney=findViewById(R.id.opponentMoney);
        opponentBet=findViewById(R.id.opponentBet);


        ArrayList<Card> hand1=new ArrayList<>();
        ArrayList<Map> tmpCards=(ArrayList<Map>) getIntent().getSerializableExtra("hand1");
        for (Map<String,Object> map : tmpCards){
            int num=(int) (long)map.getOrDefault("num",2);
            String shape=(String) map.getOrDefault("shape","clubs");
            boolean isHide=(boolean) map.getOrDefault("isHide",false);
            int rc=(int) (long)map.getOrDefault("rc",R.drawable.clubs2);
            Card card=new Card(num,shape,isHide,rc);
            hand1.add(card);
        }
        ArrayList<Card> hand2=new ArrayList<>();
        tmpCards=(ArrayList<Map>) getIntent().getSerializableExtra("hand2");
        for (Map<String,Object> map : tmpCards){
            int num=(int) (long)map.getOrDefault("num",2);
            String shape=(String) map.getOrDefault("shape","clubs");
            boolean isHide=(boolean) map.getOrDefault("isHide",false);
            int rc=(int) (long)map.getOrDefault("rc",R.drawable.clubs2);
            Card card=new Card(num,shape,isHide,rc);
            hand2.add(card);
        }
        ArrayList<Card> deck=new ArrayList<>();
        tmpCards=(ArrayList<Map>) getIntent().getSerializableExtra("deck");
        for (Map<String,Object> map : tmpCards){
            int num=(int) (long)map.getOrDefault("num",2);
            String shape=(String) map.getOrDefault("shape","clubs");
            boolean isHide=(boolean) map.getOrDefault("isHide",false);
            int rc=(int) (long)map.getOrDefault("rc",R.drawable.clubs2);
            Card card=new Card(num,shape,isHide,rc);
            deck.add(card);
        }
            mViewModel = new ViewModelProvider(this).get(MViewModel.class);
            mViewModel.buildCards(hand1,hand2,deck);
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
                    cardAdapter = new CardAdapter(cards, new CardAdapter.OnitemClicklistener() {
                        @Override
                        public void onItemClick(Card item) {

                        }
                    });
                    rcShowCards2.setLayoutManager(new LinearLayoutManager(MyGameActivity.this, RecyclerView.HORIZONTAL, false));
                    rcShowCards2.setAdapter(cardAdapter);
                    rcShowCards2.setHasFixedSize(true);
                }

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
            String nameYourBet=(String) getIntent().getStringExtra("bet");
            String nameOtherBet=(String) getIntent().getStringExtra("otherBet");
            String moneyName=(String) getIntent().getStringExtra("money");
            String otherMoneyName=(String) getIntent().getStringExtra("otherMoney");
            String code=(String) getIntent().getStringExtra("code");
            isYourTurn=(boolean) getIntent().getBooleanExtra("isYourTurn", false);
            if (isYourTurn)
                Toast.makeText(MyGameActivity.this,"you go first",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MyGameActivity.this,"opponent start",Toast.LENGTH_SHORT).show();
            mViewModel.giveData(nameYourBet,nameOtherBet,moneyName,otherMoneyName,code, isYourTurn);


            bet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isYourTurn) {
                        int betMoney = Integer.parseInt(enterMoney.getText().toString());
                        mViewModel.uptadeMoney(-betMoney);
                        int totalBet = mViewModel.updateBet(betMoney);
                        bet.setText("bet:" + totalBet);
                        Toast.makeText(MyGameActivity.this, mViewModel.getMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyGameActivity.this,"you can not do this in the opponent turn", Toast.LENGTH_SHORT).show();
                }
            });

            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isYourTurn) {
                        mViewModel.updateBet(0);
                        Toast.makeText(MyGameActivity.this, mViewModel.getMessage() + "", Toast.LENGTH_SHORT).show();
//                        tmp = cardAdapter.getCards();
//                        mViewModel.isHide();
                    }
                    else
                        Toast.makeText(MyGameActivity.this,"you can not do this in the opponent turn", Toast.LENGTH_SHORT).show();
                }
            });

            fold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isYourTurn){


                    }
                    else
                        Toast.makeText(MyGameActivity.this,"you can not do this in the opponent turn", Toast.LENGTH_SHORT).show();
                }
            });

            mViewModel.money.observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    money.setText("money:"+integer);
                }
            });
            mViewModel.hisMoney.observe(this, new Observer<Long>() {
                @Override
                public void onChanged(Long aLong) {
                    money.setText("opponent money:" + aLong);
                }
            });
            mViewModel.hisBet.observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    opponentBet.setText("opponent bet:"+integer);
                }
            });
            mViewModel.isYourTurn.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    isYourTurn=aBoolean;
                }
            });



        }
    }




