package org.blackjack;

import java.util.LinkedList;

public class Player{

    private String name;
    private LinkedList<Card> hand;

    public Player(String name_in){
        name = name_in;
        hand = new LinkedList<>();
    }

    public String getName(){
        return name;
    }

    public void giveCard(Card card){
        hand.add(card);
    }

    public void giveHand(Card card1, Card card2){
        hand.add(card1); hand.add(card2);
    }

    public LinkedList<Card> getHand(){ 
        return hand; 
    }

    public void clearHand(){
        hand = new LinkedList<>();
    }
    
    @Override
    public String toString(){
        String str = name.toLowerCase() + ":";

        String handToString = hand.toString();
        str += " " + handToString.substring(1, handToString.length() - 1);

        return str;
    }

}