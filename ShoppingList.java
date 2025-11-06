/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.shoppinglist;

import java.util.*;
/**
 *
 * @author MLabayen2026
 */


/*
Create a Java project called ShoppingList

Use the Scanner to get a list of items from the user (Carrots, Bread, Corn etc)

Store these items in an ArrayList (see my demo), and display them, 
numbered. Allow users to remove an item, numbered.

Redisplay the new list.
*/

public class ShoppingList {

    public static void main(String[] args) {
       
       //create the Array List
       ArrayList<String> ShoppingList = new ArrayList<String>();
       
       //set up scanner
       Scanner scanner = new Scanner(System.in);
      //set up loop
      System.out.println ("Type \"add\" to add to your shopping list, type"
              + " \"remove\" to remove an item "
              + "or type \"quit\" to quit");
      while (true){
          String addStr = "add"; //the add str is add
          String quitStr = "quit"; //the quit string is quit
          String removeStr = "remove"; //the remove string is remove
          String inputStr = scanner.nextLine();
          
          if (inputStr == addStr){
              
          }
          
          if (inputStr == removeStr) {
              
          }
          
          if (inputStr == quitStr) {
              
          }
      }
       //Ask to add an item
       
    }
}
