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

public class ReadFile {

	private FileInputStream fis;
	private BufferedReader br;
	private DataInputStream dis;
	private String message;	
	private String messageComplete;
	private int counter = 0;
	
	public ReadFile( String filename ){
		try{
			message = "";			
			messageComplete = "";
			fis = new FileInputStream( filename );
			dis = new DataInputStream( fis );
			br = new BufferedReader( new InputStreamReader( dis ) );
		} catch( IOException e ){
			System.out.println( "Error: " + e.getMessage() );
		}
	}
	
	public void readFile( ){
		try{
			while( ( message = br.readLine() ) != null ){
				messageComplete += message;
				messageComplete += "\n";				
			}
		} catch( IOException e ){
			System.out.println( "Error: " + e.getMessage() );
		}
	}
	
	public String getContentTextFile(){
		
		return messageComplete;
	}
	
	public void countLinesFile(){
		try{
			while( ( message = br.readLine() ) != null ){
				counter++;				
			}
		} catch( IOException e ){
			System.out.println( "Error: " + e.getMessage() );
		}		
	}
	public int getCountLinesFile(){
		return counter;
	}
	
	public void closeFile(){
		try {
			dis.close();
		} catch( IOException e ){
			System.out.println( "Error: " + e.getMessage() );
		}
	}
}
