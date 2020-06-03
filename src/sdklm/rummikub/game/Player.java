package sdklm.rummikub.game;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sdklm.rummikub.tiles.Tile;
import sdklm.rummikub.tiles.Tileset;
import sdklm.rummikub.ui.TileComponent;

public class Player {

	private int number;

	private Rack rack;

	private boolean isFirstTurn;

	public Player(int number, List<Tile> list) {
		setNumber(number);
		Rack rack = new Rack(list, number);
		setRack(rack);
		setFirstTurn(true);
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

	public int endTurn(Component[] components, Game game) {
		int score = 0;
		System.out.println("end first turn for player " + game.getCurrentPlayer().getNumber());
		List<Tile> list = new ArrayList<Tile>();
		for (Component tileComponent : components) {
			TileComponent t = (TileComponent) tileComponent;
			if (t.getTile().getPlayedBy() == game.getCurrentPlayer().getNumber())
				list.add(t.getTile());
		}
		Collections.sort(list);
		System.out.println(list);

		if (list.size() < Tileset.MIN_NUM_CARDS_IN_GROUP_OR_RUN)
			return score;
		else {
			Tileset tileset = new Tileset(list);
			if (tileset.isValidGroup() || tileset.isValidRun()) {
				score = Tileset.getScore(tileset);
				if (score >= 30)
					return score;
			}
		}
		return score;
	}

	public boolean isFirstTurn() {
		return isFirstTurn;
	}

	public void setFirstTurn(boolean isFirstTurn) {
		this.isFirstTurn = isFirstTurn;
	}
}
