package objects;
import java.util.Scanner;

import utility.Ink;

public class User {
    private String name;
    private String email;
    private long phone;
    private String bookAppointment;

    public User(String name, String email, long phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    } // constructor

    //===============>>
    // GETTERS
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public long getPhone() {
        return this.phone;
    }

    public String getBookAppointment() {
        return this.bookAppointment;
    }

    //===============>>
    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setBookAppointment(String bookAppointment) {
        this.bookAppointment = bookAppointment;
    }

    public String bookAppointment(Scanner input, Ink ink) {
        ink.printGreen("Select a # between (1-5) to choose\na day of the week for your appointment:");
        ink.printBlue("1. Monday: 8am - 4pm");
        ink.printBlue("2. Tuesday: 8am - 4pm");
        ink.printBlue("3. Wednesday: 8am - 4pm");
        ink.printBlue("4. Thursday: 8am - 4pm");
        ink.printBlue("5. Friday: 8am - 4pm");
        ink.printGreen("0. Go back");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
            System.out.println(Ink.ANSI_YELLOW + "You have booked an appointment for Monday, see you soon!" + Ink.ANSI_RESET);
            return "Monday";
        case 2:
            System.out.println(Ink.ANSI_YELLOW + "You have booked an appointment for Tuesday, see you soon!" + Ink.ANSI_RESET);
            return "Tuesday";
        case 3:
            System.out.println(Ink.ANSI_YELLOW + "You have booked an appointment for Wednesday, see you soon!" + Ink.ANSI_RESET);
            return "Wednesday";
        case 4:
            System.out.println(Ink.ANSI_YELLOW + "You have booked an appointment for Thursday, see you soon!" + Ink.ANSI_RESET);
            return "Thursday";
        case 5:
            System.out.println(Ink.ANSI_YELLOW + "You have booked an appointment for Friday, see you soon!" + Ink.ANSI_RESET);
            return "Friday";        
            case 0:
            System.out.println(Ink.ANSI_YELLOW + "No appointment made. " + Ink.ANSI_RESET);
                return "Go back";
            default:
                System.out.println("Invalid choice. Please select again.");
                return bookAppointment(input, ink);
        }
    }
} // class