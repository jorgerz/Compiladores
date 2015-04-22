/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Tokens.Token;
import Tokens.TokenType;
import Tokens.Variable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
class TablaVariables {
    private Object object;
    private List<Token> tokens;
    private List<Variable> variables;
    
    public TablaVariables(List<Token> tokens){
        this.tokens = tokens;
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
                    variables.add( new Variable( token.text, i ) );                        
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
}