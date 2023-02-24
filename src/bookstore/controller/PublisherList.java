/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.controller;

import bookstore.dto.Book;
import bookstore.dto.I_Publisher;
import bookstore.dto.Publisher;
import bookstore.utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Thanh Hai
 */
public class PublisherList extends ArrayList<Publisher> implements I_Publisher, Serializable {
    
    private static final String FILE_PATH = "src/bookstore/data/Publisher.dat";

    public PublisherList() {
        loadFromFile();
    }
    
    public ArrayList<Publisher> getPublishers() {
        return this; // return the ArrayList of Publisher objects
    }
    
    @Override
    public void createPublisher() {
        String code, name, phoneNumber;
        boolean check = true;
        System.out.println("[?] Input information of Publisher.");
        do {
            code = Utils.getString(" Publisher ID (Pxxxxx): ");
            check = this.contains(new Publisher(code));
            if (!code.matches("P\\d{5}")) {
                System.out.println("Invalid ID format. Please enter a valid ID starting with 'P' and followed by 5 digits.");
                check = true;
            }
        } while(check);
        name = Utils.getString(" Publisher Name: ", 5, 30);
        phoneNumber = Utils.getStringNumber(" Phone Number: ", 10, 12);
        Publisher newData = new Publisher(code, name, phoneNumber);
        this.add(newData);
        print();
        System.out.println("[!] Added successfully ");
        boolean confirm = Utils.askGoBackToMenu("Would you like to go back to menu? (Y/N): ");
        if(!confirm) {
            createPublisher();
        }
    }
    
    public void print() {
        this.forEach((o) -> {
            System.out.println(o.toString() + "\n");
        });
    }
    

    @Override
    public void deletePublisher() {
        BookList book = new BookList();
        ArrayList<Book> manageBook = book.getBook();
        print();
        String publisherId = Utils.getString("[?] Enter Publisher ID you want to delete:");
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getPublisherID().equals(publisherId)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("[!] Publisher's ID does not exist.");
            return;
        }
        //Check publisher ID is in the Book collections
        for(Book b : manageBook) {
            if(b.getPublisherID().equals(publisherId)) {
                System.out.println("[!] This Publisher ID is containing in the Book collections, failed to delete");
                return;
            }
        }
        this.remove(index);
        System.out.println("[!] Publisher deleted successfully.");
        boolean confirm = Utils.askGoBackToMenu("Would you like to go back to menu? (Y/N): ");
        if(!confirm) {
            deletePublisher();
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
            System.out.println("[!] Publisher list saved to file successfully.");
        } catch (IOException e) {
            System.out.println("[!] Error saving publisher list to file: " + e.getMessage());
        }
        Utils.exitMenu("Press any key to return to menu.");
    }

    @Override
    public void printFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists() || !file.canRead()) {
                System.out.println("[!] Current Publisher's data is empty or can't read it.");
                return;
            }
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            PublisherList temp = (PublisherList) in.readObject();
            // Sort by Publisher's Name ascending
            Collections.sort(temp, Comparator.comparing(Publisher::getPublisherName));
            Utils.displayDataGrid(temp);
            this.addAll(temp); //all information that gets from file, should be store into the collection
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[!] Failed to print Publisher.dat due to exception: "+e);
        }
        Utils.exitMenu("Press any key to return to menu.");
    }

    private void loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists() || !file.canRead()) {
                return;
            }
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            PublisherList temp = (PublisherList) in.readObject();
            this.addAll(temp);
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[!] Failed to load Publisher.dat due to exception: "+e);
        }
    }
    
    public void confirmSavingFile() {
        if(this.isEmpty()) {
            System.out.println("[!] Publisher List is empty, don't need to save.");
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(this);
            System.out.println("[!] Publisher list saved to file successfully.");
        } catch (IOException e) {
            System.out.println("[!] Error saving publisher list to file: " + e.getMessage());
        }
        boolean confirm = Utils.askGoBackToMenu("Would you like to go back to menu? (Y/N): ");
        if(!confirm) {
            deletePublisher();
        }
    }
}
