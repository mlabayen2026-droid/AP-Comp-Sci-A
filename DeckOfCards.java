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








/*
{"2 of Diamonds","3 of Diamonds","4 of Diamonds","5 of Diamonds","6 of Diamonds",
    "7 of Diamonds","8 of Diamonds","9 of Diamonds","10 of Diamonds",
    "Jack of Diamonds","Queen of Diamonds","King of Diamonds","Ace of Diamonds"},
            
{"2 of Hearts","3 of Hearts","4 of Hearts","5 of Hearts","6 of Hearts",
    "7 of Hearts","8 of Hearts","9 of Hearts","10 of Hearts",
    "Jack of Hearts","Queen of Hearts","King of Hearts","Ace of Hearts"},

{"2 of Spades","3 of Spades","4 of Spades","5 of Spades","6 of Spades",
    "7 of Spades","8 of Spades","9 of Spades","10 of Spades",
    "Jack of Spades","Queen of Spades","King of Spades","Ace of Spades"},

{"2 of Clubs","3 of Clubs","4 of Clubs","5 of Clubs","6 of Clubs",
    "7 of Clubs","8 of Clubs","9 of Clubs","10 of Clubs",
    "Jack of Clubs","Queen of Clubs","King of Clubs","Ace of Clubs"},
        };
*/
