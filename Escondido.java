/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package escondido;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;
import java.util.*;
/**
 *
 * @author MLabayen2026
 */
public class Escondido {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Escondido The Game!"
                + "\nPress F to Start"
                + "\nPress R for Rules"
                + "\nPress E to Exit");
        boolean running = true;
        
        while (running) {
        String MenuButton = scanner.nextLine();
        if (MenuButton.equalsIgnoreCase("F")){
            startGame();
        }
        else if (MenuButton.equalsIgnoreCase("R")){
            System.out.println("Rules:"
                    + "\nYou wakeup at the Transit Center everyday"
                    + "\nYou Fall asleep at 6pm everyday"
                    + "\nYou can hold 3 items at a time"
                    + "\nYou can Vist 3 locations everyday"
                    + "\nYou can interact 5 times per location");
        }
        else if (MenuButton.equalsIgnoreCase("E")){
            running = false;
        }
         else {
            System.out.println("That was not an option");
        }
    }
       
      
}
      public static void startGame(){
            System.out.println("Starting Game...");
            System.out.println("You wake up in the Escondido Transit Center");
            
        }
}