package com.amitai.mathprojectasafdadon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MyGameActivity extends AppCompatActivity {
private RecyclerView rcShowCards1;
private RecyclerView rcShowCards2;
private RecyclerView rcShowCards3;
private MViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);
        mViewModel = new ViewModelProvider(this).get(MViewModel.class);
        rcShowCards1=findViewById(R.id.rcShowCards1);
        rcShowCards2=findViewById(R.id.rcShowCards2);
        rcShowCards3=findViewById(R.id.rcShowCards3);
    }



}