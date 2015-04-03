package Main;

import java.awt.List;
import java.util.ArrayList;

import FilesManagement.ReadFile;
import Tokens.Token;

public class Lexer{

	private String file;
	private String [] file2;
	private ReadFile archivo;
	ArrayList<Token> token1 = new ArrayList();
	Token token = new Token(null,null);
	
	
	public Lexer(String file){
		this.file=file;
	}
	
	public void iniciar(){
		System.out.println(file);
		file2=file.split("");
		token1=(ArrayList<Token>) token.tokenize(file2);
		System.out.println("si regreso");
		mostrar();
	}

	private void mostrar() {
		for(int i=0; i<token1.size(); i++){
			System.out.println(i+"	Token: "+token1.get(i).getText()+ "		Tipo: "+token1.get(i).getToken());
		}
	}
}
 