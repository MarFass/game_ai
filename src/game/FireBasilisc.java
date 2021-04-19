package game;

import java.util.ArrayList;
import java.util.List;

import ai.Player;

public class FireBasilisc extends Monster {
	protected List<Warlands> move(Warlands w, int x, int y){
		ArrayList<Warlands> ret = new ArrayList<Warlands>();
		Warlands newW;
		if(x-1>=0&&y-1>=0) {
			if(w.getDice()[x-1][y-1]==null) {
				newW=w.copy();
				newW.getDice()[x-1][y-1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(x+1<=6&&y+1<=6) {
			if(w.getDice()[x+1][y+1]==null){
				newW=w.copy();
				newW.getDice()[x+1][y+1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(y-1>=0&&x+1<=6) {
			if(w.getDice()[x+1][y-1]==null) {
				newW=w.copy();
				newW.getDice()[x+1][y-1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(y+1<=6&&x-1>=0) {
			if(w.getDice()[x-1][y+1]==null) {
				newW=w.copy();
				newW.getDice()[x-1][y+1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		return ret;
	}
	
	protected List<Warlands> attack(Warlands w, int x, int y){
		Player<DiceWar> player = w.getDice()[x][y].getPlayer();
		List<Warlands> first = firstattack(w, x, y);
		List<Warlands> ret = new ArrayList<Warlands>();
		ret.addAll(first);
		//second move/attack
		for(Warlands r:first) {
			try {
			if(w.getDice()[x-1][y-1]!=null
					&&w.getDice()[x-1][y-1].getPlayer()!=player
					&&r.getDice()[x-1][y-1].getPlayer()==player) {
				if(r.getDice()[x-1][y-1].getValue()==5) {
					ret.addAll(move(r,x-1,y-1));
					ret.addAll(attack(r, x-1,y-1));
				}
			}}catch(ArrayIndexOutOfBoundsException e) {}
			try {
			if(w.getDice()[x-1][y+1]!=null
					&&w.getDice()[x-1][y+1].getPlayer()!=player
					&&r.getDice()[x-1][y+1].getPlayer()==player) {
				if(r.getDice()[x-1][y+1].getValue()==5) {
					ret.addAll(move(r,x-1,y+1));
					ret.addAll(attack(r, x-1,y+1));
				}
			}}catch(ArrayIndexOutOfBoundsException e) {}
			try {
			if(w.getDice()[x+1][y-1]!=null
					&&w.getDice()[x+1][y-1].getPlayer()!=player
					&&r.getDice()[x+1][y-1].getPlayer()==player) {
				if(r.getDice()[x+1][y-1].getValue()==5) {
					ret.addAll(move(r,x+1,y-1));
					ret.addAll(attack(r, x+1,y-1));
				}
			}}catch(ArrayIndexOutOfBoundsException e) {}
			try {
			if(w.getDice()[x+1][y+1]!=null
					&&w.getDice()[x+1][y+1].getPlayer()!=player
					&&r.getDice()[x+1][y+1].getPlayer()==player) {
				if(r.getDice()[x+1][y+1].getValue()==5) {
					ret.addAll(move(r,x+1,y+1));
					ret.addAll(attack(r, x+1,y+1));
				}
			}}catch(ArrayIndexOutOfBoundsException e) {}
		}
		return ret;
	}

	private List<Warlands> firstattack(Warlands w, int x, int y) {
		ArrayList<Warlands> ret = new ArrayList<Warlands>();
		Warlands newW;
		if(x-1>=0&&y-1>=0) {
			if(w.getDice()[x-1][y-1]!=null&&canAttack(w,x,y,x-1,y-1)) {
				newW=w.copy();
				newW=newW.getMonster(x-1,y-1).getAttacked(newW,x-1,y-1,x,y);
				newW.getDice()[x-1][y-1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(x+1<=6&&y+1<=6) {
			if(w.getDice()[x+1][y+1]!=null&&canAttack(w,x,y,x+1,y+1)){
				newW=w.copy();
				newW=newW.getMonster(x+1,y+1).getAttacked(newW,x+1,y+1,x,y);
				newW.getDice()[x+1][y+1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(y-1>=0&&x+1<=6) {
			if(w.getDice()[x+1][y-1]!=null&&canAttack(w,x,y,x+1,y-1)) {
				newW=w.copy();
				newW=newW.getMonster(x+1,y-1).getAttacked(newW,x+1,y-1,x,y);
				newW.getDice()[x+1][y-1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		if(y+1<=6&&x-1>=0) {
			if(w.getDice()[x-1][y+1]!=null&&canAttack(w,x,y,x-1,y+1)) {
				newW=w.copy();
				newW=newW.getMonster(x-1,y+1).getAttacked(newW,x-1,y+1,x,y);
				newW.getDice()[x-1][y+1]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				ret.add(newW);
			}
		}
		return ret;
	}
}
