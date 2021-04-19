package game;

import java.util.Objects;

import ai.Player;

public class Die {
	private int value;
	private Player<DiceWar> player1;
	
	public Die(int value, Player<DiceWar> player1) {
		this.value=value;
		this.player1=player1;
	}
	public Die copy() {
		return new Die(value, player1);
	}
	public Player<DiceWar> getPlayer() {
		// TODO Auto-generated method stub
		return player1;
	}
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(player1, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Die))
			return false;
		Die other = (Die) obj;
		return player1 == other.player1 && value == other.value;
	}

}
