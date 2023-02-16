/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.controller;

import bookstore.dto.Book;
import bookstore.dto.I_Book;
import bookstore.dto.Publisher;
import bookstore.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Thanh Hai
 */
public class BookList extends ArrayList<Book> implements I_Book {

    @Override
    public void createBook(ArrayList<Publisher> publishers) {
        
        //Check the publishers is empty or not
        if(publishers.isEmpty()) {
            System.out.println("[!] Publisher ID is not available, try again later.");
            return;
        }
                
        String code, name, status, publisherID;
        double price;
        int quantity;
        boolean check = true;
        System.out.println("[?] Input information of Book.");
        do {
            code = Utils.getString(" Book ID (Bxxxxx): ");
            check = this.contains(new Book(code));
            if (!code.matches("B\\d{5}")) {
                System.out.println("Invalid ID format. Please enter a valid ID starting with 'B' and followed by 5 digits.");
                check = true;
            }
        } while(check);  
        //Print the available Publisher list to easily manage
        System.out.println("Publisher Available: ");
        PublisherList pub = new PublisherList();
            pub.print();
        //Check publisher ID is exist or not?
        boolean isExist = false;
        publisherID = Utils.getString(" Publisher ID (Pxxxxx): ");
        do {
            isExist = find(publisherID, publishers);
        } while(!isExist);
        name = Utils.getString(" Book Name: ", 5, 30);
        price = Utils.getDouble(" Book Price: ", 0);
        quantity = Utils.getInt(" Book Quantity: ", 0);
        status = Utils.getStatus(" Book Status: ", 0);
        Book newData = new Book(code, name, price, quantity, status, publisherID);
        this.add(newData);
        System.out.println("[!] Added successfully ");
        boolean confirm = Utils.askGoBackToMenu("Would you like to go back to menu? (Y/N): ");
        if(!confirm) {
            createBook(publishers);
        }
    }

    @Override
    public void searchBook(ArrayList<Book> searchResults) {
        String searchStr = Utils.getString("Enter Publisher's Name to search: ");
        for (Book b : this) {
            if (b.getBookName().toLowerCase().contains(searchStr.toLowerCase()) || b.getPublisherID().equals(searchStr)) {
                searchResults.add(b);
            }
        }
        if (searchResults.isEmpty()) {
            System.out.println("Have no any Book.");
            return;
        }
        Collections.sort(searchResults, (b1, b2) -> b1.getBookName().compareToIgnoreCase(b2.getBookName()));
        System.out.println("Search results:");
        printBookList(searchResults);
    }

    @Override
    public void updateBook(ArrayList<Publisher> publishers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBook() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveToFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean find(String publisherID, ArrayList<Publisher> publishers) {
        boolean foundPublisher = false;
        for (Publisher p : publishers) {
            if (p.getPublisherID().equals(publisherID)) {
                foundPublisher = true;
                break;
            }
        }
        if (!foundPublisher) {
            System.out.println("Publisher's Id is not found.");
            return false;
        } else {
            return true;
        }
    }

    private void printBookList(ArrayList<Book> searchResults) {
        for (Book o : searchResults) {
            System.out.println(o.toString());
        }
    }


    
}
