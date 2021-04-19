package bauernschach;

import ai.GameState;
import ai.Player;

public class DummyPlayer implements Player<Bauernschach> {
	private String name;
	


	public DummyPlayer(String name) {
		super();
		this.name = name;
	}

	@Override
	public GameState<Bauernschach> setUp(GameState<Bauernschach> gs) {
		// TODO Auto-generated method stub
		return gs;
	}

	@Override
	public GameState<Bauernschach> turn(GameState<Bauernschach> gs) {
		// TODO Auto-generated method stub
		try {
		return gs.getPossibleMoves().get(0);
		}catch(IndexOutOfBoundsException e) {
			return gs;
		}
	}
	
	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}else if(!(o instanceof DummyPlayer)){
			return false;
		}else {
			return name.equals(((DummyPlayer)o).name);
		}
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}

}
