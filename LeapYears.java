/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.leapyears;
import java.util.*;
/**
 *
 * @author 760ma
 */

/*
Write a program that reads an integer value from the user rep-
resenting a year. The purpose of the program is to determine if
the year is a leap year (and therefore has 29 days in February)
in the Gregorian calendar. Use a boolean variable isLeapYear for 
this (initialize to false).  

A year is a leap year if it is divisable by 4, unless it is also 
divisable by 100 but not 400. For example, the year 2003 is not a leap year, 
but 2004 is. The year 1900 is not a leap year because it is divisable by 
100 but not 400, and the year 2000 is a leap year because it is divisable by
100, and also divisable by 400. Produce an error message for
any input value less than 1582 (the year the Gregorian calendar
was adopted).
*/
public class LeapYears {

    
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        
        //ask year
        System.out.println("Enter a year: ");
        
        //scan
        int year = scan.nextInt ();
        
        //boleen if it is a leap year
        boolean isLeapYear = false;
        
        //if year to early
        if (year < 1582){
            System.out.println("ERROR: Year to early, must be 1582 or later");
            return;
        }
        
        //rules of leap, divisable by four or 400 but not 100 if it is not also divisable by 400
        if ((year % 4 == 0 && year % 100 !=0) || (year % 400 == 0)){
            isLeapYear = true;
        }
        
        //now if the boolean is true tell them
        if (isLeapYear){
            System.out.println(year + " is a leap year!");
        }else {
            System.out.println(year + " is not a leap year.");
        }
        
        
    }
}
