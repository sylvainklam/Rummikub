package sdklm.rummikub.tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sdklm.rummikub.game.Game;
import sdklm.rummikub.game.ScoreTable;

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

	private static List<Tile> getGroups(List<Tile> list) {
		List<Tile> groups = new ArrayList<>();
		int number = -1;
		for (Tile t : list) {
			if (number == -1) {
				number = t.getNumber();
			}
			if (t.getNumber() == number) {
				groups.add(t);
				list.remove(t);
			}
		}
		return groups;
	}

	private static List<Tile> getRuns(List<Tile> list) {
		List<Tile> runs = new ArrayList<>();
		Collections.sort(list);
		for (Tile t : list) {
			System.out.println(t.toString());
		}
		return runs;
	}

	public static void analyze(List<Tile> list, Game game) {
		List<Tile> groups = getGroups(list);
		System.out.println("nb groups " + groups.size());
		List<Tile> runs = getRuns(list);
		System.out.println("nb runs " + runs.size());
		if (groups.size() == 0 && runs.size() == 0) {
			ScoreTable scoreTable = new ScoreTable(game.getCurrentPlayer(), game.getCurrentRound(), 0);
			game.getScoreTable().add(scoreTable);
		}
	}
}
