/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.dto;

import java.io.Serializable;

/**
 *
 * @author Thanh Hai
 */
public class Publisher implements Serializable {
    private String publisherID;
    private String publisherName;
    private String phoneNumber;

    public Publisher() {
        super();
    }

    public Publisher(String publisherID, String publisherName, String phoneNumber) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.phoneNumber = phoneNumber;
    }

    public Publisher(String ID) {
        this.publisherID = ID;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Publisher{" + "publisherID=" + publisherID + ", publisherName=" + publisherName + ", phoneNumber=" + phoneNumber + '}';
    } 
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Publisher) {
            Publisher other = (Publisher) o;
            return this.publisherID.equals(other.publisherID);
        }
        return false;
    }
}
