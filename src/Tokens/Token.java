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
	
	private void addSingleToken(String source){		
		String token = "";
		boolean foundDot = false;
		System.out.println(source);
		TokenizeState state = TokenizeState.DEFAULT;
		for(int i =0;i<source.length();i++){			           
			char c = source.charAt(i);												
            switch (state) {
	            case DEFAULT:
	                if (charTokens.indexOf(c) != -1) {
	                	if( c == '-' && source.length() > 1 ){	                		
		                	tokens.add(new Token("Token no valido", tokenError));
		                	break;
	                	}
	                	else{
	                		tokens.add(new Token(Character.toString(c), tokenType[1][charTokens.indexOf(c)]));
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
	                    if(i == source.length() - 1 ){		                	
		                    tokens.add(new Token(token, TokenType.COMENTARIO));
		                    break;                   
		                }
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
	            	if (  Character.isLetterOrDigit(c) || c == '_') {
	            		state = TokenizeState.COMENTARIO;
	                    token += c;
	                    if(i == source.length() - 1 ){
		                    tokens.add(new Token(token, TokenType.COMENTARIO));
		                    break;
		                }
	                }
	                break;
            }
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
