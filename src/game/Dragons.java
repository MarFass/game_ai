package game;

public class Dragons extends Faction {

	@Override
	protected Monster getMonster(int value) {
		switch(value) {
		case 1: return new Soul();
		case 2: return new Ghost();
		case 3: return new DragonWarrior();
		case 4: return new BloodMage();
		case 5: return new FireBasilisc();
		case 6: return new LightDragon();
		default: return null;
		}
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Dragons))
			return false;
		return true;
	}

	
}
