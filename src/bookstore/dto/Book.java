/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.dto;

import bookstore.controller.PublisherList;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Thanh Hai
 */
public class Book implements Serializable, Comparable<Book> {
    
    private String bookID;
    private String bookName;
    private double bookPrice;
    private int quantity;
    private String status;
    private String publisherID;

    public Book() {
    }

    public Book(String bookID, String bookName, double bookPrice, int quantity, String status, String publisherID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.status = status;
        this.publisherID = publisherID;
    }

    public Book(String code) {
        this.bookID = code;
    }

    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Book) {
            Book other = (Book) o;
            return this.bookID.equals(other.bookID);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Book{" + "bookID=" + bookID + ", bookName=" + bookName + ", bookPrice=" + bookPrice + ", quantity=" + quantity + ", Status=" + status + '}';
    }

    @Override
    public int compareTo(Book o) {
        return this.quantity - o.getQuantity();
    }
}
