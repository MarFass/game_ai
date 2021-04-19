package ai;

import java.util.ArrayList;
import java.util.List;

import bauernschach.BS_Evaluator;
import bauernschach.BS_TreeKI;
import bauernschach.Bauer;
import bauernschach.BauernSchachGameState;
import bauernschach.Bauernschach;


public class TurnTree<T extends Game> {
	private GameState<T> gs;
	private List<TurnTree<T>> nexts;
	private Evaluator<T> e;

	public TurnTree(GameState<T> gs2, int depth, Evaluator<T> e) {
		// TODO Auto-generated constructor stub
		this.gs=(GameState<T>) gs2;
		this.e=e;
		nexts=new ArrayList<TurnTree<T>>();
		if(depth>0) {
			fillNextsWithPossibleMoves(depth);
		}
	}

	public TurnTree<T> update(GameState<T> gs2, int depth) {
		TurnTree<T> newTree=null;
		for(TurnTree<T> next: nexts) {
			if(next.gs.equals(gs2)) {
				newTree=next;
			}
		}
		if(newTree==null) {
			newTree=new TurnTree<T>(gs2,depth,e);
		}else {
			newTree.update(depth);
		}
		return newTree;
	}
	
	private void update(int depth) {
		if(nexts.size()==0) {
			fillNextsWithPossibleMoves(depth);
		}else if(depth>0){
			for(TurnTree<T> next: nexts) {
				next.update(depth-1);
			}
		}
	}
	
	private void fillNextsWithPossibleMoves(int depth) {
		if(depth>0) {
			for(GameState<T> possibleMove: gs.getPossibleMoves()) {
				nexts.add(new TurnTree<T>(possibleMove,depth-1,e));
			}
		}
	}

	public GameState<T> bestTurn(Evaluator<T> e) {
		return bestTurn(e,gs.getCurrentPlayer());
	}
	
	private GameState<T> bestTurn(Evaluator<T> e2, Player<T> p){
		int index=0;
		boolean notOpponent=gs.getCurrentPlayer().equals(p);
		double bestValue;
		if(notOpponent) {
			bestValue=Double.MIN_VALUE;
		}else {
			bestValue=Double.MAX_VALUE;
		}
		int size=nexts.size();
		for(int i=0;i<size;i++) {
			TurnTree<T> next=nexts.get(i);
			double value=next.value(e2,p);
			if(notOpponent) {
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
		return nexts.get(index).gs;
	}
	
	private double value(Evaluator<T> e2, Player<T> p) {
		boolean opponent=!gs.getCurrentPlayer().equals(p);
		double bestValue;
		if(opponent) {
			bestValue=Double.MAX_VALUE;
		}else {
			bestValue=Double.MIN_VALUE;
		}
		if(nexts.size()==0) {
				return e2.value(gs,p);
		}else {
			for(int i=0;i<nexts.size();i++) {
				TurnTree<T> next=nexts.get(i);
				double value=next.value(e2,p);
				if(opponent) {
					if(value<bestValue) {
						bestValue=value;
						if(bestValue==Double.MIN_VALUE) {
							break;
						}
					}
				}else {
					if(value>bestValue) {
						bestValue=value;
						if(bestValue==Double.MAX_VALUE) {
							break;
						}
					}
				}
			}
			return bestValue;
		}
	}
	
	//debug
		public static void main(String[] args) {
			Player<Bauernschach> p1 = new BS_TreeKI(0, null, "p1");
			Player<Bauernschach> p2 = new BS_TreeKI(0, null, "p2");
			/*
			Bauer[][] sf= {{null, null, null},
							{new Bauer(p1,true), new Bauer(p1,true), new Bauer(p2,false)},
							{null,new Bauer(p2,false),null}};
							*/
			Bauer[][] sf= {{new Bauer(p1,true), new Bauer(p1,true), new Bauer(p1,true)},
					{null, null, null},
					{new Bauer(p2,false),null,new Bauer(p2,false)}};
			/*Bauer[][] sf= {{null, null, new Bauer(p1,true)},
					{new Bauer(p1,true), null, null},
					{new Bauer(p2,false),null,new Bauer(p2,false)}};*/
			BauernSchachGameState gs=new BauernSchachGameState(sf,p1,p2);
			BS_Evaluator e=new BS_Evaluator();
			
			TurnTree<Bauernschach> tt=new TurnTree<Bauernschach>(gs,6,e);
			System.out.println("Value for P1: "+tt.value(e, p1));
			System.out.println("Value for P2: "+tt.value(e, p2));
			System.out.println(tt.bestTurn(e));
		}
		
}
