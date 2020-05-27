package sdklm.rummikub.game;

import java.util.List;

import sdklm.rummikub.tiles.Tile;

public class Player {

	private int number;

	private Rack rack;

	public Player(int number, List<Tile> list) {
		setNumber(number);
		Rack rack = new Rack(list);
		setRack(rack);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Rack getRack() {
		return rack;
	}

	public void setRack(Rack rack) {
		this.rack = rack;
	}

}
