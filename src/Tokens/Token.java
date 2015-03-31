package Tokens;

import java.util.*;

public class Token implements ValidTokens{
	
	List<Token> tokens;
	public String text;
	public TokenType type;
	
	public Token( String text, TokenType type ){
		this.text = text;
		this.type = type;
	}
	
	private List<Token> tokenize(String[] source){
		
		tokens = new ArrayList<Token>();
		boolean found;
		String str;
		for( int i = 0; i < source.length; i++ ){
			found = false;
			for( int j = 0; j < stringTokens.length; j++ ){
				if( source[i].equals( stringTokens[j] ) ){
					tokens.add( new Token( stringTokens[j], tokenType[0][i] ));
					found = true;
					break;
				}
			}
			if(found == false){
				str = source[i];
				
			}
		}
		
		
		return tokens;
	}
}
