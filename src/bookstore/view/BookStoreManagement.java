/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.view;

import bookstore.controller.Menu;
import bookstore.dto.I_Book;
import bookstore.dto.I_ManagerFactory;
import bookstore.dto.I_Menu;
import bookstore.dto.I_Publisher;
import bookstore.dto.ManagerFactory;
import bookstore.utils.Utils;
/**
 *
 * @author Thanh Hai
 */
public class BookStoreManagement {
    
    public static void main(String[] args) {
        I_Menu menu = new Menu();
        menu.addItem("1. Publisher's Management");
        menu.addItem("2. Books Management");
        menu.addItem("3. Quit");
        int choice;
        boolean check = false;
        do {
            System.out.println(">>==x===<<<< BOOK STORE >>>>===x==<<");
            menu.showMenu();
            System.out.println(">>==x=== =o======oo======o= ===x==<<");
            choice = menu.getChoice(1, 3);
            switch(choice) {
                case 1: 
                    handlePublisherManagement();
                    break;
                case 2:
                    handleBookManagement();
                    break;
                case 3:
                    check = menu.confirmYesNo("[!] Do you want to exit the program (Y/N)?");
                    break;
            }
        } while (choice >= 0 && choice <= 3 && !check);
    } 

    private static void handlePublisherManagement() {
       I_ManagerFactory mf = new ManagerFactory();
       I_Publisher publisherManager = mf.createPublisherManager();
       I_Menu menu = new Menu();
       int choice;
       boolean check = false;
       boolean isSaved = true;
       menu.addItem("1. Create a Publisher.");
       menu.addItem("2. Delete the Publisher.");
       menu.addItem("3. Save the Publisher.");
       menu.addItem("4. Print the Publisher list from file.");
       menu.addItem("5. Exit management.");
       do {
           System.out.println(">>==x===<<<< BOOK STORE >>>>===x==<<");
            menu.showMenu();
            System.out.println(">>==x=== =o======oo======o= ===x==<<");
            choice = menu.getChoice(1, 5);
            switch(choice) {
                case 1: 
                    publisherManager.createPublisher();
                    isSaved = false;
                    break;
                case 2:
                    publisherManager.deletePublisher();
                    isSaved = false;
                    break;
                case 3:
                    publisherManager.saveToFile();
                    isSaved = true;
                    break;
                case 4:
                    publisherManager.printFromFile();
                    break;
                case 5:
                    if(!isSaved) {
                        String confirm = Utils.getString("[WARNING] You have not save Publisher list yet, would you like to save (Y/N)?: ");
                        if("Y".equalsIgnoreCase(confirm)) {
                            publisherManager.saveToFile();
                        }
                    }
                    check = menu.confirmYesNo("Do you want to exit the Publisher Management (Y/N)?");
                    break;
            }
       } while (choice >= 0 && choice <= 5 && !check);
    }

    private static void handleBookManagement() {
       I_ManagerFactory mf = new ManagerFactory();
       I_Book bookManager = mf.createBookManager();
       I_Menu menu = new Menu();
       int choice;
       boolean check = false;
       boolean isSaved = true;
       menu.addItem("1. Create a Book.");
       menu.addItem("2. Search the Book.");
       menu.addItem("3. Update a Book.");
       menu.addItem("4. Delete the Book.");
       menu.addItem("5. Save the Books.");
       menu.addItem("6. Print the Books list from the file.");
       menu.addItem("7. Exit management.");
       do {
           System.out.println(">>==x===<<<< BOOK STORE >>>>===x==<<");
            menu.showMenu();
            System.out.println(">>==x=== =o======oo======o= ===x==<<");
            choice = menu.getChoice(1, 7);
            switch(choice) {
                case 1: 
                    bookManager.createBook();
                    isSaved = false;
                    break;
                case 2:
                    bookManager.searchBook();
                    break;
                case 3:
                    bookManager.updateBook();
                    isSaved = false;
                    break;
                case 4:
                    bookManager.deleteBook();
                    isSaved = false;
                    break;
                case 5:
                    bookManager.saveToFile();
                    break;
                case 6:
                    bookManager.printFromFile();
                    break;
                case 7:
                    if(!isSaved) {
                        String confirm = Utils.getString("[WARNING] You have not save Book list yet, would you like to save (Y/N)?: ");
                        if("Y".equalsIgnoreCase(confirm)) {
                            bookManager.saveToFile();
                        }
                    }
                    check = menu.confirmYesNo("Do you want to exit the Book Management (Y/N)?");
                    break;
            }
       } while (choice >= 0 && choice <= 7 && !check);
    }
}
