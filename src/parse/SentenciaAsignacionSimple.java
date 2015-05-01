/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Tokens.Variable;

/**
 *
 * @author adriel1019
 */
public class SentenciaAsignacionSimple {
    private float variable;
    private Variable source;
    private Variable destiny;
    private boolean flag;
    
    public SentenciaAsignacionSimple(Variable destiny, Variable source){
        this.source = source;
        this.destiny = destiny;        
        flag = true;
    }
    
    public SentenciaAsignacionSimple(Variable destiny, float variable){
        this.destiny = destiny;
        this.variable = variable;        
        flag = false;
    }
    
    public void execute(){
        assignValue();
    }
        
    public void assignValue(){
        if(flag)
            assignVariableValue();
        else
            assignFloatValue();
    }
    
    private void assignVariableValue(){
        destiny.addValue(source.getValue());
    }
    
    private void assignFloatValue(){
        destiny.addValue(variable);
    }
}
