package org.blackjack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    public void testInitialValues(){
        Deck testDeck = new Deck();
        assertEquals(0,testDeck.size());
    }

    @Test
    public void canAddAndRemove(){

        Deck testDeck = new Deck();

        // Adding

        testDeck.add(new Card(CardRank.ACE,CardSuit.DIAMONDS));
        testDeck.add(new Card(CardRank.TWO,CardSuit.DIAMONDS));
        testDeck.add(new Card(CardRank.THREE,CardSuit.DIAMONDS));

        assertEquals(3,testDeck.size());

        // Removing
        assertInstanceOf(Card.class,testDeck.removeFromTop());
        assertEquals(2,testDeck.size());

        testDeck.removeFromTop();
        assertEquals(1,testDeck.size());
        testDeck.removeFromTop();
        assertEquals(0,testDeck.size());

        assertNull(testDeck.removeFromTop());
        assertEquals(0,testDeck.size());

        // And after remove

        testDeck.add(new Card(CardRank.ACE,CardSuit.DIAMONDS));
        assertEquals(1,testDeck.size());
        testDeck.removeFromTop();
        assertEquals(0,testDeck.size());
    }

    @Test
    public void testToString(){

        Deck testDeck = new Deck();

        testDeck.add(new Card(CardRank.ACE,CardSuit.DIAMONDS));
        testDeck.add(new Card(CardRank.TWO,CardSuit.DIAMONDS));
        testDeck.add(new Card(CardRank.THREE,CardSuit.DIAMONDS));

        System.out.println(testDeck.toString());

    }

    @Test
    public void testCreateShuffledDeck(){
        Deck testDeck = Deck.createShuffledDeck();

        // Deck should have 52 cards
        assertEquals(52,testDeck.size());

        // Check that two shuffled decks are not the same
        Deck testDeckTwo = Deck.createShuffledDeck();

        // Warning! This test can fail due to randomness.
        assertFalse(Arrays.equals(testDeck.toArray(),testDeckTwo.toArray()));
    }

    @Test
    public void testCreateDeckFromFile(){
        Deck testDeck = Deck.createDeckFromFile("src/main/resources/testDeck.txt");

        System.out.println(testDeck.toString());

        // Testing invalid filename
        // This should not throw a FileNotFoundException
        Deck.createDeckFromFile("BananaMan");

        // Testing invalid format in input file
        // This should not throw a WrongFileFormatException
        Deck testDeckInvalid = Deck.createDeckFromFile("src/main/resources/testDeckInvalidFormat.txt");

    }

}