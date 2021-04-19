package bauernschach;

import ai.Evaluator;
import ai.Game;
import ai.GameState;
import ai.TreeKI;
import ai.TurnTree;

public class BS_TreeKI extends TreeKI<Bauernschach> {
	String name;

	public BS_TreeKI(int depth, Evaluator<Bauernschach> e, String n) {
		super(depth, e);
		name=n;
		// TODO Auto-generated constructor stub
	}

	
	public String toString() {
		return name;
	}

	


	@Override
	public GameState<Bauernschach> setUp(GameState<Bauernschach> gs) {
		// TODO Auto-generated method stub
		return gs;
	}


	


}
