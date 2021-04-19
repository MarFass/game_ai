package player.dicewarAINew;

import ai.*;
import game.*;

public class DefaultEvaluator implements Evaluator<DiceWar> {
	
	public double value(Warlands w, Player<DiceWar> player) {
		Player<DiceWar> opponent = w.getP(!w.getP1().equals(player));
		double ownScore=score(w,player);
		double opponentScore=score(w,opponent);
		return ownScore-opponentScore;
	}

	protected double score(Warlands w, Player<DiceWar> player) {
		boolean isPlayer1=w.getP1().equals(player);
		double score=0;
		int x;
		int y;
		if(w.getP1().equals(player)) {
			x=6;
			if(w.getVp1()==10) {
				return Double.MAX_VALUE;
			}else {
				score+=w.getVp1()*100;
				score+=w.getTp1()*8;
			}
		}else {
			x=0;
			if(w.getVp2()==10) {
				return 10000;
			}else {
				score+=w.getVp2()*100;
				score+=w.getTp2()*8;
			}
		}
		
		boolean monsterToKill=false;
		for(;stopRun(isPlayer1,x);x=runThroughField(isPlayer1,x)) {
			for(y=0;y<7;y++) {
				if(w.getDice()[x][y]!=null) {
					if(!w.getDice()[x][y].getPlayer().equals(player)) {
						monsterToKill=true;
					}
					if(w.getDice()[x][y].getPlayer().equals(player)) {
						int position;
						if(isPlayer1) {
							position=x+1;
						}else {
							position=7-x;
						}
						if(!monsterToKill) {
							position=7-position;
						}
						score+=3+w.getDice()[x][y].getValue()*10+position*w.getDice()[x][y].getValue()/2.0;
						if(w.getDice()[x][y].getValue()==4) {
							score+=50;
						}
					}
				}
			}
		}
		return score;
	}
	
	/**backup
	 * private double score(Warlands w, boolean player) {
		double score=0;
		if(player) {
			if(w.getVp1()==10) {
				return 10000;
			}else {
				score+=w.getVp1()*100;
				score+=w.getTp1()*8;
			}
		}else {
			if(w.getVp2()==10) {
				return 10000;
			}else {
				score+=w.getVp2()*100;
				score+=w.getTp2()*8;
			}
		}
		
		for(int x=0;x<7;x++) {
			for(int y=0;y<7;y++) {
					if(w.getDice()[x][y].getPlayer()==player) {
						int position;
						if(player) {
							position=x+1;
						}else {
							position=7-x;
						}
						score+=3+w.getDice()[x][y].getValue()*10+position*w.getDice()[x][y].getValue()/2.0;
						if(w.getDice()[x][y].getValue()==4) {
							score+=50;
						}
					}
				}
			}
		}
		return score;
	}
	 */
	
	private int runThroughField(boolean player, int x) {
		if(player) {
			return x-1;
		}else {
			return x+1;
		}
	}
	
	private boolean stopRun(boolean player, int x) {
		if(player) {
			return x>=0;
		}else {
			return x<7;
		}
	}

	@Override
	public double value(GameState<DiceWar> gs, Player<DiceWar> p) {
		// TODO Auto-generated method stub
		return value((Warlands)gs,gs.getCurrentPlayer());
	}
}
