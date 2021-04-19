package game;

import java.util.ArrayList;
import java.util.List;

import ai.*;
import player.dicewarAINew.*;


public class DiceWar extends Game<DiceWar>{
	
	public DiceWar(Player<DiceWar> s1, Player<DiceWar> s2) {
		super(playerList(s1,s2),null);
		Faction d=new Dragons();
		gs=new Warlands(s1,s2,d,d,new Die[7][7]);
	}
	
	public DiceWar(Player<DiceWar> s1, Player<DiceWar> s2, Faction f1, Faction f2) {
		super(playerList(s1,s2),null);
		gs=new Warlands(s1, s2, f1,f2,new Die[7][7]);
	}
	
	private static List<Player<DiceWar>> playerList(Player<DiceWar> s1, Player<DiceWar> s2) {
		List<Player<DiceWar>> players=new ArrayList<Player<DiceWar>>();
		players.add(s1);
		players.add(s2);
		return players;
	}
	
	public void setUp() {
			gs=(Warlands)players.get(0).setUp((GameState<DiceWar>) gs);
			gs=(Warlands)players.get(1).setUp((GameState< DiceWar>) gs);
	}
	
	//null means not ended, otherwise winner is given
	
	
	

	public void turn(boolean player1) {
		if(player1) {
			gs=players.get(0).turn((GameState<DiceWar>) gs);
		}else {
			gs=players.get(1).turn((GameState<DiceWar>) gs);
		}
	}
	

	@Override
	public GameState<DiceWar> initializeGameState() {
		// TODO Auto-generated method stub
		return (GameState<DiceWar>) gs;
	}

	@Override
	public void initializePlayers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player<DiceWar>> isEnd() {
		return ((Warlands)gs).checkEnd();
	}
	

	public static void main(String[] args) {
		DiceWar g=new DiceWar(new DiceWarTreeKI("p1",2, new DefaultEvaluator()),new DiceWarTreeKI("p2",2, new DefaultEvaluator()));
		g.startGame();
		List<Player<DiceWar>> winner = null;
		int maxTurns=6;
		while(winner==null&&maxTurns>0) {
			winner=g.round();
			System.out.println(g.gs);
			maxTurns--;
		}
		if(winner!=null) {
			System.out.println("Winner: "+winner.get(0));
		}
	}
	
}
