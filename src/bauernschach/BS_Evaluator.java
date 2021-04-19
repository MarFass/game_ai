package bauernschach;

import ai.Evaluator;
import ai.GameState;
import ai.Player;

public class BS_Evaluator implements Evaluator<Bauernschach> {

	@Override
	public double value(GameState<Bauernschach> gs, Player<Bauernschach> p) {
		Bauer[][] spielfeld=((BauernSchachGameState)gs).getSpielfeld();
		for(int x=0;x<3;x=x+2) {
			for(int y=0;y<3;y++) {
				if(spielfeld[x][y]!=null) {
					if(x==0&&!spielfeld[x][y].movesDown()) {
						if(spielfeld[x][y].getPlayer().equals(p)) {
							return Double.MAX_VALUE;
						}else {
							return Double.MIN_VALUE;
						}
					}
					if(x==2&&spielfeld[x][y].movesDown()) {
						if(spielfeld[x][y].getPlayer().equals(p)) {
							return Double.MAX_VALUE;
						}else {
							return Double.MIN_VALUE;
						}
					}
				}
			}
		}
		if(gs.getPossibleMoves().size()==0) {
			if(p.equals(gs.getCurrentPlayer())) {
				return Double.MIN_VALUE;
			}else {
				return Double.MAX_VALUE;
			}
		}
		return 0;
		
		
	}
	
	

}
