package player.dicewarAINew;

import java.util.List;

import ai.*;
import game.*;

public class TestAI {
	public static void main(String[] args) {
		testEvaluator();
	}
	
	public static void testEvaluator() {
		Evaluator<DiceWar> e = new DefaultEvaluator();
		Player<DiceWar> p1=new DummyPlayer("p1");
		Player<DiceWar> p2=new DummyPlayer("p2");
		Die[][] dice = new Die[7][7];
		/** ---- Spielsituation -- **/
		dice[0][2]=new Die(4,p1);
		//dice[0][4]=new Die(3,p1);
		dice[1][3]=new Die(5,p2);
		//dice[6][4]=new Die(6,p2);
		/** ---------------------- **/
		
		Warlands w=new Warlands(p1, p2, new Dragons(), new Dragons(),dice);
		System.out.println(w);
		System.out.println("Value P1:"+e.value(w, p1));
		System.out.println("Value P2:"+e.value(w, p2));
	}
	
	public static void testKI() {
		DiceWar g=new DiceWar(new DiceWarTreeKI("p1",2, new DefaultEvaluator()),new DiceWarTreeKI("p2",2, new DefaultEvaluator()));
		g.startGame();
		List<Player<DiceWar>> winner = null;
		int maxTurns=10;
		while(winner==null&&maxTurns>0) {
			winner=g.round();
			maxTurns--;
		}
		if(winner!=null) {
			System.out.println("Winner: "+winner.get(0));
		}
	}
}
