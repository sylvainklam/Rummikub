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

	private List<Tile> getTilesPlayedBy(Player p, Component[] components) {
		List<Tile> list = new ArrayList<Tile>();
		for (Component tileComponent : components) {
			TileComponent t = (TileComponent) tileComponent;
			if (t.getTile().getPlayedBy() == p.getNumber())
				list.add(t.getTile());
		}
		return list;
	}

	public int endTurn(Component[] components, Game game) {
		int score = 0;
		System.out.println("end first turn for player " + game.getCurrentPlayer().getNumber());
		List<Tile> list = getTilesPlayedBy(game.getCurrentPlayer(), components);
		Collections.sort(list);
		System.out.println(list);

		if (list.size() < Tileset.MIN_NUM_TILES_IN_GROUP_OR_RUN)
			return score;
		else {
			if (list.size() == Tileset.MIN_NUM_TILES_IN_GROUP_OR_RUN || list.size() == Tileset.MAX_NUM_TILES_IN_GROUP) {
				Tileset tileset = new Tileset(list);
				if (tileset.containsJoker(list)) {
					tileset.setTiles(tileset.removeJoker(list));
					if (tileset.isValidGroup() || tileset.isValidRun()) {
						System.out.println("tileset apres suppression du joker " + tileset);
						if (tileset.isValidGroup()) {
							System.out.println("c'est un groupe");
							// Tile t = new Tile(tileset.getTiles().get(0).getNumber(),)
						}
						if (tileset.isValidRun()) {
							System.out.println("c'est un run");
						}
					}
				} else if (tileset.isValidGroup() || tileset.isValidRun()) {
					score = Tileset.getScore(tileset.getTiles());
					if (score >= 30)
						return score;
				}
			} else {
				Tileset tileset = new Tileset(list);
				if (tileset.isValidRun()) {
					score = Tileset.getScore(tileset.getTiles());
				} else {
					int nbElements = tileset.size();
					System.out.println("nb elements " + nbElements);
					int nbGroupes = nbElements / Tileset.MIN_NUM_TILES_IN_GROUP_OR_RUN;
					System.out.println(
							"nb groupes possibles avec " + Tileset.MIN_NUM_TILES_IN_GROUP_OR_RUN + " : " + nbGroupes);
					int reste = nbElements % Tileset.MIN_NUM_TILES_IN_GROUP_OR_RUN;
					if (reste == 0) {
						System.out.println("on peut former " + nbGroupes + " groupes de "
								+ Tileset.MIN_NUM_TILES_IN_GROUP_OR_RUN + " tiles");
						for (int i = 1; i <= nbGroupes; i++) {
							List<Tile> tiles = tileset.getTiles().subList(0, Tileset.MIN_NUM_TILES_IN_GROUP_OR_RUN);
							Tileset tileGroup = new Tileset(tiles);
							System.out.println("tileGroup " + tileGroup);
							if (tileGroup.isValidGroup() || tileGroup.isValidRun()) {
								score += Tileset.getScore(tileGroup.getTiles());
								System.out.println("score " + score);
								tileset.getTiles().removeAll(tiles);
							}
						}

					} else {
						System.out.println("il y aura " + nbGroupes + " groupes et il faudra ajouter 1 tile sur "
								+ reste + " groupes");
						for (int i = 1; i <= nbGroupes; i++) {
							List<Tile> tiles = tileset.getTiles().subList(0, Tileset.MIN_NUM_TILES_IN_GROUP_OR_RUN + 1);
							Tileset tileGroup = new Tileset(tiles);
							System.out.println("tileGroup " + tileGroup);
							if (tileGroup.isValidGroup() || tileGroup.isValidRun()) {
								score += Tileset.getScore(tileGroup.getTiles());
								System.out.println("score " + score);
								tileset.getTiles().removeAll(tiles);
							}
						}
					}
				}
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
