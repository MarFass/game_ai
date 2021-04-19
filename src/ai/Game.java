package ai;

import java.util.ArrayList;
import java.util.List;

import game.DiceWar;

public abstract class Game<T extends Game> {
	protected List<Player<T>> players;
	protected GameState<T> gs;
	
	public Game() {
		players = new ArrayList<Player<T>>();
	}
	
	public Game(List<Player<T>> players, GameState<T> gs) {
		super();
		this.players = players;
		this.gs = gs;
	}
	
	public abstract GameState<T> initializeGameState();
	public abstract void initializePlayers();
	public abstract List<Player<T>> isEnd();
	
	public void startGame() {
		initializeGameState();
		initializePlayers();
		for(Player<T> p:players) {
			gs=(GameState<T>) p.setUp(gs);
		}
	}
	
	public List<Player<T>> round() {
			for(Player<T> p:players) {
				gs=(GameState<T>) p.turn(gs);
				List<Player<T>> winner=isEnd();
				if(winner!=null) {
					return winner;
				}
			}
			return null;
	}
	
	
}
