package sdklm.rummikub.game;

import java.util.ArrayList;
import java.util.List;

import sdklm.rummikub.tiles.Tile;

public class Game {

	private List<Player> players = new ArrayList<Player>();
	//private List<Round> rounds;

	public Game(int nbPlayers, List<Tile> list) {
		for (int i = 1; i <= nbPlayers; i++) {
			Player player = new Player(i, list);
			this.getPlayers().add(player);
		}
		System.out.println("Game started");
	}
	
	public List<Player> getPlayers() {
		return players;
	}
}
