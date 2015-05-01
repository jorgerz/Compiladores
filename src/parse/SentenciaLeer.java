/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Main.Read;
import Tokens.Variable;

/**
 *
 * @author adriel1019
 */
public class SentenciaLeer {
    
    private Variable variable;
    private String strValue;
    private float value;
    private String text;
    public SentenciaLeer(Variable variable){
        this.variable = variable;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public void execute(){
        variable.addValue( Read.inFloat(text) );
    }
}
