/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Tokens.Variable;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class SentenciaRepite {
    private Variable variable;
    private float _float;
    private float value1 = 0.0f, value2 = 0.0f;
    private int type,c;
    private int nextInstruction, nextS, alternativeS;    
    private String textoEjecucion="";
    private static float counter;
    
    public SentenciaRepite(Variable variable, Float _float) {
        this.variable = variable;
        this._float = _float;
        counter = 0.0f;
    }        
    
    public int next(){
        return nextInstruction;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public int getType(){
        return type;
    }
    
    private void setValues(){
        
    }
    
    public void execute(){
        
        if(_float != -1.0f){            
            if(counter <_float){                
                textoEjecucion = "Repetido: "+counter+" veces\n";
                counter++;
                nextInstruction = nextS;
            }
            else if(counter == _float){
                textoEjecucion = "";
                nextInstruction = alternativeS;
                counter = 0;
            }
        }
        else{
            if(counter < variable.getValue()){
                counter++;
                nextInstruction = nextS;
            }
            else if(counter == variable.getValue()){
                nextInstruction = alternativeS;
                counter = 0;
            }
        }                
    }

    public void posibleJumps(int nextS, int alternativeS) {
        this.nextS = nextS;
        this.alternativeS = alternativeS;
    }
    
    public String textEjecucion()
    {
        return textoEjecucion;
    }
    
}
