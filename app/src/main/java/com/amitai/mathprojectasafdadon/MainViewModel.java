package com.amitai.mathprojectasafdadon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;
    Exercise E;
    public MainViewModel(){
      vNum1 = new MutableLiveData<>();
      vNum2 = new MutableLiveData<>();
      E=new Exercise();



    }

    public void challenge(){
        E.challenge();
        vNum1.setValue(E.getNum1());
        vNum2.setValue(E.getNum2());
    }










}
