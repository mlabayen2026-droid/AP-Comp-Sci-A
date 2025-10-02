/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.enumeratedtypeandwrapperclass;

import java.util.*;

/**
 *
 * @author MLabayen2026
 */
public class EnumeratedTypeAndWrapperClass {

    
    public static void main(String[] args) {

        System.out.println("\nProblem #1a\n");
        
        /*1a)  Write a main method that creates an enumeration of the days in 
the week. Once the enum has been created and the day in the week variables 
filled, print them all out, but rather than the zero based ordinals, print out
the days in the week (1-7).*/
         
        enum Days {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, }
        
        Days Sun, Mon, Tue, Wed, Thur, Fri, Sat;
        Sun = Days.Sunday;
        Mon = Days.Monday;
        Tue = Days.Tuesday;
        Wed = Days.Wednesday;
        Thur = Days.Thursday;
        Fri = Days.Friday;
        Sat = Days.Saturday;
        
        System.out.println("Sun Value: " + Sun);
        System.out.println("Sun Ordinal: " + (Sun.ordinal() + 1));
        System.out.println("Sun Name: " + Sun.name());
        System.out.println();
        System.out.println("Mon Value: " + Mon);
        System.out.println("Mon Ordinal: " + (Mon.ordinal() + 1));
        System.out.println("Mon Name: " + Mon.name());
        System.out.println();
        System.out.println("Tue Value: " + Tue);
        System.out.println("Tue Ordinal: " + (Tue.ordinal() + 1));
        System.out.println("Tue Name: " + Tue.name());
        System.out.println();
        System.out.println("Wed Value: " + Wed);
        System.out.println("Wed Ordinal: " + (Wed.ordinal() + 1));
        System.out.println("Wed Name: " + Wed.name());
        System.out.println();
        System.out.println("Thur Value: " + Thur);
        System.out.println("Thur Ordinal: " + (Thur.ordinal() + 1));
        System.out.println("Thur Name: " + Thur.name());
        System.out.println();
        System.out.println("Fri Value: " + Fri);
        System.out.println("Fri Ordinal: " + (Fri.ordinal() + 1));
        System.out.println("Fri Name: " + Fri.name());
        System.out.println();
        System.out.println("Sat Value: " + Sat);
        System.out.println("Sat Ordinal: " + (Sat.ordinal() + 1));
        System.out.println("Sat Name: " + Sat.name());
        
        System.out.println("\nProblem #1b\n");
        
        /*1b) Write a main method that creates an enumeration of the months in
the year. Once the enum has been created and the month variables filled, print
them out, but not with the enum ordinals, but the "month in the year" numbers
(1-12).*/
        
        enum Months {January, Febuary, March, April, May, June, July, August, 
        September, October, November, December}
        
        Months Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec;
        Jan = Months.January;
        Feb = Months.Febuary;
        Mar = Months.March;
        Apr = Months.April;
        May = Months.May;
        Jun = Months.June;
        Jul = Months.July;
        Aug = Months.August;
        Sep = Months.September;
        Oct = Months.October;
        Nov = Months.November;
        Dec = Months.December;
        
        System.out.println("Months of Year (1-12): ");
        for (Months month : Months.values()) {
            System.out.println(month + ": " + (month.ordinal() + 1));
        }
        
        /*2) Write a main method which asks for your CCHS username
(including graduation year).
        
        It must create a string of the username, as well as a string of the year
        part (use substring method of String object). Recall all the years have
        a length of 4 characters.Use an Integer object to parse the int value of
        that string. Have the method print your graduation year, as well as say
        what the year after your graduation year will be by adding 1 to the 
        parsed int value.

Have it also print "In computer language, you graduate in: " and then the binary
        string version of your graduation year.*/

}

    }

