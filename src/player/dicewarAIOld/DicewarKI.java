package player.dicewarAI;

import game.Die;
import game.Faction;
import game.Warlands;

public class DicewarKI extends player.Spieler {
	private int maxTurnsCalculated;
	private TurnTree tree;
	private ValueComputer vc;

	public DicewarKI(String name, Faction faction, boolean firstPlayer, int maxTurnsCalculated, ValueComputer vc) {
		super(name, faction, firstPlayer);
		this.maxTurnsCalculated=maxTurnsCalculated;
		this.vc=vc;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Warlands setUp(Warlands w, boolean startPlayer1) {
		// TODO Auto-generated method stub
		if(startPlayer1) {
			w.getDice()[0][1]=new Die(4,firstPlayer);
			w.getDice()[0][3]=new Die(6,firstPlayer);
		}else {
			w.getDice()[6][1]=new Die(5,firstPlayer);
			w.getDice()[6][2]=new Die(5,firstPlayer);
		}
		return w;
	}

	@Override
	public Warlands turn(Warlands w) {
		if(tree==null) {
			tree=new TurnTree(firstPlayer,maxTurnsCalculated,w);
		}
		tree=tree.update(w,maxTurnsCalculated);
		return tree.bestTurn(vc);
	}

}
