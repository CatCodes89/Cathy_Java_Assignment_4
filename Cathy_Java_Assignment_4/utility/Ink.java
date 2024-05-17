package utility;

import objects.Shelter;
import java.util.Scanner;
import java.util.ArrayList;

import objects.Pet;

public class Ink {
    private Scanner input = new Scanner(System.in);
    private int choice;

    public Scanner getInputScanner() {
        return input;
    }

    // Lets add color!
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";

    // Print methods with color codes
    public void print(String text, String color) {
        System.out.println(color + text + ANSI_RESET);
    }

    // Methods for different colors
    public void printRed(String text) {
        print(text, ANSI_RED);
    }

    public void printGreen(String text) {
        print(text, ANSI_GREEN);
    }

    public void printBlue(String text) {
        print(text, ANSI_BLUE);
    }

    public void printYellow(String text) {
        print(text, ANSI_YELLOW);
    }
    
    public void printWelcome() {
        System.out.println(ANSI_RED + "    _       _             _                               _     ,_     _        " + ANSI_RESET);
        System.out.println(ANSI_RED + "   / \\   __| | ___  _ __ | |_       __ _       _ __   ___| |_   |\\_,-~/       " + ANSI_RESET);
        System.out.println(ANSI_RED + "  / _ \\ / _` |/ _ \\| '_ \\| __|____ / _` |_____| '_ \\ / _ \\ __|  / _  _ |    ,--.  " + ANSI_RESET);
        System.out.println(ANSI_RED + " / ___ \\ (_| | (_) | |_) | ||_____| (_| |_____| |_) |  __/ |_  (  @  @ )   / ,-'" + ANSI_RESET);
        System.out.println(ANSI_RED + "/_/   \\_\\__,_|\\___/| .__/ \\__|     \\__,_|     | .__/ \\___|\\__|  \\  _T_/-._( (    " + ANSI_RESET);
        System.out.println(ANSI_RED + "                   |_|                        |_|               /         `. \\  " + ANSI_RESET);
        System.out.println(ANSI_RED + "                                                                |         _  \\ |  " + ANSI_RESET);
        System.out.println(ANSI_RED + "                                                                \\ \\ ,  /      | " + ANSI_RESET);
        System.out.println(ANSI_RED + "                                                                 || |-_\\__   /  " + ANSI_RESET);
        System.out.println(ANSI_RED + "                                                                 ((_/`(____,-'   " + ANSI_RESET);
    }             

    public void printGoodday() {
        System.out.println(ANSI_ORANGE + "Have a great day, pet lover!" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "    |\\_/|                  ");
            System.out.println("     | @ @   Woof!          ");
            System.out.println("     |   <>              _  ");
            System.out.println("     |  _/\\------____ ((| |))");
            System.out.println("     |               `--' |   ");
            System.out.println(" ____|_       ___|   |___.'   ");
            System.out.println("/_/_____/____/_______|        ");
        }
        

    public int validateMainMenu() {
        boolean valid = false;

        while(!valid) {
            System.out.println(ANSI_BLUE + "##### MAIN MENU #####" + ANSI_RESET);
            System.out.println(ANSI_BLUE + 
            "(1) View Pets\n(2) Shelter Details\n(3) Book Appointment\n(4) Display Pet Statistics\n(5) Exit" 
            + ANSI_RESET);
            try {
                choice = input.nextInt();
                if(choice >= 1 && choice <=5) {
                    valid = true; // escapes loop only if choice is correct dt and range
                }
                else {
                    System.out.println(ANSI_RED + "Please enter a number between 1 and 5." + ANSI_RESET);
                }
            } 
            catch (Exception e) { // runs on exception
                System.out.println(ANSI_RED + "That's not a number!" + ANSI_RESET);
            } 
            finally { // always runs!
                input.nextLine();
            }
        } // while
        return choice;
    } // printMenu()

    public void printPetDetails(Pet pet) {
        System.out.printf(ANSI_GREEN + "Name: %s\n" + ANSI_RESET, pet.getName());
        System.out.printf(ANSI_GREEN + "Age: %d\n" + ANSI_RESET, pet.getAge());
        System.out.printf(ANSI_GREEN + "Breed: %s\n" + ANSI_RESET, pet.getBreed());
        System.out.printf(ANSI_GREEN + "Color: %s\n" + ANSI_RESET, pet.getColor());
        System.out.printf(ANSI_GREEN + "Owner: %s\n" + ANSI_RESET, pet.getOwner());
        System.out.printf(ANSI_GREEN + "Is Adopted: %b\n" + ANSI_RESET, pet.getIsAdopted());
        System.out.println(Ink.ANSI_BLUE + "Adopt this pet? Y/N" + Ink.ANSI_RESET);
    } // printPetDetails()

    public void printAvailablePets(ArrayList<Pet> pets) {
        for(int i = 0; i < pets.size(); i++) {
            if(!pets.get(i).getIsAdopted()) {
                System.out.printf(ANSI_GREEN + "(%d) Name: %s Type: %s Age: %d\n" + ANSI_RESET, i + 1,
                        pets.get(i).getName(), pets.get(i).getType(), pets.get(i).getAge());
            }
        } // for
    } // printAvailablePets()

    public void printShelterDetails(Shelter shelter) {
        System.out.printf(ANSI_GREEN + "Shelter Address: %s\n" + ANSI_RESET, shelter.getAddress());
        
        String[] hours = shelter.getHours();
        for(int i = 0; i < hours.length; i++) {
            System.out.println(ANSI_BLUE + hours[i] + ANSI_RESET);
        } //for
        
        System.out.println(ANSI_GREEN + "Enter 0 to go back" + ANSI_RESET);
    } // printShelterDetails()    

} // class