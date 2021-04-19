package bauernschach;

import java.util.ArrayList;
import java.util.List;

import ai.Game;
import ai.GameState;
import ai.Player;

public class Bauernschach extends Game<Bauernschach> {
	private Player<Bauernschach> p1;
	private Player<Bauernschach> p2;
	
	

	public Bauernschach(Player<Bauernschach> p1, Player<Bauernschach> p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		players.add(p1);
		players.add(p2);
		gs =new BauernSchachGameState(new Bauer[3][3],p1,p2);
	}

	@Override
	public GameState<Bauernschach> initializeGameState() {
		for(int y=0;y<3;y++) {
			((BauernSchachGameState)gs).getSpielfeld()[0][y]=new Bauer(p1,true);
			((BauernSchachGameState)gs).getSpielfeld()[2][y]=new Bauer(p2,false);
		}
		//Debug
//		((BauernSchachGameState)gs).getSpielfeld()[0][1]=null;
//		((BauernSchachGameState)gs).getSpielfeld()[1][1]=new Bauer(p1,true);
//		return new BauernSchachGameState(((BauernSchachGameState)gs).getSpielfeld(),p2,p1);
		
		//NoDebug
		return new BauernSchachGameState(((BauernSchachGameState)gs).getSpielfeld(),p1,p2);
	}

	@Override
	public void initializePlayers() {
		
	}

	@Override
	public List<Player<Bauernschach>> isEnd() {
		List<Player<Bauernschach>> winner = new ArrayList<Player<Bauernschach>>();
		if(gs.getPossibleMoves().size()==0) {
			if(gs.getCurrentPlayer().equals(p1)) {
				winner.add(p2);
			}else {
				winner.add(p1);
			}
			return winner;
		}
		boolean p1HasBauern=false;
		boolean p2HasBauern=false;
		for(int x=0;x<3;x++) {
			for(int y=0;y<3;y++) {
				if(((BauernSchachGameState)gs).getSpielfeld()[x][y]!=null) {
					if(x==0&&((BauernSchachGameState)gs).getSpielfeld()[x][y].getPlayer().equals(p2)) {
						winner.add(p2);
						return winner;
					}
					if(x==2&&((BauernSchachGameState)gs).getSpielfeld()[x][y].getPlayer().equals(p1)) {
						winner.add(p1);
						return winner;
					}
					if(((BauernSchachGameState)gs).getSpielfeld()[x][y].getPlayer().equals(p1)) {
						p1HasBauern=true;
					}else {
						p2HasBauern=true;
					}
				}
			}
		}
		if(!p1HasBauern) {
			winner.add(p2);
			return winner;
		}
		if(!p2HasBauern) {
			winner.add(p1);
			return winner;
		}
		return null;
	}
	
	public String toString() {
		String ret="--- aktueller Spielstand ---\n";
		for(int x=0;x<3;x++) {
			for(int y=0;y<3;y++) {
				if(((BauernSchachGameState)gs).getSpielfeld()[x][y]==null) {
					ret+="OO ";
				}else {
					ret+=((BauernSchachGameState)gs).getSpielfeld()[x][y].getPlayer()+" ";
				}
			}
			ret+="\n";
		}
		return ret;
	}

	public static void main(String[] args) { 
		String p1="p1";
		String p2="p2";
		Bauernschach b=new Bauernschach(new BS_TreeKI(6, new BS_Evaluator(),p1),new BS_KonsolenSpieler(p2));
		b.startGame();
		List<Player<Bauernschach>> winner = null;
		int maxTurns=6;
		while(winner==null&&maxTurns>0) {
			winner=b.round();
			System.out.println(b);
			maxTurns--;
		}
		if(winner!=null) {
			System.out.println("Winner: "+winner.get(0));
		}
	}
}
