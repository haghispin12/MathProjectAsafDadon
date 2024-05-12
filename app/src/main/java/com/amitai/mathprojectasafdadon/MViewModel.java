package com.amitai.mathprojectasafdadon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MViewModel extends ViewModel {
private ArrayList<Card> hand1;
private ArrayList<Card> hand2;
private ArrayList<Card> deck;
    public MViewModel(){
        hand1=new ArrayList<Card>(Arrays.asList(new Card(), new Card()));
        hand2=new ArrayList<Card>(Arrays.asList(new Card(), new Card()));
        deck=new ArrayList<Card>(Arrays.asList(new Card(), new Card(),new Card()));
    }






}
