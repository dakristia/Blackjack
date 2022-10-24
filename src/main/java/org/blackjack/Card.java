package org.blackjack;

import java.util.UUID;

/** 
 * Card class with two instance variables of CardType and of CardSuit
 * 
*/
public class Card{

    final UUID uuid;

    CardRank rank;
    CardSuit suit;

    public Card(CardRank in_rank, CardSuit in_suit){
        rank = in_rank; suit = in_suit;
        uuid = UUID.randomUUID();
    }

    @Override
    public String toString(){
        return suit.label + rank.label;
    }

    public int score(){
        return rank.value;
    }

}