/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.utils;

import bookstore.controller.PublisherList;
import java.util.Scanner;

/**
 *
 * @author Thanh Hai
 */
public class Utils {
    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }
    
    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    public static String getString(String welcome, int minCharacter, int maxCharacter) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else if (result.length() < minCharacter || result.length() > maxCharacter) {
                System.out.println("Your description must be above "+minCharacter+" and below "+maxCharacter+" characters.");
            }
            else{
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static String getStringNumber(String welcome, int minCharacter, int maxCharacter) {
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.matches("\\d{" + minCharacter + "," + maxCharacter + "}")) {
                return input;
            } else {
                System.out.print("Invalid input. Please enter a number with " + minCharacter + " to " + maxCharacter + " digits: ");
            }
        }
        return null; // return null if input is not valid
    }
    
    public static boolean askGoBackToMenu(String welcome) {
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String confirm = sc.nextLine();
        if("Y".equalsIgnoreCase(confirm)) {
            System.out.println("[!] Exit to the menu");
            return true;
        } else {
            return false;
        }
    }

    public static void displayDataGrid(PublisherList temp) {
        System.out.println("----------------------------------------------------------------------");
        System.out.print(String.format("| %-15s | %-25s | %-20s |\n", "Publisher ID", "Publisher Name", "Publisher Phone"));
        System.out.println("----------------------------------------------------------------------");
        temp.forEach((publisher) -> {
            System.out.print(String.format("| %-15s | %-25s | %-20s |\n", publisher.getPublisherID(), publisher.getPublisherName(), publisher.getPhoneNumber()));
        });
        System.out.println("----------------------------------------------------------------------");
    }

    public static double getDouble(String welcome, int min) {
        boolean check = true;
        double number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Double.parseDouble(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static String getStatus(String welcome, int min) {
        String input = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Statuses:\n 1 - Available\n 2 - Not Available");
        System.out.print(welcome);
        int temp;
        do {
            temp = Integer.parseInt(sc.nextLine());
            switch (temp) {
                case 1:
                    input = "Available";
                    break;
                case 2:
                    input = "Not Available";
                    break;
                default:
                    System.out.println("[!] Invalid input. Please enter 1 or 2.");
            }
        } while (temp <= 1 || temp >= 2);
        return input;
    }
}
