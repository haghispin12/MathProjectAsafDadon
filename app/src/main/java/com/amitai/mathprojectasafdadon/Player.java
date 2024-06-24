package com.amitai.mathprojectasafdadon;

import android.view.Display;

import java.util.ArrayList;

public class Player {
    private String name;
    private int money;
    private int mode;
    private String code;
    private boolean isYourTurn;
    private ArrayList<Card> deck;

    public Player(String name, String code, int status, ArrayList<Card> deck){
        this.name=name;
        money=1000;
        code=code;
        status=status;
        this.deck=deck;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMode() {
        return mode;
    }
    public void setMode(int mode) {
        this.mode = mode;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void setYourTurn(boolean yourTurn) {
        isYourTurn = yourTurn;
    }
}

