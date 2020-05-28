package sdklm.rummikub.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sdklm.rummikub.game.Game;
import sdklm.rummikub.tiles.Tile;

@SuppressWarnings("serial")
public class PlayerTable extends JFrame {

	public PlayerTable(Game game) {
		this.setSize(1024, 768);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Player table");
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		getContentPane().setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("Player " + game.getCurrentPlayer().getNumber());
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);
		JPanel panelTiles = new JPanel();
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JButton btnTakeTile = new JButton("Take tile");
		panel_2.add(btnTakeTile);

		JButton btnEndRound = new JButton("End round");
		panel_2.add(btnEndRound);

		panel.add(panelTiles);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout());
		for (int i = 0; i < game.getCurrentPlayer().getRack().getRackTiles().size(); i++) {
			Tile t = game.getCurrentPlayer().getRack().getRackTiles().get(i);
			TileComponent btnTile = new TileComponent(t);
			panelTiles.add(btnTile, BorderLayout.CENTER);
			btnTile.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panel_1.add(btnTile);
					panelTiles.remove(btnTile);
					repaint();
				}
			});
		}

		// panel.add(btnEndRound);
		/**
		 * Action bouton End Round
		 */
		btnEndRound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int score = 0;
				int nbTiles = panel_1.getComponentCount();
				if (nbTiles == 0) {
					JOptionPane.showMessageDialog(null, "Please take a tile before ending round", " INFORMATION ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					List<Tile> list = new ArrayList<Tile>();
					for (Component tileComponent : panel_1.getComponents()) {
						TileComponent t = (TileComponent) tileComponent;
						list.add(t.getTile());
					}
					System.out.println("score " + score);
				}
			}
		});

		/**
		 * Action bouton Take Tile
		 */
		btnTakeTile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tile t = game.getPouch().getRandomTile();
				TileComponent btnTilePouch = new TileComponent(t);
				panelTiles.add(btnTilePouch, BorderLayout.CENTER);
				btnTilePouch.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						panel_1.add(btnTilePouch);
						panelTiles.remove(btnTilePouch);
						repaint();
					}
				});
				panelTiles.revalidate();
				panelTiles.repaint();
				game.getCurrentPlayer().endRound(panel_1.getComponents(), game);
				dispose();
				game.setCurrentPlayer(game.nextPlayer());
				PlayerTable playerTable = new PlayerTable(game);
				playerTable.setVisible(true);
			}
		});
	}
}