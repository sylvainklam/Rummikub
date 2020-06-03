package sdklm.rummikub.tiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Tiles list initialization
 * 
 * @author sdklm
 *
 */
public class TileFactory {

	private static List<Tile> tiles = new ArrayList<Tile>();

	/**
	 * Init tiles list at the beginning of the game
	 * @return
	 */
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
}
