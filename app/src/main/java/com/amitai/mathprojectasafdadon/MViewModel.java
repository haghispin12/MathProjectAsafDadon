package com.amitai.mathprojectasafdadon;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class MViewModel extends ViewModel {
MutableLiveData<ArrayList<Card>> hand1;
    MutableLiveData<ArrayList<Card>> hand2;
    MutableLiveData<ArrayList<Card>> deck;
    MutableLiveData<Integer> money;
    MutableLiveData<Long> hisMoney;
    private int bet;
    MutableLiveData<Integer> hisBet;
    private String nameYourBet;
    private String nameOtherBet;
    private String moneyName;
    private String otherMoneyName;
    private String code;
    private DocumentSnapshot documentSnapshot;
    private String documentId;
    private DocumentReference documentReference;
    private String message;
    MutableLiveData<Boolean> isYourTurn;


    public MViewModel(){
        hand1=new MutableLiveData<>();
        hand2=new MutableLiveData<>();
        deck=new MutableLiveData<>();
        money=new MutableLiveData<>(1000);
        hisMoney=new MutableLiveData<>(1000L);
        bet=0;
        hisBet=new MutableLiveData<>(0);
    }

    public void buildCards(ArrayList<Card> hand1, ArrayList<Card> hand2, ArrayList<Card> deck){
        this.hand1.setValue(hand1);
        this.hand2.setValue(hand2);
        this.deck.setValue(deck);
    }

    public void  giveData(String nameYourBet, String nameOtherBet, String moneyName, String otherMoneyName, String code, boolean isItYourTurn){
        this.nameYourBet=nameYourBet;
        this.nameOtherBet=nameOtherBet;
        this.moneyName=moneyName;
        this.otherMoneyName=otherMoneyName;
        this.code=code;
        this.isYourTurn.setValue(isItYourTurn);
        FirebaseFirestore.getInstance().collection("games").whereEqualTo("id",code).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                documentId = documentSnapshot.getId();
                documentReference = FirebaseFirestore.getInstance().collection("games").document(documentId);
            }
        });
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.w("Firestore", "fail", error);
                    return;
                }
                if (value!=null && value.exists()){
                    if(value.getLong(otherMoneyName)!=hisMoney.getValue());{
                        hisMoney.setValue(value.getLong(otherMoneyName));
                    }
                    if (hisBet.getValue()!=Math.toIntExact(documentSnapshot.getLong(nameOtherBet))){
                        hisBet.setValue(Math.toIntExact(documentSnapshot.getLong(nameOtherBet)));
                        isYourTurn.setValue(true);
                    }
                }
            }
        });


    }


    public void uptadeMoney(int money){
        this.money.setValue(this.money.getValue()+money);
        documentReference.update(moneyName,this.money.getValue());
    }
    public int updateBet(int bet){
        this.bet+=bet;
        if (!documentSnapshot.getBoolean("isTheSecondBet")){
            message="good job, moving to opponent turn";
            documentReference.update("isTheSecondBet",true);
            documentReference.update(nameYourBet,this.bet);
        }
        else if (documentSnapshot.getBoolean("isTheSecondBet")) {
            int hisBet= Math.toIntExact(documentSnapshot.getLong(nameOtherBet));
            if (this.bet<hisBet){
                message="not enough, bet on more or fold";
                this.bet-=bet;
                return this.bet;
            }
            else if (this.bet>hisBet){
                message="good job, moving to opponent turn";
                documentReference.update(nameYourBet,this.bet);
                isYourTurn.setValue(false);
            }
            else if (this.bet==hisBet){
                message="good job";
                documentReference.update(nameYourBet,this.bet);
                boolean hide = isHide();
                isYourTurn.setValue(false);
            }
        }
        return this.bet;
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

    public String getMessage() {
        return message;
    }


}
