package player.dicewarAI;

import game.Warlands;

public class ValueComputerMag5en extends DefaultValueComputer {
	

	protected double score(Warlands w, boolean player) {
		double score=super.score(w, player);
		for(int x=0;x<7;x++) {
			for(int y=0;y<7;y++) {
				if(w.getDice()[x][y]!=null) {
					if(w.getDice()[x][y].getPlayer()==player) {
						if(w.getDice()[x][y].getValue()==5) {
							score+=50;
						}
					}
				}
			}
		}
		return score;
	}
}
