package graphicalinterface;
import javax.swing.JFileChooser;
import java.io.File;
/**
 * This methods opens a JFileChooser and returns a file object
 * with the directory of the one selected. 
 * @author email
 * @version 1.0
 */
public class FileSelectorInterface 
{
	/**
	 * Method fileChooser, launches a file chooser window. 
	 * gets the selected file, and returns it to the user. 
	 * 
	 * If the file is not valid, we return NULL. 
	 * @return File
	 */
	public static File fileChooser()
	{
		Window window = new Window("Open a file");
		JFileChooser fc = new JFileChooser();
		File file;
		int flag = fc.showOpenDialog(window.frame);
		if(flag == JFileChooser.APPROVE_OPTION)
			file = fc.getSelectedFile();
		else
			file = null;
		return file;
	}//END fileChooser
}//END class
