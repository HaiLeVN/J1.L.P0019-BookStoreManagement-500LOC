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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Thanh Hai
 */
public class BookList extends ArrayList<Book> implements I_Book {
    
    private static final String FILE_PATH = "src/bookstore/data/Book.dat";

    public BookList() {
        loadFromFile();
    }
    
    public ArrayList<Book> getBook() {
        return this; // return the ArrayList of Book objects
    }
    
    @Override
    public void createBook() {
        PublisherList pub = new PublisherList();
        ArrayList<Publisher> publishers = pub.getPublishers();
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
            pub.print();
        //Check publisher ID is exist or not?
        boolean isExist = false;
        do {
            publisherID = Utils.getString(" Publisher ID (Pxxxxx): ");
            isExist = find(publisherID, publishers);
        } while(!isExist);
        name = Utils.getString(" Book Name: ", 5, 30);
        price = Utils.getDouble(" Book Price: ", 0);
        quantity = Utils.getInt(" Book Quantity: ", 0);
        status = Utils.getStatus(" Book Status: ");
        Book newData = new Book(code, name, price, quantity, status, publisherID);
        this.add(newData);
        System.out.println("[!] Added successfully ");
        boolean confirm = Utils.askGoBackToMenu("Would you like to go back to menu? (Y/N): ");
        if(!confirm) {
            createBook();
        }
    }

    @Override
    public void searchBook() {
        ArrayList<Book> searchResults = new ArrayList<>();
        String searchStr = Utils.getString("Enter Book's Name or Publisher's Id to search: ");

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

        boolean confirm = Utils.askGoBackToMenu("Would you like to go back to menu? (Y/N): ");
        if(!confirm) {
            searchBook();
        }
    }

    @Override
    public void updateBook() {
        int index = 0;
        String oldName, newName;
        double oldPrice, newPrice;
        int oldQuantity, newQuantity;
        String oldStatus, newStatus;

        int statusNumber;
        //Print the list first before input book ID to update
        print();
        String code = Utils.getString(" Enter Book ID to update: ", "B\\d{5}");

        //Check the Book's ID is exist or not
        if (!this.contains(new Book(code))) {
            System.out.println("[!] Book's Name does not exist.");
            return;
        }
        
        index = this.indexOf(new Book(code));
        //Update the book's information
        //Update Name of Book

        do {
            oldName = ((Book) this.get(index)).getBookName();
            newName = Utils.updateName(" Input new name: ", oldName);
        } while (newName.length() < 5 && newName.length() > 30);
        ((Book) this.get(index)).setBookName(newName);
        //Update Price
        oldPrice = ((Book) this.get(index)).getBookPrice();
        newPrice = Utils.updatePrice(" Input new price: ", oldPrice);
        ((Book) this.get(index)).setBookPrice(newPrice);
        //Update Quantity
        oldQuantity = ((Book) this.get(index)).getQuantity();
        newQuantity = Utils.updateQuantity(" Input new quantity: ", oldQuantity);
        ((Book) this.get(index)).setQuantity(newQuantity);
        //Update Status
        oldStatus = ((Book) this.get(index)).getStatus();
        if (oldStatus.equals("Available")) {
            statusNumber = 1;
        } else {
            statusNumber = 0;
        }
        newStatus = Utils.updateStatus(" Input new status: ", statusNumber);
        ((Book) this.get(index)).setStatus(newStatus);
        String publisherID = ((Book) this.get(index)).getPublisherID();
        
        System.out.println("[!] Updated successfully ");
        boolean confirm = Utils.askGoBackToMenu("Would you like to go back to menu? (Y/N): ");
        if (!confirm) {
            updateBook();
        }
    }
    public void print() {
        this.forEach((o) -> {
            System.out.println(o.toString() + "\n");
        });
    }

    @Override
    public void deleteBook() {
        int index = 0;
        //Print the list first before input book ID to update
        print();
        String code = Utils.getString(" Enter Book ID to remove: ", "B\\d{5}");
        
        //Check the Book's ID is exist or not
        if (!this.contains(new Book(code))) {
            System.out.println("[!] Book's Name does not exist.");
            return;
        }
        index = this.indexOf(new Book(code));
        this.remove(index);
        //Check the Book's ID again
        if(!this.contains(new Book(code))) {
            System.out.println("[!] Deleted successfully");
        } else {
            System.out.println("[!] Failed to delete");
        }
        boolean confirm = Utils.askGoBackToMenu("Would you like to go back to menu? (Y/N): ");
        if(!confirm) {
            deleteBook();
        }
    }

    @Override
    public void saveToFile() {
        // Create folder "data" if it does not exist
        File outputFolder = new File("src/bookstore/data");
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(this);
            System.out.println("[!] Saved file successfully");
        } catch (FileNotFoundException ex) {
            System.out.println("[!] Failed to save files at "+FILE_PATH+" due to exception "+ex);
        } catch (IOException ex) {
             System.out.println("[!] Failed to save files at "+FILE_PATH+" due to exception "+ex);
        }
        Utils.exitMenu("Press any key to return to menu.");
    }

    @Override
    public void printFromFile() {
        PublisherList pub = new PublisherList();
        File file = new File(FILE_PATH);
        if (!file.canWrite() || !file.exists()) {
            System.out.println("[!] Current Book's data is empty or can't read it.");
        } else {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                BookList temp = (BookList) ois.readObject();
                //If the Book has same Quantity then the system will be ordered by Book’s Price
                //The data grid should be displayed Book’s information order by Book’s Quantity descending.
                Collections.sort(temp, new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        int quantityDiff = o2.getQuantity() - o1.getQuantity();
                        if (quantityDiff == 0) {
                            // if Quantity is the same, compare by Price
                            return Double.compare(o1.getBookPrice(), o2.getBookPrice());
                        } else {
                            return quantityDiff;
                        }
                    }
                });
                this.addAll(temp);
                Utils.displayDataGrid(temp, pub);
            } catch (FileNotFoundException ex) {
                System.out.println("[!] Failed to print files at " + FILE_PATH + " due to exception " + ex);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("[!] Failed to print files at " + FILE_PATH + " due to exception " + ex);
            }
            Utils.exitMenu("Press any key to return to menu.");
        }
    }

    private boolean find(String publisherID, ArrayList<Publisher> publishers) {
        boolean foundPublisher = false;
        if (!publisherID.matches("P\\d{5}")) {
                System.out.println("Invalid ID format. Please enter a valid ID starting with 'P' and followed by 5 digits.");
                return false;
        }
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
    private void loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists() || !file.canRead()) {
                return;
            }
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            BookList temp = (BookList) in.readObject();
            this.addAll(temp);
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[!] Failed to print Book.dat due to exception: "+e);
        }
    }  
}
