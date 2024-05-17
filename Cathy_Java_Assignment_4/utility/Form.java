package utility;

import java.util.Scanner;

public class Form {
    private String applicantName;
    private String address;
    private String phoneNumber;

    public Form() {
        // Default constructor
    }

    //===============>>
    // GETTERS
    public String getApplicantName() {
        return applicantName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //===============>>
    // SETTERS

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // fillForm method
    public void fillForm(Scanner input) {
        // Prompt user to fill out a form
        System.out.println(Ink.ANSI_GREEN + "Please fill out the form:" + Ink.ANSI_RESET);

        // Get applicant name
        System.out.print(Ink.ANSI_ORANGE + "Applicant Name: " + Ink.ANSI_RESET);
        String name = input.nextLine();
        setApplicantName(name);

        // Get address
        System.out.print(Ink.ANSI_ORANGE + "Address: " + Ink.ANSI_RESET);
        String addr = input.nextLine();
        setAddress(addr);

        // Get phone number
        System.out.print(Ink.ANSI_ORANGE + "Phone Number: " + Ink.ANSI_RESET);
        String phone = input.nextLine();
        setPhoneNumber(phone);
    }
} // class