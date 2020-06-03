package sdklm.rummikub.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sdklm.rummikub.game.Game;
import sdklm.rummikub.tiles.Tile;
import sdklm.rummikub.tiles.TileFactory;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {

	private JPanel panelButton = new JPanel();
	private JPanel panelNbPlayers = new JPanel();

	private JLabel labelNbPlayers = new JLabel("Number of players : ");
	private JComboBox<String> comboNbPlayers = new JComboBox<String>();
	private JButton startButton = new JButton("Start game");

	public MainWindow() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My Rummikub");
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		comboNbPlayers.addItem("2 players");
		comboNbPlayers.addItem("3 players");
		comboNbPlayers.addItem("4 players");

		panelNbPlayers.add(labelNbPlayers);
		panelNbPlayers.add(comboNbPlayers);

		panelButton.add(startButton);

		this.getContentPane().add(panelNbPlayers, BorderLayout.NORTH);
		this.getContentPane().add(panelButton, BorderLayout.CENTER);

		startButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			String selectedItem = (String) this.comboNbPlayers.getSelectedItem();
			int nbPlayers = Integer.valueOf(selectedItem.substring(0, 1));
			List<Tile> tiles = TileFactory.buildTilesList();
			Game game = new Game(nbPlayers, tiles);
//			Round round = new Round(1);
//			game.setCurrentRound(round);
//			round.start(game.getPlayers().get(0));
//			game.addRound(round);
			this.dispose();
			PlayerTable playerTable = new PlayerTable(game, null);
			playerTable.setVisible(true);
		}
	}
}
