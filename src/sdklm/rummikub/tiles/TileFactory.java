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

	public static int hasGroup(List<Tile> list) {
		int number = -1;
		int cptSameNumber = 0;
		int score = 0;
		for (Tile t : list) {
			if (number == -1) {
				number = t.getNumber();
				score = number;
				cptSameNumber++;
			}
			// count tiles with same number
			if (t.getNumber() == t.getNumber()) {
				cptSameNumber++;
				score += t.getNumber();
			}
			if (cptSameNumber == 3 || cptSameNumber == 4) {
				return score;
			}
		}
		return score;
	}
}
