/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.dto;

import java.util.ArrayList;

/**
 *
 * @author Thanh Hai
 */
public interface I_Book {
    void createBook(ArrayList<Publisher> publishers);
    void searchBook(ArrayList<Book> searchResults);
    void updateBook(ArrayList<Publisher> publishers);
    void deleteBook();
    void saveToFile();
    void printFromFile();
}
