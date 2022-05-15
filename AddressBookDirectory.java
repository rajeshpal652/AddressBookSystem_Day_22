package com.bl.addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookDirectory {
    Scanner sc = new Scanner(System.in);

    Map<String, AddressBook> addressBookDirectory = new HashMap<String, AddressBook>();

    /*
     * creating switch case to call each method by option
     */
    public void displayMenu() {
        boolean moreChanges = true;
        do {
            System.out.println("\nChoose the operation on the Directory you want to perform");
            System.out.println(
                    "1.Add an Address Book\n2.Display Address book Directory\n3.Search Person by Region\n4.Exit Address book System");

            switch (sc.nextInt()) {
                case 1:
                    addAddressBook();
                    break;
                case 2:
                    displayDirectoryContents();
                    break;
                case 3:
                    System.out.println("Enter 1 to Search By City\nEnter 2 to Search By State");
                    int searChoice = sc.nextInt();
                    if(searChoice==1)
                        searchByCity();
                    else if(searChoice==2)
                        searchByState();
                    else System.out.println("Enter valid input");
                    break;
                default:
                    moreChanges = false;
                    System.out.println("Exiting Address Book Directory !");
            }

        } while (moreChanges);
    }

    /*
     * Adding new address book to by checking existing book is available or not
     */
    public void addAddressBook() {

        System.out.println("Enter the name of the AddressBook you want to add");
        String bookNameToAdd = sc.next();

        if (addressBookDirectory.containsKey(bookNameToAdd)) {
            System.out.println("AddressBook Name Already Exists");
        } else {
            AddressBook addressBook = new AddressBook();
            addressBook.setAddressBookName(bookNameToAdd);
            addressBookDirectory.put(bookNameToAdd, addressBook);
            System.out.println("Address book added successfully.");
            addressBook.displayMenu();
        }
    }

    /*
     * in this method displaying addressBook name
     */
    public void displayDirectoryContents() {

        System.out.println("----- Contents of the Address Book Directory-----");
        for (String eachBookName : addressBookDirectory.keySet()) {

            System.out.println(eachBookName);
        }
        System.out.println("-----------------------------------------");
    }

    /*
     * searching person by his/her city name
     */
    public void searchByCity() {

        System.out.println("Enter the name of the City where the Person resides : ");
        String cityName = sc.next();

        for (AddressBook addressBook : addressBookDirectory.values()) {
            ArrayList<Contact> contactList = addressBook.getContact();
            contactList.stream()
                    .filter(person -> person.getAddress().getCity().equals(cityName))
                    .forEach(person -> System.out.println(person));
        }
    }

    /*
     * searching person by his/her state name
     */
    public void searchByState() {

        System.out.println("Enter the name of the State where the Person resides : ");
        String stateName = sc.next();

        for (AddressBook addressBook : addressBookDirectory.values()) {
            ArrayList<Contact> contactList = ((AddressBook) addressBook).getContact();
            contactList.stream()
                    .filter(person -> person.getAddress().getState().equals(stateName))
                    .forEach(person -> System.out.println(person));
        }

    }

}
