package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasteListener implements ActionListener {
	private EditorView ev;
	
	public PasteListener (EditorView eView) {
		ev = eView;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ev.relayPaste();
	}
}