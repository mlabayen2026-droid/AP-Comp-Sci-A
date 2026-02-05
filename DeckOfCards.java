/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.deckofcards;
import java.util.Random;
/**
 *
 * @author MLabayen2026
 */

/*
Write a 2D String array called deckOfCards. This will have 4 arrays, each of 
length 13. Fill the 4 arrays with the 4 suits of playing cards, 
from Ace to King(1 to 13). Shuffle the deck, and deal out 4 hands of 5 cards 
from this 2D array (no dealing the same card twice). 
Show the hands(Diamonds, Hearts, Clubs, Spades).
*/
public class DeckOfCards {

    public static void main(String[] args) {
        //make the 2D array
        String[][] theDeck = new String [4][13];
String[] suits = {"Diamonds", "Hearts", "Clubs", "Spades"};
String[] value = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        
       for (int i = 0; i<4; i++) {
           for (int j = 0; j < 13; j++) {
               theDeck[i][j] = value[j] + " of " + suits[i];
           }
       }
String[][] hands = new String[4][5];
Random rand = new Random();

for (int player = 0; player < 4; player++) {
    for (int cardCount = 0; cardCount < 5; cardCount++) {
        boolean foundCard = false;
        
        while (!foundCard) { // if it has not found a card it will go find one
            int row = rand.nextInt(4); //gets the random suit
            int col = rand.nextInt(13); //gets the random value
            
            //now need to check if the card is in the deck or not
            if (theDeck[row][col] !=null) {//if the card is stil in the deck
                hands[player][cardCount] = theDeck[row][col];
                theDeck[row][col] = null; //update the deck to make the card null
                foundCard = true; //resets the loop
            }
            
        }
    }
}
        //print the shuffled hands
        for (int i = 0; i < hands.length; i++) {
            System.out.println("\n\nPlayer " + (i + 1) + ": ");
            for (int j = 0; j < hands[i].length; j++) {
                System.out.print("[" +hands[i][j] + "] ");
            }
        }

    }
}

