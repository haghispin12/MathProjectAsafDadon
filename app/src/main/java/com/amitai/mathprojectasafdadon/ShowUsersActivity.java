package com.amitai.mathprojectasafdadon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowUsersActivity extends AppCompatActivity {
    private RecyclerView rcShowFruits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        rcShowFruits = findViewById(R.id.rcShowUsers);
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("banana",R.drawable.banana));
        fruits.add(new Fruit("apple",R.drawable.apple));
        fruits.add(new Fruit("orange",R.drawable.orange));
        fruits.add(new Fruit("grapes",R.drawable.grapes));
        fruits.add(new Fruit("lemon",R.drawable.lemon));
        fruits.add(new Fruit("fru",R.drawable.fru));
        MyFruitAdapter myFruitAdapter = new MyFruitAdapter(fruits, new MyFruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit item) {
                Toast.makeText(ShowUsersActivity.this,item.getFruitName(),Toast.LENGTH_SHORT).show();
            }
        });
        rcShowFruits.setLayoutManager(new LinearLayoutManager(this));
        rcShowFruits.setAdapter(myFruitAdapter);
        rcShowFruits.setHasFixedSize(true);


    }





}