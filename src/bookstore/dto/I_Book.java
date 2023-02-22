/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.dto;

/**
 *
 * @author Thanh Hai
 */
public interface I_Book {
    void createBook();
    void searchBook();
    void updateBook();
    void deleteBook();
    void saveToFile();
    void printFromFile();
}
