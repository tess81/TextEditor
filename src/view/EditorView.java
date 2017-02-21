package view;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.EditorController;

public class EditorView {

	private EditorController ec;
	private JFrame editorFrame;
	private JMenuBar menuBar;
	private JMenu fileMenu, editMenu;
	private JMenuItem newDoc, openDoc, saveDoc, saveDocAs, exit;
	private JMenuItem cut, copy, paste;
	private JScrollPane scrollPane;
    private JTextArea textArea;
    private String editorName = " - Text Editor";
    private ExitListener el;

	public EditorView(EditorController eCon) {
		ec = eCon;
		el = new ExitListener(this);
	}
	
	/* Updates view with filename och content */
	public void updateView(String fileName, String docContent) {
		editorFrame.setTitle(fileName + editorName);
		if (docContent != null) {
			textArea.setText(docContent);
		}
	}
	
	/* Dialog that gets the files path and returns it */
	public String saveAsDialog() {
		
		String dialogTitleSaveAs = "Spara som";
		JFileChooser saveAs = new JFileChooser();
		saveAs.setDialogTitle(dialogTitleSaveAs);
		int result = saveAs.showSaveDialog(null);
		String filePath = null;
		
		switch (result) {
		case JFileChooser.APPROVE_OPTION:
			filePath = saveAs.getSelectedFile().getAbsolutePath();
			break;
		case JFileChooser.CANCEL_OPTION:
			filePath = null;
			break;
		}
		return filePath;
	}
	
	/* Dialog that depending on reply from user returns differents strings */ 
	/* 161108 TODO Exception currently not handled if user choose to close dialog with X*/
	public String saveChangeDialog() {

		String title = "Spara ändringar?";
		String message = "Vill du spara ändringarna i dokumentet?";
		Object[] options = {"Spara", "Spara inte", "Avbryt"};
		int result = JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, options, null);
		String answer = null;
		
		switch (result) {
		case JOptionPane.YES_OPTION:
			answer = "yes";
			break;
		case JOptionPane.NO_OPTION:
			answer = "no";
			break;
		case JOptionPane.CANCEL_OPTION:
			answer = "cancel";
			break;
		}
		return answer;
	}
	
	/* Dialog that gets the filepath and returns it */
	public String openDialog() {
	
		JFileChooser open = new JFileChooser();
        int result = open.showOpenDialog(null);
        
        String filePath = null;
		switch (result) {
		case JFileChooser.APPROVE_OPTION:
			filePath = open.getSelectedFile().getAbsolutePath();
			break;
		case JFileChooser.CANCEL_OPTION:
			filePath = null;
			break;
		}
		return filePath;
    }
	
	public void relayDocChanged() {
		ec.handleDocChanged();
	}
	
	public void relayOpen() {
		ec.handleOpenEvent();
	}
	
	public void relayNew() {
		ec.handleNewEvent();
	}
	
	public void relaySave() {
		ec.handleSaveEvent();
	}
	
	public void relaySaveAs() {
		ec.handleSaveAsEvent();
	}
	
	public void relayExit() {
		ec.handleExitEvent();
	}
	
	public void relayCopy() {
		ec.handleCopyEvent();
	}
	public void relayCut() {
		ec.handleCutEvent();
		
	}
	public void relayPaste() {
		ec.handlePasteEvent();
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
public void createGUI(String fileName) {
		
        // File Menu
        fileMenu = new JMenu("Arkiv");
        fileMenu.setPreferredSize(new Dimension(40, 20));
        fileMenu.setMnemonic(KeyEvent.VK_A);
		
        // File Menu items
        newDoc = new JMenuItem("Nytt");
        newDoc.addActionListener(new NewDocListener(this));
        newDoc.setPreferredSize(new Dimension(100, 20));
        newDoc.setEnabled(true);
        
        openDoc = new JMenuItem("Öppna...");
        openDoc.addActionListener(new OpenDocListener(this));
        openDoc.setPreferredSize(new Dimension(100, 20));
        openDoc.setEnabled(true);
        
        saveDoc = new JMenuItem("Spara");
        saveDoc.addActionListener(new SaveDocListener(this));
        saveDoc.setPreferredSize(new Dimension(100, 20));
        saveDoc.setEnabled(true);
        
        saveDocAs = new JMenuItem("Spara som...");
        saveDocAs.addActionListener(new SaveDocAsListener(this));
        saveDocAs.setPreferredSize(new Dimension(100, 20));
        saveDocAs.setEnabled(true);
        
        exit = new JMenuItem("Avsluta");
        exit.addActionListener(el);
        exit.setPreferredSize(new Dimension(100, 20));
        exit.setEnabled(true);
        
        // Add items to file menu
        fileMenu.add(newDoc);
        fileMenu.add(openDoc);
        fileMenu.add(saveDoc);
        fileMenu.add(saveDocAs);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        
        // Edit Menu
        editMenu = new JMenu("Redigera");
        editMenu.setPreferredSize(new Dimension(60, 20));
        editMenu.setMnemonic(KeyEvent.VK_R);
        
        // Edit Menu items
        cut = new JMenuItem("Klipp ut");
        cut.addActionListener(new CutListener(this));
        cut.setPreferredSize(new Dimension(100, 20));
        cut.setEnabled(true);
        
        copy = new JMenuItem("Kopiera");
        copy.addActionListener(new CopyListener(this));
        copy.setPreferredSize(new Dimension(100, 20));
        copy.setEnabled(true);
        
        paste = new JMenuItem("Klistra in");
        paste.addActionListener(new PasteListener(this));
        paste.setPreferredSize(new Dimension(100, 20));
        paste.setEnabled(true);
        
        // Add items to edit menu
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        
        // Menu bar
        menuBar = new JMenuBar();
        
        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
  
        // TextArea
        textArea = new JTextArea();
        textArea.getDocument().addDocumentListener(new DocListener(this));
        setTextArea(textArea);
        getTextArea().setEditable(true);
        scrollPane = new JScrollPane(getTextArea());
        
        // Window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(screenSize.width*(0.50), screenSize.height*(0.80));
        int newHeight = screenSize.height;
        int newWidth = screenSize.width;
        
        editorFrame = new JFrame(fileName + editorName);
        editorFrame.setSize(newWidth, newHeight);
        editorFrame.setVisible(true);
        editorFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        editorFrame.addWindowListener(el);
        editorFrame.setLocationRelativeTo(null);    
        
        // Add menuBar and textArea to window
        editorFrame.setJMenuBar(menuBar);
        editorFrame.add(scrollPane);
	}
}