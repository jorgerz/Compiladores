package Main;

import FilesManagement.ReadFile;

public class Application {
	static ReadFile archivo;
	static Lexer lex;
	public static void main(String[] args) { 
		archivo = new ReadFile("Archivos/prueba.txt");
		archivo.readFile();	
		lex = new Lexer(archivo.getContentTextFile());
		lex.iniciar();
	}
}
