package com.amitai.mathprojectasafdadon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MViewModel extends ViewModel {
MutableLiveData<ArrayList<Card>> hand1;
    MutableLiveData<ArrayList<Card>> hand2;
    MutableLiveData<ArrayList<Card>> deck;
    private ArrayList cards;
    private Card tmpCard;
    public MViewModel(){
        cards=new ArrayList<Card>();
        for (int i=0;i<9;i++){
            tmpCard=new Card();
            if (i==0||i==1||i==7||i==8)
                tmpCard.setIsHide(true);
            cards.add(tmpCard);
        }
        while (!checkCards(cards))
            for (int i=0;i<cards.size();i++){
                tmpCard=new Card();
                if (i==2||i==3||i==7||i==8)
                    tmpCard.setIsHide(true);
                cards.add(tmpCard);
            }
        int g=0;

        hand1=new MutableLiveData<>(new ArrayList<>(Arrays.asList((Card)cards.get(0), (Card)cards.get(1))));
        hand2=new MutableLiveData<>(new ArrayList<>(Arrays.asList((Card)cards.get(2), (Card)cards.get(3))));
        deck=new MutableLiveData<>(new ArrayList<>(Arrays.asList((Card)cards.get(4),(Card)cards.get(5),(Card)cards.get(6),(Card)cards.get(7),(Card)cards.get(8))));
    }



    public Boolean checkCards(ArrayList<Card> arrayList){//פעולה הבודקת אם יש שני קלפים זהים במערך
        Boolean isGood=true;
        for (int i=0; i<arrayList.size(); i++){
            for (int j=i+1;j<arrayList.size();j++){
                if (arrayList.get(i).getNum()==arrayList.get(j).getNum()&&arrayList.get(i).getShape().equals(arrayList.get(j).getShape())) {
                    isGood = false;
                    break;
                }
            }
        }
        return isGood;
    }




}
