/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

import Main.Write;
import java.util.ArrayList;
import parse.*;

/**
 *
 * @author adriel1019
 */
public class ListStatement {

    ArrayList<Object> statements;
    public ListStatement(ArrayList<Object> statements){
        this.statements = statements;
    }
    
    public void executeProgram(){
        int i = 0;
        int instruction = 0;
        Statement actualStatement;
        boolean startLoop = false;
        System.out.println("Tamano del programa: "+statements.size());
        Write.OutText("Sentencias del programa");
        while( i < statements.size()){  
            actualStatement = (Statement) statements.get(i);
            if(actualStatement.isSentenciaAsignacionSimple()){             
                Write.OutText("SentenciaAsignacionSimple");
            }
            else if(actualStatement.isSentenciaAsignacionOperacion()){
                Write.OutText("SentenciaAsignacionOperacion");
            }
            else if(actualStatement.isSentenciaCondicion()){          
                Write.OutText("SentenciaCondicion");
            }            
            else if(actualStatement.isSentenciaEscribir()){
                Write.OutText("SentenciaEscribir");
            }
            else if(actualStatement.isSentenciaLeer()){
                Write.OutText("SentenciaLeer");
            }
            Write.OutText("Instruction "+ actualStatement.getIndexS()+
                        ", nextS = "+actualStatement.getNextS()+", alternative = "+ actualStatement.getAlternativeS() );
            i++;
        }
        Write.OutText("Ejecucion del programa");
        i = 0;
        while( i < statements.size()){  
            actualStatement = (Statement) statements.get(i);
            if(actualStatement.isSentenciaAsignacionSimple()){
                SentenciaAsignacionSimple sas = (SentenciaAsignacionSimple)actualStatement.getStatement();                               
                sas.execute();                
                i = actualStatement.getNextS();
            }
            else if(actualStatement.isSentenciaAsignacionOperacion()){
                SentenciaAsignacionOperacion sao = (SentenciaAsignacionOperacion)actualStatement.getStatement();                
//                System.out.println("SentenciaAsignacionOperacion");
//                System.out.println("Instruccion: " + i);        
                sao.execute();
                i = actualStatement.getNextS();
            }
            else if(actualStatement.isSentenciaCondicion()){                
                SentenciaCondicion sc = (SentenciaCondicion)actualStatement.getStatement();
                sc.posibleJumps(actualStatement.getNextS(),actualStatement.getAlternativeS());                
//                System.out.println("SentenciaAsignacionCondicion");
//                System.out.println("Instruccion: " + i);        
                sc.execute();
                i = sc.next();                
            }
            else if(actualStatement.isSentenciaEscribir()){
                SentenciaEscribir se = (SentenciaEscribir)actualStatement.getStatement();                                
                se.setText("Escribir: ");
//                System.out.println("SentenciaEscribir");
//                System.out.println("Instruccion: " + i);        
                se.execute();
                i = actualStatement.getNextS();
            }
            else if(actualStatement.isSentenciaLeer()){
                SentenciaLeer sl = (SentenciaLeer)actualStatement.getStatement();                
//                System.out.println("SentenciaLeer");
//                System.out.println("Instruccion: " + i);        
                sl.execute();                
                i = actualStatement.getNextS();
            }       
        }                
    }
}
