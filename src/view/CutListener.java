package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CutListener implements ActionListener {
	private EditorView ev;
	
	public CutListener (EditorView eView) {
		ev = eView;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ev.relayCut();
	}
}