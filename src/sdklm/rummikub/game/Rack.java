package sdklm.rummikub.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sdklm.rummikub.tiles.Tile;

public class Rack {

	private static final int MAX_TILES = 14;

	private List<Tile> rackTiles = new ArrayList<Tile>();

	public Rack(List<Tile> tiles, int playerNumber) {
		for (int i = 1; i <= MAX_TILES; i++) {
			Tile tile = getRandomTile(tiles);
			tile.setPlayedBy(playerNumber);
			getRackTiles().add(tile);
		}
	}

	public List<Tile> getRackTiles() {
		return rackTiles;
	}

	private Tile getRandomTile(List<Tile> tiles) {
		Random random = new Random();
		Tile randomTile = tiles.get(random.nextInt(tiles.size()));
		tiles.remove(randomTile);
		return randomTile;
	}

	public void addTileToRack(Tile t, Player p) {
		t.setPlayedBy(p.getNumber());
		getRackTiles().add(t);
	}

	public void removeTilesFromRack(List<Tile> list) {
		getRackTiles().removeAll(list);
	}
}
