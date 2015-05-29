/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author adriel1019
 */
public class Write {
    
    public Write(){
    }
    
    public static void OutText(String text){
        System.out.println(text);
    }
    
    public static void OutG(String text){
        JOptionPane.showMessageDialog(null, text);
    }
}
