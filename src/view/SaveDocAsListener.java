package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDocAsListener implements ActionListener {

	private EditorView ev;
	
	public SaveDocAsListener (EditorView eView) {
		ev = eView;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ev.relaySaveAs();
	}

}