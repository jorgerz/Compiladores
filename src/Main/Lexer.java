package Main;

import java.awt.List;
import java.util.ArrayList;

import FilesManagement.ReadFile;
import Tokens.Token;

public class Lexer{

	private String[] source;		
	ArrayList<Token> tokenList = new ArrayList();
	Token token = new Token(null,null);
	
	
	public Lexer(String[] source){
		this.source = source;
	}
	
	public void initLexer(){		
		tokenList=(ArrayList<Token>) token.tokenize(source);		
		showTokens();
	}

	private void showTokens() {
		for(int i=0; i<tokenList.size(); i++){
			System.out.println(i+"	Token: "+tokenList.get(i).getText()+ "		Tipo: "+tokenList.get(i).getToken());
		}
	}
}
 