package org.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardRankTest {

    @Test
    public void generateExplicitCardRank(){
        //assertEquals(CardRank.Ace,cardRank);
        assertEquals(2,CardRank.TWO.value);
        assertEquals(5,CardRank.FIVE.value);
        assertEquals(10,CardRank.TEN.value);
        assertEquals(10,CardRank.KING.value);
        assertEquals(11,CardRank.ACE.value);
        //assertEquals(11,CardRank.findByLabel("A").value);
    }

    @Test
    public void canGetEnumByLabel(){
        assertEquals(CardRank.ACE,CardRank.findByLabel("A"));
        assertEquals(11,CardRank.findByLabel("A").value);
    }

}


