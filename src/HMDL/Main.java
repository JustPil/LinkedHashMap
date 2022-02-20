package HMDL;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        HashMap<Integer, String> hashmap = new HashMap<>(10);
        testDriver(scn, hashmap);
        scn.close();
    }

    /**
     * menu The menu of choices for the HashMap.
     */
    public static void menu() {
        System.out.print("\nMethods for Hash Map\n1 - PUT - Inserts a key value pair into the Hash Map\n2 - " +
                "GET - Returns the value matching a key, if present\n3 - REMOVE - Removes a key value pair " +
                "from the Hash Map\n4 - CONTAINS - Checks if a key is present in the Hash Map\n5 - IS EMPTY " +
                "Checks if the Hash Map is empty\n6 - IS FULL - Checks if the Hash Map is full\n7 - SIZE - " +
                "Returns the number of key-value pairs in the Hash Map\n8 - INSERTION ORDER - Returns the insertion" +
                "order of the elements\nX - Terminate\n\n" + "Enter choice: ");
    }

    /**
     * testDriver A test driver method for interacting with a HashMap object.
     * @param scn A Scanner object for user input.
     * @param hashmap A HashMap object.
     */
    public static void testDriver(Scanner scn, HashMap<Integer, String> hashmap) {
        String choice = "";
        while(!choice.equalsIgnoreCase("X")) {
            menu();
            choice = scn.nextLine();
            if (choice.equals("1")) {
                System.out.print("Input an integer to insert as a key: ");
                int key = scn.nextInt();
                scn.nextLine();
                System.out.print("Input a string to insert as a value: ");
                String value = scn.nextLine();
                System.out.println("Added: " + hashmap.put(key, value));
            } else if (choice.equals("2")) {
                System.out.print("Input an integer to use as a search key to return its corresponding value: ");
                int key = scn.nextInt();
                scn.nextLine();
                System.out.println("Key: " + key + " value: " + hashmap.get(key));
            } else if (choice.equals("3")) {
                System.out.print("Input an integer to use as a search key for removing a key-value pair: ");
                int key = scn.nextInt();
                scn.nextLine();
                System.out.println("Key: " + key + " removed: " + hashmap.remove(key));
            } else if (choice.equals("4")) {
                System.out.print("Input an integer to use as a search key to check if it's in the Hash Map: ");
                int key = scn.nextInt();
                scn.nextLine();
                System.out.println("Key: " + key + " found: " + hashmap.contains(key));
            } else if (choice.equals("5")) {
                System.out.println("Is empty: " + hashmap.isEmpty());
            } else if (choice.equals("6")) {
                System.out.println("Is full: " + hashmap.isFull());
            } else if (choice.equals("7")) {
                System.out.println("Size: " + hashmap.size());
            } else if(choice.equals("8")) {
                Object[] arr = hashmap.getInsertionOrder();
                System.out.println("Insertion order of elements in HashMap:");
                for(Object val : arr) {
                    System.out.print(val + " ");
                }
            } else if (choice.equalsIgnoreCase("X")) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
