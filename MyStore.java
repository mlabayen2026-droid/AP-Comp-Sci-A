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
            System.out.println("Welcome to TheStore!");
            System.out.println("Your max budget is $" + MAX_BUDGET);
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
            System.out.println("Enter an item name (or type STOP to check-out): ");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("stop")) {
                break;
            }

            // Search for the real item
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
                    System.out.println(" ");
                    continue;
                }

            // Check if adding item stays under budget
                if (total + selected.getCost() > MAX_BUDGET) {
                    System.out.println(" ");
                    System.out.println("WARNING: Adding this item will exceed your set $" + MAX_BUDGET + " maximum budget!!!");
                    System.out.println(" ");
                    System.out.println("**ITEM REMOVED**");
                    System.out.println(" ");
                    System.out.println("Total amount spent: $" + String.format("%.2f", total));
                    System.out.println("Total amount remaining: $" + String.format("%.2f",(MAX_BUDGET - total)));
                    System.out.println(" ");
                    continue;
                }

            // It's a valid purchase
                total += selected.getCost();
                count++;

            //Response
                System.out.println(" ");
                System.out.println("Added: " + selected.toString());
                System.out.println("Items purchased: " + count);
                //Calculates for remainder of capital spent & remaining
                    System.out.println("Total amount spent: $" + String.format("%.2f", total));
                    System.out.println("Total amount remaining: $" + String.format("%.2f",(MAX_BUDGET - total)));
            }

        //Ending Text
            System.out.println(" ");
            System.out.println("Final amount due: $" + String.format("%.2f", total));
            System.out.println("Change due: $" + String.format("%.2f",(MAX_BUDGET - total)));
            System.out.println("Items purchased: " + count);
            System.out.println(" ");
            System.out.println("Thank you for shopping at Ted-Mart!");
        

            }
        }
        
        //class
        
        class Item {
            //declare variables
            private String strName;
            private double dblCost;
            
            
            //constructor
            
            public Item(String strName, double dblCost) {
                this.strName = strName;
                this.dblCost = dblCost;
                
            }
            
            //getter
            //name
            public String getName(){
                return strName;
            }
            
            //Cost
            
            public double getCost(){
                return dblCost;
            }
            
            //setter
            //name
            public void setName(String strName){
                this.strName = strName;
            }
            
            //Price
            public void setPrice (double dblCost){
                this.dblCost = dblCost;
            }
            
            //to string
            
            @Override
            public String toString(){
                return "Item: " + strName +", Cost: " + dblCost;
            }
        }
    }
}
