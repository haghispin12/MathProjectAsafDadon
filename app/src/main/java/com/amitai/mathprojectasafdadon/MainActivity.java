package com.amitai.mathprojectasafdadon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private void showToast (String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }


    private Button challenge;
    private Button multi20;
    private Button multiTable;
    private Button check;
    private TextView text1;
    private TextView text3;
    private EditText answer;
    private Button Check;
    private Exercise E;
    int num1;
    int num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        challenge = findViewById(R.id.challege);
        text1 = findViewById(R.id.text1);
        text3 = findViewById(R.id.text3);
        multi20 = findViewById(R.id.multi20);
        multiTable = findViewById(R.id.multitable);
        answer = findViewById(R.id.answer);
        check = findViewById(R.id.check);
        E = new Exercise();
        setOnClickListener();
    }



public void setOnClickListener(){




  challenge.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          E.challenge();
          text1.setText(E.getNum1() +"");
          text3.setText(E.getNum2() +"");
      }
  });

  multi20.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          E.multi20();
          text1.setText(E.getNum1() +"");
          text3.setText(E.getNum2() +"");
      }
  });

  multiTable.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          E.multiTable();
          text1.setText(E.getNum1() +"");
          text3.setText(E.getNum2() +"");
      }
  });

  check.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          int num = Integer.parseInt(answer.getText().toString());
          if(E.check(num))
              showToast("good job");
          else
              showToast("you failed");
          answer.setText(" ");
      }
  });

}
}



