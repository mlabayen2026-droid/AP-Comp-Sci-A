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

      while (true){
          
          System.out.println ("Type \"add\" to add to your shopping list, type"
              + " \"remove\" to remove an item "
              + "or type \"quit\" to quit");
          
          String addStr = "add"; //the add str is add
          String quitStr = "quit"; //the quit string is quit
          String removeStr = "remove"; //the remove string is remove
          String inputStr = scanner.nextLine();
          
          if (inputStr.equals(addStr)){
              System.out.println("\nWhat would you like to add?");
              //user will type out the item to add
              String item;
              item = scanner.nextLine();
              ShoppingList.add(item);
              System.out.println("\nHere is your current list!");
              for (int i = 0; i < ShoppingList.size(); i++) {
                  System.out.println((i + 1) + ". " + ShoppingList.get(i));
                  
              }
          }
          
          if (inputStr.equals(removeStr)) {
              System.out.println("\nEnter the number of what you would like to remove.");
              int input = scanner.nextInt();
              ShoppingList.remove(input - 1);
              scanner.nextLine(); //fixes a double prompt print bug
              
                            System.out.println("\nHere is your current list!");
              for (int i = 0; i < ShoppingList.size(); i++) {
                  System.out.println((i + 1) + ". " + ShoppingList.get(i));
              
          }
          }
          if (inputStr.equals(quitStr)) {
              System.exit(0);
          }
     
      }
    }
}

      
    

