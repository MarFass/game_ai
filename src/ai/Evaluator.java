package ai;

public interface Evaluator<T extends Game> {

	public double value(GameState<T> gs, Player<T> p);

}
