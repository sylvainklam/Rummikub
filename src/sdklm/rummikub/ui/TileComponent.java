package sdklm.rummikub.ui;

import java.awt.Color;

import javax.swing.JButton;

import sdklm.rummikub.tiles.Tile;

/**
 * Tile component (JButton like)
 * 
 * @author SKLAM
 *
 */
@SuppressWarnings("serial")
public class TileComponent extends JButton implements Comparable<TileComponent> {

	private Tile tile;

	public TileComponent(Tile t) {
		setTile(t);
		this.setText("" + t.getNumber());
		setForeground(Color.WHITE);
		if (t.getColor() == null) {
			setBackground(Color.white);
			setForeground(Color.black);
		} else {
			switch (t.getColor()) {
			case BLACK:
				setBackground(Color.black);
				break;
			case RED:
				setBackground(Color.red);
				break;
			case BLUE:
				setBackground(Color.blue);
				break;
			case ORANGE:
				setBackground(Color.orange);
				break;
			default:
				setBackground(Color.white);
				setForeground(Color.black);
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

	@Override
	public int compareTo(TileComponent o) {
		return this.getTile().getNumber() - o.getTile().getNumber();
	}
}
