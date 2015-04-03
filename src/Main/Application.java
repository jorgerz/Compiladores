package Main;

import FilesManagement.ReadFile;

public class Application {
	ReadFile archivo;
	Lexer lex;
	public static void main(String[] args) { 
		Application app = new Application();
		app.start();
	}
	
	public void start(){
		archivo = new ReadFile("Archivos/prueba.txt");
		archivo.readFile();	
		String[] source = archivo.getContentTextFile().split(" ");
		lex = new Lexer(source);
		lex.initLexer();		
	}
}
