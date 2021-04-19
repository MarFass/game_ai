package player.dicewarAINew;

import ai.*;
import game.*;

public class DiceWarTreeKI extends TreeKI<DiceWar> {
	private String name;

	public DiceWarTreeKI(String name,int depth, Evaluator<DiceWar> e) {
		super(depth, e);
		this.name=name;
		// TODO Auto-generated constructor stub
	}

	@Override
	public GameState<DiceWar> setUp(GameState<DiceWar> gs) {
		// TODO Auto-generated method stub
		Warlands ret=(Warlands) gs;
		int x;
		if(ret.getP1().equals(this)) {
			x=0;
		}else {
			x=6;
		}
		ret.getDice()[x][1]=new Die(4,this);
		ret.getDice()[x][3]=new Die(6,this);
		return ret;
	}

	public String toString() {
		return name;
	}
}
