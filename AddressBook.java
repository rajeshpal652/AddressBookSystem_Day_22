package com.bl.addressbook;
/*
 *  Performing operations by creating methods.
 * @Author: Rajesh Pal
 */
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    Scanner scanner = new Scanner(System.in);
    public Map<String, Contact> contactList = new HashMap<String, Contact>();

    public static HashMap<String, ArrayList<Contact>> city = new HashMap<String, ArrayList<Contact>>();
    public static HashMap<String, ArrayList<Contact>> state = new HashMap<String, ArrayList<Contact>>();

    public String addressBookName;
    public boolean isPresent = false;

    public String getAddressBookName(){
        return addressBookName;
    }
    public void setAddressBookName(String addressBookName){
        this.addressBookName = addressBookName;
    }

    public ArrayList<Contact> getContact(){
        return new ArrayList<Contact>(contactList.values());
    }

    public void displayMenu() {
        boolean change = true;
        do {
            System.out.println("\n Select the operation you want to perform : ");
            System.out.println(
                    "1.Add Contact To Address Book\n2.Edit Existing Entry\n3.Delete Contact\n4.Display Address book\n5.Sort Address Book\n6.Exit Address Book System");
            switch (scanner.nextInt()) {
                case 1:
                    addContact();
                    break;
                case 2:
                    editPerson();
                    break;
                case 3:
                    deletePerson();
                    break;
                case 4:
                    displayContents();
                    break;
                case 5:
                    sortAddressBook();
                    break;
                default:
                    change = false;
                    System.out.println("Exiting Address Book: " + this.getAddressBookName() + " !");
            }
        }while (change);
    }

    public void addContact(){

        Contact person = new Contact();
        Address address = new Address();

        System.out.println("Enter first name: ");
        String firstName = scanner.next();

        contactList.entrySet().stream().forEach(entry -> {
            if (entry.getKey().equals(firstName.toLowerCase())) {
                System.out.println("Contact already exists.");
                isPresent = true;
            }
        });

        if (isPresent == false){
            System.out.println("Enter last name : ");
            String lastName = scanner.next();
            System.out.println("Enter phone number :");
            long phoneNumber = scanner.nextLong();
            System.out.println("Enter email: ");
            String email = scanner.next();
            System.out.println("Enter city :");
            String city = scanner.next();
            System.out.println("enter state: ");
            String state = scanner.next();
            System.out.println("Enter zip code: ");
            long zip = scanner.nextLong();

            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setPhoneNumber(phoneNumber);
            person.setEmail(email);
            address.setCity(city);
            address.setState(state);
            address.setZip(zip);
            person.setAddress(address);
            contactList.put(firstName.toLowerCase(), person);
        }
    }

    public void displayContents() {
        System.out.println("----- Contents of the Address Book " + this.getAddressBookName() + " -----");
        for (String eachContact : contactList.keySet()) {
            Contact contact = contactList.get(eachContact);
            System.out.println(contact);
        }
        System.out.println("----------------------------------------------------");
    }

    public void editPerson() {
        Contact person = new Contact();

        System.out.println("Enter first name : ");
        String firstName = scanner.next();

        if (contactList.containsKey(firstName)) {
            person = contactList.get(firstName);
            Address address = person.getAddress();
            System.out.println("Choose you want to change : ");
            System.out.println("1.Last Name\n2.Phone Number\n3.Email\n4.City\n5.State\n6.ZipCode");
            switch (scanner.nextInt()) {
                case 1:

                    System.out.println("Enter the correct Last Name :");
                    String lastName = scanner.next();
                    person.setLastName(lastName);
                    break;
                case 2:
                    System.out.println("Enter the correct Phone Number :");
                    long phoneNumber = scanner.nextLong();
                    person.setPhoneNumber(phoneNumber);
                    break;
                case 3:
                    System.out.println("Enter the correct Email Address :");
                    String email = scanner.next();
                    person.setEmail(email);
                    break;
                case 4:
                    System.out.println("Enter the correct City :");
                    String city = scanner.next();
                    address.setCity(city);
                    break;
                case 5:
                    System.out.println("Enter the correct State :");
                    String state = scanner.next();
                    address.setState(state);
                    break;
                case 6:
                    System.out.println("Enter the correct ZipCode :");
                    long zip = scanner.nextLong();
                    address.setZip(zip);
                    break;
            }
        }
        else {
            System.out.println(" Name does not exist.");
        }
    }

    public void deletePerson() {
        System.out.println("Enter first name of the person to delete : ");
        String firstName = scanner.next();
        if (contactList.containsKey(firstName)) {
            contactList.remove(firstName);
            System.out.println("Successfully deleted.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void addPersonToCity(Contact contact) {
        if (city.containsKey(contact.getAddress().getCity())) {
            city.get(contact.getAddress().getCity()).add(contact);
        } else {
            ArrayList<Contact> cityList = new ArrayList<Contact>();
            cityList.add(contact);
            city.put(contact.getAddress().getCity(), cityList);
        }
    }

    public void addPersonToState(Contact contact) {
        if (state.containsKey(contact.getAddress().getState())) {
            state.get(contact.getAddress().getState()).add(contact);
        } else {
            ArrayList<Contact> stateList = new ArrayList<Contact>();
            stateList.add(contact);
            state.put(contact.getAddress().getState(), stateList);
        }
    }

    public void printSortedList(List<Contact> sortedContactList) {
        System.out.println("------Sorted address book " +this.getAddressBookName()+ "-------- ");
        Iterator iterator = sortedContactList.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
            System.out.println();
        }
        System.out.println("=======================================");
    }

    public void sortAddressBook() {
        List<Contact> sortedContactList;
        System.out.println("Select the parameter on which you want to sort address book :");
        System.out.println("1. First name\n2. City\n3. State\n4. Zip Code");
        int sortingChoice = scanner.nextInt();
        switch (sortingChoice) {
            case 1:
                sortedContactList = contactList.values().stream().sorted((firstPerson, secondPerson) -> firstPerson.getFirstName().
                        compareTo(secondPerson.getFirstName())).collect(Collectors.toList());
                printSortedList(sortedContactList);
                break;
            case 2:
                sortedContactList = contactList.values().stream().sorted((firstPerson, secondPerson) -> firstPerson.getAddress()
                        .getCity().compareTo(secondPerson.getAddress().getCity())).collect(Collectors.toList());
                printSortedList(sortedContactList);
                break;
            case 3:
                sortedContactList = contactList.values().stream().sorted((firstPerson, secondPerson) -> firstPerson.getAddress()
                        .getState().compareTo(secondPerson.getAddress().getState())).collect(Collectors.toList());
                printSortedList(sortedContactList);
                break;
            case 4:
                sortedContactList = contactList.values().stream().sorted((firstPerson, secondPerson) ->Long.valueOf(firstPerson.getAddress()
                        .getZip()).compareTo(Long.valueOf(secondPerson.getAddress().getZip()))).collect(Collectors.toList());
                printSortedList(sortedContactList);
                break;
        }
    }
}
