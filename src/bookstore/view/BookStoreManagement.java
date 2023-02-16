/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.view;

import bookstore.controller.Menu;
import bookstore.dto.Book;
import bookstore.dto.I_Book;
import bookstore.dto.I_Menu;
import bookstore.dto.I_Publisher;
import bookstore.dto.ManagerFactory;
import bookstore.dto.Publisher;
import java.util.ArrayList;

/**
 *
 * @author Thanh Hai
 */
public class BookStoreManagement {
    
    static ArrayList<Publisher> publisher = new ArrayList<>();
    static ArrayList<Book> book = new ArrayList<>();
    public static void main(String[] args) {
        I_Menu menu = new Menu();
        menu.addItem("1. Publisher's Management");
        menu.addItem("2. Books Management");
        menu.addItem(" Others - Quit");
        int choice;
        boolean check = false;
        do {
            System.out.println(">>==x===<<<< BOOK STORE >>>>===x==<<");
            menu.showMenu();
            System.out.println(">>==x=== =o======oo======o= ===x==<<");
            choice = menu.getChoice();
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
       I_Publisher publisherManager = ManagerFactory.createPublisherManager();
       I_Menu menu = new Menu();
       int choice;
       boolean check = false;
       menu.addItem("1. Create a Publisher.");
       menu.addItem("2. Delete the Publisher.");
       menu.addItem("3. Save the Publisher.");
       menu.addItem("4. Print the Publisher list from file.");
       menu.addItem("Others - Exit management.");
       do {
           System.out.println(">>==x===<<<< BOOK STORE >>>>===x==<<");
            menu.showMenu();
            System.out.println(">>==x=== =o======oo======o= ===x==<<");
            choice = menu.getChoice();
            switch(choice) {
                case 1: 
                    publisherManager.createPublisher();
                    break;
                case 2:
                    publisherManager.deletePublisher();
                    break;
                case 3:
                    publisherManager.saveToFile();
                    break;
                case 4:
                    publisherManager.printFromFile();
                    break;
                case 5:
                    check = menu.confirmYesNo("Do you want to exit the Publisher Management (Y/N)?");
                    break;
            }
       } while (choice >= 0 && choice <= 5 && !check);
    }

    private static void handleBookManagement() {
       I_Publisher publisherManager = ManagerFactory.createPublisherManager();
       I_Book bookManager = ManagerFactory.createBookManager();
       I_Menu menu = new Menu();
       int choice;
       boolean check = false;
       menu.addItem("1. Create a Book.");
       menu.addItem("2. Search the Book.");
       menu.addItem("3. Update a Book.");
       menu.addItem("4. Delete the Book.");
       menu.addItem("5. Save the Books.");
       menu.addItem("6. Print the Books list from the file.");
       menu.addItem("Others - Exit management.");
       do {
           System.out.println(">>==x===<<<< BOOK STORE >>>>===x==<<");
            menu.showMenu();
            System.out.println(">>==x=== =o======oo======o= ===x==<<");
            choice = menu.getChoice();
            switch(choice) {
                case 1: 
                    bookManager.createBook((ArrayList<Publisher>) publisherManager);
                    break;
                case 2:
                    bookManager.searchBook((ArrayList<Publisher>) publisherManager);
                    break;
                case 3:
                    bookManager.updateBook((ArrayList<Publisher>) publisherManager);
                    break;
                case 4:
                    bookManager.deleteBook();
                    break;
                case 5:
                    bookManager.saveToFile();
                case 6:
                    bookManager.printFromFile();
                case 7:
                    check = menu.confirmYesNo("Do you want to exit the Book Management (Y/N)?");
                    break;
            }
       } while (choice >= 0 && choice <= 7 && !check);
    }
}
