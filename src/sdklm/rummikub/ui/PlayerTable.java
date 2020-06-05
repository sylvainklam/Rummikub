package sdklm.rummikub.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sdklm.rummikub.game.Game;
import sdklm.rummikub.tiles.Tile;

/**
 * Player table
 * 
 * @author SKLAM
 *
 */
@SuppressWarnings("serial")
public class PlayerTable extends JFrame {

	public PlayerTable(Game game, Component[] components) {
		this.setSize(1280, 768);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Player table");
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		getContentPane().setLayout(new GridLayout(2, 2, 0, 0));

		/**
		 * Main panel : we will add buttons, player tiles and timer
		 */
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		/**
		 * Label and buttons
		 */
		JLabel lblNewLabel = new JLabel(
				"Round " + game.getCurrentRound().getNumber() + " - Player " + game.getCurrentPlayer().getNumber());
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);
		JPanel panelButtons = new JPanel();
		panel.add(panelButtons);

		/**
		 * Order tiles
		 */
		JButton btnOrderTiles = new JButton("Order tiles");
		panelButtons.add(btnOrderTiles);
		/**
		 * Take tile button
		 */
		JButton btnTakeTile = new JButton("Take tile");
		panelButtons.add(btnTakeTile);

		/**
		 * End turn button
		 */
		JButton btnEndTurn = new JButton("End turn");
		panelButtons.add(btnEndTurn);

		/**
		 * Player tiles
		 */
		JPanel panelTiles = new JPanel();
		panel.add(panelTiles);

		/**
		 * Board which contains tilesets from players
		 */
		JPanel panelBoard = new JPanel();
		getContentPane().add(panelBoard);
		panelBoard.setLayout(new FlowLayout());

		/**
		 * Timer
		 */
//		CountdownPanel panelTimer = new CountdownPanel(game, panelBoard, this);
//		panel.add(panelTimer);

		/**
		 * Display player tiles
		 */
		for (int i = 0; i < game.getCurrentPlayer().getRack().getRackTiles().size(); i++) {
			Tile t = game.getCurrentPlayer().getRack().getRackTiles().get(i);
			TileComponent btnTile = new TileComponent(t);
			panelTiles.add(btnTile, BorderLayout.CENTER);
			btnTile.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panelBoard.add(btnTile);
					panelTiles.remove(btnTile);
					repaint();
				}
			});
		}
		if (components != null && components.length > 0) {
			for (Component component : components) {
				panelBoard.add(component);
			}
		}

		/**
		 * Action button Order tiles
		 */
		btnOrderTiles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<TileComponent> components = new ArrayList<TileComponent>();
				for (Component component : panelTiles.getComponents()) {
					TileComponent tileComponent = (TileComponent) component;
					components.add(tileComponent);
				}
				Collections.sort(components);
				panelTiles.removeAll();
				for (TileComponent tileComponent : components) {
					panelTiles.add(tileComponent, BorderLayout.CENTER);
				}
				revalidate();
				repaint();
			}
		});
		/**
		 * Action button End turn
		 */
		btnEndTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.getCurrentPlayer().isFirstTurn()) {
					int score = game.getCurrentPlayer().endTurn(panelBoard.getComponents(), game);
					if (score < 30) {
						JOptionPane.showMessageDialog(null,
								"Your score is " + score + " points. You must have at least 30 points",
								" Not enough points ", JOptionPane.WARNING_MESSAGE);
						for (Component component : panelBoard.getComponents()) {
							TileComponent tileComponent = (TileComponent) component;
							if (tileComponent.getTile().getPlayedBy() == game.getCurrentPlayer().getNumber()) {
								panelTiles.add(component);
								panelBoard.remove(component);
							}
						}
						repaint();
					} else {
						System.out.println("score = " + score);
						List<Tile> listToRemove = new ArrayList<Tile>();
						revalidate();
						repaint();
						JOptionPane.showMessageDialog(null, "Your score is " + score + " points.", " Congratulations! ",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						Component[] tabComponents = panelBoard.getComponents();
						for (Component component : tabComponents) {
							TileComponent tileComponent = (TileComponent) component;
							listToRemove.add(tileComponent.getTile());
						}
						game.getCurrentPlayer().getRack().removeTilesFromRack(listToRemove);
						game.getCurrentPlayer().setFirstTurn(false);
						game.setCurrentPlayer(game.nextPlayer());
						PlayerTable playerTable = new PlayerTable(game, panelBoard.getComponents());
						playerTable.setVisible(true);
					}
				} else {
					System.out.println("end turn for player " + game.getCurrentPlayer().getNumber());
					if (game.getCurrentPlayer().getRack().getRackTiles().size() == 0) {
						JOptionPane.showMessageDialog(null,
								"RUMMIKUB ! Player " + game.getCurrentPlayer().getNumber() + " wins.",
								" Congratulations! ", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						ScoreTable scoreTable = new ScoreTable();
						scoreTable.setVisible(true);
					} else {
						List<Tile> listToRemove = new ArrayList<Tile>();
						revalidate();
						repaint();
						dispose();
						Component[] tabComponents = panelBoard.getComponents();
						for (Component component : tabComponents) {
							TileComponent tileComponent = (TileComponent) component;
							listToRemove.add(tileComponent.getTile());
						}
						game.getCurrentPlayer().getRack().removeTilesFromRack(listToRemove);
						game.setCurrentPlayer(game.nextPlayer());
						PlayerTable playerTable = new PlayerTable(game, panelBoard.getComponents());
						playerTable.setVisible(true);
					}
				}
			}
		});

		/**
		 * Action button Take Tile
		 */
		btnTakeTile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tile t = game.getPouch().getRandomTile();
				System.out.println("Player " + game.getCurrentPlayer().getNumber() + " took " + t.toString());
				JOptionPane.showMessageDialog(null, "You took " + t.toString(), " Tile from the pouch",
						JOptionPane.INFORMATION_MESSAGE);
				game.getCurrentPlayer().getRack().addTileToRack(t);
				TileComponent btnTilePouch = new TileComponent(t);
				panelTiles.add(btnTilePouch, BorderLayout.CENTER);
				btnTilePouch.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						panelBoard.add(btnTilePouch);
						repaint();
					}
				});
				panelTiles.revalidate();
				panelTiles.repaint();
				game.getCurrentPlayer().endTurn(panelBoard.getComponents(), game);
				dispose();
				game.setCurrentPlayer(game.nextPlayer());
				PlayerTable playerTable = new PlayerTable(game, panelBoard.getComponents());
				playerTable.setVisible(true);
			}
		});
	}
}