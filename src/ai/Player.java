package ai;

public interface Player<T extends Game> {
	public GameState<T> setUp(GameState<T> gs);
	public GameState<T> turn(GameState<T> gs);
}
