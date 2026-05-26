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
 * @author MLabayen2026
 */
public class Escondido {

    private static Clip currentClip = null;

    public static void main(String[] args) {
        // 1. Creates the folder structure first
        GameFiles();
        
        // 2. Automatically load the story file right at startup
        DialogueManager.loadStory("Game Files/story.txt");
        
        Scanner input = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n" + DialogueManager.get("main_menu") + "\n");
            String MenuButton = input.nextLine();
            
            if (MenuButton.equalsIgnoreCase("F")){
                startGame(input);
            }
            else if (MenuButton.equalsIgnoreCase("R")){
                System.out.println("\n" + DialogueManager.get("rules"));
            }
            else if (MenuButton.equalsIgnoreCase("E")){
                running = false;
            }
            else {
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
    }
    
    public static void GameFiles(){
        File GameFiles = new File("Game Files");
        if (!GameFiles.exists()){
            GameFiles.mkdir(); 
        }
        String[] fileNames = {"inventory.txt", "box.txt", "save.txt"};
        for (String name: fileNames) {
            File file = new File(GameFiles, name); 
            try{
                if (file.createNewFile()) {}
            } catch (IOException e){}
        }
    }


    
    
     public static void startGame(Scanner input){
        System.out.println("\n" + DialogueManager.get("start_game"));
        
        int daysPlayed = 0;
        int dollars = 0;
        
        File file = new File("Game Files/save.txt");
        if (file.exists() && file.length() > 0 ) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    String[] values = line.split(",");
                    if (values.length >= 2){
                        daysPlayed = Integer.parseInt(values[0].trim());
                        dollars = Integer.parseInt(values[1].trim());
                    }
                }
                System.out.println("\n[Save Loaded] Day " + daysPlayed + "| Dollars: $" + dollars);
            } catch (IOException | NumberFormatException e){
                System.out.println("No save data. Creating new save");
            }      
        } else {
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
                } catch (IOException | NumberFormatException e) {}
            }
            stopSound();
            playSound("LOBBY.wav");
            
            System.out.println("\n" + DialogueManager.get("transit_center_wake"));
            System.out.println("Day: " + daysPlayed + "  | Dollars: $" + dollars + "\n");
            
            String Input = input.nextLine(); 
            
            if (Input.equalsIgnoreCase("e")){
                boxMenu(input);
            }
            else if (Input.equalsIgnoreCase("f")){
                stopSound();
                InGameDay(input);
                daysPlayed += 1;
                System.out.println("\n" + DialogueManager.get("sleep_reset"));
                
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
                saveGame(daysPlayed, dollars);
            }
            else if (Input.equalsIgnoreCase("r")){
                playing = false;
            }
            else{
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
    }
      
    public static void InGameDay (Scanner input){
        int elapsedTime = 0;
        while (elapsedTime < 60){
            int hours = 12 + (elapsedTime/60);
            int clockHour = hours % 12==0 ? 12 : hours % 12;
            int minutes = elapsedTime % 60;
            String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
          
            System.out.println("\n" + DialogueManager.get("morning_travel") + "                  " + clockHour + ":" + formattedMinutes + "PM\n");
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
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
    }
      
    public static void gazebo(Scanner input, int elapsedTime){
        System.out.println("\n" + DialogueManager.get("gazebo_arrival") + "\n");
        while (elapsedTime < 120){
            int hours = 12 + (elapsedTime/60);
            int clockHour = hours % 12==0 ? 12 : hours % 12;
            int minutes = elapsedTime % 60;
            String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
            
            System.out.println(DialogueManager.get("gazebo_choices") + "                  " + clockHour + ":" + formattedMinutes + "PM\n");
            String interact = input.nextLine(); 
            if(interact.equalsIgnoreCase("e")){
                playSound("LIGHTER.wav");      
                ArrayList<String> inventory = loadInventory();
                if (!inventory.contains("Lighter") && inventory.size() < 3) {
                    inventory.add("Lighter");
                    saveInventory(inventory);
                    System.out.println(DialogueManager.get("gazebo_search_success"));
                } else if (inventory.size() >= 3) {
                    System.out.println(DialogueManager.get("gazebo_search_full"));
                } else {
                    System.out.println(DialogueManager.get("gazebo_search_duplicate"));
                }
                elapsedTime +=60;
            }
            else if(interact.equalsIgnoreCase("f")){
                kid(input);
                elapsedTime +=60;    
            }
            else{
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
        afternoonTravel(input, elapsedTime);     
    }
      
    public static void teaRoom(Scanner input, int elapsedTime){
        System.out.println("\n" + DialogueManager.get("tea_room_arrival") + "\n");
        while (elapsedTime < 120){
            int hours = 12 + (elapsedTime/60);
            int clockHour = hours % 12==0 ? 12 : hours % 12;
            int minutes = elapsedTime % 60;
            String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
            
            System.out.println(DialogueManager.get("tea_room_choices") + "                  " + clockHour + ":" + formattedMinutes + "PM\n");
            String interact = input.nextLine(); 
            if(interact.equalsIgnoreCase("e")){
                playSound("DISCOVER.wav");
                System.out.println(DialogueManager.get("tea_room_search"));
                elapsedTime +=60;
            }
            else if(interact.equalsIgnoreCase("f")){
                teaSeller(input);
                elapsedTime +=60;
            }
            else{
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
        afternoonTravel(input, elapsedTime);   
    }
      
    public static void afternoonTravel(Scanner input, int elapsedTime){
        while(elapsedTime < 180){
            int hours = 12 + (elapsedTime/60);
            int clockHour = hours % 12==0 ? 12 : hours % 12;
            int minutes = elapsedTime % 60;
            String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
          
            System.out.println("\n" + DialogueManager.get("afternoon_travel") + "                  " + clockHour + ":" + formattedMinutes + "PM\n");
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
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
    }
      
    public static void sevenEleven(Scanner input, int elapsedTime){
        playSound("711.wav");
        System.out.println("\n" + DialogueManager.get("seven_eleven_arrival") + "\n");
        while (elapsedTime < 240){
            int hours = 12 + (elapsedTime/60);
            int clockHour = hours % 12==0 ? 12 : hours % 12;
            int minutes = elapsedTime % 60;
            String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
            
            System.out.println(DialogueManager.get("seven_eleven_choices") + "                  " + clockHour + ":" + formattedMinutes + "PM\n");
            String interact = input.nextLine(); 
            if(interact.equalsIgnoreCase("e")){
                playSound("KEYS.wav");      
                ArrayList<String> inventory = loadInventory();
                if (!inventory.contains("Keys") && inventory.size() < 3) {
                    inventory.add("Keys");
                    saveInventory(inventory);
                    System.out.println(DialogueManager.get("seven_eleven_search_success"));
                     playSound("DISCOVER.wav");
                } else if (inventory.size() >= 3) {
                    System.out.println(DialogueManager.get("seven_eleven_search_full"));
                } else {
                    System.out.println(DialogueManager.get("seven_eleven_search_duplicate"));
                }
                elapsedTime +=60;
            }
            else if(interact.equalsIgnoreCase("f")){
                shadyGuy(input);
                elapsedTime +=60;    
            }
            else{
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
        duskTravel(input, elapsedTime);       
    }
      
    public static void burgerBench(Scanner input, int elapsedTime){
        playSound("BURGER.wav");
        System.out.println("\n" + DialogueManager.get("burger_bench_arrival") + "\n");
        while (elapsedTime < 240){
            int hours = 12 + (elapsedTime/60);
            int clockHour = hours % 12==0 ? 12 : hours % 12;
            int minutes = elapsedTime % 60;
            String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
            
            System.out.println(DialogueManager.get("burger_bench_choices") + "                  " + clockHour + ":" + formattedMinutes + "PM\n");
            String interact = input.nextLine(); 
              
            if(interact.equalsIgnoreCase("e")){
                playSound("MONEY.wav");  
                System.out.println(DialogueManager.get("burger_bench_search"));
                
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
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
        duskTravel(input, elapsedTime); 
    }

    public static void duskTravel(Scanner input, int elapsedTime){
        while (elapsedTime < 300){
            int hours = 12 + (elapsedTime/60);
            int clockHour = hours % 12==0 ? 12 : hours % 12;
            int minutes = elapsedTime % 60;
            String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
          
            System.out.println("\n" + DialogueManager.get("dusk_travel") + "                  " + clockHour + ":" + formattedMinutes + "PM\n");
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
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
    }           
      
      
    public static void joorMuffler(Scanner input, int elapsedTime){
        System.out.println("\n" + DialogueManager.get("joor_muffler_arrival") + "\n");
        while (elapsedTime < 360){
            int hours = 12 + (elapsedTime/60);
            int clockHour = hours % 12==0 ? 12 : hours % 12;
            int minutes = elapsedTime % 60;
            String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
            
            System.out.println(DialogueManager.get("joor_muffler_choices") + "                  " + clockHour + ":" + formattedMinutes + "PM\n");
            String interact = input.nextLine(); 
            if(interact.equalsIgnoreCase("e")){
                ArrayList<String> inventory = loadInventory();
                if (inventory.contains("Keys")) {
                    System.out.println(DialogueManager.get("joor_muffler_search_unlocked"));
                    String mechChoice = input.nextLine().trim();
                    if (mechChoice.equalsIgnoreCase("e")) {
                        if (inventory.contains("Heart of Escondido")) {
                            mechsuitEnding();
                            System.out.println("\n" + DialogueManager.get("exit_loop_prompt"));
                            String decision = input.nextLine().trim();
                                if (decision.equalsIgnoreCase("y")){
                                    System.out.println("\nYou haven't broken the loop"
                                            + "\nYOU CANNOT LEAVE");
                                }
                            elapsedTime += 360;
                        } else {
                            System.out.println(DialogueManager.get("joor_muffler_no_heart"));
                        }
                    }
                } else {
                    System.out.println(DialogueManager.get("joor_muffler_search_locked"));
                }
                elapsedTime += 60;
            }
            else if(interact.equalsIgnoreCase("f")){
                mechanic(input);
                elapsedTime +=60;    
            }
            else{
                System.out.println(DialogueManager.get("menu_invalid"));
            }
        }
    }
      
    public static void regalEscondido(Scanner input, int elapsedTime){
        playSound("MOVIE.wav");
        System.out.println("\n" + DialogueManager.get("regal_escondido_arrival") + "\n");
        while (elapsedTime < 360){
            int hours = 12 + (elapsedTime/60);
            int clockHour = hours % 12==0 ? 12 : hours % 12;
            int minutes = elapsedTime % 60;
            String formattedMinutes = (minutes<10) ? "0" + minutes : String.valueOf(minutes);
            
            System.out.println(DialogueManager.get("regal_escondido_choices") + "                  " + clockHour + ":" + formattedMinutes + "PM\n");
            String interact = input.nextLine(); 
            if(interact.equalsIgnoreCase("e")){
                ArrayList<String> inventory = loadInventory();
                
                if (inventory.contains("Crowbar")) {
                    System.out.println("\n" + DialogueManager.get("regal_escondido_search_crowbar"));
                    String ans = input.nextLine().trim();
                    if (ans.equalsIgnoreCase("e")) {
                        System.out.println(DialogueManager.get("laboratory_choices"));
                        String labChoice = input.nextLine().trim();
                        if (labChoice.equalsIgnoreCase("e")) {
                            if (inventory.contains("Keycard") && inventory.contains("Heart of Escondido")) {
                                playSound("SWIPE.wav");
                                regalEnding();
                                System.out.println("\n" + DialogueManager.get("exit_loop_prompt"));
                                String decision = input.nextLine().trim();
                                if (decision.equalsIgnoreCase("y")){
                                    System.exit(0);
                                }
                            } else {
                                System.out.println(DialogueManager.get("laboratory_no_items"));
                            }
                        }
                    } else {
                        System.out.println(DialogueManager.get("laboratory_leave"));
                    }
                } else {
                    System.out.println(DialogueManager.get("regal_escondido_search_no_crowbar"));
                }
                elapsedTime +=60;
            }
            else if(interact.equalsIgnoreCase("f")){
                concessionWorker(input);
                elapsedTime +=60;    
            }
            else{
                System.out.println(DialogueManager.get("menu_invalid") + "\n");
            }
        }
    }
      
    public static void kid(Scanner input){
        playSound("LISTEN.wav");
        System.out.println("\n" + DialogueManager.get("kid"));
        ArrayList<String> inventory = loadInventory();
        String interact = input.nextLine();
        if (interact.equalsIgnoreCase("e")){
            if (inventory.contains("Candy")) {
                inventory.remove("Candy");
                inventory.add("Keycard");
                saveInventory(inventory);
                System.out.println(DialogueManager.get("kid_success"));
            } else {
                System.out.println(DialogueManager.get("kid_no_candy"));
            }
        }
        else if (interact.equalsIgnoreCase("f")){
            System.out.println("\n" + DialogueManager.get("kid_decline"));
        }
        else{
            System.out.println(DialogueManager.get("menu_invalid") + "\n");
        }
    }
      
    public static void teaSeller(Scanner input){
        System.out.println("\n" + DialogueManager.get("teaSeller"));
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
                    System.out.println(DialogueManager.get("teaSeller_success") + " Your wallet now has $" + dollars);
                } else {
                    System.out.println("Inventory full! You cannot hold more items.");
                }
            } else {
                System.out.println(DialogueManager.get("teaSeller_no_money"));
            }
        }
        else if (Interact.equalsIgnoreCase("f")){
            System.out.println("\n" + DialogueManager.get("teaSeller_decline"));
        }
        else{
            System.out.println(DialogueManager.get("menu_invalid") + "\n");
        }
    }
      
    public static void shadyGuy(Scanner input){
        System.out.println("\n" + DialogueManager.get("shadyGuy"));
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
                System.out.println(DialogueManager.get("shadyGuy_success") + " Wallet now has: $" + dollars);
            } else {
                System.out.println(DialogueManager.get("shadyGuy_no_lighter"));
            }
        }
        else if (Interact.equalsIgnoreCase("f")){
            System.out.println("\n" + DialogueManager.get("shadyGuy_decline"));
        }
        else{
            System.out.println(DialogueManager.get("menu_invalid") + "\n");
        }
    }
      
    public static void fancyGuy(Scanner input){
        System.out.println("\n" + DialogueManager.get("fancyGuy"));
        String Interact = input.nextLine();
        if (Interact.equalsIgnoreCase("e")){
            ArrayList<String> inventory = loadInventory();
            if (inventory.contains("Tea")) {
                inventory.remove("Tea");
                inventory.add("Heart of Escondido");
                saveInventory(inventory);
                System.out.println(DialogueManager.get("fancyGuy_success"));
                 playSound("DISCOVER.wav");
            } else {
                System.out.println(DialogueManager.get("fancyGuy_no_tea"));
            }
        }
        else if (Interact.equalsIgnoreCase("f")){
            System.out.println("\n" + DialogueManager.get("fancyGuy_decline"));
        }
        else{
            System.out.println(DialogueManager.get("menu_invalid"));
        }
    }

    public static void mechanic(Scanner input) {
        System.out.println("\n" + DialogueManager.get("mechanic"));
        String interact = input.nextLine();
        if (interact.equalsIgnoreCase("e")) {
            ArrayList<String> inventory = loadInventory();
            if (inventory.contains("Keys")) {
                inventory.remove("Keys");
                inventory.add("Crowbar");
                saveInventory(inventory);
                System.out.println(DialogueManager.get("mechanic_success"));
            } else {
                System.out.println(DialogueManager.get("mechanic_fail"));
            }
        } else if (interact.equalsIgnoreCase("f")) {
            System.out.println("\n" + DialogueManager.get("mechanic_decline"));
        } else {
            System.out.println(DialogueManager.get("menu_invalid"));
        }
    }

    public static void concessionWorker(Scanner input) {
        System.out.println("\n" + DialogueManager.get("concessionWorker"));
        String interact = input.nextLine();
        if (interact.equalsIgnoreCase("e")) {
            File file = new File("Game Files/save.txt");
            int day = 0; int dollars = 0;
            if (file.exists() && file.length() > 0) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line = reader.readLine();
                    if (line != null) {
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
                    System.out.println("You bought candy! Wallet now has: $" + dollars);
                } else {
                    System.out.println("Inventory full!");
                }
            } else {
                System.out.println("Not enough money!");
            }
        } else if (interact.equalsIgnoreCase("f")) {
            System.out.println("\n" + DialogueManager.get("concession_decline"));
        } else {
            System.out.println(DialogueManager.get("menu_invalid"));
        }
    }

    public static void mechsuitEnding() {
        playSound("MECH.wav");
        System.out.println("\n" + DialogueManager.get("mechsuitEnding"));
    }

    public static void regalEnding() {
        playSound("BOOM.wav");
        playSound("TRUE.wav");
        System.out.println("\n" + DialogueManager.get("regalEnding"));
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
      
public static class DialogueManager {

        private static HashMap<String, String> dialogueMap = new HashMap<>();



        public static void loadStory(String filePath) {

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                String line;

                String currentKey = null;

                StringBuilder currentText = new StringBuilder();



                while ((line = br.readLine()) != null) {

                    line = line.trim();



                    // Detects tags like [transitCenter]

                    if (line.startsWith("[") && line.endsWith("]")) {

                        if (currentKey != null) {

                            dialogueMap.put(currentKey, currentText.toString().trim());

                        }

                        currentKey = line.substring(1, line.length() - 1);

                        currentText = new StringBuilder();

                    } else {

                        if (currentKey != null) {

                            currentText.append(line).append("\n");

                        }

                    }

                }

                if (currentKey != null) {

                    dialogueMap.put(currentKey, currentText.toString().trim());

                }

            } catch (Exception e) {

                System.out.println("[Error] Could not read Game Files/story.txt: " + e.getMessage());

            }

        }



        public static String get(String key) {

            return dialogueMap.getOrDefault(key, "[Error: Text for '" + key + "' missing]");

        }

          
}

     
}