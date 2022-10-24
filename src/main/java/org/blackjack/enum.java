package org.blackjack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

enum CardRank {
    ACE("A",11),
    TWO("2",2),
    THREE("3",3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("J",10),
    QUEEN("Q",10),
    KING("K",10);

    public final String label;
    public final int value;

    //Attach values to enums
    CardRank(String label, int value){
        this.label = label;
        this.value = value;
    }

    //Create map, so we can look up enums by their label
    private static final Map<String, CardRank> map;
    static {
        //map = new HashMap<String,CardRank>();
        // for (CardRank v  : CardRank.values()){
        //     map.put(v.label, v);
        // }

        map = Arrays.stream(CardRank.values())
        .collect(Collectors.toMap(
            v -> v.label, 
            v -> v));
    }

    public static CardRank findByLabel(String la){
        return map.get(la);
    }
}

enum CardSuit {

    SPADES("S"),
    HEARTS("H"),
    CLUBS("C"),
    DIAMONDS("D");
    
    public final String label;

    //Attach values to enums
    CardSuit(String label){
        this.label = label;
    }

    //Create map, so we can look up enums by their label
    private static final Map<String, CardSuit> map;
    static {
        map = new HashMap<String,CardSuit>();
        for (CardSuit v  : CardSuit.values()){
            map.put(v.label, v);
        }
    }

    public static CardSuit findByLabel(String la){
        return map.get(la);
    }

}