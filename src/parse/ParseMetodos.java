/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Tokens.Stack;
import Tokens.Statement;
import Tokens.TokenType;
import Tokens.Token;
import java.util.ArrayList;
import javax.swing.JLabel;
import Tokens.TablaVariables;
import Tokens.Variable;

/**
 *
 * @author Jorge
 */
public class ParseMetodos {
    private ArrayList<Token> tokens;
    private ArrayList<Object> statements;
    private TokenType tokenType;
    private int contador;
    private boolean flag;
    private TablaVariables varsTable;
    private Stack stackSiMientras;
    private Stack stackInstructions;
    private int checkSiMientras;
    private int instructionNumber;

    public ParseMetodos(ArrayList<Token> tokens) {
        this.tokens = tokens;
        contador = 0;
        flag = false;
        statements = new ArrayList<Object>();
    }
    
    public ParseMetodos(ArrayList<Token> tokens, TablaVariables varsTable) {
        this.tokens = tokens;
        this.varsTable = varsTable;
        contador = 0;
        flag = false;
        statements = new ArrayList<Object>();
        stackSiMientras = new Stack();
        stackInstructions = new Stack();
        checkSiMientras = 0;
        instructionNumber = 0;
    }
    
    public boolean evaluarParser(){
        //System.out.println("inicio "+tokens.get(contador).type.toString());
        System.out.println("iniciar: "+tokens.get(contador).text);
        return programa();
    }
    
    public boolean evaluate(){
        return programa();
    }
    
    public void showResults(JLabel result){
        flag = evaluate();
        if(flag){
            result.setText("Exito en el programa");
        }else{
            result.setText("Error en el programa");
        }
    }
    
    private boolean programa(){
        if(inicioPrograma()){
            if(sentencias()){
                if(finPrograma()){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            } 
        }
        else{
            return false;  // poner un mensaje de error
        }  
    }
    
    private boolean inicioPrograma(){
        int temp=contador;
       
        if(tokens.get(contador).type == TokenType.INICIO_PROGRAMA){
            contador++;
            return true;
        }
        contador=temp;
       return false;
    }
    
    private boolean sentencias(){
	if(sentencia()){
            if(sentencias()){
                    return true;
            }else{
                if(tokens.get(contador).type==TokenType.FIN_PROGRAMA){
                        return true;
                }
            }
        }
        return false;
    }
    
    private boolean sentenciasBloque(){
	if(sentencia()){
            if(sentenciasBloque()){
                    return true;
            }else{
                if(tokens.get(contador).type==tokenType.FIN){
                    int valueSiMientras = stackSiMientras.pop();
                    int pointerInstruction = stackInstructions.pop();                    
                    Statement condition = (Statement) statements.get(pointerInstruction);                        
                    condition.addAlternativeS(instructionNumber);       
                    SentenciaCondicion sc = (SentenciaCondicion)condition.getStatement();
                    sc.setType(valueSiMientras);
                    condition.setStatement(sc);
                    statements.add(pointerInstruction, condition);
                    return true;
                }
            }
        }
        return false;
    }
    

    
    private boolean sentencia(){
        return sentenciaAsignacionSimple() || sentenciaAsignacionOperacion() || senteciaLeer() || sentenciaEscribir()|| sentenciaSi() || sentenciaMientras();
    }
    
    private boolean sentenciaAsignacionSimple(){
        int temp = contador;
        float value;
        int posSource, posDestiny;
        boolean foundId;
        if(tokens.get(contador).type==tokenType.IDENTIFICADOR){
            contador++;
            if(tokens.get(contador).type==tokenType.IGUAL){
                contador++;
                if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
                    contador++;
                    if(tokens.get(contador).type==tokenType.OPERADOR_ARITMETICO){
                        contador=temp;
                        return false;
                    }else{
                        if(tokens.get(contador).type==tokenType.IDENTIFICADOR){
                            posDestiny = varsTable.indexVariableName(tokens.get(contador-3).text);
                            posSource = varsTable.indexVariableName(tokens.get(contador-1).text);                           
                            statements.add( new Statement( instructionNumber, 
                                    new SentenciaAsignacionSimple( varsTable.getVariables().get(posDestiny), 
                                            varsTable.getVariables().get(posSource) ), 
                                    instructionNumber+1, ++instructionNumber ) );
                        }
                        else{                        
                            posDestiny = varsTable.indexVariableName(tokens.get(contador-3).text);
                            statements.add( new Statement( instructionNumber, 
                                    new SentenciaAsignacionSimple( varsTable.getVariables().get(posDestiny), 
                                            Float.parseFloat(tokens.get(contador-1).text) ), 
                                    instructionNumber+1, ++instructionNumber ) );
                        }
                        return true;
                    }
                }
            }
        }
        contador=temp;
        return false;
    }
    
    private boolean finPrograma(){
        return tokens.get(contador).type==tokenType.FIN_PROGRAMA;
    }

    private boolean sentenciaAsignacionOperacion(){
        int temp = contador;        
        ArrayList<Variable> vars = new ArrayList<Variable>();
        ArrayList<String> operators = new ArrayList<String>();
        ArrayList<Float> floats = new ArrayList<Float>();
        int c = 0, i=0, pos;        
        boolean foundId;
        if(tokens.get(contador).type==tokenType.IDENTIFICADOR){
            contador++;
            if(tokens.get(contador).type==tokenType.IGUAL){
                contador++;
                if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
                    contador++;
                    if(tokens.get(contador).type==tokenType.OPERADOR_ARITMETICO){
                        contador++;                        
                        if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
                            i = contador - 2;                            
                            while(c < 3){
                                if(c!=1){
                                    if(tokens.get(i).type==tokenType.IDENTIFICADOR){                 
                                        pos = varsTable.indexVariableName(tokens.get(i).text);
                                        vars.add(varsTable.getVariables().get(pos));
                                        floats.add( -1.0f );                                                                                
                                    }
                                    else{                   
                                        vars.add( null );
                                        floats.add( Float.parseFloat(tokens.get(i).text) );
                                    }
                                }
                                else{
                                    operators.add(tokens.get(i).text);
                                }
                                i++;
                                c++;
                            }                
                            pos = varsTable.indexVariableName(tokens.get(contador-4).text);                            
                            statements.add( new Statement( instructionNumber, 
                                    new SentenciaAsignacionOperacion(varsTable.getVariables().get(pos),vars,floats,operators), 
                                    instructionNumber+1, ++instructionNumber ) );
                            contador++;
                            return true;
                        }
                    }
                }
            }        
        }
        contador=temp;
        return false;
    }
    
    private boolean senteciaLeer() {
        int temp=contador, pos;
        if(tokens.get(contador).type==tokenType.LEER ){
            contador++;
            if(tokens.get(contador).type==tokenType.IDENTIFICADOR){
                pos = varsTable.indexVariableName(tokens.get(contador).text);
                statements.add( new Statement( instructionNumber, 
                        new SentenciaLeer(varsTable.getVariables().get(pos)), 
                        instructionNumber+1, ++instructionNumber ));
                contador++;
                return true;
            }
        }
        contador=temp;
        return false;
    }

    private boolean sentenciaEscribir() {
        int temp=contador, pos;
        if(tokens.get(contador).type==tokenType.ESCRIBIR ){
            contador++;
            if(tokens.get(contador).type==tokenType.IDENTIFICADOR){
                pos = varsTable.indexVariableName(tokens.get(contador).text);
                statements.add( new Statement( instructionNumber, 
                        new SentenciaEscribir(varsTable.getVariables().get(pos)),
                        instructionNumber+1, ++instructionNumber ));
                contador++;
                return true;
            }
        }
        contador=temp;
        return false;
    }

    private boolean sentenciaSi() {
        int temp=contador;
        if(tokens.get(contador).type==tokenType.SI){
            contador++;
            if(condicion()){
                contador++; 
                if(tokens.get(contador).type==tokenType.ENTONCES){
                    contador++;
                    if(tokens.get(contador).type==tokenType.INICIO){
                        contador++;
                        stackSiMientras.push(1);
                        stackInstructions.push(instructionNumber-1);                            
                        if(sentenciasBloque()){                            
                            contador++;
                            return true;
                        }
                    }
                    else if(sentencias()){
                        return true;
                    }
                }
            }
        }
        contador=temp;
        return false;
    }
    
    private boolean condicion(){
        int temp=contador;
        int c = 0, i=0, pos;        
        boolean foundId;
        ArrayList<Variable> vars = new ArrayList<Variable>();
        ArrayList<Float> floats = new ArrayList<Float>();
        String operator = "";
        if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
            contador++;
            if(tokens.get(contador).type==tokenType.OPERADOR_RELACIONAL){
                contador++;
                if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
                    i = contador - 2;
                    while(c<3){
                        if(c!=1){                            
                            if(tokens.get(i).type==tokenType.IDENTIFICADOR){
                                pos = varsTable.indexVariableName(tokens.get(i).text);
                                vars.add( varsTable.getVariables().get(pos));
                                floats.add( -1.0f);
                            }
                            else{// if(tokens.get(i).type==tokenType.FLOTANTE){
                                vars.add(null);
                                floats.add(Float.parseFloat(tokens.get(i).text));
                                //floats.add(Float.parseFloat("25"));
                            }
                        }
                        else{
                            operator = tokens.get(i).text;
                        }
                        i++;
                        c++;
                    }
                    statements.add( new Statement( instructionNumber, new SentenciaCondicion(vars,floats,operator), ++instructionNumber ));                    
                    return true;
                }    
                    
            }
        }
        contador=temp;
        return false;
    }
  
    private boolean sentenciaMientras() {
        int temp = contador;
        
        if(tokens.get(contador).type==tokenType.MIENTRAS){
            contador++;
            if(condicion()){
                contador++;
                
                if(tokens.get(contador).type==tokenType.INICIO){
                    contador++;
                    stackSiMientras.push(2);
                    stackInstructions.push(instructionNumber-1);
                    if(sentenciasBloque()){                        
                        contador++;
                        return true;
                    }
                }
                if(sentencias()){
                    return true;
                }                
            }     
        }
        contador= temp;
        return false;
    }
    
    public ArrayList<Object> getAllStatements(){
        return statements;
    }
}
