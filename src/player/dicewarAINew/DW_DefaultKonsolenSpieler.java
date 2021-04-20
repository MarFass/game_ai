package player.dicewarAINew;

import java.util.Scanner;

import ai.*;
import game.*;

public class DW_DefaultKonsolenSpieler extends Default_KonsolenSpieler<DiceWar> {

	public DW_DefaultKonsolenSpieler(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	
	public GameState<DiceWar> setUp(GameState<DiceWar> gs){
		Warlands w=(Warlands) gs;
		/*int x;
		if(w.getP1().equals(this)) {
			x=0;
			w.setReserve1(13);
		}else {
			w.setReserve2(13);
			x=6;
		}
		w.getDice()[x][3]=new Die(5,this);
		w.getDice()[x][4]=new Die(5,this);
		return w;*/
		boolean startPlayer=this.equals(w.getP1());
		if(!startPlayer) {
			System.out.println("Hier ist die Aufstellung deines Mitspielers:");
			w.printfield();
		}
		System.out.println("\n Gib deine Anfangstellung gemäß dem Beispiel \"0406000\" (für einen Blutmagier und einen Drachen an der entsprechenden Position) ein.");
		boolean validSetUp=false;
		Die[] setUp=null;
		while(!validSetUp) {
			try {
				validSetUp=true;
				setUp=getSetUp(startPlayer);
			}catch(IllegalArgumentException e){
				validSetUp=false;
				setUp=null;
			}
		}
		int x=0;
		if(!startPlayer) {
			x=6;
		}
		w.getDice()[x]=setUp;
		return w;
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
			if(value!=0) {
				setUp[i]=new Die(value,this);
			}
		}
		if(summe!=10) {
			throw new IllegalArgumentException("Deine Monster müssen in der Summe eine Stufe von 10 haben.");
		}
		return setUp;
	}
}


