package controller;
import model.DocumentManager;
import view.EditorView;

public class EditorController {
	
	private EditorView editorView;
	private DocumentManager docManager;
	private String fileName;
	private String filePath;
	private String docContent;
	private boolean docChanged = false;
	private boolean docHasName = false;
	
	/* Constructor */
	public EditorController() {
		docManager = new DocumentManager();
		editorView = new EditorView(this);
		editorView.createGUI(docManager.getFileName());
	}
	
	public void handleCopyEvent() {
		editorView.getTextArea().copy();
	}
	public void handleCutEvent() {
		editorView.getTextArea().cut();
	}
	public void handlePasteEvent() {
		editorView.getTextArea().paste();
	}
	
	/* When change occurs in the document, docChanged is set to true. */
	public void handleDocChanged() {
		docChanged = true;
	}
	
	/* Calls openDocument if document has not changed since opened/saved. If document has changed and user has chosen to save the changes,
	 * the method checks if it has a name. If it has, saveDocument is called. If it doesn't have a name, saveAsDocument is called. 
	 * Thereafter openDocument is called. If the user has chosen not to save, openDocument is called. If user chose Cancel, nothing happens. */
	public void handleOpenEvent() {
		if (!docChanged) { 
			openDocument();
		}
		else {
			String answer = editorView.saveChangeDialog();
			
			switch (answer) {
			case "yes":
				if (docHasName) {
					saveDocument();
				}
				else {
					saveAsDocument();
				}
				openDocument();
				break;
			case "no":
				openDocument();
				break;
			case "cancel":
				break;	
			}
		}
	}
	
	/* Calls newDocument if document has not changed since opened/saved. If document has changed and user has chosen to save the changes,
	 * the method checks if it has a name. If it has, saveDocument is called. If it doesn't have a name, saveAsDocument is called. 
	 * Thereafter newDocument is called. If the user has chosen not to save, newDocument is called. If user chose Cancel, nothing happens. */
	public void handleNewEvent() {
		if (!docChanged) {
			newDocument();
		}
		else {
			String answer = editorView.saveChangeDialog();
			
			switch (answer) {
			case "yes":
				if (docHasName) {
					saveDocument();
				}
				else {
					saveAsDocument();
				}
				newDocument();
				break;
				
			case "no":
				newDocument();
				break;
				
			case "cancel":
				break;
			}	
		}
	}
	
	/* If document has no name, saveAsDocument is called. If it has a name already, saveDocument is called. */
	public void handleSaveEvent() {

		if (!docHasName) {
			saveAsDocument();
		}
		else {
			saveDocument();
		}
	}
	
	/* Calls saveAsDocument */
	public void handleSaveAsEvent() {
		saveAsDocument();
	}
	
	/* Calls exit if document has not changed since opened/saved. If document has changed and user has chosen to save the changes,
	 * the method checks if it has a name. If it has, saveDocument is called. If it doesn't have a name, saveAsDocument is called. 
	 * After that exit is called. If user chose not to save changes exit is called. If user chose cancel nothing happens.*/
	public void handleExitEvent() {
		
		if (!docChanged) {
			exit();
		} 
		else {
			String answer = editorView.saveChangeDialog();
			
			switch (answer) {
			case "yes":
				if (docHasName) {
					saveDocument();
				}
				else {
					saveAsDocument();
				}
				exit();
				break;
				
			case "no":
				exit();
				break;
				
			case "cancel":
				break;
			}	
		}
	}
	
	/* Gets filePath and content, sends is onwards. */
	public void saveDocument() {
		filePath = docManager.getFilePath();
		docContent = editorView.getTextArea().getText();
		docManager.saveDocument(filePath, docContent);	
		docHasName = true;
		docChanged = false;
	}
	
	/* Gets filePath, checks that it's not null. Gets content. Sends filePath and content. Gets fileName.
	 * Sends fileName. */
	public void saveAsDocument() {
		filePath = editorView.saveAsDialog();
		if (filePath != null) {
			docContent = editorView.getTextArea().getText();
			docManager.saveDocument(filePath, docContent);	
			fileName = docManager.getFileName();
			editorView.updateView(fileName, null);
			docHasName = true;
			docChanged = false;
		}
	}
	
	/* Sets fileName to default value and sends it onwards. Sets docContent to default value. Sends both. */
	public void newDocument() {
		fileName = "Namnlös";
		docManager.setFileName(fileName);
		docContent = "";
		editorView.updateView(fileName, docContent);
		docHasName = false;
		docChanged = false;
	}
	
	/* Gets filePath, checks that it's not null. Gets content and fileName. Sends both onwards. */
	public void openDocument() {
		filePath = editorView.openDialog();
		if (filePath != null) {
			docContent = docManager.getDocument(filePath);		
			fileName = docManager.getFileName();
			editorView.updateView(fileName, docContent);
			docHasName = true;
			docChanged = false;
		}
	}
	/* Exits system. */
	public void exit() {
		System.exit(0);
	}
	public static void main(String[] args) {
		new EditorController();
	}
}