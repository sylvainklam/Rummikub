package sdklm.rummikub.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sdklm.rummikub.game.Player;
import sdklm.rummikub.game.Pouch;
import sdklm.rummikub.tiles.Tile;

@SuppressWarnings("serial")
public class PlayerTable extends JFrame {

	public PlayerTable(Player p, Pouch pouch) {
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

		JLabel lblNewLabel = new JLabel("Player " + p.getNumber());
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
		for (int i = 0; i < p.getRack().getRackTiles().size(); i++) {
			Tile t = p.getRack().getRackTiles().get(i);
			TileComponent btnTile = new TileComponent(t);
			panelTiles.add(btnTile, BorderLayout.CENTER);
			btnTile.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panel_1.add(btnTile, BorderLayout.CENTER);
					panelTiles.remove(btnTile);
					repaint();
				}
			});
		}

		// panel.add(btnEndRound);
		btnEndRound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//						int nbTiles = panel_1.getComponentCount();
//						if (nbTiles == 0) {
//							JOptionPane.showMessageDialog(null, "Put some tiles before ending your turn !", " WARNING ",
//									JOptionPane.WARNING_MESSAGE);
//						} else {
//							List<Tile> list = new ArrayList<Tile>();
//							System.out.println("nbtiles > 0");
//							for (Component tileComponent : panel_1.getComponents()) {
//								TileComponent t = (TileComponent) tileComponent;
//								list.add(t.getTile());
//							}
//							int score = TileFactory.hasGroup(list);
//							System.out.println("score " + score);
//						}
			}
		});
		btnTakeTile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int nbTiles = panel.getComponentCount();
				if (nbTiles == 17) {
					JOptionPane.showMessageDialog(null, "You can't have more than 14 tiles in your rack !", " WARNING ",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Tile t = pouch.getRandomTile();
					TileComponent btnTilePouch = new TileComponent(t);
					panel.add(btnTilePouch, BorderLayout.CENTER);
					btnTilePouch.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							panelTiles.add(btnTilePouch);
						}
					});
					panelTiles.revalidate();
					panelTiles.repaint();
				}
			}
		});
	}
}