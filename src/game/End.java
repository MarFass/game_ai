package game;
import ai.Player;
import player.Spieler;

public class End {
	private String winner="";

	public End(Player<DiceWar> p1) {
		// TODO Auto-generated constructor stub
		winner=p1.getName();
	}

	public End(String string) {
		// TODO Auto-generated constructor stub
		winner=string;
	}

	public String getResult() {
		// TODO Auto-generated method stub
		return "Winner: "+winner;
	}

}
