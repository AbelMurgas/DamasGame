package core;

import java.util.Scanner;

public class Main {
	private static void showTable(Table table){
		String[] rowLetter = {"a","b","c","d","e","f","g","h"};
		System.out.printf("   A  B  C  D  E  F  G  H\n");
		int contador = 0;
		for (int[] i: table.getStructure()){
			System.out.printf("%s ",rowLetter[contador]);
			for (int j:i){
				switch (j) {
					case 1:
						System.out.printf("[\u001B[41mO\u001B[0m]");
						break;
					case -1:
						System.out.printf("[\u001B[46mO\u001B[0m]");
						break;
					case -2:
						System.out.printf("[\u001B[46mM\u001B[0m]");
						break;
					case 2:
						System.out.printf("[\u001B[41mM\u001B[0m]");
						break;
					default:
						System.out.printf("[ ]");
						break;
				}
			}
			contador++;
			System.out.printf("\n");
		}
		 
	}
	public static void main(String[] args) {
		Table tb = new Table();
		showTable(tb);
		Scanner sc = new Scanner(System.in);
		System.out.printf("input the from move: ");
		String from = sc.nextLine();
		System.out.printf("input the to move: ");
		String to = sc.nextLine();
		tb.checkMovement(from, to);
	}

}
