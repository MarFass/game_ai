package game;

import java.util.ArrayList;
import java.util.List;

import ai.Player;

public class LightDragon extends Monster {
	protected List<Warlands> spell(Warlands w, int x, int y){
		List<Warlands> ret=new ArrayList<Warlands>();
		boolean enoughReserve=false;
		Player<DiceWar> player=w.getDice()[x][y].getPlayer();
		boolean player1=player.equals(w.getP1());
		if(player1) {
			enoughReserve=w.getReserve1()>=2;
		}else {
			enoughReserve=w.getReserve2()>=2;
		}
		if(enoughReserve) {
			if(x>0&&x<6) {
				if(w.getDice()[x-1][y]==null&&w.getDice()[x+1][y]==null) {
					Warlands newW=w.copy();
					newW.getDice()[x-1][y]=new Die(3,player);
					newW.getDice()[x+1][y]=new Die(3,player);
					newW.getDice()[x][y]=null;
					if(player1) {
						newW.setReserve1(newW.getReserve1()-1);
					}else {
						newW.setReserve1(newW.getReserve2()-1);
					}

					ret.add(newW);
				}
			}
			if(y>0&&y<6) {
				if(w.getDice()[x][y-1]==null&&w.getDice()[x][y+1]==null) {
					Warlands newW=w.copy();
					newW.getDice()[x][y-1]=new Die(3,player);
					newW.getDice()[x][y+1]=new Die(3,player);
					newW.getDice()[x][y]=null;
					if(player1) {
						newW.setReserve1(newW.getReserve1()-1);
					}else {
						newW.setReserve1(newW.getReserve2()-1);
					}

					ret.add(newW);
				}
			}
		}
		
		return ret;
	}
}
