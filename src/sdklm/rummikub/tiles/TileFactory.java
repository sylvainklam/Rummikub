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

	private static int getTilesOfSameColor(List<Tile> list) {
		int score = 0;
		Collections.sort(list);
		System.out.println("list tiles before check same color " + list);
		List<Tile> listWithSameColor = new ArrayList<Tile>();
		Color colorToTest = null;
		for (Tile t : list) {
			if (colorToTest == null)
				colorToTest = t.getColor();
			if (t.getColor().equals(colorToTest)) {
				listWithSameColor.add(t);
			}
		}
		for (Tile t : listWithSameColor) {
			score += t.getNumber();
			list.remove(t);
		}
		return score;
	}

	public static int analyze(List<Tile> list, Game game) {
		int score = 0;
		System.out.println("analyse");
		Collections.sort(list);
		System.out.println(list);
		System.out.println("GROUPS");
		List<Tile> listWithSameNumber = new ArrayList<Tile>();
		do {
			List<Tile> tempList = new ArrayList<Tile>();
			listWithSameNumber = getTilesWithSameNumber(list);
			System.out.println(listWithSameNumber);
			for (Tile t : listWithSameNumber) {
				tempList.add(t);
			}
			if (tempList.size() == 3 || tempList.size() == 4) {
				for (Tile t : tempList) {
					score += t.getNumber();
				}
				tempList.clear();
			}

			if (listWithSameNumber.size() == 0)
				break;
		} while (list.size() != 0);
		score += getTilesOfSameColor(list);
		System.out.println("score : " + score);
		return score;
	}

	/**
	 * Get tiles with same numbers (groups)
	 * 
	 * @param list
	 * @return
	 */
	private static List<Tile> getTilesWithSameNumber(List<Tile> list) {
		List<Tile> listToReturn = new ArrayList<Tile>();
		int number = -1;
		for (Tile t : list) {
			if (number == -1)
				number = t.getNumber();
			if (t.getNumber() == number) {
				listToReturn.add(t);
			}
		}
		if (listToReturn.size() > 1) {
			for (Tile t : listToReturn) {
				list.remove(t);
			}
		}
		if (listToReturn.size() == 1)
			listToReturn.clear();
		return listToReturn;
	}
}
