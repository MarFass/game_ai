package player.dicewarAINew;

import ai.*;
import game.*;

public class DW_DefaultKonsolenSpieler extends Default_KonsolenSpieler<DiceWar> {

	public DW_DefaultKonsolenSpieler(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	
	public GameState<DiceWar> setUp(GameState<DiceWar> gs){
		Warlands w=(Warlands) gs;
		int x;
		if(w.getP1().equals(this)) {
			x=0;
			w.setReserve1(13);
		}else {
			w.setReserve2(13);
			x=6;
		}
		w.getDice()[x][3]=new Die(5,this);
		w.getDice()[x][4]=new Die(5,this);
		return w;
	}
}


