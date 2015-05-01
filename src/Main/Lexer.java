/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Jorge
 */
import Tokens.TablaVariables;
import java.awt.List;
import java.util.ArrayList;

import FilesManagement.ReadFile;
import Tokens.Token;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import parse.ParseMetodos;
import java.util.Hashtable;

public class Lexer{

    private String[] source;
    ArrayList<String> datos= new ArrayList();
    String datos1="";
    private String[][] src;
    private ReadFile file;
    private String  iden="IDENTIFICADOR";
    private Almacenar almacena;
    ArrayList<Token> tokenList = new ArrayList();    
    Token token = new Token(null,null);
    private JTextArea textAreaTokens;
    private JLabel resultado;
    ParseMetodos parse;
    private boolean flag = false;
    private Hashtable<String,TablaVariables> variables = new  Hashtable<String, TablaVariables>();
    private String info;
	
	
    public Lexer(String[] source){
            this.source = source;
    }

    public Lexer(String[][] src){
            this.src = src;
    }

    public void initLexer(){		
        String[] contentLines = getContentTextFile().split("\n");
        tokenList=(ArrayList<Token>) token.tokenize(src,contentLines);;          
    }

    public void showTokens(JTextArea textAreaTokens) {
        int j=0;
        for(int i=0; i<tokenList.size(); i++){
            textAreaTokens.append(i+"	Token: "+tokenList.get(i).getText()+ "		Tipo: "+tokenList.get(i).getToken()+"\n");
            if(tokenList.get(i).getToken()==iden){
                datos1=Integer.toString(i)+" TOken: "+tokenList.get(i).getText()+"\n";
                info=tokenList.get(i).getText();
                datos.add(datos1);
                j++;
            }
        }
    }

    public ArrayList getTokens(){
        return tokenList;
    }
    
    public String getContentTextFile(){
        return file.getContentTextFile();
    }


    public String guardar(){
        datos1="";
        for(int i=0; i<datos.size(); i++)
            datos1+=datos.get(i);
        return datos1;
    }

    public void fileUsed(ReadFile file, JTextArea textAreaTokens){
        this.file = file;
        this.textAreaTokens=textAreaTokens;
    }

    public void showParse(JLabel resultado) {
        flag = parse.evaluarParser();
        if(flag){
            resultado.setText("Exito en el programa");
        }else{
            resultado.setText("Error en el programa");
        }
    }
}
 
