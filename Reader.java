/******
 * Reader
 * Author: Adam Curley
 * 
 * This interface informs objects that read .txt files that they need to gather the attributes from the file and set them
 * 
 ******/

public interface Reader {
	public void setupAttributes(int choice);
	public void setValues(String[] s);
}
