package org.blackjack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Collections;
import java.util.UUID;


/**
 * 
 * Deck class containing up to 52 Card. 
 * 
 */
public class Deck{

    final UUID uuid = UUID.randomUUID();
    final int DECK_MAX_SIZE = 52;

    private LinkedList<Card> deck = new LinkedList<>();
    private int size = 0;

    /**
     * Add a {@code Card} to the deck. Fails if the deck is full.
     * @param card
     */
    public void add(Card card){
        if(size >= DECK_MAX_SIZE) System.out.println("Deck is full.");
        else{deck.add(card); size++;}
    }

    /**
     * Remove a {@code Card} from the top of the deck.
     * @return The {@code Card} object on top of the deck.
     */
    public Card removeFromTop(){
        if(size <= 0) return null;
        else size--; return deck.pop();
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public int size() { return size; }


    public Object[] toArray(){
        return deck.toArray();
    }

    @Override
    public String toString(){
        String str = "";
        
        str += "Deck: \n[";
        for(Card c : deck){
            str += c + " ";
        }
        str = str.substring(0,str.length()-1);
        str += "]\n";

        return str;
    }

    /**
     * Creates a new, shuffled {@code Deck} with 52 cards
     * @return New shuffled {@code Deck} object
     */
    public static Deck createShuffledDeck(){

        Deck new_deck = new Deck();

        /* Make one card for each rank of every type. We can use the enum values for this */
        for(CardSuit card_suit : CardSuit.values()){
            for(CardRank card_value : CardRank.values()){
                Card new_card = new Card(card_value,card_suit);
                new_deck.add(new_card);
            }
        }
        new_deck.shuffle();

        return new_deck;
    }

    /**
     * Creates a new {@code Deck}, given the path of a file
     * 
     * @param filepath filepath to a .txt file
     * @return new Deck object
     */
    public static Deck createDeckFromFile(String filepath){

        Deck new_deck = new Deck();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            
            /* File should only contain one line */
            String line = bufferedReader.readLine();

            //Remove whitespace, make array by splitting on ','
            String[] line_array = line.replaceAll(" ","").split(",");



            for(String word : line_array){

                if(word.length() != 2) throw new WrongFileFormatException("Word in file is wrong size:" + word);

                String suit_string = word.charAt(0) + "";
                String value_string = word.substring(1);

                CardSuit cardsuit = CardSuit.findByLabel(suit_string);
                CardRank cardvalue = CardRank.findByLabel(value_string);

                Card card = new Card(cardvalue,cardsuit);

                new_deck.add(card);

            }

            bufferedReader.close();

        }
        catch(FileNotFoundException e){
            System.err.println("No file found at path " + filepath + "." );
            System.out.println("Creating a new shuffled deck instead...");
            return createShuffledDeck();
        }
        catch(WrongFileFormatException e){
            System.err.print(e.toString());
            System.out.println("Creating a new shuffled deck instead...");
            return createShuffledDeck();
        }
        catch(IOException e){
            System.err.println("Error reading file. Exiting.");
            System.exit(1);

        }

        return new_deck;
    }

}