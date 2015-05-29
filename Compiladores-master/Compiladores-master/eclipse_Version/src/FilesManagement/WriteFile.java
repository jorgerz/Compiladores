package FilesManagement;

import java.io.*;

public class WriteFile {

	private FileOutputStream fs;
	private ObjectOutputStream os;
	
	public WriteFile( String fileName ){
		
		try{
			fs = new FileOutputStream( fileName );
			os = new ObjectOutputStream( fs );
		} catch( IOException e ){
			System.out.println( "Error: 1" + e.getMessage() ); 
		}		
	}
	
	public void saveFile( Object object ){
				
		try {
			os.writeObject( object );			
		} catch (IOException e) {
			System.out.println( "Error: 2 " + e.getMessage() );
		}
	}
	
	public void closeFile( ){
		try {
			os.close();
		} catch (IOException e) {
			System.out.println( "Error: 3" + e.getMessage() );
		}
	}
}
 