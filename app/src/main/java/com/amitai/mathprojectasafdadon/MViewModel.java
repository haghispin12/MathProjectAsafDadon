package com.amitai.mathprojectasafdadon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MViewModel extends ViewModel {
MutableLiveData<ArrayList<Card>> hand1;
    MutableLiveData<ArrayList<Card>> hand2;
    MutableLiveData<ArrayList<Card>> deck;
    MutableLiveData<Integer> money;

    public MViewModel(){
        hand1=new MutableLiveData<>();
        hand2=new MutableLiveData<>();
        deck=new MutableLiveData<>();
        money=new MutableLiveData<>(1000);
    }

    public void buildCards(ArrayList<Card> hand1, ArrayList<Card> hand2, ArrayList<Card> deck){
        this.hand1.setValue(hand1);
        this.hand2.setValue(hand2);
        this.deck.setValue(deck);
    }



    public Boolean checkCards(ArrayList<Card> arrayList){//פעולה הבודקת אם יש שני קלפים זהים במערך אם אין המערך תקין
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


public boolean isHide(){
        boolean isHide=true;

        if (deck.getValue().get(3).getIsHide()){
            deck.getValue().get(3).setIsHide(false);
            deck.setValue(deck.getValue());
            return true;
        }
        if (deck.getValue().get(4).getIsHide()){
            deck.getValue().get(4).setIsHide(false);
            deck.setValue(deck.getValue());
            return true;
        }
        return false;
}

public boolean isRechef(ArrayList<Card> arrayList, int first, int count){
        for (int i=0;i<arrayList.size();i++){
            if ((first + 1) == arrayList.get(i).getNum()){
                count++;
                if (count==5)
                    return true;
                if (isRechef(arrayList,arrayList.get(i).getNum(),count))
                    return true;
            }}
        return false;}
public boolean isColor(ArrayList<Card> arrayList){
        int diamond=0;
        int clubs=0;
        int heart=0;
        int spades=0;
        for (int i=0;i<arrayList.size();i++){
            if (arrayList.get(i).getShape().equals("diamond")) {
                diamond++;
                if (diamond==5)
                    return true;
            }
            if (arrayList.get(i).getShape().equals("clubs")) {
                clubs++;
                if (clubs==5)
                    return true;
            }
            if (arrayList.get(i).getShape().equals("heart")) {
                heart++;
                if (heart==5)
                    return true;
            }
            if (arrayList.get(i).getShape().equals("spades")) {
                spades++;
                if (spades==5)
                    return true;
            }
        }
        return false;}

public int isFour(ArrayList<Card> arrayList){
        int count=1;
        for (int i=0; i<arrayList.size();i++){

        }
        return 0;
}


}
