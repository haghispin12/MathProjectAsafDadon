package com.amitai.mathprojectasafdadon;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    String answer;
    private int score;
    private User user;
    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;
    MutableLiveData<ArrayList<User>> users;
    Exercise E;
    public MainViewModel(){
      vNum1 = new MutableLiveData<>();
      vNum2 = new MutableLiveData<>();
      users = new MutableLiveData<>(new ArrayList<>());
      E=new Exercise();
    }
    public void challenge(){
        score=20;
        E.challenge();
        vNum1.setValue(E.getNum1());
        vNum2.setValue(E.getNum2());
    }
    public void multi20(){
        score=10;
        E.multi20();
        vNum1.setValue(E.getNum1());
        vNum2.setValue(E.getNum2());
    }

    public void multitable(){
        score=5;
        E.multiTable();
        vNum1.setValue(E.getNum1());
        vNum2.setValue(E.getNum2());
    }
    public boolean check(int num){
        if(E.check(num)) {
            user.addScore(score);
            answer = "good job";
            return true;
        }
        answer="you failed";
        return false;
    }
    public void setName(String name){
        user=new User(name);
    }

    public String getUserName(){
        return user.getName();
    }
    public int getScore(){
        return score;
    }

    public int getUserRate(){
        return  user.getRate();
    }

    public String getAnswer() {
        return answer;
    }

    public void setRate(int rate){
        user.setRate(rate);
    }

    public void setUri(Uri uri) {
        user.setUri(uri);
    }

    public void insertUser(Context context){
        DBHelper dbHelper= new DBHelper(context);
        dbHelper.insert(user, context);
        ArrayList<User> tmp = dbHelper.selectAll();
        users.setValue(tmp);
    }
    public void useSelectAll(Context context){
        DBHelper dbHelper= new DBHelper(context);
        ArrayList<User> tmp = dbHelper.selectAll();
        users.setValue(tmp);
    }

    public void useUpdate(Context context, User user){
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.update(user);
        ArrayList<User> tmp = dbHelper.selectAll();
        users.setValue(tmp);
    }

}
