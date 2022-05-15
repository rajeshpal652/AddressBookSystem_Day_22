package com.bl.addressbook;

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
                    "1.Add an Address Book\n2.Display Address book Directory\n4.Exit Address book System");

            switch (sc.nextInt()) {
                case 1:
                    addAddressBook();
                    break;
                case 2:
                    displayDirectoryContents();
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

}
