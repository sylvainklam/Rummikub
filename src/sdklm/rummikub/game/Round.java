package sdklm.rummikub.game;

public class Round {

	private int number;

	public Round(int number) {
		setNumber(number);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void start(Player p) {
		System.out.println("Round " + this.getNumber() + " start for player " + p.getNumber());
//		System.out.println("Rack content");
//		for (Tile t : p.getRack().getRackTiles()) {
//			System.out.println(t.toString());
//		}
	}
}
