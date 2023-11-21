package com.amitai.mathprojectasafdadon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    String answer;
    private int score;
    private User user;
    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;
    Exercise E;
    public MainViewModel(){
      vNum1 = new MutableLiveData<>();
      vNum2 = new MutableLiveData<>();
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
        user.addScore(score);
        if(E.check(num)) {
            answer = "good job";
            return true;
        }
        answer="you failed";
        return false;
    }

    public void getName(String name){
        user=new User(name);
    }


    public String getAnswer() {
        return answer;
    }
}
