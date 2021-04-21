package game;

import java.util.*;
import ai.*;

public class Warlands implements GameState<DiceWar>{
	private Faction faction1;
	private Faction faction2;
	private Die[][] dice;
	private int vp1=0;
	private int vp2=0;
	private int tp1=0;
	private int tp2=0;
	private int reserve1=15;
	private int reserve2=15;
	private Player<DiceWar> p1;
	private Player<DiceWar> p2;
	private Player<DiceWar> currentPlayer;
	
	

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player<DiceWar> currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * @return the p1
	 */
	public Player<DiceWar> getP1() {
		return p1;
	}

	/**
	 * @return the p2
	 */
	public Player<DiceWar> getP2() {
		return p2;
	}
	
	public Player<DiceWar> getP(boolean p1){
		if(p1) {
			return this.p1;
		}else {
			return this.p2;
		}
	}

	public int getReserve1() {
		return reserve1;
	}

	public void setReserve1(int reserve1) {
		this.reserve1 = reserve1;
	}

	public int getReserve2() {
		return reserve2;
	}

	public void setReserve2(int reserver2) {
		this.reserve2 = reserver2;
	}

	public int getVp1() {
		return vp1;
	}

	public void setVp1(int vp1) {
		this.vp1 = vp1;
	}

	public int getVp2() {
		return vp2;
	}

	public void setVp2(int vp2) {
		this.vp2 = vp2;
	}

	public int getTp1() {
		return tp1;
	}

	public void setTp1(int tp1) {
		this.tp1 = tp1;
		if(this.tp1>10) {
			this.tp1=10;
		}
	}

	public int getTp2() {
		return tp2;
	}

	public void setTp2(int tp2) {
		this.tp2 = tp2;
		if(this.tp2>10) {
			this.tp2=10;
		}
	}
 
	public Warlands(Player<DiceWar> p1, Player<DiceWar> p2, Faction fraction1, Faction fraction2, Die[][] dice) {
		super();
		this.faction1 = fraction1;
		this.faction2 = fraction2;
		this.dice = dice;
		this.p1=p1;
		this.p2=p2;
		this.currentPlayer=p1;
	}

	public Die[][] getDice() {
		return dice;
	}

	public Warlands copy() {
		Warlands ret = new Warlands(p1,p2,faction1, faction2,null);
		ret.dice=new Die[7][7];
		for(int x=0;x<dice[0].length;x++) {
			for(int y=0;y<dice.length;y++) {
				if(dice[x][y]!=null) {
					ret.dice[x][y]=dice[x][y].copy();
				}
			}
		}
		ret.setReserve1(this.getReserve1());
		ret.setReserve2(this.getReserve2());
		ret.setTp1(this.getTp1());
		ret.setTp2(this.getTp2());
		ret.setVp1(this.getVp1());
		ret.setVp2(this.getVp2());
		ret.p1=p1;
		ret.p2=p2;
		ret.currentPlayer=currentPlayer;
		return ret;
	}

	public Monster getMonster(int x, int y) {
		if(dice[x][y]==null) {
			return null;
		}
		if(dice[x][y].getPlayer().equals(p1)) {
			return faction1.getMonster(dice[x][y].getValue());
		}else {
			return faction2.getMonster(dice[x][y].getValue());
		}
	}
	
	public List<Warlands> getPossibleMoves(int x, int y){
		if(dice[x][y]!=null) {
			return getMonster(x,y).possibleMoves(this, x, y);
		}else {
			return null;
		}
	}
	
	public String toString() {
		String ret="";
		ret+="--- Warlands: ---\n";
		ret+="VP1: "+vp1+" TP1: "+tp1+" Reserve: "+reserve1+"\n";
		ret+="VP2: "+vp2+" TP2: "+tp2+" Reserve: "+reserve2+"\n";
		ret+="   0   1   2   3   4   5   6 \n";
		for(int x=0;x<dice[0].length;x++) {
			ret+=x+" ";
			for(int y=0;y<dice.length;y++) {
				
				if(dice[x][y]==null) {
					ret+="ooo ";
				}else {
					if(dice[x][y].getPlayer().equals(p1)) {
						ret+="-"+dice[x][y].getValue()+"- ";
					}else {
						ret+="+"+dice[x][y].getValue()+"+ ";
					}
				}
			}
			ret+="\n";
		}

		return ret;
	}
	
	public void printfield() {
		System.out.println("--- Warlands: ---");
		System.out.println("VP1: "+vp1+" TP1: "+tp1+"Reserve: "+reserve1);
		System.out.println("VP2: "+vp2+" TP2: "+tp2+"Reserve: "+reserve2);
		for(int x=0;x<dice[0].length;x++) {
			for(int y=0;y<dice.length;y++) {
				if(dice[x][y]==null) {
					System.out.print("ooo ");
				}else {
					if(dice[x][y].getPlayer().equals(p1)) {
						System.out.print("-"+dice[x][y].getValue()+"- ");
					}else {
						System.out.print("+"+dice[x][y].getValue()+"+ ");
					}
				}
			}
			System.out.println("");
		}
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(dice);
		result = prime * result + Objects.hash(faction1, faction2, reserve1, reserve2, tp1, tp2, vp1, vp2);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if (this == obj)
			return true;
		if (!(obj instanceof Warlands))
			return false;
		Warlands other = (Warlands) obj;
		boolean equalDice=true;
		for(int x=0;x<7&&equalDice;x++) {
			for(int y=0;y<7&&equalDice;y++) {
				if(dice[x][y]!=null){
				if((!dice[x][y].equals(other.dice[x][y]))) {
					equalDice=false;
				}
				}else if(other.dice[x][y]!=null) {
					equalDice=false;
				}
			}
		}
		return equalDice && Objects.equals(faction1, other.faction1)
				&& Objects.equals(faction2, other.faction2) && reserve1 == other.reserve1
				&& reserve2 == other.reserve2 && tp1 == other.tp1 && tp2 == other.tp2 && vp1 == other.vp1
				&& vp2 == other.vp2;
	}

	@Override
	public Player<DiceWar> getCurrentPlayer() {
		// TODO Auto-generated method stub
		return currentPlayer;
	}

	@Override
	public List<GameState<DiceWar>> getPossibleMoves() {
		List<GameState<DiceWar>> allMoves=new ArrayList<GameState<DiceWar>>();
		for(int x=0;x<7;x++) {
			for(int y=0;y<7;y++) {
				if(this.getDice()[x][y]!=null&&this.getDice()[x][y].getPlayer().equals(currentPlayer)) {
					allMoves.addAll(this.getMonster(x, y).possibleMoves(this, x, y));
				}
			}
		}
		return  allMoves;
	}

	@Override
	public GameState<DiceWar> clone() {
		// TODO Auto-generated method stub
		return this.copy();
	}

	public boolean hasNoMonsters(Player<DiceWar> player) {
		for(int x=0;x<7;x++) {
			for(int y=0;y<7;y++) {
				if(this.getDice()[x][y]!=null&&this.getDice()[x][y].getPlayer().equals(player)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public List<Player<DiceWar>> checkEnd() {
		List<Player<DiceWar>> winners=new ArrayList<Player<DiceWar>>();
		//check Creatures left
				boolean p1HasCreature=false;
				boolean p2HasCreature=false;
				for(int x=0;x<7&&(!p1HasCreature||!p2HasCreature);x++) {
					for(int y=0;y<7&&(!p1HasCreature||!p2HasCreature);y++) {
						if(this.getDice()[x][y]!=null) {
								if(this.getDice()[x][y].getPlayer().equals(p1)) {
									p1HasCreature=true;
								}else {
									p2HasCreature=true;
								}
						}
					}
				}
				if(!p1HasCreature) {
					if(p2HasCreature) {
						winners.add(p1);
						return winners;
					}else {
						winners.add(p1);
						winners.add(p2);
						return winners;
					}
				}else {
					if(!p2HasCreature) {
						winners.add(p2);
						return winners;
					}
				}
				
		//check VP
		if(this.getVp1()>=10||this.getVp2()>=10) {
			if(this.getVp1()>this.getVp2()) {
				winners.add(p1);
				return winners;
			}else if(this.getVp1()<this.getVp2()) {
				winners.add(p2);
				return winners;
			}else {
				winners.add(p1);
				winners.add(p2);
				return winners;
			}
		}
		
		return null;
	}

	public void nextPlayer() {
		if(currentPlayer.equals(p1)) {
			currentPlayer=p2;
		}else {
			currentPlayer=p1;
		}
	}

//	public static void main(String[] args) {
//		Die[][] dice = new Die[7][7];
//		dice[0][0]=new Die(4,true);
//		dice[0][6]=new Die(4,true);
//		dice[3][3]=new Die(1,true);
//		//dice[5][3]=new Die(1,true);
////		dice[5][2]=new Die(3,false);
////		dice[2][4]=new Die(4,false);
////		dice[1][1]=new Die(3,false);
////		dice[6][3]=new Die(2,false);
//		/*dice[6][3]=new Die(6,false);*/
//		Warlands w = new Warlands(new Dragons(), new Dragons(),dice);
//		w.printfield();
//		boolean currentPlayer=true;
//		List<Warlands> currents=new ArrayList<Warlands>();
//		currents.add(w);
//		for(int i=0;i<4;i++) {
//			List<Warlands> newCurrents=new ArrayList<Warlands>();
//			for(Warlands current:currents) {
//				for(int x=0;x<current.dice[0].length;x++) {
//					for(int y=0;y<current.dice.length;y++) {
//						if(current.dice[x][y]!=null&&current.dice[x][y].getPlayer()==currentPlayer) {
//							List<Warlands> l = current.getMonster(x,y).possibleMoves(current, x, y);
//							System.out.println(x+"|"+y);
//							for(Warlands newW: l) {
//								newW.printfield();
//							}
//							newCurrents.addAll(l);
//						}
//					}
//				}
//			}
//			currents=newCurrents;
//			currentPlayer=!currentPlayer;
//		}
//		
//	}
}
