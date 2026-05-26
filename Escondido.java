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
import javax.sound.sampled.*;
/**
 *
 * @author MLabayen2026
 */
public class Escondido {

    private static Clip currentClip = null;
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
                + "\n[f] Start"
                + "\n[r] Rules"
                + "\n[e] Exit"
                            + "\n");
        String MenuButton = input.nextLine();
        if (MenuButton.equalsIgnoreCase("F")){

            startGame(input);
        }
        else if (MenuButton.equalsIgnoreCase("R")){
            System.out.println("\nRules:"
                    + "\nYou wake up at the Transit Center everyday"
                    + "\nYou fall asleep at 6:00PM everyday"
                    + "\nYou can hold 3 items at a time"
                    + "\nYou can vist 3 locations everyday"
                    + "\nYou can interact 1 time per location"
                    + "\nEvery interaction takes 60 in game minutes"
                    + "\nEvery travel takes 60 in game minutes");
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
            
            int daysPlayed = 0;
            int dollars = 0;
            
            File file = new File("Game Files/save.txt");
            if (file.exists() && file.length() > 0 ) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line = reader.readLine();
                    
                    if (line != null && !line.trim().isEmpty()) {
                        //turns the csv into an array
                        String[] values = line.split(",");
                        
                        if (values.length >= 2){
                            //.trim clears the potential whitespace
                            daysPlayed = Integer.parseInt(values[0].trim());
                            dollars = Integer.parseInt(values[1].trim());
                        
                        }
                    }
                    System.out.println("\n[Save Loaded] Day " + daysPlayed + "| Dollars: $" + dollars);
                    
                    
                } catch (IOException | NumberFormatException e){
                    System.out.println("No save data. Creating new save");
                }       
            }
            
              else {
                        saveGame(daysPlayed, dollars);
                        } 
            
            
            boolean playing = true;
            
            while (playing){
                File saveFile = new File("Game Files/save.txt");
                if (saveFile.exists() && saveFile.length() > 0) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
                        String line = reader.readLine();
                        if (line != null && !line.trim().isEmpty()) {
                            String[] values = line.split(",");
                            daysPlayed = Integer.parseInt(values[0].trim());
                            dollars = Integer.parseInt(values[1].trim());
                        }
                    } catch (IOException | NumberFormatException e) {

                    }
                }
                stopSound();
                playSound("LOBBY.wav");
            System.out.println("\nYou wake up in the Escondido Transit Center"
                    + "\nDay: " + daysPlayed + "  | Dollars: $" + dollars
                    + "\nThere's a box on the ground"
                    + "\nWhat would you like to do?"
                    + "\n"
                    + "\n[e] Interact with the box"
                    + "\n[f] Start your day"
                    + "\n[r] Return to main Menu"
                    + "\n");
            String Input = input.nextLine(); 
            
            if (Input.equalsIgnoreCase("e")){
                //box logic
                boxMenu(input);
            }
            
            else if (Input.equalsIgnoreCase("f")){
                //game logic
                stopSound();
                InGameDay(input);
                daysPlayed += 1;
                System.out.println("\nIt is 6:00PM and you drop to the floor asleep");
                // Reload the updated dollars from the save file
                file = new File("Game Files/save.txt");
                if (file.exists() && file.length() > 0) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line = reader.readLine();
                        if (line != null && !line.trim().isEmpty()) {
                            String[] values = line.split(",");
                            dollars = Integer.parseInt(values[1].trim());
                        }
                    } catch (IOException | NumberFormatException e) {
                        System.out.println("Error reading updated wallet value");
                    }
                }
                //save new game data
                saveGame(daysPlayed, dollars);
            }
            
            else if (Input.equalsIgnoreCase("r")){
                playing = false;
            }
            
            else{
                System.out.println("That was not an option");
            }
                
            
            }
        }
      
      public static void InGameDay (Scanner input){
          
          int elapsedTime = 0;
          
          
          
         while (elapsedTime < 60){
              int hours = 12 + (elapsedTime/60);
              int clockHour = hours % 12==0 ? 12 : hours % 12;
          int minutes = elapsedTime % 60;
          // so that time of 0 reads at 00, not 0
          String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
          
              System.out.println("\nWhere would you like to go first?"
                      + "\n[e] Gazebo on Juniper and Grand"
                      + "\n[f] Grand Tea room                  " + clockHour + ":" + formattedMinutes + "PM"
                              + "\n");
              String interact = input.nextLine(); 
              if(interact.equalsIgnoreCase("e")){
          System.out.println("Walking to the Gazebo");
          elapsedTime +=60;
          gazebo(input, elapsedTime);
              }
          else if(interact.equalsIgnoreCase("f")){
              System.out.println("Walking to the Grand Tea Room");
          elapsedTime +=60;    
              teaRoom(input, elapsedTime);
          }
              else{
                System.out.println("That was not an option");
            }
              
         }
          
      }
      
      //FIRST LOCATIONS
      public static void gazebo(Scanner input, int elapsedTime){
    System.out.println("\nYou arrive at Gazebo on Juniper and Grand!\n");
          while (elapsedTime < 120){
              int hours = 12 + (elapsedTime/60);
              int clockHour = hours % 12==0 ? 12 : hours % 12;
          int minutes = elapsedTime % 60;
          // so that time of 0 reads at 00, not 0
          String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
          System.out.println("What would you like to do?"
                      + "\n[e] Search the area"
                      + "\n[f] Talk to Kid                  " + clockHour + ":" + formattedMinutes + "PM"
                              + "\n");
              String interact = input.nextLine(); 
              if(interact.equalsIgnoreCase("e")){
          System.out.println("You search the area and find a Lighter!");
          playSound("LIGHTER.wav");      
          ArrayList<String> inventory = loadInventory();
                if (!inventory.contains("Lighter") && inventory.size() < 3) {
                    inventory.add("Lighter");
                    saveInventory(inventory);
                    System.out.println("Lighter added to inventory.");
                } else if (inventory.size() >= 3) {
                    System.out.println("Inventory full! You cannot hold more items.");
                } else {
                    System.out.println("You already have a Lighter.");
                }
                elapsedTime +=60;
          
              }
          else if(interact.equalsIgnoreCase("f")){
              kid(input);
          elapsedTime +=60;    
              
          }
              else{
                System.out.println("That was not an option");
                
            }
    }
       afternoonTravel(input, elapsedTime);     
      }
      
      public static void teaRoom(Scanner input, int elapsedTime){
        System.out.println("\nYou arrive at The Grand Tea Room!\n");
          while (elapsedTime < 120){
              int hours = 12 + (elapsedTime/60);
              int clockHour = hours % 12==0 ? 12 : hours % 12;
          int minutes = elapsedTime % 60;
          // so that time of 0 reads at 00, not 0
          String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
                    System.out.println("What would you like to do?"
                      + "\n[e] Search the area"
                      + "\n[f] Talk to Tea Seller                  " + clockHour + ":" + formattedMinutes + "PM"
                              + "\n");
              String interact = input.nextLine(); 
              if(interact.equalsIgnoreCase("e")){
          playSound("DISCOVER.wav");
                  System.out.println("You search the area and find an old, dusty note!");
                System.out.println("Note: 'The time loop is not infinite. There is a cinematic way out, you just have to find it.'");
                elapsedTime +=60;
            }
            else if(interact.equalsIgnoreCase("f")){
                teaSeller(input);
                elapsedTime +=60;
          
              }

              else{
                System.out.println("That was not an option");
            }
    }
         afternoonTravel(input, elapsedTime);   
      }
      
      //AFTERNOON TRAVEL METHOD
      public static void afternoonTravel(Scanner input, int elapsedTime){
         
          while(elapsedTime < 180){
          int hours = 12 + (elapsedTime/60);
              int clockHour = hours % 12==0 ? 12 : hours % 12;
          int minutes = elapsedTime % 60;
          // so that time of 0 reads at 00, not 0
          String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
          
              System.out.println("\nYou want to get an afternoon snack."
                      + "\nwhere would you like to go?"
                      + "\n[e] 7-11"
                      + "\n[f] Burger Bench                  " + clockHour + ":" + formattedMinutes + "PM"
                              + "\n");
              String interact = input.nextLine(); 
              if(interact.equalsIgnoreCase("e")){
          System.out.println("Walking to 7-11");
          elapsedTime +=60;
          sevenEleven(input, elapsedTime);
              }
          else if(interact.equalsIgnoreCase("f")){
              System.out.println("Walking to Burger Bench");
          elapsedTime +=60;    
              burgerBench(input, elapsedTime);
          }
              else{
                System.out.println("That was not an option");
            }
          }
      }
      
      //AFTERNOON LOCATIONS
      public static void sevenEleven(Scanner input, int elapsedTime){
         playSound("711.wav");
          System.out.println("\nYou arrive at 7-11!\n");
          while (elapsedTime < 240){
              int hours = 12 + (elapsedTime/60);
              int clockHour = hours % 12==0 ? 12 : hours % 12;
          int minutes = elapsedTime % 60;
          // so that time of 0 reads at 00, not 0
          String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
                    System.out.println("What would you like to do?"
                      + "\n[e] Search the area"
                      + "\n[f] Talk to Shady Guy                  " + clockHour + ":" + formattedMinutes + "PM"
                              + "\n");
              String interact = input.nextLine(); 
              if(interact.equalsIgnoreCase("e")){
          System.out.println("You search the area and find Keys!");
          playSound("KEYS.wav");      
          ArrayList<String> inventory = loadInventory();
                if (!inventory.contains("Keys") && inventory.size() < 3) {
                    inventory.add("Keys");
                    saveInventory(inventory);
                    System.out.println("Keys added to inventory.");
                } else if (inventory.size() >= 3) {
                    System.out.println("Your inventory is full! You cannot hold more items.");
                } else {
                    System.out.println("You already have the Keys.");
                }
                elapsedTime +=60;
          
              }
          else if(interact.equalsIgnoreCase("f")){
              shadyGuy(input);
          elapsedTime +=60;    
              
          }
              else{
                System.out.println("That was not an option");
            }
    }
     duskTravel(input, elapsedTime);       
      }
      
      public static void burgerBench(Scanner input, int elapsedTime){
          playSound("BURGER.wav");
          System.out.println("\nYou arrive at Burger Bench!\n");
          while (elapsedTime < 240){
              int hours = 12 + (elapsedTime/60);
              int clockHour = hours % 12==0 ? 12 : hours % 12;
          int minutes = elapsedTime % 60;
          // so that time of 0 reads at 00, not 0
          String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
                    System.out.println("What would you like to do?"
                      + "\n[e] Search the area"
                      + "\n[f] Talk to Fancy Guy                  " + clockHour + ":" + formattedMinutes + "PM"
                              + "\n");
              String interact = input.nextLine(); 
              
          if(interact.equalsIgnoreCase("e")){
              playSound("MONEY.wav");  
              System.out.println("You search the area and find $3 on the ground!");
                
                File file = new File("Game Files/save.txt");
                int day = 0;
                int dollars = 0;
                if (file.exists() && file.length() > 0) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line = reader.readLine();
                        if (line != null && !line.trim().isEmpty()) {
                            String[] values = line.split(",");
                            day = Integer.parseInt(values[0].trim());
                            dollars = Integer.parseInt(values[1].trim());
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading save data");
                    }
                }
                dollars += 3;
                saveGame(day, dollars);
                System.out.println("Your wallet now has $" + dollars);
                
                elapsedTime +=60;
          }
              
          else if(interact.equalsIgnoreCase("f")){
              fancyGuy(input);
          elapsedTime +=60;    
              
          }
              else{
                System.out.println("That was not an option");
            }
    }
         duskTravel(input, elapsedTime); 
      }
      //DUSK TRAVEL METHOD
            public static void duskTravel(Scanner input, int elapsedTime){
                
                while (elapsedTime < 300){
          int hours = 12 + (elapsedTime/60);
              int clockHour = hours % 12==0 ? 12 : hours % 12;
          int minutes = elapsedTime % 60;
          // so that time of 0 reads at 00, not 0
          String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
          
              System.out.println("\nJust enough time for one more place."
                      + "\nwhere would you like to go?"
                      + "\n[e] Joor Muffler"
                      + "\n[f] Regal Escondido                  " + clockHour + ":" + formattedMinutes + "PM"
                              + "\n");
              String interact = input.nextLine(); 
              if(interact.equalsIgnoreCase("e")){
          System.out.println("Walking to Joor Muffler");
          elapsedTime +=60;
          joorMuffler(input, elapsedTime);
              }
          else if(interact.equalsIgnoreCase("f")){
              System.out.println("Walking to Regal Escondido");
          elapsedTime +=60;    
              regalEscondido(input, elapsedTime);
          }
              else{
                System.out.println("That was not an option");
            }
                }
                }
                
      
      
      //DUSK LOCATIONS
      public static void joorMuffler(Scanner input, int elapsedTime){
          System.out.println("\nYou arrive at Joor Muffler!\n");
          while (elapsedTime < 360){
              int hours = 12 + (elapsedTime/60);
              int clockHour = hours % 12==0 ? 12 : hours % 12;
          int minutes = elapsedTime % 60;
          // so that time of 0 reads at 00, not 0
          String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
                    System.out.println("What would you like to do?"
                      + "\n[e] Search the area"
                      + "\n[f] Talk to Mechanic                  " + clockHour + ":" + formattedMinutes + "PM"
                              + "\n");
              String interact = input.nextLine(); 
              if(interact.equalsIgnoreCase("e")){
                System.out.println("You notice the Big Joor Muffler Statue. How did you miss it on your way in?"
                        + "\nYou approach the big Joor Muffler statue and find that it has a locked door.");
                ArrayList<String> inventory = loadInventory();
                if (inventory.contains("Keys")) {
                    System.out.println("You use the Keys to unlock the door and step into the secret Joor Muffler Mechsuit!");
                    // True Ending 1
                    System.out.println("Would you like to insert the Heart of Escondido to power up the suit and take out San Marcos?");
                    System.out.println("[e] Yes, pilot the suit");
                    System.out.println("[f] No, leave the mechsuit alone");
                    String mechChoice = input.nextLine().trim();
                    if (mechChoice.equalsIgnoreCase("e")) {
                        if (inventory.contains("Heart of Escondido")) {
                            mechsuitEnding();
                            elapsedTime += 360;
                        } else {
                            System.out.println("You need the Heart of Escondido to power up the suit's reactor!");
                        }
                    }
                } 
                    else {
                    System.out.println("The door is locked. You need the Keys to open it.");
                }
                elapsedTime += 60;
          
              }
          else if(interact.equalsIgnoreCase("f")){
              mechanic(input);
          elapsedTime +=60;    
              
          }
              else{
                System.out.println("That was not an option");
            }
    }
          
      }
      
      public static void regalEscondido(Scanner input, int elapsedTime){
          playSound("MOVIE.wav");
          System.out.println("\nYou arrive at Regal Escondido!\n");
          while (elapsedTime < 360){
              int hours = 12 + (elapsedTime/60);
              int clockHour = hours % 12==0 ? 12 : hours % 12;
          int minutes = elapsedTime % 60;
          // so that time of 0 reads at 00, not 0
          String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
                    System.out.println("What would you like to do?"
                      + "\n[e] Search the area"
                      + "\n[f] Talk to Concession Worker                  " + clockHour + ":" + formattedMinutes + "PM"
                              + "\n");
              String interact = input.nextLine(); 
              if(interact.equalsIgnoreCase("e")){
          System.out.println("You search the area and find a boarded-up door in the back.");
                ArrayList<String> inventory = loadInventory();
                
                if (inventory.contains("Crowbar")) {
                    System.out.println("\n[e] Use your Crowbar to pry open the door"
                            + "\n[f] Leave the door alone");
                    String ans = input.nextLine().trim();
                    if (ans.equalsIgnoreCase("e")) {
                        System.out.println("You pry open the door with the Crowbar, revealing a hidden laboratory with a time-looping device!");
                        // True Ending 2
                        System.out.println("What would you like to do in the hidden laboratory?");
                        System.out.println("[e] Overload the time device with the Heart of Escondido and the Keycard");
                        System.out.println("[f] Leave the time device alone");
                        String labChoice = input.nextLine().trim();
                        if (labChoice.equalsIgnoreCase("e")) {
                            if (inventory.contains("Keycard") && inventory.contains("Heart of Escondido")) {
                                playSound("BOOM.wav");
                                regalEnding();
                                System.out.println("\nExit the loop?"
                                        + "\ny/n?");
                                String decision = input.nextLine().trim();
                                if (decision.equalsIgnoreCase("y")){
                                System.exit(0);

                                }
                                else{
                                
                                }
                            
                                }
                                
                            
                                  else {
                                System.out.println("You need both the Keycard and the Heart of Escondido to overcharge the device!");
                            }
                        }
                    } else {
                        System.out.println("You decide to leave the door alone for now.");
                    }
                } else {
                    System.out.println("You need a Crowbar to pry this door open.");
                }
                elapsedTime +=60;
          
              }
          else if(interact.equalsIgnoreCase("f")){
              concessionWorker(input);
          elapsedTime +=60;    
              
          }
              else{
                System.out.println("That was not an option\n");
            }
    }
          
      }
      
      
      
      
      
      //CHARCTER INTERACTIONS
      
      public static void kid(Scanner input){
          playSound("LISTEN.wav");
          System.out.println("\nKid: Hey stranger! Can you get me some candy?"
                  + "\n[e] Give the kid candy"
                  + "\n[f] He can go without more sugar");
          ArrayList<String> inventory = loadInventory();
          String interact = input.nextLine();
          if (interact.equalsIgnoreCase("e")){
          if (inventory.contains("Candy")) {
              inventory.remove("Candy");
              if (!inventory.contains("Keycard") && inventory.size() < 3) {
                  inventory.add("Keycard");
              } else if (inventory.size() >= 3) {
                  inventory.add("Keycard");
              }
              saveInventory(inventory);
              System.out.println("You give the candy to the kid."
                      + "\nKid: Thanks sir! Heres a keycard I found at the movies");
          } else {
              System.out.println("The kid looks hungry. Maybe get him some candy");
          }
          
          }
          else if (interact.equals("f")){
              System.out.println("\nKid: HEY! its rude to ignore people you know?"
                      + "\nYou know and you don't care, you ignore him anyways");
          }
          
          else{
                System.out.println("That was not an option\n");
            }
          
      }
      
      public static void teaSeller(Scanner input){
          System.out.println("\nTea Seller: Welcome to The Gand Tea room! The Tea here is the best."
                  + "\nTea Seller: Tea costs $10, would you like some?"
                  + "\n[e] Buy tea"
                  + "\n[f] Pass on the tea");
          String Interact = input.nextLine();
          if (Interact.equalsIgnoreCase("e")){
          File file = new File("Game Files/save.txt");
          int day = 0;
          int dollars = 0;
          if (file.exists() && file.length() > 0) {
              try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                  String line = reader.readLine();
                  if (line != null && !line.trim().isEmpty()) {
                      String[] values = line.split(",");
                      day = Integer.parseInt(values[0].trim());
                      dollars = Integer.parseInt(values[1].trim());
                  }
              } catch (IOException e) {}
          }
          
          if (dollars >= 10) {
              ArrayList<String> inventory = loadInventory();
              if (inventory.size() < 3) {
                  dollars -= 10;
                  saveGame(day, dollars);
                  inventory.add("Tea");
                  saveInventory(inventory);
                  System.out.println("You bought Tea for $10. Your wallet now has $" + dollars);
              } else {
                  System.out.println("Inventory full! You cannot hold more items.");
              }
          } else {
              System.out.println("You don't have enough money for tea. It costs $10.");
          }
          }
          
          else if (Interact.equalsIgnoreCase("f")){
              System.out.println("\nTea Seller: I did't want to sell tea anyways");
          }
          else{
                System.out.println("That was not an option\n");
            }
      }
      
      public static void shadyGuy(Scanner input){
          System.out.println("\nShady Guy: Psst! Hey you, whats someone gotta do to get a lighter 'round here?"
                  + "\nShady Guy: I really need one, so I'd pay like 10 bucks for one right now"
                  + "\n[e] Sell him a lighter"
                  + "\n[f] Stranger Danger");
          String Interact = input.nextLine();
          if (Interact.equalsIgnoreCase("e")){
          ArrayList<String> inventory = loadInventory();
          if (inventory.contains("Lighter")) {
              inventory.remove("Lighter");
              saveInventory(inventory);
              
              File file = new File("Game Files/save.txt");
              int day = 0;
              int dollars = 0;
              if (file.exists() && file.length() > 0) {
                  try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                      String line = reader.readLine();
                      if (line != null && !line.trim().isEmpty()) {
                          String[] values = line.split(",");
                          day = Integer.parseInt(values[0].trim());
                          dollars = Integer.parseInt(values[1].trim());
                      }
                  } catch (IOException e) {}
              }
              
              dollars += 10;
             playSound("MONEY.wav");
              saveGame(day, dollars);
              System.out.println("You give the lighter to the shady guy. He hands you $10. Wallet now has: $" + dollars);
          } 
          else {
              System.out.println("You don't have the lighter to sell to him");
          }
          }
          else if (Interact.equalsIgnoreCase("f")){
          System.out.println("\nShady Guy: You're missing out on a reeeally good deal!");
      }
          else{
                System.out.println("That was not an option\n");
            }
      }
      
      public static void fancyGuy(Scanner input){
          System.out.println("\nFancy Guy: Oh hello there! I must say I am parched, you wouldn't happen to have any tea on you would you?"
                  + "\n[e] Give the Fancy Guy tea"
                  + "\n[f] Ignore the old man");
         String Interact = input.nextLine();
         if (Interact.equalsIgnoreCase("e")){
          ArrayList<String> inventory = loadInventory();

          
          if (inventory.contains("Tea")) {
              inventory.remove("Tea");
              if (!inventory.contains("Heart of Escondido") && inventory.size() < 3) {
                  inventory.add("Heart of Escondido");
                  playSound("*DISCOVER.way");
              } else if (inventory.size() >= 3) {
                  inventory.add("Heart of Escondido");
              playSound("DISCOVER");
              }
              saveInventory(inventory);
              System.out.println("You give the tea to the fancy guy. He hands you a stone called the Heart of Escondido.");
          } else {
              System.out.println("The fancy guy looks like he wants some tea.");
          }
         }
         else if (Interact.equalsIgnoreCase("f")){
          System.out.println("\nFancy Guy: You seemed to wild to have tea anyways");
      }
         else{
                System.out.println("That was not an option\n");
            }
      }
      
      public static void mechanic(Scanner input){
         System.out.println("\nMechanic: Hey. You do know we are closing soon right?"
                 + "\nMechanic: You know what, I lost my keys, so if you can find them I could give you something pretty cool in return"
                 + "\n[e] Give him the Keys"
                 + "\n[f] His keys, his problem");
         String Interact = input.nextLine();
         if (Interact.equalsIgnoreCase("e")){
             ArrayList<String> inventory = loadInventory();
          if (inventory.contains("Keys")) {
              inventory.remove("Keys");
              if (!inventory.contains("Crowbar") && inventory.size() < 3) {
                  inventory.add("Crowbar");
              } else if (inventory.size() >= 3) {
                  inventory.add("Crowbar");
              }
              saveInventory(inventory);
              System.out.println("You give the mechanic his missing keys. He hands you a crowbar.");
          } else {
              System.out.println("The mechanic mublems to himself about 7-11 earlier that day");
          }
         }
        else if (Interact.equalsIgnoreCase("f")){
             System.out.println("Mechanic: Just let me know if you find those keys, I really need them");
         }
         else{
                System.out.println("That was not an option\n");
            }
      }
      
      public static void concessionWorker(Scanner input){
          System.out.println("\nConcession Worker: welcome to regal escnodido, can I intrest you in buying some $5 candy?"
                  + "\n[e] Buy the candy"
                  + "\n[f] Save your money");
          String Interact = input.nextLine();
          if (Interact.equalsIgnoreCase("e")){
              File file = new File("Game Files/save.txt");
          int day = 0;
          int dollars = 0;
          if (file.exists() && file.length() > 0) {
              try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                  String line = reader.readLine();
                  if (line != null && !line.trim().isEmpty()) {
                      String[] values = line.split(",");
                      day = Integer.parseInt(values[0].trim());
                      dollars = Integer.parseInt(values[1].trim());
                  }
              } catch (IOException e) {}
          }
          
          if (dollars >= 5) {
              ArrayList<String> inventory = loadInventory();
              if (inventory.size() < 3) {
                  dollars -= 5;
                  saveGame(day, dollars);
                  inventory.add("Candy");
                  saveInventory(inventory);
                  System.out.println("You bought Candy for $5. Your wallet now has $" + dollars);
              } else {
                  System.out.println("Inventory full! You cannot hold more items");
              }
          } else {
              System.out.println("You don't have enough money for candy. It costs $5");
          }
          }
          else if (Interact.equalsIgnoreCase("f")){
              System.out.println("\nConcession Worker: I hate my job...");
          }
          else{
                System.out.println("That was not an option\n");
            }
      }
      
      
      
      
      
      //INVENTORY
      // Load inventory from file
    public static ArrayList<String> loadInventory() {
        ArrayList<String> inventory = new ArrayList<>();
        File file = new File("Game Files/inventory.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        inventory.add(line.trim());
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading inventory");
            }
        }
        return inventory;
    }

    // Save inventory to file
    public static void saveInventory(ArrayList<String> inventory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Game Files/inventory.txt"))) {
            for (String item : inventory) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory");
        }
    }

    // Load box contents from file
    public static ArrayList<String> loadBox() {
        ArrayList<String> box = new ArrayList<>();
        File file = new File("Game Files/box.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        box.add(line.trim());
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading box data");
            }
        }
        return box;
    }

    // Save box items to file
    public static void saveBox(ArrayList<String> box) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Game Files/box.txt"))) {
            for (String item : box) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving box data");
        }
    }
    
    //BOX
    public static void boxMenu(Scanner input) {
        ArrayList<String> inventory = loadInventory();
        ArrayList<String> box = loadBox();

        boolean inBoxMenu = true;
        while (inBoxMenu) {
            System.out.println("\nCardboard Box");
            System.out.println("Your Inventory (" + inventory.size() + "/3): " + inventory);
            System.out.println("Items in Box: " + box);
            
            System.out.println("\nWhat would you like to do?"
                    + "\n[e] Take an item from the box"
                    + "\n[f] Drop an item into the box"
                    + "\n[r] Return to Transit Center");

            String choice = input.nextLine();

            if (choice.equals("e")) {
                if (inventory.size() >= 3) {
                    System.out.println("Inventory full! You can only hold 3 items.");
                } else if (box.isEmpty()) {
                    System.out.println("The box is empty.");
                } else {
                    System.out.println("Enter the name of the item to take:");
                    String item = input.nextLine().trim();
                    if (box.contains(item)) {
                        box.remove(item);
                        inventory.add(item);
                        saveInventory(inventory);
                        saveBox(box);
                        System.out.println("Moved " + item + " to inventory");
                    } else {
                        System.out.println("That item isn't in the box");
                    }
                }
            } 
            else if (choice.equals("f")) {
                if (inventory.isEmpty()) {
                    System.out.println("Your inventory is empty.");
                } else {
                    System.out.println("Enter the name of the item to drop into the box:");
                    String item = input.nextLine().trim();
                    if (inventory.contains(item)) {
                        inventory.remove(item);
                        box.add(item);
                        saveInventory(inventory);
                        saveBox(box);
                        System.out.println("Put " + item + " into the box");
                    } else {
                        System.out.println("That item isn't in your inventory");
                    }
                }
            } 
            else if (choice.equals("r")) {
                inBoxMenu = false;
            } 
            else {
                System.out.println("That was not an option\n");
            }
        }
    }
    
      public static void saveGame(int day, int money) {
          try (BufferedWriter writer = new BufferedWriter(new FileWriter("Game Files/save.txt"))){
         writer.write(day + "," + money);
         System.out.println("\n[Game Saved]");
          }
         catch (IOException e ){
             System.out.println("Game save failed");
         }
      }
      
public static void playSound(String soundFile) {
    try {
        // Stop any track that is currently playing first
        stopSound();

        File file = new File("Game Files/Sounds/" + soundFile);
        if (file.exists()) {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            
            // Assign the clip directly to the global class variable
            currentClip = AudioSystem.getClip(); 
            currentClip.open(audioStream);
            currentClip.start();
        } else {
            System.out.println("[Sound Error] File not found: " + file.getPath());
        }
    } catch (Exception e) {
        System.out.println("[Sound Error] Could not play sound: " + e.getMessage());
    }
}

public static void stopSound() {
    try {
        // Force stop, flush the data line, and completely close the resource
        if (currentClip != null) {
            currentClip.stop();
            currentClip.flush();
            currentClip.close();
            currentClip = null; // Reset the pointer
        }
    } catch (Exception e) {
        System.out.println("[Sound Error] Error stopping clip: " + e.getMessage());
    }
}
      
      
      // TRUE ENDINGS
    public static void mechsuitEnding() {
        stopSound();
        playSound("MECH.wav");
        System.out.println("\nTemporary Win: The March on San Marcos");
        System.out.println("Using the Keys to unlock the Joor Muffler mechsuit...");
        System.out.println("Inserting the Heart of Escondido into the reactor core...");
        System.out.println("The suit roars to life with a deep, mechanical rumble.");
        System.out.println("You pilot the massive mech through the streets of Escondido, breaking through barricades.");
        System.out.println("You cross the boundary into San Marcos, and lay ruin upon the entire land.");
        System.out.println("Escondido establishes its dominance.");
        System.out.println("You have been given the title Mr. Escondido");
        System.out.println("You have won the day but you have not broken free of the Transit Center Time loop");
    }

    public static void regalEnding() {
        stopSound();
        System.out.println("\nTrue Ending: Transit Center Time Loop ");
        System.out.println("Prying the boarded doors open with the Crowbar...");
        System.out.println("Using the Keycard to gain admin access to the devices in the lab.");
        System.out.println("You turn off the saftey messures and all firewalls.");
        System.out.println("As the clock ticks closer to 6:00PM you overload the machine with the Heart of Escondido.");
        System.out.println("The device destabilizes, triggering a critical system malfunction.");
        System.out.println("You are overwhelmed with relief, finally you are free. "
                + "\nYou have won the game!");
                playSound("TRUE.wav");
        
    }
}