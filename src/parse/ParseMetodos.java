/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import Tokens.Token;
import Tokens.TokenType;
import java.util.List;
import Tokens.Token;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Jorge
 */
public class ParseMetodos {
    private List<Token> tokens;
    private TokenType tokenType;
    private int contador;

    public ParseMetodos(ArrayList<Token> tokenList) {
        this.tokens = tokens;
        contador = 0;
    }
    
    public boolean evaluarParser(){
        return programa();
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
        if(tokens.get(contador).type == tokenType.INICIO_PROGRAMA){
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
                if(tokens.get(contador).type==tokenType.FIN_PROGRAMA){
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
                   return true;
                }
            }
        }
        return false;
    }
    

    
    private boolean sentencia(){
        if(sentenciaAsignacionSimple() || sentenciaAsignacionOperacion() || senteciaLeer() || sentenciaEscribir()|| sentenciaSi() || sentenciaMientras()){
            return true;
        }
        return false;
    }
    
    private boolean sentenciaAsignacionSimple(){
        int temp = contador;
        
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
                        return true;
                    }
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

    private  boolean sentenciaAsignacionOperacion(){
        int temp = contador;
        if(tokens.get(contador).type==tokenType.IDENTIFICADOR){
            contador++;
            if(tokens.get(contador).type==tokenType.IGUAL){
                contador++;
                if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
                    contador++;
                    if(tokens.get(contador).type==tokenType.OPERADOR_ARITMETICO){
                        contador++;
                        if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
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
        int temp=contador;
        if(tokens.get(contador).type==tokenType.LEER ){
            contador++;
            if(tokens.get(contador).type==tokenType.IDENTIFICADOR){
                contador++;
                return true;
            }
        }
        contador=temp;
        return false;
    }

    private boolean sentenciaEscribir() {
        int temp=contador;
        if(tokens.get(contador).type==tokenType.ESCRIBIR ){
            contador++;
            if(tokens.get(contador).type==tokenType.IDENTIFICADOR){
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
        }
        contador=temp;
        return false;
    }
    
    private boolean condicion(){
        int temp=contador;
        if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE){
            contador++;
            if(tokens.get(contador).type==tokenType.OPERADOR_RELACIONAL){
                contador++;
                if(tokens.get(contador).type==tokenType.IDENTIFICADOR || tokens.get(contador).type==tokenType.FLOTANTE)
                    return true;
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
                if(tokens.get(contador).type==tokenType.ENTONCES){
                   contador++;
                    if(tokens.get(contador).type==tokenType.INICIO){
                        contador++;
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
        }
        contador= temp;
        return false;
    }
}
