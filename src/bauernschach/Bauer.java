package bauernschach;

import ai.Player;

public class Bauer {
	private Player<Bauernschach> p;
	private boolean movesDown;

	public Bauer(Player<Bauernschach> p1, boolean movesDown) {
		p=p1;
		this.movesDown=movesDown;
	}

	public Object getPlayer() {
		// TODO Auto-generated method stub
		return p;
	}
	
	/**
	 * @return the movesDown
	 */
	public boolean movesDown() {
		return movesDown;
	}

	public Bauer clone() {
		return new Bauer(p,movesDown);
	}

}
