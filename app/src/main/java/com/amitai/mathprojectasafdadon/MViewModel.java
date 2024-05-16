package com.amitai.mathprojectasafdadon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MViewModel extends ViewModel {
MutableLiveData<ArrayList<Card>> hand1;
    MutableLiveData<ArrayList<Card>> hand2;
    MutableLiveData<ArrayList<Card>> deck;
    public MViewModel(){
        hand1=new MutableLiveData<>(new ArrayList<>(Arrays.asList(new Card(), new Card())));
        hand2=new MutableLiveData<>(new ArrayList<>(Arrays.asList(new Card(), new Card())));
        deck=new MutableLiveData<>(new ArrayList<>(Arrays.asList(new Card(), new Card(), new Card())));
    }






}
