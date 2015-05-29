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
    private final ArrayList<Variable> vars;
    private final ArrayList<Float> floats;
    private float value1 = 0.0f, value2 = 0.0f;
    private int type,c;
    private int nextInstruction, nextS, alternativeS;    
    private String textoEjecucion="";
    
    SentenciaRepite(ArrayList<Variable> vars, ArrayList<Float> floats) {
        this.vars=vars;
        this.floats=floats;
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
        
        if(vars.get(0) == null){      
            c=0;
            value1 = floats.get(0);
        }
        else if(vars.get(0) != null){            
            c=1;
            value1 = vars.get(0).getValue();
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
