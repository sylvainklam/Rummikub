package sdklm.rummikub.tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sdklm.rummikub.game.Game;

/**
 * Tiles list initialization
 * 
 * @author sdklm
 *
 */
public class TileFactory {

	private static List<Tile> tiles = new ArrayList<Tile>();

	public static List<Tile> buildTilesList() {
		for (int n = 1; n <= 2; n++) {
			for (int i = 1; i <= 13; i++) {
				for (Color color : Color.values()) {
					Tile tile = new Tile(i, color);
					tiles.add(tile);
				}
			}
			tiles.add(new Joker());
		}
		return tiles;
	}

	public static void analyze(List<Tile> list, Game game) {
		System.out.println("analyse");
		Collections.sort(list);
		System.out.println(list);

	}

	private int getNbTilesWithSameNumber(List<Tile> list) {
		int nb = 0;
		for (int i = 1; i <= 13; i++) {
			for (Tile t : list) {
				if (t.getNumber() == i) {
					nb++;
				}
			}
		}
		return nb;
	}
}
