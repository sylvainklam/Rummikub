package sdklm.rummikub.ui;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import sdklm.rummikub.game.Game;

/**
 * Countdown panel
 * 
 * @author SKLAM
 *
 */
@SuppressWarnings("serial")
public class CountdownPanel extends JPanel {

	private Timer timer;
	private long startTime = -1;
	private long duration = 120000;

	private JLabel label;

	public CountdownPanel(Game game, JPanel panelBoard, JFrame frame) {
		setLayout(new GridBagLayout());
		timer = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (startTime < 0) {
					startTime = System.currentTimeMillis();
				}
				long now = System.currentTimeMillis();
				long clockTime = now - startTime;
				if (clockTime >= duration) {
					clockTime = duration;
					timer.stop();
					JOptionPane.showMessageDialog(null, "Your time is elapsed. Click OK to end turn", " Time elapsed ",
							JOptionPane.WARNING_MESSAGE);
					if (game.getCurrentPlayer().isFirstTurn())
						game.getCurrentPlayer().setFirstTurn(false);
					frame.dispose();
					game.setCurrentPlayer(game.nextPlayer());
					PlayerTable playerTable = new PlayerTable(game, panelBoard.getComponents());
					playerTable.setVisible(true);
				}
				SimpleDateFormat df = new SimpleDateFormat("mm:ss");
				label.setText(df.format(duration - clockTime));
			}
		});
		timer.setInitialDelay(0);
		if (!timer.isRunning()) {
			startTime = -1;
			timer.start();
		}
		label = new JLabel("...");
		add(label);
	}

	public boolean stopTimer() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			System.out.println("Timer is not running");
			return false;
		}
		return true;
	}
}
