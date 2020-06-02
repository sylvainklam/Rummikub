package sdklm.rummikub.game;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import sdklm.rummikub.tiles.Tile;
import sdklm.rummikub.tiles.TileFactory;
import sdklm.rummikub.ui.TileComponent;

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

	public void takeTile(Tile t) {
		this.getRack().getRackTiles().add(t);
	}

	public int endRound(Component[] components, Game game) {
		System.out.println("end Round " + game.getCurrentRound().getNumber() + " for player "
				+ game.getCurrentPlayer().getNumber());
		List<Tile> list = new ArrayList<Tile>();
		for (Component tileComponent : components) {
			TileComponent t = (TileComponent) tileComponent;
			list.add(t.getTile());
		}
		return TileFactory.analyze(list, game);
	}
}
