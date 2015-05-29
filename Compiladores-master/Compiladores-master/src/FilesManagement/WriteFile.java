/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesManagement;

/**
 *
 * @author Jorge
 */
import java.io.*;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteFile {
    private FileWriter escritor;
    private BufferedWriter out;

    //private FileOutputStream fs;
    //private ObjectOutputStream os;

    public WriteFile( String fileName ){
        try{
            

            escritor=new FileWriter(fileName);
             out=new BufferedWriter(escritor);
               //fs = new FileOutputStream( fileName );
            //os = new ObjectOutputStream( fs );
        } catch( IOException e ){
            System.out.println( "Error: 1" + e.getMessage() ); 
        }		
    }
	
    public void saveFile( String texto ){

        try {
            out.write(texto);
        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
    public void closeFile( ){
        try {
            out.close();
        } catch (IOException e) {
            System.out.println( "Error: 3" + e.getMessage() );
        }
    }
}
 