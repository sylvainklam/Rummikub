package sdklm.rummikub.game;

import java.util.ArrayList;
import java.util.List;

import sdklm.rummikub.tiles.Tile;

public class Game {

	private List<Player> players = new ArrayList<Player>();
	private List<Round> rounds;

	private Pouch pouch;

	public Game(int nbPlayers, List<Tile> list) {
		for (int i = 1; i <= nbPlayers; i++) {
			Player player = new Player(i, list);
			this.getPlayers().add(player);
			this.setPouch(new Pouch(list));
		}
		System.out.println("Game started - pouch content = " + this.getPouch().getContent().size());
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Pouch getPouch() {
		return pouch;
	}

	public void setPouch(Pouch pouch) {
		this.pouch = pouch;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}
}
