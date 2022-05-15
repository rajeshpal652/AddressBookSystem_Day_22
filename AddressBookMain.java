package com.bl.addressbook;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("-----------------Welcome to address book Program --------------------");
        System.out.println();
        AddressBookDirectory addressBookDirectory = new AddressBookDirectory();
        addressBookDirectory.displayMenu();
        System.out.println("Operation successful.");
    }
}
