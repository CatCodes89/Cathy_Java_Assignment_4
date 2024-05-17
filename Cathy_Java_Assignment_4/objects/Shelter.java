package objects;
import java.util.ArrayList;

import utility.Ink;

public class Shelter {

  private String address = "1234 Forever Furry Friends Lane";
  private String[] hours = {
    "Mon: 8am - 4pm",
    "Tues: 8am - 4pm",
    "Wed: 8am - 4pm",
    "Thurs: 8am - 4pm",
    "Fri: 8am - 4pm",
    "Sat/Sun: closed"
  };
  // composition: a class made up of other classes
  private ArrayList<Pet> pets = new ArrayList<>();

  public Shelter() {
    // do nothing
  }

  //=============>>
  // GETTERS
  public String getAddress() {
    return this.address;
  }
  public String[] getHours() {
    return this.hours;
  }
  public ArrayList<Pet> getPets() {
    return this.pets;
  }
  public Pet getPet(int idx) {
    return pets.get(idx);
  }

  //=============>>
  // SETTERS
  public void setAddress(String address) {
    this.address = address;
  }
  public void setHours(String[] hours) {
    this.hours = hours;
  }
  public void adopt(int idx, String owner) {
    pets.get(idx).setIsAdopted();
    pets.get(idx).setOwner(owner);
    System.out.printf(Ink.ANSI_YELLOW + "%s is now yours!\n" + Ink.ANSI_RESET, 
    pets.get(idx).getName());
    System.out.printf(Ink.ANSI_RED + """
    ,d88b.d88b,
    88888888888
    'Y8888888Y'
      'Y888Y'    
        'Y'
    """ + Ink.ANSI_RESET);
    pets.remove(idx); // takes the pet from the shelter
  }
  public void addPet(Pet pet) {
    pets.add(pet);
  }

  // Method to filter pets by type
  public ArrayList<Pet> filterPetsByType(String type) {
    ArrayList<Pet> filteredPets = new ArrayList<>();
    for (Pet pet : pets) {
        if (pet.getType().equalsIgnoreCase(type)) {
            filteredPets.add(pet);
        }
    }
    return filteredPets;
  }
} // class