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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class game extends AppCompatActivity {
private Button create;
private Button join;
private EditText yourCode;
FirebaseAuth firebaseAuth;


ArrayList<Card> deck=new ArrayList<>();
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
            String code= UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
            yourCode.setText(code);

            GameManeger gameManeger=new GameManeger(code, firebaseAuth.getCurrentUser().getEmail().toString(), false);
            FirebaseFirestore.getInstance().collection("games").add(gameManeger).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error!=null){
                                Log.w("Firestore", "fail", error);
                                return;
                            }
                            if (value!=null && value.exists()){
                                if(!value.getBoolean("mode")){
                                    Intent intent=new Intent(com.amitai.mathprojectasafdadon.game.this,MyGameActivity.class);
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
                            documentReference.update("mode", true);
                            Intent intent = new Intent(com.amitai.mathprojectasafdadon.game.this, MyGameActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        }
    });
    }





}
