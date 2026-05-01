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
        //creates the game files needed to play
        GameFiles();
        
        
        // TODO code application logic here
        Scanner input = new Scanner(System.in);

        boolean running = true;
        
        while (running) {
                    System.out.println("\nWelcome to Escondido The Game!"
                + "\nPress F to Start"
                + "\nPress R for Rules"
                + "\nPress E to Exit");
        String MenuButton = input.nextLine();
        if (MenuButton.equalsIgnoreCase("F")){
            startGame(input);
        }
        else if (MenuButton.equalsIgnoreCase("R")){
            System.out.println("\nRules:"
                    + "\nYou wake up at the Transit Center everyday"
                    + "\nYou fall asleep at 6pm everyday"
                    + "\nYou can hold 3 items at a time"
                    + "\nYou can vist 3 locations everyday"
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
    
    public static void GameFiles(){
        
        File GameFiles = new File("Game Files");
        if (!GameFiles.exists()){
            GameFiles.mkdir(); //this creates the Folder
        }
        
        //now create the 3 game files
        String[] fileNames = {"inventory.txt", "box.txt", "save.txt"};
                for (String name: fileNames) {
                    
                    //puts the new files in the folder
                    File file = new File(GameFiles, name); 
                    try{
                        if (file.createNewFile()) {
                        }
                    } catch (IOException e){
                            
                            }
                    
                }
              
    }
    
    
    
      public static void startGame(Scanner input){
         
          
            System.out.println("\nStarting Game...");
            
            boolean playing = true;
            
            while (playing){
            System.out.println("\nYou wake up in the Escondido Transit Center"
                    + "\nThere's a box on the ground"
                    + "\nWhat would you like to do?"
                    + "\n"
                    + "\nInteract with the box [e]"
                    + "\nStart your day [f]"
                    + "\nReturn to main Menu [r]");
            String Input = input.nextLine(); 
            
            if (Input.equalsIgnoreCase("e")){
                //box logic
            }
            
            else if (Input.equalsIgnoreCase("f")){
                //game logic
                InGameDay(input);
            }
            
            else if (Input.equalsIgnoreCase("r")){
                playing = false;
            }
            
            else{
                System.out.println("That was not an option");
            }
                
            
            }
        }
      
      public static void InGameDay (Scanner Input){
          
          int elapsedTime = 0;
          while (elapsedTime < 360){
          System.out.println("Interact +20minutes");
          }
                  
      }
      
      
      
}