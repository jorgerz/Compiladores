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
public class SentenciaAsignacionOperacion {
 
    private float variable;
    private ArrayList<Variable> vars;
    private ArrayList<String> operators;
    private ArrayList<Float> floats;
    private Variable destiny;
    private boolean flag;
    
    public SentenciaAsignacionOperacion(Variable destiny, ArrayList<Variable> vars, ArrayList<Float> floats, ArrayList<String> operators){
        this.vars = vars;
        this.destiny = destiny;        
        this.operators = operators;
        this.floats = floats;
        flag = true;
    }        
    
    public void execute(){
        assignValue();
        Write.OutText(destiny.getName()+" = "+destiny.getValue());
    }
        
    private void assignValue(){
        int c = 0;
        float value1 = 0.0f, value2 = 0.0f;
        
        if(vars.get(0) == null && vars.get(1) == null){            
            value1 = floats.get(0);
            value2 = floats.get(1);
        }
        else if(vars.get(0) != null && vars.get(1) == null){            
            value1 = vars.get(0).getValue();
            value2 = floats.get(1);
//            Write.OutText(vars.get(0).getName()+": "+value1+" float: "+value2);
        }
        else if(vars.get(0) == null && vars.get(1) != null){            
            value1 = floats.get(0);
            value2 = vars.get(1).getValue();
        }
        else if(vars.get(0) != null && vars.get(1) != null){            
            value1 = vars.get(0).getValue();
            value2 = vars.get(1).getValue();
        }
               
        if(operators.get(0).equals("+")){
            destiny.addValue( value1 + value2 );
        } 
        else if(operators.get(0).equals("-")){
            destiny.addValue( value1 - value2 );
        }
        else if(operators.get(0).equals("/")){
            destiny.addValue( value1 / value2 );
        }
        else if(operators.get(0).equals("*")){
            destiny.addValue( value1 * value2 );
        }
        else if(operators.get(0).equals("%")){
            destiny.addValue( value1 % value2 );
        }        
        
    }               
}
