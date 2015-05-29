/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

/**
 *
 * @author adriel1019
 */
public class Variable {
    
    private float value;
    private String name;
    private int pointer;
    public Variable(){
    }
    
    public Variable(String name, int pointer){
        this.name = name;        
        this.pointer = pointer;
    }    
    
    public Variable(String name, float value, int pointer){
        this.name = name;
        this.value = value;
        this.pointer = pointer;
    }    
    
    public String getName(){
        return name;
    }
    
    public void addValue(float value){
        this.value = value;
    }
    
    public float getValue(){
        return value;
    }
}
