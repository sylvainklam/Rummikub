package sdklm.rummikub;

/**
 * Main class for Rummikub game
 */
import java.util.List;

import sdklm.rummikub.game.Game;
import sdklm.rummikub.ui.MainWindow;

public class Rummikub {

	private List<Game> games;

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}
