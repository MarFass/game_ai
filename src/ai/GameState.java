package ai;

import java.util.List;

public interface GameState<T extends Game> {

	public Player<T> getCurrentPlayer();
	public List<GameState<T>> getPossibleMoves();
	public GameState<T> clone();
	public void setCurrentPlayer(Player<T> p);

}
