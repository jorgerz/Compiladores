/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

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
        while( i < statements.size()){  
            if(instruction != -1)
                i = instruction;            
            actualStatement = (Statement) statements.get(i);
            instruction = -1;
            if(actualStatement.isSentenciaAsignacionSimple()){
                SentenciaAsignacionSimple sas = (SentenciaAsignacionSimple)actualStatement.getStatement();
                sas.execute();
            }
            else if(actualStatement.isSentenciaAsignacionOperacion()){
                SentenciaAsignacionOperacion sao = (SentenciaAsignacionOperacion)actualStatement.getStatement();
                sao.execute();
            }
            else if(actualStatement.isSentenciaCondicion()){                
                SentenciaCondicion sc = (SentenciaCondicion)actualStatement.getStatement();
                sc.posibleJumps(actualStatement.getNextS(),actualStatement.getAlternativeS());
                sc.execute();
                instruction = sc.next();
                
            }
            else if(actualStatement.isSentenciaEscribir()){
                SentenciaEscribir se = (SentenciaEscribir)actualStatement.getStatement();
                se.execute();
            }
            else if(actualStatement.isSentenciaLeer()){
                SentenciaLeer sl = (SentenciaLeer)actualStatement.getStatement();
                sl.execute();
            }
            i++;
        }
    }
}
