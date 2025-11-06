/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mystore;

import java.util.*;
        
/**
 *
 * @author MLabayen2026
 */
/*
Write a program (project) called "MyStore"

After the public Class MyStore, add another class called "Item"

Give the Item Class variables strName and dblPrice.

Items: T Shirt - 29.99, Shoes - 125.99, Jeans - 60.99, Jacket - 250.00, Hat 
- 25.99, Shorts - 75.99, Blouse - 88.99

Create a toString method that return the name and price (e.g. "Item: Shoes,
Price: 125.99")

Add getters and setters to set/return both variables

in the MyStore controlling main method, set a colnstant variable MAX_BUDGET to
$250.00

Ask the user to enter items. If they enter "T Shirt" or whatever, get the price 
for that item. Add each item's price to a total, and increment a count.

After each entry, let them know the count and how much they've spent so far. 
Set conditional statements so that they cannot go over budget (give a warning 
if they go over budget).

You can use a while(true) loop for this.
*/

//set up class Item
        
        class Item {
            //declare the variables from the assignment prompt
            private String strName;
            private double dblPrice;
            
            
            //set up the constructor
            
            public Item(String strName, double dblPrice) {
                this.strName = strName;
                this.dblPrice = dblPrice;
                
            }
            
            //getter
            // get the name
            public String getName(){
                return strName;
            }
            
            //get the Price
            
            public double getPrice(){
                return dblPrice;
            }
            
            //setter
            // set the name
            public void setName(String strName){
                this.strName = strName;
            }
            
            // set the Price
            public void setPrice (double dblPrice){
                this.dblPrice = dblPrice;
            }
            
            //do the to string
            
            @Override
            public String toString(){
                return "Item: " + strName +", Price: " + dblPrice;
            }
        }

public class MyStore {
//max budget
    public static final double MAX_BUDGET = 250.00;
    
    public static void main(String[] args) {
        // make the start stuff
        Scanner input = new Scanner(System.in);
        double total = 0.0;
        int count = 0;
        
        // store stock
            Item[] items = {
                new Item("T Shirt", 29.99),
                new Item("Shoes", 125.99),
                new Item("Jeans", 60.99),
                new Item("Jacket", 250.00),
                new Item("Hat", 25.99),
                new Item("Shorts", 75.99),
                new Item("Blouse", 88.99)
            };

        //Dialog
            System.out.println("Welcome to Cool Store!");
            System.out.println("The max budget is $" + MAX_BUDGET);
            System.out.println("Heres whats in stock: ");
            System.out.println("T Shirt - $29.99");
            System.out.println("Shoes - $125.99");
            System.out.println("Jeans - $60.99");
            System.out.println("Jacket - $250.00");
            System.out.println("Hat - $25.99");
            System.out.println("Shorts - $75.99");
            System.out.println("Blouse - $88.99");

        //conditional WHILE 
        while (true) {
            System.out.println(" ");
            System.out.println("Enter an item name (or type DONE to check-out): ");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("done")) {
                break;
            }

            // Search for the item
            Item selected = null;
            for (Item i : items) {
                if (i.getName().equalsIgnoreCase(choice)) {
                    selected = i;
                    break;
                }
            }
            //If it doesn't exist
                if (selected == null) {
                    System.out.println("Item(s) not found. Try again.");
                    continue;
                }

            // Check if adding item stays under budget
                if (total + selected.getPrice() > MAX_BUDGET) {

                    System.out.println("\nWARNING: Cannot add item because it is over the $" + MAX_BUDGET + " maximum budget");
                    System.out.println("\n**ITEM REMOVED**");
                    System.out.println("\nTotal amount spent: $" + String.format("%.2f", total));
                    System.out.println("Total amount remaining: $" + String.format("%.2f",(MAX_BUDGET - total)));
                    continue;
                }

            // if the purchace is good
                total += selected.getPrice();
                count++;

            //Response

                System.out.println("\nAdded: " + selected.toString());
                System.out.println("Items purchased: " + count);
                //Calculates for remainder of money spent
                    System.out.println("Total amount spent: $" + String.format("%.2f", total));
                    System.out.println("Total amount remaining: $" + String.format("%.2f",(MAX_BUDGET - total)));
            }

        //Check out Text
            System.out.println("\nFinal amount due: $" + String.format("%.2f", total));
            System.out.println("Change due: $" + String.format("%.2f",(MAX_BUDGET - total)));
            
            System.out.println("Items purchased: " + count);
            System.out.println(" ");
            System.out.println("Thank you for shopping! Have a nice day!");
        

            }
        }
        
        
    
