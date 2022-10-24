package org.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

//    public static void main(String[] args){
//    Result result = JUnitCore.runClasses(CardTest.class);
//
//    for (Failure failure : result.getFailures()){
//        System.out.println(failure.toString());
//    }
//
//    System.out.println(result.wasSuccessful());
//
//    }

    @Test
    public void canGenerateCard(){
        //assertEquals(CardRank.Ace,cardRank);
        System.out.println(CardRank.findByLabel("A"));
        Card card = new Card(CardRank.findByLabel("A"), CardSuit.SPADES);
        assertEquals(11,card.rank.value);
        //assertEquals(11,CardRank.findByLabel("A").value);
    }

    @Test
    public void hasUUID(){
        Card card = new Card(null,null);
        System.out.println(card.uuid);
        assertNotNull(card.uuid);
    }

}


