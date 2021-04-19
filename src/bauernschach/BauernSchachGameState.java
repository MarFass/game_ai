package bauernschach;

import java.util.ArrayList;
import java.util.List;

import ai.GameState;
import ai.Player;

public class BauernSchachGameState implements GameState<Bauernschach> {
	private Bauer[][] spielfeld;
	private Player<Bauernschach> currentPlayer;
	private Player<Bauernschach> otherPlayer;

	public BauernSchachGameState() {}

	public BauernSchachGameState(Bauer[][] spielfeld, Player<Bauernschach> currentPlayer,Player<Bauernschach> otherPlayer) {
		super();
		this.spielfeld = spielfeld;
		this.currentPlayer = currentPlayer;
		this.otherPlayer = otherPlayer;
	}

	@Override
	public Player<Bauernschach> getCurrentPlayer() {
		// TODO Auto-generated method stub
		return currentPlayer;
	}

	@Override
	public List<GameState<Bauernschach>> getPossibleMoves() {
		List<GameState<Bauernschach>> moves=new ArrayList<GameState<Bauernschach>>();
		if(this.isEnd()) {
			return moves;
		}
		for(int x=0;x<3;x++) {
			for(int y=0;y<3;y++) {
				if(spielfeld[x][y]!=null&&spielfeld[x][y].getPlayer().equals(currentPlayer)) {
					if(spielfeld[x][y].movesDown()) {
						if(canMoveTo(x+1,y,currentPlayer,false)) {
							BauernSchachGameState gs=this.clone();
							gs.spielfeld[x+1][y]=gs.spielfeld[x][y];
							gs.spielfeld[x][y]=null;
							gs.currentPlayer=this.otherPlayer;
							gs.otherPlayer=this.currentPlayer;
							moves.add(gs);
							//debug
	//						System.out.println(gs);
						}
						if(canMoveTo(x+1,y-1,currentPlayer,true)) {
							BauernSchachGameState gs=this.clone();
							gs.spielfeld[x+1][y-1]=gs.spielfeld[x][y];
							gs.spielfeld[x][y]=null;
							gs.currentPlayer=this.otherPlayer;
							gs.otherPlayer=this.currentPlayer;
							moves.add(gs);
							//debug
	//						System.out.println(gs);
						}
						if(canMoveTo(x+1,y+1,currentPlayer,true)) {
							BauernSchachGameState gs=this.clone();
							gs.spielfeld[x+1][y+1]=gs.spielfeld[x][y];
							gs.spielfeld[x][y]=null;
							gs.currentPlayer=this.otherPlayer;
							gs.otherPlayer=this.currentPlayer;
							moves.add(gs);
							//debug
	//						System.out.println(gs);
						}}
						else{
							if(canMoveTo(x-1,y,currentPlayer,false)) {
								BauernSchachGameState gs=this.clone();
								gs.spielfeld[x-1][y]=gs.spielfeld[x][y];
								gs.spielfeld[x][y]=null;
								gs.currentPlayer=this.otherPlayer;
								gs.otherPlayer=this.currentPlayer;
								moves.add(gs);
								//debug
		//						System.out.println(gs);
							}
							if(canMoveTo(x-1,y-1,currentPlayer,true)) {
								BauernSchachGameState gs=this.clone();
								gs.spielfeld[x-1][y-1]=gs.spielfeld[x][y];
								gs.spielfeld[x][y]=null;
								gs.currentPlayer=this.otherPlayer;
								gs.otherPlayer=this.currentPlayer;
								moves.add(gs);
								//debug
		//						System.out.println(gs);
							}
							if(canMoveTo(x-1,y+1,currentPlayer,true)) {
								BauernSchachGameState gs=this.clone();
								gs.spielfeld[x-1][y+1]=gs.spielfeld[x][y];
								gs.spielfeld[x][y]=null;
								gs.currentPlayer=this.otherPlayer;
								gs.otherPlayer=this.currentPlayer;
								moves.add(gs);
								//debug
		//						System.out.println(gs);
							}
						
					}
				}
			}
		}
		//TODO
		return moves;
	}
	
	private boolean isEnd() {
		boolean p1HasBauern=false;
		boolean p2HasBauern=false;
		for(int x=0;x<3;x++) {
			for(int y=0;y<3;y++) {
				if(((BauernSchachGameState)this).getSpielfeld()[x][y]!=null) {
					if(x==0&&(!((BauernSchachGameState)this).getSpielfeld()[x][y].movesDown())) {
						return true;
					}
					if(x==2&&((BauernSchachGameState)this).getSpielfeld()[x][y].movesDown()) {
						return true;
					}
					if(((BauernSchachGameState)this).getSpielfeld()[x][y].getPlayer().equals(this.currentPlayer)) {
						p1HasBauern=true;
					}else {
						p2HasBauern=true;
					}
				}
			}
		}
		if(!p1HasBauern||!p2HasBauern) {
			return true;
		}
		return false;
	}
	
	
	private boolean canMoveTo(int x, int y, Player p, boolean schlaegt) {
		if(x<0||x>2||y<0||y>2) {
			return false;
		}
		if(spielfeld[x][y]!=null) {
			if(schlaegt&&!spielfeld[x][y].getPlayer().equals(p)) {
				return true;
			}else {
				return false;
			}
		}
		return !schlaegt;
	}
	
	public BauernSchachGameState clone() {
		BauernSchachGameState gs=new BauernSchachGameState();
		Bauer[][] newSpielfeld = new Bauer[3][3];
		for(int x=0;x<3;x++) {
			for(int y=0;y<3;y++) {
				if(spielfeld[x][y]==null) {
					newSpielfeld[x][y]=null;
				}else {
					newSpielfeld[x][y]=spielfeld[x][y].clone();
				}
			}
		}
		gs.spielfeld=newSpielfeld;
		gs.currentPlayer=currentPlayer;
		return gs;
	}
	
	public String toString() {
		String ret="";
		ret+="aktueller Spieler: "+currentPlayer.toString()+"\n";
		for(int x=0;x<3;x++) {
			for(int y=0;y<3;y++) {
				if(spielfeld[x][y]==null) {
					ret+="OO ";
				}else {
					ret+=spielfeld[x][y].getPlayer()+" ";
				}
			}
			ret+="\n";
		}
		return ret;
	}
	
	
	/**
	 * @return the spielfeld
	 */
	public Bauer[][] getSpielfeld() {
		return spielfeld;
	}

	public void setCurrentPlayer(Player<Bauernschach> p) {
		this.currentPlayer=p;
	}

}
