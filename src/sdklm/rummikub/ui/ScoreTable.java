package sdklm.rummikub.ui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ScoreTable extends JFrame {

	public ScoreTable() {
		this.setSize(1024, 768);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Score table");
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
