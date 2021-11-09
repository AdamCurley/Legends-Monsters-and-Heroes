/******
 * TypeReader
 * Author: Adam Curley
 * 
 * This interface informs objects that read .txt files that they need to gather the attributes from the file and set them
 * it's specially for objects that have a specific type that determines which file they read
 * 
 ******/

public interface TypeReader {
	public void setupAttributes(String type, int character) ;
	public void setValues(String[] s);
}
