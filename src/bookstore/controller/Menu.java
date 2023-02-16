/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.controller;

import bookstore.dto.I_Menu;
import bookstore.utils.Utils;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Thanh Hai
 */
public class Menu extends ArrayList<String> implements I_Menu {

    @Override
    public void addItem(String str) {
        this.add(str);
    }

    @Override
    public int getChoice() {
        int result = -1;
        result = Utils.getInt("Input your choice:", 1, 10);
        return result;
    }

    @Override
    public void showMenu() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i).toString());
        }
    }
    @Override
    public boolean confirmYesNo(String welcome) {
        String confirm;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        confirm = sc.nextLine();
        if("Y".equalsIgnoreCase(confirm)) {
            System.out.println("[!] Exited");
            return true;
        }
        return false;    
    }
    
}
