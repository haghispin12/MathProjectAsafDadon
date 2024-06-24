package com.amitai.mathprojectasafdadon;

import java.util.ArrayList;

public class GameManeger {
    private String id;
    private String player1;
    private String player2;
    private boolean mode;
    private ArrayList<Card> cards;
    private int money1;
    private int money2;
    public GameManeger(String id, String player1, boolean mode){
        this.id=id;
        this.player1=player1;
        this.mode=mode;
        player2="";
        money1=1000;
        money2=1000;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getMoney1() {
        return money1;
    }

    public void setMoney1(int money1) {
        this.money1 = money1;
    }

    public int getMoney2() {
        return money2;
    }

    public void setMoney2(int money2) {
        this.money2 = money2;
    }
}
