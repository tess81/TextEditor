package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class DocumentManager {

	private FileHandler fh = new FileHandler();
	private String savedFileName = "Namnlös";
	private String fileExtension = ".txt";
	private String savedFilePath;
	private String content;
	
	/* Receives filePath, sends it onwards to get content and returns content */
	public String getDocument(String filePath) {
		
		try {
			content = fh.openDocument(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setFilePath(filePath);	/* saves the path of the file*/
		setFileName(Paths.get(filePath).getFileName().toString());	/* Saves the name of the file.*/
		return content;
	}

	/* Receives filPath and content. If filePath doesnt have .txt extension it gets it. FilePath and content sent onwards. */
	public void saveDocument(String filePath, String content) {
		
		if (!filePath.endsWith(fileExtension)) {
			filePath = filePath + fileExtension;				
		}
		setFilePath(filePath);
		setFileName(Paths.get(filePath).getFileName().toString());
		try {
			fh.saveDocumentToFile(filePath, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* If fileName has a file extension, it's deleted */
	public void setFileName(String fileName) {
		if (fileName.endsWith(fileExtension)) {
			savedFileName = fileName.substring(0, fileName.lastIndexOf('.'));
		}
		else {
			savedFileName = fileName;
		}
	}
	public String getFileName() {
		return savedFileName;
	}
	private void setFilePath(String filePath) {
		savedFilePath = filePath;
	}
	public String getFilePath() {
		return savedFilePath;
	}
}