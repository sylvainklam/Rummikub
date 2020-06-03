package sdklm.rummikub.tiles;

/**
 * A single Tile Object
 * 
 * @author sdklm
 *
 */
public class Tile implements Comparable<Tile> {

	private int number;// from 1 to 13
	private Color color;// see Color Enumeration
	private int playedBy;
	
	public Tile() {// this will be a joker by default
		setNumber(0);
		setColor(null);
	}

	public Tile(int number, Color color) {
		setNumber(number);
		setColor(color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String toString() {
		return "Tile " + getNumber() + " (" + getColor() + ")";
	}

	@Override
	public int compareTo(Tile o) {
		int compareNumber = ((Tile) o).getNumber();
		return this.number - compareNumber;
	}

	public int getPlayedBy() {
		return playedBy;
	}

	public void setPlayedBy(int playedBy) {
		this.playedBy = playedBy;
	}
}