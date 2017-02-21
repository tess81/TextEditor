package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileHandler {
	
	/* Saves content in document to file. */
	public void saveDocumentToFile(String filePath, String content) throws IOException {
		File file = new File(filePath);
		file.createNewFile();
		OutputStream output = new FileOutputStream(file);
		Writer writer = new OutputStreamWriter(output);
		BufferedWriter buffWriter = new BufferedWriter(writer);
		buffWriter.write(content);
		buffWriter.flush();
		buffWriter.close();
	}

	/* Opens file and returns the content */
	public String openDocument(String filePath) throws IOException, FileNotFoundException {
		
		StringBuilder builder = new StringBuilder();
		
		FileInputStream input = new FileInputStream(filePath);
		InputStreamReader reader = new InputStreamReader(input);
		BufferedReader buffReader = new BufferedReader(reader);
		
		String line = buffReader.readLine();
		
		while (line != null) {
			builder.append(line + "\r\n");
	    	line = buffReader.readLine();  
	    }
	    buffReader.close();
	    
		String fileContent = builder.toString();
		return fileContent;
	}
}