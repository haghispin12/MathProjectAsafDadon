package com.amitai.mathprojectasafdadon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class RateActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
private SeekBar sb;
private Button save;
private TextView num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        Main();
    }


    private void Main(){
        sb = findViewById(R.id.SeekBar);
        save = findViewById(R.id.save);
        num = findViewById(R.id.num);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("rate", sb.getProgress());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        num.setText(progress+"");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
