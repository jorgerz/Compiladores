/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Tokens.Stack;

/**
 *
 * @author adriel1019
 */
public class TestStack {
   
    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push(4);
        stack.push(5);
        System.out.println("Sale un: "+stack.pop());
        System.out.println("Sale un: "+stack.pop());
        System.out.println("Sale un: "+stack.pop());
    }
}
