/******
 * AttributeGatherer
 * Author: Adam Curley
 * 
 * This object is used to read .txt files and populate arrays of strings with the contents of those files
 * 
 ******/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AttributeGatherer {
	private String[] _split;
	private Scanner _file;
	
	/*
	 * Read the file and return the contents of a particular line of that file as an array of strings
	 */
	public String[] readFile(String filename, int row) {
		int lines = getNumberOfLines(filename);
		
		try {
			_file = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < lines && _file.hasNext(); i++) {
			if (i == row) {
				_split = _file.nextLine().split("\\s+");
			}
			else {
				_file.nextLine();
			}
		}
		
		return _split;
	}
	
	/*
	 * Read the file and return a count of the number of lines in that file
	 */
	public int getNumberOfLines(String filename) {
		int lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			while(reader.readLine() != null) lines++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
}
