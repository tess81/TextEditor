package view;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocListener implements DocumentListener {

	private EditorView ev;
	
	public DocListener (EditorView eView) {
		ev = eView;
	}
	
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		ev.relayDocChanged();	
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		ev.relayDocChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		ev.relayDocChanged();
	}	
}