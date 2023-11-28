package com.amitai.mathprojectasafdadon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

public class RateActivity extends AppCompatActivity {
private SeekBar sb;
private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        Main();
    }


    private void Main(){
        sb = findViewById(R.id.SeekBar);
        save = findViewById(R.id.save);
        Intent intent = new Intent();
        intent.putExtra("rate", sb.getProgress());
        setResult(RESULT_OK,intent);
        finish();
    }



}