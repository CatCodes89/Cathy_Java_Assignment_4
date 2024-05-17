import utility.*;
import objects.User;
import objects.Pet;
import objects.Shelter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Adopt_a_pet {
    private static Scanner input = new Scanner(System.in);
    private static Ink ink = new Ink();
    private static User user;
    private static Shelter shelter = new Shelter();
    private static Pet pet;
    private static Form form = new Form(); // Object created

    private static int choice;
    private static long longPhone;
    private static boolean isDone = false;
    private static boolean goBack = false;

    public static void main(String[] args) {

        ink.printWelcome(); // Print welcome message
        createUser(input); // create the user
        createPets(); // helper pets
        form.fillForm(input); // Fill out the form

        while (!isDone) {
            choice = ink.validateMainMenu();

            switch (choice) {
                case 1: // print available pets
                    ink.printAvailablePets(shelter.getPets());
                    while (!goBack) {
                        int pick = input.nextInt();
                        if (pick != 0) {
                            ink.printPetDetails(shelter.getPet(pick - 1));
                            String answer = input.next();
                            if (answer.equalsIgnoreCase("Y")) {
                                shelter.adopt(pick - 1, user.getName());
                                goBack = !goBack;
                            } else {
                                goBack = !goBack;
                            }
                        } else {
                            goBack = !goBack;
                        }
                    } // while
                    break;
                case 2: // print shelter details
                    ink.printShelterDetails(shelter);
                    int shelterChoice = input.nextInt();
                    if (shelterChoice == 0) {
                        // Go back to the main menu
                    }
                    break;
                case 3: // book an appointment menu
                    String appointmentDay = user.bookAppointment(input, ink);
                    if (!appointmentDay.equals("Go back")) {
                    user.setBookAppointment(appointmentDay);
                    String appointmentData = user.getName() + " - " + appointmentDay;
                    String fileName = "appointments.txt";
                    saveAppointmentToFile(appointmentData, fileName);
                    goBack = true; // To exit
                    }
                    break;
                    case 4: // Display pet statistics
                    displayStatistics(shelter);
                    System.out.println(Ink.ANSI_PURPLE + "1. Show all pets" + Ink.ANSI_RESET);
                    System.out.println(Ink.ANSI_PURPLE + "2. Filter pets by type" + Ink.ANSI_RESET);
                    System.out.print(Ink.ANSI_GREEN + "0. Go back\n" + Ink.ANSI_RESET);
                    int statsChoice = input.nextInt();
                    if (statsChoice == 1) {
                        ink.printAvailablePets(shelter.getPets());
                        // After displaying pets, allow the user to go back
                        System.out.println(Ink.ANSI_BLUE + "Enter 0 to go back" + Ink.ANSI_RESET);
                        input.nextInt(); // Consume the input
                    } else if (statsChoice == 2) {
                        System.out.print(Ink.ANSI_BLUE + "Enter pet type to filter by (e.g., Dog, Cat): " + Ink.ANSI_RESET);
                        String filterType = input.next();
                        ArrayList<Pet> filteredPets = shelter.filterPetsByType(filterType);
                        ink.printAvailablePets(filteredPets);
                        // After displaying filtered pets, allow the user to go back
                        System.out.println(Ink.ANSI_BLUE + "Enter 0 to go back" + Ink.ANSI_RESET);
                        input.nextInt(); // Consume the input
                    } else if (statsChoice == 0) {
                        // Go back to the main menu
                    } else {
                        System.out.println(Ink.ANSI_RED + "Invalid choice. Please select a valid option." + Ink.ANSI_RESET);
                    }
                    break;                
                case 5: // Exit
                    isDone = !isDone;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            } // switch
            goBack = !goBack; // Toggle goBack to false
        } // while(main)

        ink.printGoodday();

        // Print the filled form in green color
        System.out.println(Ink.ANSI_GREEN + "Form Details:");
        System.out.println("Applicant Name: " + form.getApplicantName());
        System.out.println("Address: " + form.getAddress());
        System.out.println("Phone Number: " + form.getPhoneNumber() + Ink.ANSI_RESET);

        // Check if there are appointments and print accordingly
        String fileName = "appointments.txt";
        if (hasAppointments(fileName, user.getName())) {
            printAppointmentsFromFile(fileName, user.getName());
        } else {
            System.out.println(Ink.ANSI_YELLOW + "Your Appointments: No appointment was made." + Ink.ANSI_RESET);
        }

    } // main()

    // Meet the pets!
    public static void createPets() {
        pet = new Pet("Roger", "Dog",
                3, "Black", "Hound");
        shelter.addPet(pet);
        pet = new Pet("Mochi", "Dog",
                7, "Tan", "Pug");
        shelter.addPet(pet);
        pet = new Pet("Tiger", "Cat",
                1, "Black/Orange/White", "Calico");
        shelter.addPet(pet);
    } // createPets()

    public static void createUser(Scanner input) {
        String name, email;
        long phone;

        System.out.print(Ink.ANSI_YELLOW + "What is your name? " + Ink.ANSI_RESET);
        name = input.nextLine();
        System.out.print(Ink.ANSI_YELLOW + "What is your email? " + Ink.ANSI_RESET);
        email = input.nextLine();

        boolean validPhone = false;
        while (!validPhone) {
            System.out.print(Ink.ANSI_YELLOW + "What is your phone? " + Ink.ANSI_RESET);
            try {
                phone = Long.parseLong(input.nextLine());
                if (String.valueOf(phone).length() >= 10) {
                    validPhone = true;
                } else {
                    System.out.println(Ink.ANSI_RED + "This is not a 10 digit phone number.\nWould you like to proceed without a number? (Y/N)" + Ink.ANSI_RESET);
                    String choice = input.nextLine();
                    if (choice.equalsIgnoreCase("Y")) {
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(Ink.ANSI_RED + "Invalid input. Please enter a valid phone number." + Ink.ANSI_RESET);
            }
        }

        user = new User(name, email, longPhone);
    } // createUser()   

    // Print the filled form
    public static void printForm(Form form) {
        System.out.println("Form Details:");
        System.out.println("Applicant Name: " + form.getApplicantName());
        System.out.println("Address: " + form.getAddress());
        System.out.println("Phone Number: " + form.getPhoneNumber());
    }

    // Method to save appointment data to a file
    public static void saveAppointmentToFile(String appointmentData, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(appointmentData);
            // Print the newly saved appointment
            System.out.println("Appointment saved: " + appointmentData);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void printAppointmentsFromFile(String fileName, String userName) {
        boolean hasAppointments = false; // Flag to track if appointments are found
        System.out.println(Ink.ANSI_YELLOW + "Your Appointments: " + Ink.ANSI_RESET );
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into name and appointment
                String[] parts = line.split(" - ");
                if (parts.length == 2 && parts[0].equals(userName)) {
                    // Print the appointment if it matches the user's name
                    System.out.println(parts[1]);
                    hasAppointments = true; // Set flag to true as appointments are found
                }
            }
            if (!hasAppointments) {
                System.out.print(Ink.ANSI_YELLOW + "No appointment was made. " + Ink.ANSI_RESET);
            }
            System.out.println(); // Move to the next line after printing the message
        } catch (IOException e) {
            System.err.println("Error reading appointments from file: " + e.getMessage());
        }
    }

    // Method to check if there are any appointments for the user
    public static boolean hasAppointments(String fileName, String userName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2 && parts[0].equals(userName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading appointments from file: " + e.getMessage());
        }
        return false;
    }

    // Method to display pet statistics
public static void displayStatistics(Shelter shelter) {
    System.out.print(Ink.ANSI_BLUE); // Set text color to blue
    int totalPets = shelter.getPets().size();
    int totalDogs = 0;
    int totalCats = 0;

    for (Pet pet : shelter.getPets()) {
        if (pet.getType().equalsIgnoreCase("Dog")) {
            totalDogs++;
        } else if (pet.getType().equalsIgnoreCase("Cat")) {
            totalCats++;
        }
    }

    // Print statistics
    System.out.println("Statistics:");
    System.out.println("Total number of pets in the shelter: " + totalPets);
    System.out.println("Number of dogs: " + totalDogs);
    System.out.println("Number of cats: " + totalCats);
    System.out.print(Ink.ANSI_RESET); // Reset text color
}

} // class