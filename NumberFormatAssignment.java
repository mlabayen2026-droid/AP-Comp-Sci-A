/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.numberformatassignment;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.text.DecimalFormat;

/**
 *
 * @author MLabayen2026
 */
public class NumberFormatAssignment {

    public static void main(String[] args) {
    System.out.println("Problem #1 \n");
        /*1. Ask for total number of students at a school (any school).
Now ask for the number of girls at that school. Using Number formatting, 
output the % of girls and % of boys at that school.*/
        
        // set up scanner
        Scanner scanner = new Scanner (System.in);
        String StrIn= "";
        //set up % format
        NumberFormat fmtperc = NumberFormat.getPercentInstance();
        fmtperc.setMaximumFractionDigits(3);
        double Boys;
        System.out.println("How many students attend the school?");
        double Students = scanner.nextDouble();
        System.out.println("How many students are girls?");
        double Girls = scanner.nextDouble();
        Boys = Students - Girls;
        
        //extra line of code to check variables are returning proper values are imputted correctly
        //System.out.println( Girls + "" + Boys + "" + Students + "" + (Girls/Students) + "" + (Boys/Students));
        
        System.out.println("There are: \n" + fmtperc.format(Girls/Students) + " Girl Students" + "\n" + fmtperc.format(Boys/Students) + " Boy Students" + "\nAt this school");
        
    System.out.println("\nProblem #2 \n");
        
        /*2.Ask for total amout of money in Dollars and Cents. The method must
convert this value to British Pounds (gbp - Exchange rate on 9/26 is 0.75 pence
to 1$). Output the number of Pounds, mentioning the exchange rate, in a British
(locale) number format. Do the same for the Euro (86 eCents to 1$ today)*/
        NumberFormat fmtPound = NumberFormat.getCurrencyInstance(Locale.ENGLISH); //format for Pounds
        NumberFormat fmtEuro = NumberFormat.getCurrencyInstance(Locale.GERMANY); //format for Euros
        System.out.println("How much money do you have?");
        double money = scanner.nextDouble();
        double moneyX100 = money*100;//convert money to an int so we don't lose data
        int MoneyInt = (int) moneyX100;
        
        //variable check code
        //System.out.println("\n" + money + "    " + moneyX100 + "   " + MoneyInt);
        
        //numbers for Pounds
        int PoundsInt = MoneyInt*75;
        double Pounds = (double) PoundsInt/10000;
        
        //numbers for Euros
        int EuroInt = MoneyInt*86;
        double Euros = (double) EuroInt/10000;
        //check varriables
        //System.out.println("\n" + PoundsInt + "   " + Pounds + "\n" + EuroInt + "   " + Euros);
        
        //Output value
        System.out.println("The exchange rate for Pounds and USD is 0.75 pence to 1 dollar\n The exchange rate for Euros and USD is 0.86 ecents to 1 dollar\nYou put in $"
                + money + " Dollars \nThat would be " + fmtPound.format(Pounds) + " Pounds" 
                + "\nThat would be " + fmtEuro.format(Euros) + " Euros");
        
    System.out.println("\nProblem #3 \n"); 
    
        /*3. Ask for an integer from 0 to 15, and based on the input, format
the number Pi (from the Math class) to that number of decimal places, and print
it out appropriately.*/
        System.out.println("Pick a number 0-15");
        int Round = scanner.nextInt();
        String fmtPI = String.format("%." + Round + "f", Math.PI);
        System.out.println("Pi rounded to the " + Round + "Decimal: \n" + fmtPI);
        
        
        /*4. Generate a random number from 100,000,000 to 999e18as a decimal
number (no scientific notation). */
        
        System.out.println("\nProblem #4\n");
        
        //Create Random Number generator
        Random generator = new Random();
        DecimalFormat decFmt = new DecimalFormat("");
        double RandNum;
        RandNum = generator.nextDouble(999e18-100000000)+100000000;
        
        //variable check
        //System.out.println(RandNum);
        
        System.out.println(decFmt.format(RandNum));
        
    }
}
