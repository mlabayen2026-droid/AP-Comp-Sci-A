/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package readwriteantfiles;
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
public class ReadWriteAntFiles {
public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    System.out.println("Hello! What is the name of the file we are working with?");
    String file = scanner.nextLine();
    String filePath = "Contacts/"+ file + ".txt";
    
    
String outPath = "Contacts/" + file + ".txt";


    class Contact{
        private String name, email, address, username;
        private int gradYear;
        
        public Contact(String name, String email, String address, int gradYear, String username) {
            
            this.name = name;
            this.email = email;
            this.address = address;
            this.gradYear = gradYear;
            this.username = username;
        }
        @Override
        public String toString(){
            return name + ", " + email + ", " + address + ", " + gradYear + ", " + username;
        }
    }
        ArrayList<Contact> contactList = new ArrayList<>();

        try {
    // Use the variable filePath so it matches what the user typed
    File myFile = new File(filePath); 

    // CREATES the file if it's missing
    if (!myFile.exists()) {
        // Create the "Contacts" folder if it doesn't exist
        if (myFile.getParentFile() != null) {
            myFile.getParentFile().mkdirs();
        }
        myFile.createNewFile(); 
        System.out.println("Created new file: " + filePath);
    }

    // The Scanner will always find a file (even if it's empty)
    Scanner fileReader = new Scanner(myFile);
    while (fileReader.hasNextLine()) {
        String line = fileReader.nextLine();
        String[] data = line.split(",");
        if (data.length >= 5) {
            Contact c = new Contact(
                data[0].trim(), data[1].trim(), data[2].trim(),
                Integer.parseInt(data[3].trim()), data[4].trim()
            );
            contactList.add(c);
        }
    }
    fileReader.close();

} catch (IOException e) {
    // This catches errors from createNewFile() and the Scanner
    System.out.println("FileSystem Error: " + e.getMessage());
}
    
    boolean running = true;
    
    while (running) {
    
    System.out.println("What would you like to do?"
            + "\nType \"a\" to add contact details"
            + "\nType \"e\" to order by email address"
            + "\nType \"y\" order by grad year"
            + "\nType \"n\" for order by name");
    String command = scanner.nextLine();

    if (command.equals("a")) {
    
    System.out.println("What is your name?");
    String name = scanner.nextLine();
    System.out.println("What is your email?");
    String email = scanner.nextLine();
    System.out.println("What is your address?");
    String address = scanner.nextLine();
    System.out.println("What is your graduation year?");
    String year = scanner.nextLine();
    System.out.println("What is your username?");
    String user = scanner.nextLine();
    
    Contact newContact = new Contact(name, email, address, Integer.parseInt(year), user);
    contactList.add(newContact);
    
LocalDateTime now = LocalDateTime.now();
DateTimeFormatter shortFormatter = DateTimeFormatter
.ofLocalizedDateTime(FormatStyle.SHORT)
.withLocale(Locale.US); // Explicitly setting to US locale for consistent example
// Format the LocalDateTime to a string
String formattedDateTime = now.format(shortFormatter);

String contentToWrite = name
        + ", " + email
        + ", " + address
        + ", " + year
        + ", " + user
        + "\n";
// Write to file
try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,
true))) {
//writer.write(contentToWrite);
writer.append(contentToWrite);
System.out.println("Successfully wrote to the file." +
formattedDateTime);
} catch (IOException e) {
System.err.println("An error occurred while writing to the file: " +
e.getMessage());
}
// Read from file
try (BufferedReader reader = new BufferedReader(new
FileReader(outPath))) {
String line;
System.out.println("\nReading from the file:");
while ((line = reader.readLine()) != null) {
System.out.println(line);
}
} catch (IOException e) {
System.err.println("An error occurred while reading from the file: "
+ e.getMessage());
}


}
    else if (command.equals("e")){ 
    contactList.sort(Comparator.comparing(c -> c.email));
}
else if (command.equals("y")){ 
    contactList.sort(Comparator.comparingInt(c -> c.gradYear));
}
else if (command.equals("n")){ 
    contactList.sort(Comparator.comparing(c -> c.name));
}
else { 
    System.out.println("Invalid action");
}
  
    System.out.println("\nContacts: ");
    for (Contact c : contactList) {
        System.out.println(c);
    }
    
    
    
    
    
    if (running) {
        System.out.println("\nContinue?");
        System.out.println("y/n");
        String answer = scanner.nextLine();
        if (answer.equals ("n")){
            running = false;
            System.out.println("Bye!");
        }
    }
    
    
}
}

}