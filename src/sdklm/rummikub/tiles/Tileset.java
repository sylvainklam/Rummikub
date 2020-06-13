package sdklm.rummikub.tiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Tileset {
	public static final int MIN_NUM_TILES_IN_GROUP_OR_RUN = 3;
	public static final int MAX_NUM_TILES_IN_GROUP = 4;

	private List<Tile> tiles;

	public Tileset(List<Tile> tiles) {
		setTiles(tiles);
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}

	public String toString() {
		return getTiles().toString();
	}

	public int size() {
		return getTiles().size();
	}

	private static boolean isValidRankInRun(final int rank, final int cardNumberInRun) {
		return rank >= cardNumberInRun;
	}

	public boolean isAscendingRanks(List<Tile> tiles) {
		int rankExpected = 0;
		boolean first = true;
		for (int i = 0, to = tiles.size(); i < to; i++) {
			final Tile tile = tiles.get(i);
			final int rank = tile.getNumber();
			if (first && 0 != rank) {
				if (!isValidRankInRun(rank, i)) {
					return false;
				}
				rankExpected = rank;
				first = false;
			}
			if (rank != 0 && rankExpected != rank) {
				return false;
			}
			rankExpected++;
		}
		return true;
	}

	private static boolean isDifferentColors(final Collection<Tile> tiles) {
		final Collection<Color> colors = new HashSet<>();
		for (final Tile tile : tiles) {
			final Color cardColor = tile.getColor();
			if (null == cardColor) {
				continue;
			}
			if (colors.contains(cardColor)) {
				return false;
			}
			colors.add(cardColor);
		}
		return true;
	}

	boolean isValid() {
		if (tiles.size() < MIN_NUM_TILES_IN_GROUP_OR_RUN) {
			System.out.print(tiles + " is invalid. Expecting at least " + MIN_NUM_TILES_IN_GROUP_OR_RUN
					+ " cards, found " + tiles.size());
			return false;
		}
		if (isValidGroup()) {
			System.out.println(tiles + "{} is a valid group.");
			return true;
		}
		if (isValidRun()) {
			System.out.println(tiles + " is a valid run.");
			return true;
		}
		System.out.println(tiles + " is not a valid formation.");
		return false;
	}

	public boolean isValidGroup() {
		return tiles.size() <= MAX_NUM_TILES_IN_GROUP && isDifferentColors(tiles) && isSameRanks(tiles);
	}

	private boolean isSameRanks(List<Tile> tiles2) {
		boolean isSameRank = true;
		int rank = -1;
		for (Tile t : tiles2) {
			if (rank == -1)
				rank = t.getNumber();
			if (rank != t.getNumber())
				return false;
		}
		return isSameRank;
	}

	/**
	 * Valid run : all in the same color and ascending rank
	 * 
	 * @return
	 */
	public boolean isValidRun() {
		return isAllSameColor(tiles) && isAscendingRanks(tiles);
	}

	/**
	 * All in the same color ??
	 * 
	 * @param tiles2
	 * @return
	 */
	private boolean isAllSameColor(List<Tile> tiles2) {
		boolean isSameColor = true;
		Color color = null;
		for (Tile t : tiles2) {
			if (color == null)
				color = t.getColor();
			if (color != t.getColor())
				return false;
		}
		return isSameColor;
	}

	/**
	 * Calcul du score du set
	 * 
	 * @param tileset
	 * @return
	 */
	public static int getScore(List<Tile> tiles) {
		int score = 0;
		for (Tile t : tiles) {
			score += t.getNumber();
		}
		return score;
	}

	/**
	 * Tileset contains Joker ??
	 * 
	 * @param tiles
	 * @return
	 */
	public boolean containsJoker(List<Tile> tiles) {
		for (Tile t : tiles) {
			if (t.getNumber() == 0)
				return true;
		}
		return false;
	}

	public List<Tile> removeJoker(List<Tile> list) {
		List<Tile> tilesToRemove = new ArrayList<Tile>();
		for (Tile t : tiles) {
			if (t.getNumber() == 0) {
				tilesToRemove.add(t);
			}
		}
		for (Tile tileToRemove : tilesToRemove) {
			list.remove(tileToRemove);
		}
		Collections.sort(list);
		return list;
	}
}
