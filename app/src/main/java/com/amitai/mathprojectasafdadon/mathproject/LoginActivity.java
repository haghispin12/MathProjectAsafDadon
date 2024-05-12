package com.amitai.mathprojectasafdadon.mathproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amitai.mathprojectasafdadon.R;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setOnClickListener();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("username", "");
        username.setText(s1);

    }

    private void setOnClickListener(){
        username = findViewById(R.id.username);
        submit = findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("username", username.getText().toString());
                myEdit.apply();


                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("username",username.getText().toString());
                startActivity(intent);
            }
        });

    }


}

