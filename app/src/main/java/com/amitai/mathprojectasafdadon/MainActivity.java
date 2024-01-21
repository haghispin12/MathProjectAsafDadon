package com.amitai.mathprojectasafdadon;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    private void showToast (String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
    MainViewModel mainViewModel;
    private Button challenge;
    private Button multi20;
    private Button multiTable;
    private Button check;
    private TextView text1;
    private TextView text3;
    private EditText answer;
    private Button Check;
    private Button rate;
    private Button show;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
       String userName = intent.getStringExtra("username");
       showToast("Hellow " + userName);
        setOnClickListener();
       mainViewModel.setName(userName);




    }
public void setOnClickListener(){
    setContentView(R.layout.activity_main);
    challenge = findViewById(R.id.challege);
    text1 = findViewById(R.id.text1);
    text3 = findViewById(R.id.text3);
    multi20 = findViewById(R.id.multi20);
    multiTable = findViewById(R.id.multitable);
    answer = findViewById(R.id.answer);
    check = findViewById(R.id.check);
    rate = findViewById(R.id.rate);
    show= findViewById(R.id.show);

            mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    mainViewModel.vNum2.observe(this, new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {

            text1.setText(integer+"");
        }
    });
    mainViewModel.vNum1.observe(this, new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            text3.setText(integer+"");
        }
    });

  challenge.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          mainViewModel.challenge();
      }
  });

  multi20.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {mainViewModel.multi20();}
  });

  multiTable.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) { mainViewModel.multitable(); }
  });

  check.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          int num = Integer.parseInt(answer.getText().toString());
          mainViewModel.check(num);
          showToast(mainViewModel.getAnswer());
          answer.setText("");
      }
  });

  show.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          FragmentTransaction trans = getSupportFragmentManager().beginTransaction();trans.add(R.id.frameLayout, new ShowUser());trans.commit();
//          Intent intent = new Intent(MainActivity.this, ShowUsersActivity.class);
//          startActivity(intent);
      }
  });



    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int myRate = result.getData().getIntExtra("rate",-1);
                    showToast(myRate+"");
                    mainViewModel.setRate(myRate);
                }
            });



  rate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent = new Intent(MainActivity.this, RateActivity.class);
          activityResultLauncher.launch(intent);
      }
  });

}
}



