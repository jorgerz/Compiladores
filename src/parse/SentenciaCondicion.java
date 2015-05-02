/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Main.Write;
import Tokens.Variable;
import java.util.ArrayList;

/**
 *
 * @author adriel1019
 */
public class SentenciaCondicion {
    
    private ArrayList<Variable> vars;    
    private ArrayList<Float> floats;
    private String operator;
    private float value1 = 0.0f, value2 = 0.0f;
    private int type,c;
    private int nextInstruction, nextS, alternativeS;    
    public SentenciaCondicion(ArrayList<Variable> vars, ArrayList<Float> floats, String operator){
        this.vars = vars;        
        this.floats = floats;
        this.operator = operator;
    }
    
    public void execute(){                
        
        setValues();
        evaluateCondition();
    }
    
    private void evaluateCondition(){
        Write.OutText("Condition: "+value1+" "+ operator+" "+value2);
        if(operator.equals("<")){            
            if(value1 < value2)
                nextInstruction = nextS;
            else
                nextInstruction = alternativeS;            
        }
        else if(operator.equals("<=")){
            if(value1 <= value2)
                nextInstruction = nextS;
            else
                nextInstruction = alternativeS;
        }
        else if(operator.equals(">")){
            if(value1 > value2)
                nextInstruction = nextS;
            else
                nextInstruction = alternativeS;
        }
        else if(operator.equals(">=")){
            if(value1 >= value2)
                nextInstruction = nextS;
            else
                nextInstruction = alternativeS;
        }
        else if(operator.equals("==")){
            if(value1 == value2)
                nextInstruction = nextS;
            else
                nextInstruction = alternativeS;
        }
        else if(operator.equals("!=")){
            if(value1 != value2)
                nextInstruction = nextS;
            else
                nextInstruction = alternativeS;
        }
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
        
        if(vars.get(0) == null && vars.get(1) == null){      
            c=0;
            value1 = floats.get(0);
            value2 = floats.get(1);
        }
        else if(vars.get(0) != null && vars.get(1) == null){            
            c=1;
            value1 = vars.get(0).getValue();
            value2 = floats.get(1);
        }
        else if(vars.get(0) == null && vars.get(1) != null){            
            c=2;
            value1 = floats.get(0);
            value2 = vars.get(1).getValue();
        }
        else if(vars.get(0) != null && vars.get(1) != null){            
            c=3;
            value1 = vars.get(0).getValue();
            value2 = vars.get(1).getValue();
        }
    }

    public void posibleJumps(int nextS, int alternativeS) {
        this.nextS = nextS;
        this.alternativeS = alternativeS;
    }
}
