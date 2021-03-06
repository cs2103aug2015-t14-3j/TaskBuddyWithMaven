package com.cs2013t143j.TaskBuddyM.Logic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserName {
	
	private String filePath;
	
	public UserName(String filePath) {
		this.filePath = filePath;
	}
	
	public String getUserName() {
		
		String userName = null;
		
		File fileToRead = new File(filePath);
		try{
		BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
		userName = reader.readLine();
		reader.close();
		
		} catch (FileNotFoundException ex) {
		userName = null;
		
		} catch (IOException ex) {
		System.out.println(ex);
		
		}
		
		return userName;
	}
	
	public void setUserName(String userName) {
		
		File file = new File(filePath);
		
		//Clears the file
		try{
		PrintWriter writer = new PrintWriter(new FileWriter(file));
		writer.close();
		} catch (IOException ex) {
		System.out.println(ex);
		}
		
		//Writes userName to file
		try{
			PrintWriter writer = new PrintWriter(new FileWriter(file, true));
			writer.println(userName);
			writer.close();
			} catch (IOException ex) {
			System.out.println(ex);
			}

	}

}
