package sdklm.rummikub.game;

public class ScoreTable {

	private int score;
	private Player player;
	private Round round;

	public ScoreTable(Player p, Round r, int score) {
		setRound(r);
		setPlayer(p);
		setScore(score);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}
}
