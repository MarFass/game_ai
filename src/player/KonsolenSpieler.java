package player;

import java.util.Scanner;

import game.Die;
import game.Faction;
import game.Warlands;

public class KonsolenSpieler extends Spieler {

	public KonsolenSpieler(String name, Faction faction, boolean firstPlayer) {
		super(name, faction, firstPlayer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Warlands setUp(Warlands w, boolean startPlayer1) {
		// TODO Auto-generated method stub
		if(!startPlayer1) {
			System.out.println("Hier ist die Aufstellung deines Mitspielers:");
			w.printfield();
		}
		System.out.println("Gib deine Anfangstellung gemäß dem Beispiel \"0406000\" (für einen Blutmagier und einen Drachen an der entsprechenden Position) ein.");
		boolean validSetUp=false;
		Die[] setUp=null;
		while(!validSetUp) {
			try {
				validSetUp=true;
				setUp=getSetUp(firstPlayer);
			}catch(IllegalArgumentException e){
				validSetUp=false;
			}
		}
		int x=0;
		if(!startPlayer1) {
			x=6;
		}
		for(int y=0;y<6;y++) {
			w.getDice()[x][y]=setUp[y];
		}
		System.out.println("Gleich geht's los! Du musst immer zunächst eine Kreatur und dann einen der möglichen Züge auswählen.\n"
				+ "Die Kreatur wählst du durch Angabe der Position ein. Dabei ist das Feld oben links das Feld 00,"
				+ "nach unten verändert sich die erste Stelle bis 60 und nach rechts die zweite Stelle bis 06."
				+ "Das Feld ganz unten rechts ist dann entsprechend 66.\n"
				+ "Dir werden dann mit einer Nummer durchnummeriert alle möglichen Züge der Kreatur angezeigt. "
				+ "Durch Eingabe der Zahl, wählst du den Zug. Durch Eingabe von -1 kommst du zurück zur Kreaturenauswahl.\n"
				+ "Viel Spaß!");
		return w;
	}

	@Override
	public Warlands turn(Warlands w) {
		System.out.println("Mit welcher Kreatur möchtest du ziehen?");
		getAndPrintPossibleMoves(w);
		
	}
	
	private void getAndPrintPossibleMoves(Warlands w) {
		// TODO Auto-generated method stub
		try {
			String input=getPosition(w);
		
	}

	private String getPosition(Warlands w) throws IllegalArgumentException{
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
	}

	public Die[] getSetUp(boolean player1) throws IllegalArgumentException {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.length()!=7) {
			scanner.close();
			throw new IllegalArgumentException("Deine Eingabe muss aus genau 7 Ziffern bestehen für die 7 Felder deiner Aufstellungszone.");
		}
		Die[] setUp=new Die[7];
		int summe=0;
		for(int i=0;i<7;i++) {
			int value=input.charAt(i)-'0';
			summe+=value;
			if(value<0||value>6) {
				throw new IllegalArgumentException("Mit deiner Eingabe stimmt etwas nicht.");
			}
			setUp[i]=new Die(value,player1);
		}
		if(summe!=10) {
			throw new IllegalArgumentException("Deine Monster müssen in der Summe eine Stufe von 10 haben.");
		}
		scanner.close();
		return setUp;
	}

}
