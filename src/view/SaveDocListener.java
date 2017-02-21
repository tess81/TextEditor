package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDocListener implements ActionListener {

	private EditorView ev;
	
	public SaveDocListener (EditorView eView) {
		ev = eView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ev.relaySave();
	}

}