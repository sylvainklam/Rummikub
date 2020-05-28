package sdklm.rummikub.game;

import java.util.ArrayList;
import java.util.List;

import sdklm.rummikub.tiles.Tile;

public class Game {

	private List<Player> players;
	private List<Round> rounds;

	private Player currentPlayer;
	private Round currentRound;

	private Pouch pouch;

	private List<ScoreTable> scoreTable;

	public Game(int nbPlayers, List<Tile> list) {
		players = new ArrayList<Player>();
		rounds = new ArrayList<Round>();
		scoreTable = new ArrayList<ScoreTable>();
		for (int i = 1; i <= nbPlayers; i++) {
			Player player = new Player(i, list);
			this.getPlayers().add(player);
		}
		this.setCurrentPlayer(this.getPlayers().get(0));
		this.setPouch(new Pouch(list));
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

	public void addRound(Round round) {
		this.getRounds().add(round);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player nextPlayer() {
		int number = getCurrentPlayer().getNumber();
		if (number == getPlayers().size()) {
			return getPlayers().get(0);
		}
		return getPlayers().get(number++);

	}

	public Round getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(Round currentRound) {
		this.currentRound = currentRound;
	}

	public List<ScoreTable> getScoreTable() {
		return scoreTable;
	}

	public void setScoreTable(List<ScoreTable> scoreTable) {
		this.scoreTable = scoreTable;
	}
}