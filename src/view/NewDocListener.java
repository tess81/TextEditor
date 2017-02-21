package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDocListener implements ActionListener {

	private EditorView ev;
	
	public NewDocListener (EditorView eView) {
		ev = eView;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ev.relayNew();
	}

}