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
public interface I_Menu {
    // add a menu item--> add text to menu
   void addItem(String s);
   // get user choice( user input their choice)
   int getChoice(int min, int max);
   // show menu for user choice
   void showMenu();
   
   boolean confirmYesNo(String welcome);
}
