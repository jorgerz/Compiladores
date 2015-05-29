/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

import Tokens.Token;
import Tokens.TokenType;
import Tokens.Variable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class TablaVariables {
    private Object object;
    private ArrayList<Token> tokens;
    private ArrayList<Variable> variables;
    private int counter;
    
    public TablaVariables(ArrayList<Token> tokens){
        this.tokens = tokens;
        counter = 0;
        generateVarsTable();
        showVariables();
    }
    
    private void generateVarsTable(){
        variables = new ArrayList<Variable>() {};
        Token token;
        Boolean found = false;
        for(int i = 0; i < tokens.size(); i++){
            token = tokens.get(i);
            found = false;
            if(token.type == TokenType.IDENTIFICADOR){
                for(int j = 0; j < variables.size(); j++){                                        
                    if(token.text.equals( variables.get(j).getName() )){
                        found = true;
                        break;
                    }
                }           
                if(!found){
                    variables.add( new Variable( token.text, counter++ ) );                        
                }
            }    
        }
    }
    
    public void showVariables(){
        for(int i = 0; i < variables.size(); i++){
            System.out.println("nombre: "+variables.get(i).getName());
        }
    }
    
    public void addValueToVariable(float value, int pointer){
        variables.get(pointer).addValue(value);
    }
    
    void setValor(Object object) {
        this.object=object;        
    }
    
    public int indexVariableName(String name){
        for(int i = 0; i < variables.size(); i++){
            if(variables.get(i).getName().equals(name))
                return i;          
        }
        return -1;
    }
    
    public ArrayList<Variable> getVariables(){
        return variables;
    }
}