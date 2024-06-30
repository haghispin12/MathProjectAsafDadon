package com.amitai.mathprojectasafdadon;

import java.util.ArrayList;

public class GameManeger {
    private String id;
    private String player1;
    private String player2;
    private boolean mode;
    private int money1;
    private int money2;
    private int player1Bet;
    private int player2Bet;
    private boolean isTheSecondBet;
    private ArrayList<Card> hand1;
    private ArrayList<Card> hand2;
    private ArrayList<Card> deck;
    public GameManeger(String id, String player1, boolean mode, ArrayList<Card> hand1, ArrayList<Card> hand2, ArrayList<Card> deck){
        this.id=id;
        this.player1=player1;
        this.mode=mode;
        player2="";
        money1=1000;
        money2=1000;
        player1Bet=0;
        player2Bet=0;
        isTheSecondBet=false;
        this.hand1=hand1;
        this.hand2=hand2;
        this.deck=deck;
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

    public int getMoney1() {
        return money1;
    }

    public void setMoney1(int money1) {
        this.money1 = money1;
    }

    public int getMoney2() {
        return money2;
    }

    public void setMoney2(int money2) { this.money2 = money2; }

    public int getPlayer1Bet() { return player1Bet; }

    public void setPlayer1Bet(int player1Bet) { this.player1Bet = player1Bet; }

    public int getPlayer2Bet() { return player2Bet; }

    public void setPlayer2Bet(int player2Bet) { this.player2Bet = player2Bet; }

    public boolean isTheSecondBet() {
        return isTheSecondBet;
    }

    public void setTheSecondBet(boolean theSecondBet) {
        isTheSecondBet = theSecondBet;
    }

    public ArrayList<Card> getHand1() {
        return hand1;
    }

    public void setHand1(ArrayList<Card> hand1) {
        this.hand1 = hand1;
    }

    public ArrayList<Card> getHand2() {
        return hand2;
    }

    public void setHand2(ArrayList<Card> hand2) {
        this.hand2 = hand2;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
