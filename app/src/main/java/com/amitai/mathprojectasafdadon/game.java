package com.amitai.mathprojectasafdadon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class game extends AppCompatActivity {
private Button create;
private Button join;
private EditText yourCode;
private EditText joinCode;
FirebaseAuth firebaseAuth;
private ArrayList<Card> hand1;
private ArrayList<Card> hand2;
private ArrayList<Card> deck;
private ArrayList<Card> cards;
private Card tmpCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        create=findViewById(R.id.create);
        join=findViewById(R.id.join);
        firebaseAuth=FirebaseAuth.getInstance();
        yourCode=findViewById(R.id.code);




    create.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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
                    if (i==0||i==1||i==7||i==8)
                        tmpCard.setIsHide(true);
                    cards.add(tmpCard);
                }
            hand1=new ArrayList<>(Arrays.asList(cards.get(0), cards.get(1)));
            hand2=new ArrayList<>(Arrays.asList(cards.get(2), cards.get(3)));
            deck=new ArrayList<>(Arrays.asList(cards.get(4),cards.get(5),cards.get(6),cards.get(7),cards.get(8)));


            String code= UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
            yourCode.setText(code);
            GameManeger gameManeger=new GameManeger(code, firebaseAuth.getCurrentUser().getEmail().toString(), false, hand1, hand2, deck);
            FirebaseFirestore.getInstance().collection("games").add(gameManeger).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            ArrayList<Map> tmpHand1=(ArrayList<Map>) value.get("hand1");
                            ArrayList<Map> tmpHand2=(ArrayList<Map>) value.get("hand2");
                            ArrayList<Map> tmpDeck=(ArrayList<Map>) value.get("deck");
                            if (error!=null){
                                Log.w("Firestore", "fail", error);
                                return;
                            }
                            if (value!=null && value.exists()){
                                if(value.getBoolean("mode")){
                                    Intent intent=new Intent(com.amitai.mathprojectasafdadon.game.this,MyGameActivity.class);
                                    intent.putExtra("hand1",tmpHand1);
                                    intent.putExtra("hand2",tmpHand2);
                                    intent.putExtra("deck",tmpDeck);
                                    intent.putExtra("code", code);
                                    intent.putExtra("money","money1");
                                    intent.putExtra("otherMoney","money2");
                                    startActivity(intent);
                                }
                            }
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(game.this, "error", Toast.LENGTH_SHORT).show();
                }
            });


        }
    });

    join.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String code=yourCode.getText().toString();
            if (!code.isEmpty()){
                FirebaseFirestore.getInstance().collection("games").whereEqualTo("id",yourCode.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.getDocuments().size() > 0) {
                            DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                            String documentId = documentSnapshot.getId();
                            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("games").document(documentId);
                            documentReference.update("player2", firebaseAuth.getCurrentUser().getEmail().toString());


                            ArrayList<Map> tmpHand1=(ArrayList<Map>) documentSnapshot.get("hand2");
                            for (Map<String,Object> map : tmpHand1){
                                map.put("isHide",true);
                            }

                            ArrayList<Map> tmpHand2=(ArrayList<Map>) documentSnapshot.get("hand1");
                            for (Map<String,Object> map : tmpHand2){
                                map.put("isHide",false);
                            }

                            ArrayList<Map> tmpDeck=(ArrayList<Map>) documentSnapshot.get("hand2");



                            documentReference.update("mode", true);
                            Intent intent = new Intent(com.amitai.mathprojectasafdadon.game.this, MyGameActivity.class);
                            intent.putExtra("hand1",tmpHand1);
                            intent.putExtra("hand2",tmpHand2);
                            intent.putExtra("deck",tmpDeck);
                            intent.putExtra("code", code);
                            intent.putExtra("money","money2");
                            intent.putExtra("otherMoney","money1");
                            startActivity(intent);
                        }
                    }
                });
            }
        }
    });
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

}
