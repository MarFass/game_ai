package ai;

import java.util.List;
import java.util.Scanner;

public class Default_KonsolenSpieler<T extends Game> implements Player<T> {
	private String name="p";

	public Default_KonsolenSpieler(String name) {
		super();
		this.name = name;
	}


	public String toString() {
		return name;
	}




	@Override
	public GameState<T> setUp(GameState<T> gs) {
		// TODO Auto-generated method stub
		return gs;
	}


	@Override
	public GameState<T> turn(GameState<T> gs) {
		System.out.println("--- aktueller Spielstand --- \n"+gs);
		System.out.println("Wähle aus den folgenden Möglichkeiten, deinen nächsten Zug. Durch Eingabe der Nummer.");
		List<? extends GameState<T>> possibleMoves=(List<GameState<T>>) gs.getPossibleMoves();
		int i=0;
		for(GameState<T> newGs:possibleMoves) {
			
			System.out.println("Zug: "+i);
			System.out.println(newGs);
			System.out.println("----------------");
			i++;
		}
		Scanner scanner = new Scanner(System.in);
		boolean noTurn=true;
		int input=0;
		while(noTurn) {
			try {
				input = scanner.nextInt();
				noTurn=false;
			}catch(Exception e) {
				System.out.println("Versuch es nochmal.");
			}
		}
		return possibleMoves.get(input);
	}

}
