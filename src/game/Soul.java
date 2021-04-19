package game;

import java.util.ArrayList;
import java.util.List;

public class Soul extends Monster {
	@Override
	protected boolean canBeAttacked(Warlands w, int x1, int y1, int x2, int y2) {
		int adjacentSouls=adjacentSouls(w,x1,y1);
		if(adjacentSouls>=2) {
			return false;
		}else if(adjacentSouls==0) {
			return true;
		}else {
			if(x1>0) {
				if(w.getDice()[x1-1][y1]!=null
						&&w.getDice()[x1-1][y1].getPlayer()==w.getDice()[x1][y1].getPlayer()
						&&w.getMonster(x1-1, y1) instanceof Soul
						&&((Soul)w.getMonster(x1-1, y1)).adjacentSouls(w,x1-1,y1)>=2) {
					return false;
				}
			}
			if(x1<6) {
				if(w.getDice()[x1+1][y1]!=null
						&&w.getDice()[x1+1][y1].getPlayer()==w.getDice()[x1][y1].getPlayer()
						&&w.getMonster(x1+1, y1) instanceof Soul
						&&((Soul)w.getMonster(x1+1, y1)).adjacentSouls(w,x1+1,y1)>=2) {
					return false;
				}
			}
			if(y1>0) {
				if(w.getDice()[x1][y1-1]!=null
						&&w.getDice()[x1][y1-1].getPlayer()==w.getDice()[x1][y1].getPlayer()
						&&w.getMonster(x1, y1-1) instanceof Soul
						&&((Soul)w.getMonster(x1, y1-1)).adjacentSouls(w,x1,y1-1)>=2) {
					return false;
				}
			}
			if(y1<6) {
				if(w.getDice()[x1][y1+1]!=null
						&&w.getDice()[x1][y1+1].getPlayer()==w.getDice()[x1][y1].getPlayer()
						&&w.getMonster(x1, y1+1) instanceof Soul
						&&((Soul)w.getMonster(x1, y1+1)).adjacentSouls(w,x1,y1+1)>=2) {
					return false;
				}
			}
		}
		return true;
	}
	
	protected List<Warlands> move(Warlands w, int x, int y){
		List<Warlands> ret=new ArrayList<Warlands>();
		ret.addAll(super.move(w, x, y));
		if(x>0) {
			if(w.getDice()[x-1][y]!=null
					&&w.getDice()[x-1][y].getPlayer()==w.getDice()[x][y].getPlayer()&&w.getMonster(x-1, y) instanceof Soul) {
				Warlands newW=w.copy();
				boolean player=newW.getDice()[x][y].getPlayer().equals(newW.getP1());
				newW.getDice()[x][y]=null;
				List<Warlands> steps = new ArrayList<Warlands>();
				steps=w.getMonster(x-1, y).move(newW, x-1, y);
				for(Warlands ins:steps) {
					if(player) {
						ins.getDice()[x-1][y]=new Die(1,newW.getP1());
					}else {
						ins.getDice()[x-1][y]=new Die(1,newW.getP2());
					}
				}
				ret.addAll(steps);
			}
		}
		if(x<6) {
			if(w.getDice()[x+1][y]!=null
					&&w.getDice()[x+1][y].getPlayer()==w.getDice()[x][y].getPlayer()
					&&w.getMonster(x+1, y) instanceof Soul) {
				Warlands newW=w.copy();
				boolean player=newW.getDice()[x][y].getPlayer().equals(newW.getP1());
				newW.getDice()[x][y]=null;
				List<Warlands> steps = new ArrayList<Warlands>();
				steps=w.getMonster(x+1, y).move(newW, x+1, y);
				for(Warlands ins:steps) {
					if(player) {
						ins.getDice()[x+1][y]=new Die(1,newW.getP1());
					}else {
						ins.getDice()[x+1][y]=new Die(1,newW.getP2());
					}
				}
				ret.addAll(steps);
			}
		}
		if(y>0) {
			if(w.getDice()[x][y-1]!=null
					&&w.getDice()[x][y-1].getPlayer()==w.getDice()[x][y].getPlayer()&&w.getMonster(x, y-1) instanceof Soul) {
				Warlands newW=w.copy();
				boolean player=newW.getDice()[x][y].getPlayer().equals(newW.getP1());
				newW.getDice()[x][y]=null;
				List<Warlands> steps = new ArrayList<Warlands>();
				steps=w.getMonster(x, y-1).move(newW, x, y-1);
				for(Warlands ins:steps) {
					if(player) {
						ins.getDice()[x][y-1]=new Die(1,newW.getP1());
					}else {
						ins.getDice()[x][y-1]=new Die(1,newW.getP2());
					}
				}
				ret.addAll(steps);
			}
		}
		if(y<6) {
			if(w.getDice()[x][y+1]!=null
					&&w.getDice()[x][y+1].getPlayer()==w.getDice()[x][y].getPlayer()&&w.getMonster(x, y+1) instanceof Soul) {
				Warlands newW=w.copy();
				boolean player=newW.getDice()[x][y].getPlayer().equals(newW.getP1());
				newW.getDice()[x][y]=null;
				List<Warlands> steps = new ArrayList<Warlands>();
				steps=w.getMonster(x, y+1).move(newW, x, y+1);
				for(Warlands ins:steps) {
					if(player) {
						ins.getDice()[x][y+1]=new Die(1,newW.getP1());
					}else {
						ins.getDice()[x][y+1]=new Die(1,newW.getP2());
					}
				}
				ret.addAll(steps);
			}
		}
		return ret;
		
	}
	
	private int adjacentSouls(Warlands w, int x1, int y1) {
		int adjacentSouls=0;
		if(x1>0) {
			if(w.getDice()[x1-1][y1]!=null
					&&w.getDice()[x1-1][y1].getPlayer()==w.getDice()[x1][y1].getPlayer()&&w.getMonster(x1-1, y1) instanceof Soul) {
				adjacentSouls++;
			}
		}
		if(x1<6) {
			if(w.getDice()[x1+1][y1]!=null
					&&w.getDice()[x1+1][y1].getPlayer()==w.getDice()[x1][y1].getPlayer()&&w.getMonster(x1+1, y1) instanceof Soul) {
				adjacentSouls++;
			}
		}
		if(y1>0) {
			if(w.getDice()[x1][y1-1]!=null
					&&w.getDice()[x1][y1-1].getPlayer()==w.getDice()[x1][y1].getPlayer()&&w.getMonster(x1, y1-1) instanceof Soul) {
				adjacentSouls++;
			}
		}
		if(y1<6) {
			if(w.getDice()[x1][y1+1]!=null
					&&w.getDice()[x1][y1+1].getPlayer()==w.getDice()[x1][y1].getPlayer()&&w.getMonster(x1, y1+1) instanceof Soul) {
				adjacentSouls++;
			}
		}
		return adjacentSouls;
	}
}
