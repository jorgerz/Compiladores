/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adriel1019
 */
public class Stack {

    private ArrayList<Integer> integer;
    private int top;
    private boolean empty;
    public Stack(){
        integer = new ArrayList<>();
        top = integer.size();
        empty = true;
    }
    
    public void push(int value){        
        integer.add(value);
        top = integer.size();        
        empty = false;
    }
    
    public int pop(){
        int value;
        if(isEmpty()){
            return -1;
        }
        value = integer.get(--top);
        integer.remove(top);
        if(top == 0)
            empty = true;
        return value;
    }
    
    public boolean isEmpty(){
        return empty?true:false;
    }
}
