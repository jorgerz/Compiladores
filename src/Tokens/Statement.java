/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

import parse.*;

/**
 *
 * @author adriel1019
 */
public class Statement {
  
    private int indexS, nextS, alternativeS; //S de Statement
    private Object statement;
    public Statement(Object statement){
        this.statement = statement;        
    }
    
    public Statement(int indexS, Object statement, int nextS){
        this.statement = statement;      
        this.indexS = indexS;
        this.nextS = nextS;
    }
    
    public Statement(int indexS, Object statement, int nextS, int alternativeS){
        this.statement = statement;      
        this.indexS = indexS;        
        this.nextS = nextS;
        this.alternativeS = alternativeS;
    }
    
    public void setNextS(int nextS){        
        this.nextS = nextS;
    }
    
    public void addAlternativeS(int alternativeS){        
        this.alternativeS = alternativeS;
    }
        
    public boolean isSentenciaAsignacionSimple(){
        return (statement instanceof SentenciaAsignacionSimple);
    }
    
    public boolean isSentenciaAsignacionOperacion(){
        return (statement instanceof SentenciaAsignacionOperacion);
    }
    
    public boolean isSentenciaCondicion(){
        return (statement instanceof SentenciaCondicion);
    }
    
    public boolean isSentenciaEscribir(){
        return (statement instanceof SentenciaEscribir);
    }
    
    public boolean isSentenciaLeer(){
        return (statement instanceof SentenciaLeer);
    }
    
    public void setStatement(Object statement){
        this.statement = statement;
    }
    
    public Object getStatement(){
        return statement;
    }
    
    public int getIndexS(){
        return indexS;
    }
    
    public int getNextS(){
        return nextS;
    }
    
    public int getAlternativeS(){
        return alternativeS;
    }
}
