package org.blackjack;
/*
 *  Program called 21. Sam and the Dealer play a game of Blackjack. 
 * 
 *  @author Daniel Kristiansen
 */


import java.util.LinkedList;


public class Blackjack {

    static Deck drawing_deck;
    static Player player;
    static Player dealer;
    /**
     * Main method for TwentyOne class
     * @param args first argument must be a filepath to a .txt file
     */
    public static void main(String[] args){

        init(args);

        play();

    }

    public static void init(String[] args){

        /*
         * If at least one argument, assume that it is the filepath string. Else create a new deck.
         */
        if(args.length > 0){
            String filepath = args[0];
            drawing_deck = Deck.createDeckFromFile(filepath);
        }
        else{
            System.out.println("Missing argument: Deck");
            System.out.println("Creating a new deck...");

            drawing_deck = Deck.createShuffledDeck();
        }

        player = new Player("Sam");
        dealer = new Player("Dealer"); 

    }

    public static void play(){

        deal();

        /*
          Sam always wins if he starts with blackjack.
          If Sam doesn't have blackjack, but the dealer does, then the dealer wins
         */
        if(isBlackJack(player.getHand())) endGame(player);
        if(isBlackJack(dealer.getHand())) endGame(dealer);

        /*
         * If both players starts with 22, the dealer wins.
         */
        if(countScore(player.getHand()) == 22 && countScore(dealer.getHand()) == 22) endGame(dealer);

        /* Draw cards as long as sam's score is lower than 17 */
        int score_sam = countScore(player.getHand());
        while(score_sam < 17){
            Card drawn_card = drawing_deck.removeFromTop();
            player.giveCard(drawn_card);

            //Update score. If score is over 21, dealer wins.
            score_sam = countScore(player.getHand());
            if(score_sam > 21) endGame(dealer);
            
        }

        /* Dealer draws cards as long as they have less points than player */
        int score_dealer = countScore(dealer.getHand());
        while(score_dealer <= score_sam){
            Card drawn_card = drawing_deck.removeFromTop();
            dealer.giveCard(drawn_card);

            score_dealer = countScore(dealer.getHand());
            if(score_dealer > 21) endGame(player);
        }

        // This will always be true. Consider keeping for clarity
        if(score_dealer > score_sam) endGame(dealer);
        else endGame(player);

    }

    /**
     * Deal the first hand.
     */
    public static void deal(){
        player.giveCard(drawing_deck.removeFromTop());
        dealer.giveCard(drawing_deck.removeFromTop());
        player.giveCard(drawing_deck.removeFromTop());
        dealer.giveCard(drawing_deck.removeFromTop());
    }

    /**
     * Check is hand is blackjack
     * 
     * @param hand a {@code LinkedList<Card>} object
     * @return true if hand is exactly 21 points, else false
     */
    public static boolean isBlackJack(LinkedList<Card> hand){
        return countScore(hand) == 21;
    }

    /**
     * Calculate the score of a hand
     * 
     * @param hand a {@code LinkedList<Card>} object
     * @return
     */
    public static int countScore(LinkedList<Card> hand){

        int score = hand.stream()
                .map(Card::score)
                .reduce(0, Integer::sum);

        return score;

    }

    /**
     * End the game by announcing the winner, showing each player's hand. And exiting the program
     * 
     * @param winner
     */
    public static void endGame(Player winner){

        System.out.println("The winner is: " + winner.getName().toLowerCase() + "!");
        
        System.out.println(player);
        System.out.println(dealer);

        System.exit(0);

    }

}


