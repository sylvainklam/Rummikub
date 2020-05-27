package sdklm.rummikub.ui;

import javax.swing.JButton;

import sdklm.rummikub.tiles.Tile;

@SuppressWarnings("serial")
public class TileComponent extends JButton {

	private Tile tile;

	public TileComponent(Tile t) {
		setTile(t);
		this.setText("" + t.getNumber());
		setForeground(java.awt.Color.WHITE);
		if (t.getColor() == null) {
			setBackground(java.awt.Color.DARK_GRAY);
		} else {
			switch (t.getColor()) {
			case BLACK:
				setBackground(java.awt.Color.black);
				break;
			case RED:
				setBackground(java.awt.Color.red);
				break;
			case BLUE:
				setBackground(java.awt.Color.blue);
				break;
			case ORANGE:
				setBackground(java.awt.Color.orange);
				break;
			default:
				setBackground(java.awt.Color.darkGray);
				break;
			}
		}
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
}
