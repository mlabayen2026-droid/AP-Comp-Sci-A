/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.darrays;
import java.util.Random;
/**
 *
 * @author 760ma
 */
/*
Write out an initialized array "weekDays" with all 7 days of the week. Print the
days of the week out, one day per row. Now resize the array to 5, and copy just
the weekdays (so not Saturday or Sunday) to it, and print the days again, one
day per row. Comment your code where you are resizing it.

For an extra bonus point, add a shuffling method, to change the order of the
days randolmly.
*/
public class App {

    public static void main(String[] args) {
        //initialize the array with the 7 days needed
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday",
            "Tursday", "Friday", "Saturday"};
        
        System.out.println("DAYS OF THE WEEK!");
        printArray (weekDays);        
        
        //Resizing the array, I am just gonna make a new one with only the 5 work days
        weekDays = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday"};
        System.out.println("\nNO WEEKENDS!");
        printArray(weekDays); //print the new array
        
        //shuffle the stuff
        shuffleArray(weekDays);
        System.out.println("\nSHUFFLED!");
        printArray(weekDays);

    }
    
    //method so that the array prints once per row
    public static void printArray(String[] arr) {
        for (String day : arr) { //essentially just says print whatever day in the array and repeat down the list starting with 0 which is monday
            System.out.println(day);
        }
    }
    //create the shuffle function
    public static void shuffleArray(String[] arr) {
        Random rand = new Random(); //random to pick what is swapped
        /*for (int i = arr.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            //swap them
            String temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        } */
        for (int i = 0; i < arr.length; i++) {
//set random target index
int randomIndexToSwap = rand.nextInt(arr.length);
//set a temp variable to the value at that index
String temp = arr[randomIndexToSwap];
//set the target array element to the current element value
arr[randomIndexToSwap] = arr[i];
//set the current element to the temp variable value
arr[i] = temp;
        
    }
    }
}
