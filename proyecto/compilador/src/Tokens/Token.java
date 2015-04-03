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
				if( source[i].equals( stringTokens[j].trim() ) ){
					System.out.println("entro");
					tokens.add( new Token( stringTokens[j], tokenType[0][i] ));
					found = true;
					break;
				}
			}
			if(found == false){
				str=(String)source[i];
				char c = str.charAt(0);
				System.out.println(c);
	            switch (state){
	            case DEFAULT:
	                if (charTokens.indexOf(c) != -1) {
	                	tokens.add(new Token(Character.toString(c),tokenType[1][charTokens.indexOf(c)]));
	                } else if (Character.isLetter(c)) {
	                    token += c;
	                    state = TokenizeState.PALABRA;
	                } 
	            else if (Character.isDigit(c)) {
	                    token += c;
	                    state = TokenizeState.NUMERO;
	                } else if (c == '"') {
	                    state = TokenizeState.STRING;
	                } else if (c == '@') {
	                    state = TokenizeState.COMENTARIO;
	                } else{
	                	System.out.println("Error case DEFAULT");
	                }
	                break;
	                
	            case PALABRA:

	                if (Character.isLetterOrDigit(c)) {
	                    token += c;
	                } else if (c=='_'){
	                	token+="_";
	                	state = TokenizeState.DEFAULT;
	                }else if (c == ':') {
	                    tokens.add(new Token(token, TokenType.DOS_PUNTOS));
	                    token = "";
	                    state = TokenizeState.DEFAULT;
	                } else {
	                    tokens.add(new Token(token, TokenType.PALABRA));
	                    token = "";
	                    state = TokenizeState.DEFAULT;
	                    i--;
	                }
	                break;
	                
	            case NUMERO:
	                if (Character.isDigit(c)) {
	                    token += c;
	                } 
	                else if(c=='.'){
						if(flag==false){
	                		token+='.';
	                		state = state.FLOTANTE;	
	                		flag=true;
	                	}
						else{
							System.out.println("Error en cas NUMERO");
						}
	                }
	                else {
	                    tokens.add(new Token(token, TokenType.NUMERO));
	                    token = "";
	                    state = TokenizeState.DEFAULT;
	                    i--;
	                }
	                break;
	                
	            case FLOTANTE:
	            		if(Character.isDigit(c)){
	            			token+=c;
	            		}else{
	            			tokens.add(new Token(token,TokenType.FLOTANTE));
	            			token="";
	            			state = TokenizeState.DEFAULT;
	            			i--;
	            		}
	            			
	            	break;
	            case STRING:
	                if (c == '"') {
	                    tokens.add(new Token(token, TokenType.STRING));
	                    token = "";
	                    state = TokenizeState.DEFAULT;
	                } else {
	                    token += c;
	                }
	                break;
	                
	            case COMENTARIO:
	                if (c == '@') {
	                    state = TokenizeState.DEFAULT;
	                }
	                break;
	            }
			}
		}
		
		return tokens;
	}
	
	private enum TokenizeState {
		DEFAULT, PALABRA, NUMERO, STRING, COMENTARIO, FLOTANTE
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
