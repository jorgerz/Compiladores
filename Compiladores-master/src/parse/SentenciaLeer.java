/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Main.Read;
import Main.Write;
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
    private String textoEjecucion="";
    public SentenciaLeer(Variable variable){
        this.variable = variable;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public void execute(){
        variable.addValue( Read.inFloat(text) );
        Write.OutText("Leido "+variable.getName()+" = "+variable.getValue());
        textoEjecucion+="Leido "+variable.getName()+" = "+variable.getValue()+"\n\n";
    }
    
    public String textEjecucion()
    {
        return textoEjecucion;
    }
}
