/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

import Main.Write;
import java.util.ArrayList;
import javax.swing.JTextArea;
import parse.*;

/**
 *
 * @author adriel1019
 */
public class ListStatement {

    ArrayList<Object> statements;
    private JTextArea textAreaTokens;
    String mensajesEjecucion="";
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
        mensajesEjecucion+="Sentencias del programa"+"\n\n";
        
        while( i < statements.size()){  
            actualStatement = (Statement) statements.get(i);
            if(actualStatement.isSentenciaAsignacionSimple()){             
                Write.OutText("SentenciaAsignacionSimple");
                 mensajesEjecucion+="SentenciaAsignacionSimple"+"\n";
            }
            else if(actualStatement.isSentenciaAsignacionOperacion()){
                Write.OutText("SentenciaAsignacionOperacion");
                mensajesEjecucion+="SentenciaAsignacionOperacion"+"\n";
            }
            else if(actualStatement.isSentenciaCondicion()){          
                Write.OutText("SentenciaCondicion");
                mensajesEjecucion+="SentenciaCondicion"+"\n";
            }            
            else if(actualStatement.isSentenciaEscribir()){
                Write.OutText("SentenciaEscribir");
                mensajesEjecucion+="SentenciaEscribir"+"\n";
                
            }
            else if(actualStatement.isSentenciaLeer()){
                Write.OutText("SentenciaLeer");
                mensajesEjecucion+="SentenciaLeer"+"\n";
            }
            Write.OutText("Instruction "+ actualStatement.getIndexS()+
                        ", nextS = "+actualStatement.getNextS()+", alternative = "+ actualStatement.getAlternativeS() );
            mensajesEjecucion+="Instruction "+ actualStatement.getIndexS()+
                        ", nextS = "+actualStatement.getNextS()+", alternative = "+ actualStatement.getAlternativeS()+"\n\n";
            i++;
        }
        textAreaTokens.setText(mensajesEjecucion);
        Write.OutText("Ejecucion del programa");
        mensajesEjecucion+="\nEjecucion del programa"+"\n\n";
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
                sao.execute();
                i = actualStatement.getNextS();                
            }
            else if(actualStatement.isSentenciaCondicion()){                
                SentenciaCondicion sc = (SentenciaCondicion)actualStatement.getStatement();
                sc.posibleJumps(actualStatement.getNextS(),actualStatement.getAlternativeS());                
                sc.execute();
                mensajesEjecucion+=sc.textEjecucion();
                i = sc.next();
               
            }
            else if(actualStatement.isSentenciaEscribir()){
                SentenciaEscribir se = (SentenciaEscribir)actualStatement.getStatement();                                
                se.setText("Escribir: ");
                se.execute();
                mensajesEjecucion+=se.textEjecucion();
                i = actualStatement.getNextS();
            }
            else if(actualStatement.isSentenciaLeer()){
                SentenciaLeer sl = (SentenciaLeer)actualStatement.getStatement();                
                sl.setText("Introduzca el valor de: "+sl.getVariable().getName());
                sl.execute();
                mensajesEjecucion+=sl.textEjecucion();
                i = actualStatement.getNextS();
            }
            else if(actualStatement.isSentenciaRepite()){
                SentenciaRepite sr = (SentenciaRepite)actualStatement.getStatement();
                sr.posibleJumps(actualStatement.getNextS(), actualStatement.getAlternativeS());
                sr.execute();
                i = sr.next();
                mensajesEjecucion+=sr.textEjecucion();
            }
            textAreaTokens.setText(mensajesEjecucion);
        }                
    }

    public void showEjecucion(JTextArea textAreaTokens) {
        this.textAreaTokens=textAreaTokens;
        
    }
}
