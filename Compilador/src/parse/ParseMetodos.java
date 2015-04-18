/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Tokens.Token;
import Tokens.TokenType;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class ParseMetodos {
    private List<Token> tokens;
    private TokenType tokenType;
    private int contador;
    public ParseMetodos(List<Token> tokens){
        this.tokens = tokens;
        contador = 0;
    }
    
    public boolean evaluarParser(){
        return programa();
    }
    
    private boolean programa(){
        if(inicioPrograma()){
            if(sentencia()){
                if(finPrograma()){
                    return true;
               
                }
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;  // poner un mensaje de error
    }
    
    private boolean inicioPrograma(){
        if(tokens.get(contador).type==tokenType.INICIO_PROGRAMA){
            contador++;
            return true;
        }
       return false;
    }
    private boolean secuencia(){
        if(secuencia()){
            if(secuencia()){
                return true;
            }
        }
        else {
            if(tokens.get(contador).type==tokenType.FIN_PROGRAMA)
                return true;
        }
        return false;
    }
    
    private boolean sentencia(){
        if(senteciaAsignacionSimples() || sentenciaAsignacionOperacion() || senteciaLeer() || sentenciaEscribir()|| sentenciaSi() || sentenciaMientras()){
            return true;
        } 
        return false;
    }
    
    private boolean sentenciaAsignacionSimple(){
        int temp = contador;
        
        if(tokens.get(contador).type==tokenType.IDENTIFICADOR){
            contador++;
            if(tokens.get(contador).type==tokenType.ASIGNACION){
                contador++;
                if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
                    contador++;
                    return true;
                }
            }
        }
        contador=temp;
        return false;
    }
    
    private boolean finPrograma(){
        if(tokens.get(contador).type==tokenType.FIN_PROGRAMA)
            return true;
        return false;
    }

    private boolean senteciaAsignacionSimples() {
        if(tokens.get(contador).type==tokenType.ASIGNACION)
            return true;
        return false;
    }

    private boolean sentenciaAsignacionOperacion() {
        if(tokens.get(contador).type==tokenType.ASIGNACION || tokens.get(contador).type==tokenType.OPERADOR_RELACIONAL || tokens.get(contador).type==tokenType.OPERADOR_ARITMETICO)
            return true;
        return false;
    }

    private boolean senteciaLeer() {
        if(tokens.get(contador).type==tokenType.LEER ){
            contador++;
            if(tokens.get(contador).type==tokenType.IDENTIFICADOR){    
                return true;
            }
        }
        return false;
    }

    private boolean sentenciaEscribir() {
        if(tokens.get(contador).type==tokenType.ESCRIBIR ){
            contador++;
            if(tokens.get(contador).type==tokenType.IDENTIFICADOR){    
                return true;
            }
        }
        return false;
    }

    private boolean sentenciaSi() {
        if(tokens.get(contador).type==tokenType.SI){
            contador++;
            if()
                return true;
        }
        return false;
    }
    
    private boolean condicion(){
        if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
            contador++;
            if(tokens.get(contador).type==tokenType.OPERADOR_RELACIONAL){
                contador++;
                if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE)
                    return true;
            }
        }
        return false;
    }
    

    private boolean sentenciaMientras() {
        if(tokens.get(contador).type==tokenType.MIENTRAS){
            contador++;
            if(tokens.get(contador).type==tokenType.ENTONCES){
                contador++;
                if(sentencia())
                    return true;
            }
        }
        return false;
    }
}
