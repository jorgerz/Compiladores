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
		//String[] source = archivo.getContentTextFile().split(" ");		
		String[] content = archivo.getContentTextFile().split("\n");
		String[][] source = new String[content.length][];
		for(int i = 0; i < content.length; i++){
			source[i] = content[i].split(" ");							
		}
		lex = new Lexer(source);
		lex.fileUsed(archivo);
		lex.initLexer();		
	}
}