/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

/**
 *
 * @author Jorge
 */
import java.util.*;

public class Token implements ValidTokens{
	
	ArrayList<Token> tokens;
	public String text;
	public TokenType type;
	boolean flag = false;
	public String comment = ""; 
	
	public Token( String text, TokenType type ){
		this.text = text;
		this.type = type;
	}
	
	public List<Token> tokenize(String[] source){
		System.out.println("Si llego a list");
		tokens = new ArrayList<Token>();
		TokenizeState state = TokenizeState.DEFAULT;
		String token = "";
		boolean flag = false;
		
		boolean found;
		String str;
		for( int i = 0; i < source.length; i++ ){			
			found = false;
			for( int j = 0; j < stringTokens.length; j++ ){
				if( source[i].equals( stringTokens[j] ) ){
					tokens.add( new Token( source[i], tokenType[0][j] ));
					found = true;					
					break;
				}
			}	
			if( found == false)
			{						
				str = source[i];
				addSingleToken(str);				
			}
		}
		
		return tokens;
	}
	
	public List<Token> tokenize(String[][] source, String[] contentLines){
		tokens = new ArrayList<Token>();
		TokenizeState state = TokenizeState.DEFAULT;
		String token = "";
		
		
		boolean found;
		String str;
		for( int k = 0; k < source.length; k++ ){
			flag = false;
			for( int i = 0; i < source[k].length; i++ ){			
				found = false;					
				for( int j = 0; j < stringTokens.length; j++ ){
					if( source[k][i].equals( stringTokens[j] ) ){
						tokens.add( new Token( source[k][i], tokenType[0][j] ));
						found = true;					
						break;
					}
					
				}					
				if( found == false)
				{						
					str = source[k][i];
					addSingleToken(str);					
				}
				if(flag == true && i == source[k].length - 1){
					comment = contentLines[k];
					tokens.add(new Token(comment, TokenType.COMENTARIO));                
					comment = "";					
				}
			}
			
		}
		
		
		return tokens;
	}
	
	private void addSingleToken(String source){		
		String token = "";
		boolean foundDot = false;
		System.out.println(source);
		boolean out = false;
		TokenizeState state = TokenizeState.DEFAULT;
		for(int i =0;i<source.length();i++){			           
			char c = source.charAt(i);						
			if(flag == true)
				state = TokenizeState.COMENTARIO;
            switch (state) {
	            case DEFAULT:
	                if (charTokens.indexOf(c) != -1) {
	                	if( source.length() > 1 ){
	                		out = true;
		                	tokens.add(new Token("Token no valido", tokenError));
		                	break;
	                	}
	                	else{
	                		tokens.add(new Token(Character.toString(c), tokenType[1][charTokens.indexOf(c)]));
	                		break;
	                	}
	                } else if (Character.isLetter(c)) {
	                    token += c;
	                    state = TokenizeState.IDENTIFICADOR;
	                    if(i == source.length() - 1 ){		                	
		                    tokens.add(new Token(token, TokenType.IDENTIFICADOR));
		                    break;                   
		                }
	                } else if (Character.isDigit(c)) {
	                    token += c;
	                    state = TokenizeState.FLOTANTE;
	                    if(i == source.length() - 1 ){		                	
	                    	tokens.add(new Token("Token no valido", tokenError));
	                    	//le falta el punto decimal forzadamente
		                    break;                   
		                }
	                } 
	                else if ( c=='@' ) {	                	
	                	token+= c;
	                    state = TokenizeState.COMENTARIO;
	                    flag = true;
	                    comment += token;
//	                    if(i == source.length() - 1 ){		                	
//		                    tokens.add(new Token(token, TokenType.COMENTARIO));
//		                    break;                   
//		                }
	                } else{
	                	tokens.add(new Token("Token no valido", tokenError));
	                	break;	                
	                }
	                break;
	                
	            case IDENTIFICADOR:
	            	if (Character.isLetterOrDigit(c) || c == '_' ){ 
	                    token += c;
	                    if(i == source.length() - 1 ){	                	
		                    tokens.add(new Token(token, TokenType.IDENTIFICADOR));
		                    break;                   
		                }
	            	}	                	                	                	              
	                 else {
	                	tokens.add(new Token("Token no valido", tokenError));
	                	break;
	                }
	                break;
	                
	            case FLOTANTE:	            	
	            	if (Character.isDigit(c)) {
	                    token += c;
	                    state = TokenizeState.FLOTANTE;
	                    if(i == source.length() - 1 ){
		                    tokens.add(new Token(token, TokenType.FLOTANTE));
		                    break;
		                }
	                } else if( c == '.' ){
	                	if( foundDot == false ){	                		
	                		token += '.';
	                		state = TokenizeState.FLOTANTE;
	                		foundDot = true;
	                	}else{
	                		tokens.add(new Token("Token no valido", tokenError));
		                	break;
	                	}
	                }
	                else {
	                	tokens.add(new Token("Token no valido", tokenError));
	                	break;
	                }
	                break;
	                	                	              
	            case COMENTARIO:	            	
//	            	if(flag == true)
//	            		
//		            	if (  Character.isLetterOrDigit(c) || c == '_' || c == ' ' ) {
//		            		state = TokenizeState.COMENTARIO;
//		                    token += c;
//		                    comment+= token;
//		                    if(i == source.length() - 1 ){
//			                    tokens.add(new Token(token, TokenType.COMENTARIO));
//			                    break;
//			                }
//		                }
	                break;
            }
            if(out)
            	break;
		}
		
	}
	
	private enum TokenizeState {
		DEFAULT, IDENTIFICADOR, PALABRA, NUMERO, STRING, COMENTARIO, FLOTANTE, ERROR
	}

	public String getText() {
		String string_token = null;
			string_token=text;
		return string_token;
	}
	public String getToken(){
		String dato=null;
			dato=type.name();
		return dato;
	}
}

