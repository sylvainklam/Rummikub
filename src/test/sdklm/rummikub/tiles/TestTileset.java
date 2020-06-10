package test.sdklm.rummikub.tiles;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import sdklm.rummikub.tiles.Color;
import sdklm.rummikub.tiles.Tile;
import sdklm.rummikub.tiles.Tileset;

class TestTileset {

	@Test
	void testRun() {
		List<Tile> listRun = new ArrayList<Tile>();
		for (int i = 0; i < 3; i++) {
			Tile t = new Tile(i++, Color.BLACK);
			listRun.add(t);
		}
//		Tile t1 = new Tile(5, Color.BLACK);
//		listRun.add(t1);
		Tileset tileset = new Tileset(listRun);
		System.out.println(tileset.isValidRun());
	}

	@Test
	void testGroup() {
		List<Tile> listGroup = new ArrayList<Tile>();
		Tile t1 = new Tile(5, Color.BLACK);
		Tile t2 = new Tile(5, Color.BLUE);
		Tile t3 = new Tile(5, Color.ORANGE);
		listGroup.add(t1);
		listGroup.add(t2);
		listGroup.add(t3);

		Tileset tileset = new Tileset(listGroup);
		System.out.println(tileset.isValidGroup());
	}

}
