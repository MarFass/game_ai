package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Monster {
	private int stufe;
	
	
	public List<Warlands> possibleMoves(Warlands w, int x, int y){
		List<Warlands> possibleMoves = new ArrayList<Warlands>();
		possibleMoves.addAll(move(w,x,y));
		possibleMoves.addAll(upgrade(w,x,y));
		possibleMoves.addAll(spell(w,x,y));
		possibleMoves.addAll(attack(w,x,y));
		
		return possibleMoves;
	}

	protected List<Warlands> attack(Warlands w, int x, int y) {
		ArrayList<Warlands> ret = new ArrayList<Warlands>();
		Warlands newW;
		if(x-1>=0) {
			if(w.getDice()[x-1][y]!=null&&canAttack(w,x,y,x-1,y)) {
				newW=w.copy();
				newW=newW.getMonster(x-1,y).getAttacked(newW,x-1,y,x,y);
				newW.getDice()[x-1][y]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(x+1<=6) {
			if(w.getDice()[x+1][y]!=null&&canAttack(w,x,y,x+1,y)){
				newW=w.copy();
				newW=newW.getMonster(x+1,y).getAttacked(newW,x+1,y,x,y);
				newW.getDice()[x+1][y]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(y-1>=0) {
			if(w.getDice()[x][y-1]!=null&&canAttack(w,x,y,x,y-1)) {
				newW=w.copy();
				newW=newW.getMonster(x,y-1).getAttacked(newW,x,y-1,x,y);
				newW.getDice()[x][y-1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(y+1<=6) {
			if(w.getDice()[x][y+1]!=null&&canAttack(w,x,y,x,y+1)) {
				newW=w.copy();
				newW=newW.getMonster(x,y+1).getAttacked(newW,x,y+1,x,y);
				newW.getDice()[x][y+1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		return ret;
	}

	protected List<Warlands> spell(Warlands w, int x, int y){
		return new ArrayList<Warlands>();
	}

	protected List<Warlands> move(Warlands w, int x, int y){
		ArrayList<Warlands> ret = new ArrayList<Warlands>();
		Warlands newW;
		if(x-1>=0) {
			if(w.getDice()[x-1][y]==null) {
				newW=w.copy();
				newW.getDice()[x-1][y]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(x+1<=6) {
			if(w.getDice()[x+1][y]==null){
				newW=w.copy();
				newW.getDice()[x+1][y]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(y-1>=0) {
			if(w.getDice()[x][y-1]==null) {
				newW=w.copy();
				newW.getDice()[x][y-1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(y+1<=6) {
			if(w.getDice()[x][y+1]==null) {
				newW=w.copy();
				newW.getDice()[x][y+1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		return ret;
	}

	protected List<Warlands> upgrade(Warlands w, int x, int y){
		ArrayList<Warlands> ret = new ArrayList<Warlands>();
		if(w.getDice()[x][y]!=null&&w.getDice()[x][y].getValue()<6) {
			Warlands newW = w.copy();
			newW.getDice()[x][y]=new Die(newW.getDice()[x][y].getValue()+1,newW.getDice()[x][y].getPlayer());
			ret.add(newW);
		}
		return ret;
	}
	

	protected boolean canAttack(Warlands w,int x1,int y1,int x2,int y2) {
		if(w.getDice()[x1][y1].getPlayer()==w.getDice()[x2][y2].getPlayer()) {
			return false;
		}
		int lvl1 = w.getDice()[x1][y1].getValue();
		int lvl2 = w.getDice()[x2][y2].getValue();
		boolean lvlOK = lvl1>lvl2 || (lvl1==3&&lvl2==6);
		boolean canBeAttacked = w.getMonster(x2,y2).canBeAttacked(w, x2, y2, x1, y1);//TODO
		return lvlOK&&canBeAttacked;
	}
	
	protected boolean canBeAttacked(Warlands w, int x1, int y1, int x2, int y2) {
		return true;
	}
	
	protected boolean canBeEffectedBySpecalties(Warlands w, int x1, int y1, int x2, int y2) {
		return true;
	}
	
	protected Warlands getAttacked(Warlands w,int x1,int y1,int x2,int y2) {
		Warlands ret = w.copy();
		if(ret.getDice()[x2][y2].getPlayer()) {
			ret.setVp1(ret.getVp1()+ret.getDice()[x1][y1].getValue());
		}else {
				ret.setVp2(ret.getVp2()+ret.getDice()[x1][y1].getValue());
		}
		return ret;
	}
}
