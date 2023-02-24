/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.dto;

import bookstore.controller.BookList;
import bookstore.controller.PublisherList;

/**
 *
 * @author Thanh Hai
 */
public class ManagerFactory implements I_ManagerFactory {
    
    @Override
    public I_Book createBookManager() {
        return new BookList();
    }

    @Override
    public I_Publisher createPublisherManager() {
        return new PublisherList();
    }
}
