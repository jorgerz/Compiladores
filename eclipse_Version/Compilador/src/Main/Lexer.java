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
import java.awt.List;
import java.util.ArrayList;

import FilesManagement.ReadFile;
import Tokens.Token;
import javax.swing.JTextArea;

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
	
	
    public Lexer(String[] source){
            this.source = source;
    }

    public Lexer(String[][] src){
            this.src = src;
    }

    public void initLexer(){		
//		tokenList=(ArrayList<Token>) token.tokenize(source);
            String[] contentLines = getContentTextFile().split("\n");
            tokenList=(ArrayList<Token>) token.tokenize(src,contentLines);		
            showTokens();
    }

    private void showTokens() {
        int j=0;
        for(int i=0; i<tokenList.size(); i++){
            //System.out.println(i+"	Token: "+tokenList.get(i).getText()+ "		Tipo: "+tokenList.get(i).getToken());
            //almacena=new Almacenar(i,tokenList.get(i).getText(),tokenList.get(i).getToken());
            textAreaTokens.append(i+"	Token: "+tokenList.get(i).getText()+ "		Tipo: "+tokenList.get(i).getToken()+"\n");
            if(tokenList.get(i).getToken()==iden){
                datos1=Integer.toString(i)+" TOken: "+tokenList.get(i).getText()+"\n";
                //+"   "+"Tipo: "+tokenList.get(i).getToken()
                System.out.println(datos1);
                datos.add(datos1);
                j++;
            }
        }
    }

    public String getContentTextFile(){
        return file.getContentTextFile();
    }

    public void fileUsed(ReadFile file,JTextArea textAreaTokens){
        this.file = file;
        this.textAreaTokens=textAreaTokens;
    }
    public String guardar(){
        datos1="";
        for(int i=0; i<datos.size(); i++)
            datos1+=datos.get(i);
        return datos1;
    }
}
 
