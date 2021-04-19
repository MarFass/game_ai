package player.dicewarAI;

import java.util.ArrayList;
import java.util.List;

import game.*;

public class TurnTree {
	private boolean player1;
	private int depth;
	private Warlands w;
	private List<TurnTree> childs;
	
	public TurnTree(boolean player1, int depth, Warlands w) {
		super();
		this.player1 = player1;
		this.depth = depth;
		this.w = w;
		this.childs = new ArrayList<TurnTree>();
		if(depth>0) {
			List<Warlands> allMoves=w.allPossibleTurns(player1);
			for(Warlands move:allMoves) {
				childs.add(new TurnTree(!player1,depth-1,move));
			}
		}
	}
	

	public TurnTree update(Warlands w2,int depth) {
		// TODO Auto-generated method stub
		TurnTree newTree = null;
		boolean found=false;
		for(TurnTree child: childs) {
			if(child.getWarlands().equals(w2)) {
				newTree=child;
				found=true;
				break;
			}
		}
		if(found) {
			newTree.addDepth();
		}else {
			newTree=new TurnTree(player1,depth,w2);
		}
		return newTree;
	}
	
	public void addDepth() {
		depth+=1;
		if(depth==1) {
			List<Warlands> allMoves=w.allPossibleTurns(player1);
			for(Warlands move:allMoves) {
				childs.add(new TurnTree(!player1,depth-1,move));
			}
		}else {
			for(TurnTree child: childs) {
				child.addDepth();
			}
		}
	}

	private Warlands getWarlands() {
		// TODO Auto-generated method stub
		return w;
	}


	public Warlands bestTurn(ValueComputer vc) {
		return bestTurn(vc,player1);
	}


	private Warlands bestTurn(ValueComputer vc, boolean fixPlayer) {
		int index=0;
		double bestValue;
		if(fixPlayer==player1) {
			bestValue=-100000;
		}else {
			bestValue=100000;
		}
		int size=childs.size();
		for(int i=0;i<size;i++) {
			TurnTree child=childs.get(i);
			double value=child.value(vc,fixPlayer);
			if(fixPlayer==player1) {
				if(value>bestValue) {
					bestValue=value;
					index=i;
				}
			}else {
				if(value<bestValue) {
					bestValue=value;
					index=i;
				}
			}
		}
		return childs.get(index).getWarlands();
		}


	private double value(ValueComputer vc, boolean fixPlayer) {
		double bestValue;
		if(fixPlayer==player1) {
			bestValue=-100000;
		}else {
			bestValue=100000;
		}
		if(childs.size()==0) {
				return vc.value(w, fixPlayer);
		}else {
			for(int i=0;i<childs.size();i++) {
				TurnTree child=childs.get(i);
				double value=child.value(vc,fixPlayer);
				if(fixPlayer==player1) {
					if(value>bestValue) {
						bestValue=value;
					}
				}else {
					if(value<bestValue) {
						bestValue=value;
					}
				}
			}
			return bestValue;
		}
	}
	
}
