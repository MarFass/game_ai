package game;

import java.util.ArrayList;
import java.util.List;

import ai.Player;

public class BloodMage extends Monster {
	protected List<Warlands> spell(Warlands w, int x, int y){
		List<Warlands> ret = new ArrayList<Warlands>();
		
		boolean player=w.getDice()[x][y].getPlayer().equals(w.getP1());
		int reserve;
		if(player) {
			reserve=w.getReserve1();
		}else {
			reserve=w.getReserve2();
		}
		int maxExile=Math.min(getNumBloodMage(w,player), reserve);
		int triggerPoints;
		if(player) {
			triggerPoints=w.getTp1()+1;
			w.setTp1(0);
		}else {
			triggerPoints=w.getTp2()+1;
			w.setTp2(0);
		}
		
		//TODO: Fehler bei bereits vorhandenen Triggerpunkten, die nicht genutzt werden -> Reserve wird größer
			
		upgradeN(w,x,y,maxExile+triggerPoints,0, triggerPoints,ret,0,0,player);
		
		return ret;
		
	}
	
	/**private List<Warlands> upgradeN(Warlands w, int x, int y, int N){
		List<Warlands> ret=new ArrayList<Warlands>();
		upgradeN(w,x,y,N,new ArrayList<Warlands>(),0,0);
	}*/
	
	private void upgradeN(Warlands w, int x, int y, int maxN, int currentN, int tpWithoutExiled, List<Warlands> list, int x2, int y2,boolean player){
		List<Warlands> backUp=new ArrayList<Warlands>();
		for(Warlands m:list) {
			backUp.add(m);
		}
		/*if(N==0) {
			return list;
		}else {
			int y2=yAktuell;
			for(int x2=xAktuell;x<w.getDice().length;x2++) {
				for(;y<w.getDice()[0].length;y2++) {
					if((x2!=x||y2!=y)&&w.getMonster(x2, y2)!=null&&w.getDice()[x2][y2].getValue()<6) {
						Warlands newW=w.copy();
						newW=newW.getMonster(x2, y2).upgrade(newW, x2, y2).get(0);
						list.addAll(upgradeN(newW,x,y,N-1,list,x2,y2));
						if(y2!=6) {
							list.addAll(upgradeN(w,x,y,N,list,x2,y2+1));
						}else if(x2!=6){
							list.addAll(upgradeN(w,x,y,N,list,x2+1,0));
						}else {
							return N;
						}
					}
				}
				y2=0;
			}
		}*/
		
		if(maxN!=0) {
					if((x2!=x||y2!=y)&&w.getMonster(x2, y2)!=null&&w.getDice()[x2][y2].getPlayer().equals(w.getCurrentPlayer())&&w.getDice()[x2][y2].getValue()<6) {
						Warlands newW=w.copy();
						newW=newW.getMonster(x2, y2).upgrade(newW, x2, y2).get(0);
						Warlands newWoldTriggers=newW.copy();
						if(player) {
							newW.setReserve1(newW.getReserve1()-(currentN+1-tpWithoutExiled));
						}else {
							newW.setReserve2(newW.getReserve2()-(currentN+1-tpWithoutExiled));
						}
						list.add(newW);
						try {
							upgradeN(newWoldTriggers,x,y,maxN-1,currentN+1,tpWithoutExiled,list,x2,y2,player);
						}catch(java.lang.OutOfMemoryError e) {list=backUp;}
					}
					if(y2!=6) {
						try {
						upgradeN(w,x,y,maxN,currentN,tpWithoutExiled,list,x2,y2+1,player);
						}catch(java.lang.OutOfMemoryError e) {list=backUp;}
					}else if(x2!=6){
						try {
						upgradeN(w,x,y,maxN,currentN,tpWithoutExiled,list,x2+1,0,player);
						}catch(java.lang.OutOfMemoryError e) {list=backUp;}
					}
				}
		
		}
	
	private int getNumBloodMage(Warlands w, boolean player) {
		Player<DiceWar> p=null;
		if(player) {
			p=w.getP1();
		}else {
			p=w.getP2();
		}
		int num=0;
		for(int x=0;x<w.getDice().length;x++) {
			for(int y=0;y<w.getDice()[0].length;y++) {
				if(w.getDice()[x][y]!=null&&w.getDice()[x][y].getPlayer().equals(p)&&w.getDice()[x][y].getValue()==4) {
					num++;
				}
			}
		}
		return num;
	}
}
