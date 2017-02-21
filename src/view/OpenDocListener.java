package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenDocListener implements ActionListener {

	private EditorView ev;
	
	public OpenDocListener(EditorView eView) {
		ev = eView;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ev.relayOpen();
	}
}