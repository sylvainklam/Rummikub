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
	
	public static boolean isGroup(List<Tile> list) {
		int number = -1;
		boolean sameNumber = false;
		if (list.size() == 3 || list.size() == 4) {
			for (Tile t : list) {
				int tileNumber = t.getNumber();
				if (number == -1)
					number = tileNumber;
				if (tileNumber == number) {
					sameNumber = true;
				} else {
					sameNumber = false;
					break;
				}
			}
		}
		return sameNumber;
	}

	public static boolean isRun(List<Tile> list) {
		boolean sameColor = false;
		Color color = null;
		if (list.size() >= 3) {
			for (Tile t : list) {
				Color tileColor = t.getColor();
				if (color == null)
					color = tileColor;
				if (tileColor == color)
					sameColor = true;
				else {
					sameColor = false;
					break;
				}
			}
		}
		return sameColor;
	}
}
