/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Tokens.Variable;
import Main.Write;
/**
 *
 * @author adriel1019
 */
public class SentenciaEscribir {
    private Variable variable;
    private String strValue;
    private float value;
    private String text;
    private String textoEjecucion="";
    public SentenciaEscribir(Variable variable){
        this.variable = variable;
        text = "";
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public void execute(){        
        if(variable.getValue() != -1.0f){
            Write.OutText(""+text+""+ variable.getName() +" = "+variable.getValue());
            textoEjecucion+=""+text+""+ variable.getName() +" = "+variable.getValue()+"\n\n";
        }
        else{
            Write.OutText(""+text);
            textoEjecucion+=""+text+"\n\n";
        }
    }
    
    public String textEjecucion()
    {
        return textoEjecucion;
    }
}
