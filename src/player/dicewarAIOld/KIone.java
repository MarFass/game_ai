package player.dicewarAI;

import java.util.ArrayList;
import java.util.List;

import game.*;
import player.Spieler;

public abstract class KIone extends Spieler {
	
	private ValueComputer vComputer;

	
	public KIone(String name, Faction faction, boolean firstPlayer, ValueComputer v) {
		super(name, faction, firstPlayer);
		this.vComputer=v;
		// TODO Auto-generated constructor stub
	}
	
	public Warlands turn(Warlands w) {
		List<Warlands> allMoves=new ArrayList<Warlands>();
		for(int x=0;x<7;x++) {
			for(int y=0;y<7;y++) {
				if(w.getDice()[x][y]!=null&&w.getDice()[x][y].getPlayer()==firstPlayer) {
					allMoves.addAll(w.getMonster(x, y).possibleMoves(w, x, y));
				}
			}
		}
		int bestIndex=0;
		double bestValue=-10000;
		int size=allMoves.size();
		for(int i=0;i<size;i++) {
			//debug
			//allMoves.get(i).printfield();
			double v=vComputer.value(allMoves.get(i),firstPlayer);
			if(v>bestValue) {
				bestIndex=i;
				bestValue=v;
			}
		}
		return allMoves.get(bestIndex);
	}

}
