package game;

import java.util.ArrayList;
import java.util.List;

public class Ghost extends Monster {
	protected List<Warlands> move(Warlands w, int x, int y){
		List<Warlands> ret=new ArrayList<Warlands>();
		ret.addAll(super.move(w, x, y));
		Warlands newW;
		if(x-2>=0) {
			if(w.getDice()[x-2][y]==null) {
				newW=w.copy();
				newW.getDice()[x-2][y]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				if(newW.getDice()[x-1][y]!=null) {
						newW.setTp1(newW.getTp1()+1);
						if(newW.getDice()[x-1][y].getValue()==1) {
							newW.getDice()[x-1][y]=null;
						}else {
							newW.getDice()[x-1][y]=new Die(newW.getDice()[x-1][y].getValue()-1,newW.getDice()[x-1][y].getPlayer());
						}
					
				}
				ret.add(newW);
			}
		}
		
		if(x+2<=6) {
			if(w.getDice()[x+2][y]==null){
				newW=w.copy();
				newW.getDice()[x+2][y]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				if(newW.getDice()[x+1][y]!=null) {
						newW.setTp1(newW.getTp1()+1);
						if(newW.getDice()[x+1][y].getValue()==1) {
							newW.getDice()[x+1][y]=null;
						}else {
							newW.getDice()[x+1][y]=new Die(newW.getDice()[x+1][y].getValue()-1,newW.getDice()[x+1][y].getPlayer());
						}
					
				}
				ret.add(newW);
			}
		}
		if(y-2>=0) {
			if(w.getDice()[x][y-2]==null) {
				newW=w.copy();
				newW.getDice()[x][y-2]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				if(newW.getDice()[x][y-1]!=null) {
						newW.setTp1(newW.getTp1()+1);
						if(newW.getDice()[x][y-1].getValue()==1) {
							newW.getDice()[x][y-1]=null;
						}else {
							newW.getDice()[x][y-1]=new Die(newW.getDice()[x][y-1].getValue()-1,newW.getDice()[x][y-1].getPlayer());
						}
					
				}
				ret.add(newW);
			}
		}
		if(y+2<=6) {
			if(w.getDice()[x][y+2]==null) {
				newW=w.copy();
				newW.getDice()[x][y+2]=newW.getDice()[x][y];
				newW.getDice()[x][y]=null;
				if(newW.getDice()[x][y+1]!=null) {
						newW.setTp1(newW.getTp1()+1);
						if(newW.getDice()[x][y+1].getValue()==1) {
							newW.getDice()[x][y+1]=null;
						}else {
							newW.getDice()[x][y+1]=new Die(newW.getDice()[x][y+1].getValue()-1,newW.getDice()[x][y+1].getPlayer());
						}
					
				}
				ret.add(newW);
			}
		}
		return ret;
	}
	
	protected Warlands getAttacked(Warlands w,int x1,int y1,int x2,int y2) {
		Warlands ret = w.copy();
		ret=super.getAttacked(ret, x1, y1, x2, y2);
		ret.getDice()[x2][y2]=new Die(w.getDice()[x2][y2].getValue()-2,w.getDice()[x2][y2].getPlayer());
		return ret;
	}
}

