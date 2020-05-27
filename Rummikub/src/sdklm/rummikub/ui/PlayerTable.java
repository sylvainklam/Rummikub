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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sdklm.rummikub.game.Player;
import sdklm.rummikub.tiles.Tile;
import sdklm.rummikub.tiles.TileFactory;

@SuppressWarnings("serial")
public class PlayerTable extends JFrame {

	public PlayerTable(Player p) {
		this.setSize(1024, 768);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Player table");
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("Player " + p.getNumber());
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout());
		for (int i = 0; i < p.getRack().getRackTiles().size(); i++) {
			Tile t = p.getRack().getRackTiles().get(i);
			TileComponent btnTile = new TileComponent(t);
			panel.add(btnTile, BorderLayout.CENTER);
			btnTile.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panel_1.add(btnTile, BorderLayout.CENTER);
					panel.remove(btnTile);
				}
			});

		}

		JButton btnEndRound = new JButton("End round");
		panel.add(btnEndRound);
		btnEndRound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int nbTiles = panel_1.getComponentCount();
				if (nbTiles == 0) {
					System.out.println("nbtiles = 0");
				} else {
					List<Tile> list = new ArrayList<Tile>();
					System.out.println("nbtiles > 0");
					for (Component tileComponent : panel_1.getComponents()) {
						TileComponent t = (TileComponent) tileComponent;
						list.add(t.getTile());
					}
					System.out.println("isGroup " + TileFactory.isGroup(list));
					System.out.println("isRun " + TileFactory.isRun(list));
				}
			}
		});
	}
}