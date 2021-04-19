package game;

import java.util.ArrayList;
import java.util.List;

public class DragonWarrior extends Monster {
	protected List<Warlands> spell(Warlands w, int x, int y){
		List<Warlands> ret=new ArrayList<Warlands>();
		boolean enoughReserve=false;
		boolean player=(w.getDice()[x][y].getPlayer()).equals(w.getP1());
		if(player) {
			enoughReserve=w.getReserve1()>=2;
		}else {
			enoughReserve=w.getReserve2()>=2;
		}
		if(enoughReserve) {
			if(x>0&&x<6) {
				if(w.getDice()[x-1][y]==null&&w.getDice()[x+1][y]==null) {
					Warlands newW=w.copy();
					newW.getDice()[x-1][y]=new Die(1,w.getP(player));
					newW.getDice()[x+1][y]=new Die(1,w.getP(player));
					newW.getDice()[x][y]=new Die(1,w.getP(player));
					if(player) {
						newW.setReserve1(newW.getReserve1()-2);
					}else {
						newW.setReserve1(newW.getReserve2()-2);
					}

					ret.add(newW);
				}
			}
			if(y>0&&y<6) {
				if(w.getDice()[x][y-1]==null&&w.getDice()[x][y+1]==null) {
					Warlands newW=w.copy();
					newW.getDice()[x][y-1]=new Die(1,w.getP(player));
					newW.getDice()[x][y+1]=new Die(1,w.getP(player));
					newW.getDice()[x][y]=new Die(1,w.getP(player));
					if(player) {
						newW.setReserve1(newW.getReserve1()-2);
					}else {
						newW.setReserve1(newW.getReserve2()-2);
					}

					ret.add(newW);
				}
			}
		}
		
		return ret;
	}
	
}
