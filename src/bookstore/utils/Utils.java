/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.utils;

import bookstore.controller.BookList;
import bookstore.controller.PublisherList;
import bookstore.dto.Book;
import bookstore.dto.Publisher;
import java.util.ArrayList;
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
        System.out.println("+--------------------------------------------------------------------+");
        System.out.print(String.format("| %-15s | %-25s | %-20s |\n", "Publisher ID", "Publisher Name", "Publisher Phone"));
        System.out.println("+--------------------------------------------------------------------+");
        temp.forEach((publisher) -> {
            System.out.print(String.format("| %-15s | %-25s | %-20s |\n", publisher.getPublisherID(), publisher.getPublisherName(), publisher.getPhoneNumber()));
        });
        System.out.println("+--------------------------------------------------------------------+");
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
    
    public static String getStatus(String welcome) {
        String input = "Not Available";
        Scanner sc = new Scanner(System.in);
        System.out.println("Statuses:\n Default - Not Available\n 1 - Available");
        System.out.print(welcome);
        String tempStr = sc.next(); // Read input as a string
        try {
            int temp = Integer.parseInt(tempStr); // Parse string to integer
            if (temp == 1) {
                input = "Available";
            }
        } catch (NumberFormatException e) {
            // Input is not a valid integer, do nothing
        }
        return input;
    }

    public static String getString(String welcome, String formatter) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (!result.matches(formatter)) {
                System.out.println("Invalid ID format. Please enter a valid ID starting with 'B' and followed by 5 digits.");
            } else {
                if (result.isEmpty()) {
                    System.out.println("Input text!!!");
                } else {
                    check = false;
                }
            }
        } while (check);
        return result;
    }
    public static String updateName(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static double updatePrice(String welcome, double oldData) {
        boolean check = true;
        double number = oldData;
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
        } while (check);
        return number;
    }

    public static int updateQuantity(String welcome, int oldData) {
        boolean check = true;
        int number = oldData;
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
        } while (check);
        return number;
    }

    public static String updateStatus(String welcome, int oldData) {
        Scanner sc = new Scanner(System.in);
        int statusNumber;
        String status;
        boolean check = true;

        if (oldData == 1) {
            status = "Available";
        } else {
            status = "Not Available";
        }
        do {
            try {
                System.out.print(welcome);
                System.out.println("Statuses:\n Default - Not Available\n 1 - Available");
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    statusNumber = Integer.parseInt(tmp);
                    if (statusNumber == 1) {
                        status = "Available";
                    } else {
                        status = "Not Available";
                    }
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return status;
    }

    public static void displayDataGrid(BookList temp, PublisherList pub) {
        ArrayList<Publisher> publishers = pub.getPublishers();
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
        // Format the column headers
        System.out.format("| %-10s | %-35s | %-10s | %-10s | %-10s | %-25s| %-13s |\n", "Book ID", "             Book Name", "Price", "Quantity", "Subtotal", "Publisher", "Status");
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
        for (Book b : temp) {
            for (Publisher p : publishers) {
                if (p.getPublisherID().equals(b.getPublisherID())) {
                    // Calculate the subtotal for the book
                    double subtotal = (double) (b.getQuantity() * b.getBookPrice());
                    // Format the output for each book
                    System.out.format("| %-10s | %-35s | %-10s | %-10s | %-10s | %-25s| %-13s |\n", b.getBookID(), b.getBookName(), b.getBookPrice(), b.getQuantity(), subtotal, p.getPublisherName(), b.getStatus());
                }
            }
        }
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
    }

    public static void exitMenu(String welcome) {
        String key = "";
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        key = sc.nextLine();
    }
}
