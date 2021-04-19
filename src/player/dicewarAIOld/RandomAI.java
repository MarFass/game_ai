package player.dicewarAI;

import java.util.List;

import game.*;
import player.Spieler;

public class RandomAI extends Spieler {
	public RandomAI(String name, Faction faction, boolean firstPlayer) {
		super(name, faction, firstPlayer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Warlands setUp(Warlands w, boolean startPlayer1) {
		// TODO Auto-generated method stub
		if(startPlayer1) {
			w.getDice()[0][1]=new Die(4,firstPlayer);
			w.getDice()[0][3]=new Die(6,firstPlayer);
		}else {
			w.getDice()[6][1]=new Die(4,firstPlayer);
			w.getDice()[6][3]=new Die(6,firstPlayer);
		}
		return w;
	}
	
	public Warlands turn(Warlands w) {
		double numCreatures=countOwnCreatures(w);
		double chance = 1.0/numCreatures;
		boolean decided=false;
		Monster m=null;
		int x=0;
		int y=0;
		for(x=0;x<7&&!decided;x++) {
			for(y=0;y<7&&!decided;y++) {
				if(w.getDice()[x][y]!=null&&w.getDice()[x][y].getPlayer()==firstPlayer) {
					if(Math.random()<chance) {
						decided=true;
						m=w.getMonster(x, y);
					}else {
						numCreatures--;
						chance=1.0/numCreatures;
					}
				}
			}
		}
		x--;
		y--;
		List<Warlands> possibleMoves = m.possibleMoves(w, x, y);
		int randomMove=(int)(Math.random()*possibleMoves.size());
		return possibleMoves.get(randomMove);
	}
	
	private int countOwnCreatures(Warlands w) {
		int count=0;
		for(int x=0;x<7;x++) {
			for(int y=0;y<7;y++) {
				if(w.getDice()[x][y]!=null&&w.getDice()[x][y].getPlayer()==firstPlayer) {
					count++;
				}
			}
		}
		return count;
	}

	
	
}
