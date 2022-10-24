package org.blackjack;

public class WrongFileFormatException extends Exception{

    public WrongFileFormatException(String errorMessage){
        super(errorMessage);
    }

}
