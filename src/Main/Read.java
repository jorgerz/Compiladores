/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.JOptionPane;

/**
 *
 * @author adriel1019
 */
public class Read {
    
    public static float inFloat(String text){
        String result;
        float r = -0.1f;
        boolean exit = false;
        do{
            result = JOptionPane.showInputDialog(null, text);
            try{
                r = Float.parseFloat(result);
                exit = true;
            }catch(Exception e){
                e.printStackTrace();
            }            
        }while(!exit);
        return r;
    }
}
