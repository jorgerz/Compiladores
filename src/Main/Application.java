package Main;

import FilesManagement.ReadFile;

public class Application {
	static ReadFile archivo;
	public static void main(String[] args) { 
		archivo = new ReadFile("Archivos/prueba.txt");
		archivo.readFile();
		 
	}
}
