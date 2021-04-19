package player;

import game.*;

public abstract class Spieler {
	private String name;
	protected Faction faction;
	protected boolean firstPlayer;


	public Spieler(String name, Faction faction, boolean firstPlayer) {
		super();
		this.setName(name);
		this.faction = faction;
		this.firstPlayer = firstPlayer;
	}

	public Faction getFaction() {
		return faction;
	};

	public abstract Warlands setUp(Warlands w, boolean startPlayer1);

	public abstract Warlands turn(Warlands w);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
