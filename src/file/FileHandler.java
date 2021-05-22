package file;
import java.io.*;
import java.nio.file.*;
import java.util.regex.*;
/**
 * This class handles all File IO.
 * @author Ryan
 * @version 1.0
 */
/* PUBLIC METHODS IN CLASS
 * readFile <- String				returns null if error thrown
 * readWholeFile <- String 			returns null if error thrown
 * deserialiseFile <- String		returns null if error thrown
 * writeFile <- String, String		throws IOException
 * createDirectory <- file			throws IOException
 * serialiseFile <- file, object	throws IOException
 */
public class FileHandler 
{
	/*
	 * PUBLIC READ FILE METHODS
	 */
	/**
	 * Returns the lines of the file in an array. 
	 * Handles the reading of the file.
	 * This method returns null if the file cannot be read.
	 * 
	 * @param fileName 
	 * @return String[] of lines of file
	 */
	public static String[] readFile(String fileName)
	{
		String[] contents = null;
		try 
		{
			//create file object and access openFile method
			File file = openFile(fileName); //throws NullPointerException
			contents = new String[lineCount(file)]; //throws IOException
			BufferedReader br = new BufferedReader(new FileReader(file));
			for(int i = 0; i < contents.length; i ++)
			{
				contents[i] = br.readLine(); //throws IOException
			}
			br.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		return contents;
	}//END readFile
	/**
	 * This method calls the readFIle method and joins the resultant
	 * array with newline characters.
	 * If fileArray returns null (occurs in an exception thrown), 
	 * contents is returned as NULL.
	 * 
	 * @param fileName
	 * @return contents String of whole file
	 */
	public static String readWholeFile(String fileName)
	{
		String contents = null;
		String[] contentArray = readFile(fileName);
		if(contentArray != null)
			contents = String.join("\n", contentArray);
		return contents;
	}//END readWholeFile
	/**
	 * This method deserialises a file based upon a filename path.
	 * If an IOException occurs, it returns an object of NULL.
	 * 
	 * @param fileName
	 * @return object 
	 */
	public static Object deserialiseFile(String fileName)
	{
		Object object = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			object = in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}//END deserialiseFile
	/*
	 * PUBLIC WRITE FILE METHODS
	 */
	/**
	 * This method takes filename and contents strings, and prints
	 * the contents string to the desired file. 
	 * 
	 * @param fileName
	 * @param contents
	 * @throws IOException
	 */
	public static String writeFile(String fileName, String contents) throws IOException
	{
		try 
		{
			fileName = fileName.replaceAll(":", "_"); //throws null pointer exception
			File file = openFile(fileName); //throws null pointer exception
			FileWriter fw = new FileWriter(file); //throws io exception
			fw.write(contents);
			fw.close();
		}catch(NullPointerException e) {
			throw new IOException();
		}catch(PatternSyntaxException e) {
			throw new IOException();
		}
		return fileName;
	}//END writeFile
	/**
	 * This method creates a directory with the specified name in the
	 * current working directory of the program.
	 * 
	 * throws an IOException if the folderName is null, OR we cannot create
	 * a folder with the name specified.
	 * 
	 * @param folderName String
	 * @throws IOException 
	 */
	public static void createDirectory(String folderName) throws IOException
	{
		try {
			folderName = folderName.replaceAll(":", "_");
			Path path = Paths.get(folderName + "/");
			Files.createDirectories(path);
		}catch(PatternSyntaxException e) {
			throw new IOException();
		}
	}//END createDirectory
	/**
	 * This method serialises an object to a file. 
	 * if the name of the file is illegal/null OR the file streams face 
	 * IO Errors, we throw an IOException
	 * 
	 * @param fileName
	 * @param obj
	 * @throws IOException
	 */
	public static void seraliseFile(String fileName, Object obj) throws IOException
	{
		try {
			fileName = fileName.replaceAll(":", "_"); //throws PatternSyntaxException
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			out.close();
			fileOut.close();
			System.out.println("// file successfully closed");
		}catch(PatternSyntaxException e) {
			throw new IOException();
		}
	}//END seraliseFile
	/*
	 * PRIVATE METHODS
	 */
	/**
	 * This method simply opens the file at the fileName location. 
	 * If this fileName string input is NULL, then a NullPointerException is thrown.
	 * @param fileName
	 * @return File object of current file
	 * @throws NullPointerException
	 */
	private static File openFile(String fileName) throws NullPointerException
	{
		File file = null;
		file = new File(fileName);
		return file;
	}//END openFile
	/**
	 * This method counts the lines in a file
	 * If an IO exception is thrown, meaning the file cannot be read,
	 * it will throw an IOException
	 * @param file File object of file to count lines from.
	 * @return lines Integer 
	 * @throws IOException
	 */
	private static int lineCount(File file) throws IOException
	{
		int lines = 0;
		FileReader reader = new FileReader(file);
		int ch = reader.read();
		while((ch = reader.read()) != -1)
		{
			char key = (char)ch;
			if(key == '\n')
				lines ++;
		}
		reader.close();
		return lines;
	}//END lineCount
}//END CLASS
