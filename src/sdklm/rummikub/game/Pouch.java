package sdklm.rummikub.game;

import java.util.List;
import java.util.Random;

import sdklm.rummikub.tiles.Tile;

/**
 * Game pouch
 * 
 * @author SKLAM
 *
 */
public class Pouch {

	public Pouch(List<Tile> list) {
		setContent(list);
	}

	private List<Tile> content;

	public List<Tile> getContent() {
		return content;
	}

	public void setContent(List<Tile> content) {
		this.content = content;
	}

	public Tile getRandomTile() {
		Random random = new Random();
		Tile randomTile = content.get(random.nextInt(content.size()));
		content.remove(randomTile);
		return randomTile;
	}
}
