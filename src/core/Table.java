package core;

import java.util.Arrays;

public class Table {
	/* Model :
	 A B C D E F G H 
	a[][][][][][][][]
	b[][][][][][][][]
	c[][][][][][][][]
	d[][][][][][][][]
	e[][][][][][][][]
	f[][][][][][][][] 
	g[][][][][][][][]   
	h[][][][][][][][]
	*/
	private int[][] structure = 
		{
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{-1,0,-1,0,-1,0,-1,0},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,-0,-1,0,-1,0}
		};
	private int currentPlayer = 1;
	private boolean currentPieceMoveExist;
	private boolean isCurrentPieceMaster;
	private int fromCoordNumberX;
	private int fromCoordNumberY;
	private int toCoordNumberX;
	private int toCoordNumberY;
	void checkMovement(String fromCoordString, String toCoordString) {
		String fromCoordNumber = stringToNumberCoords(fromCoordString);
		String toCoordNumber = stringToNumberCoords(toCoordString);
		this.fromCoordNumberX = Integer.parseInt(fromCoordNumber.substring(0,1));
		this.fromCoordNumberY = Integer.parseInt(fromCoordNumber.substring(1,2));
		this.toCoordNumberX = Integer.parseInt(toCoordNumber.substring(0,1));
		this.toCoordNumberY = Integer.parseInt(toCoordNumber.substring(1,2));
		this.currentPieceMoveExist = checkPieceInTable();
		if (this.currentPieceMoveExist){
			this.isCurrentPieceMaster = checkCurrentPieceMaster(fromCoordNumber);
			checkIsEatMovement(toCoordNumber);
		}
	}
	private String stringToNumberCoords(String coordString) {
		/*
		 * take the coords in string and change to number coords to acces in better way on the array
		 * @param coordString the string use to change
		 * @return The numeric coord
		 */
		coordString = coordString.toLowerCase();
		String coordsNumber = "";
		for (int i = 0; i < 2; i ++) {
			String letter = coordString.substring(i,i+1);
			switch (letter) {
			case "a": {
				coordsNumber = coordsNumber.concat("0");
				break;
			}
			case "b": {
				coordsNumber = coordsNumber.concat("1");
				break;
			}
			case "c": {
				coordsNumber = coordsNumber.concat("2");
				break;
			}
			case "d": {
				coordsNumber = coordsNumber.concat("3");
				break;
			}
			case "e": {
				coordsNumber = coordsNumber.concat("4");
				break;
			}	
			case "f": {
				coordsNumber = coordsNumber.concat("5");
				break;
			}
			case "g": {
				coordsNumber = coordsNumber.concat("6");
				break;
			}
			case "h": {
				coordsNumber = coordsNumber.concat("7");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value coord String: " + letter);
			}
		}
		return  coordsNumber;
	}
	private boolean checkPieceInTable(){
		/**
		 * check if the piece exist in the table (of the current player)
		 * @param fromNumber the piece that want to check
		 * @return True or false if the piece exist 
		 */
		if (this.structure[this.fromCoordNumberY][this.fromCoordNumberX] != this.currentPlayer) {
			System.out.println("not this piece does not exits ");
			return false;
		}
		return true;
	}
	private boolean checkCurrentPieceMaster(String fromNumber){
		/**
		 * check if the current piece in use is a piece Master:
		 * @param FromNumber the piece coords that want to shake
		 * @return True or false if the piece is a Master
		 */
		int fromNumberX = Integer.parseInt(fromNumber.substring(0,1));
		int fromNumberY = Integer.parseInt(fromNumber.substring(1,2));
		if (Math.abs(this.structure[fromNumberY][fromNumberX]) != 2) {
			return false;
		}
		return true;
	}
	private boolean checkIsEatMovement(String toNumber){
		/**
		 * check if is a allow movement:
		 * @param toNumber the position that want to do
		 * @return True or false if the piece exist 
		 */
		return true;
	}
	private boolean checkIsEatAllowMovement(String toCoodNumber){

	
		return true;
	}
	public void makeMovement(String toCoodNumeber){

	}
	private void setStructure(){
		this.structure[this.fromCoordNumberY][this.fromCoordNumberX] = 0;
		this.structure[this.toCoordNumberY][this.toCoordNumberX] = this.currentPlayer;
	}
	public int[][] getStructure(){
		return this.structure;
	}
	private void swapPlayer(){
		/* If the player is the 1 swap to -1 and viceversa */
		this.currentPlayer = this.currentPlayer * -1;
	}
	private boolean checkGameFinish(){
		boolean contains;
		for(int[] i: this.structure){
			contains = Arrays.stream(i).anyMatch(x -> x == (this.currentPlayer*-1));
			if (contains){
				return false;
			}
		}
		return true;
	}
}
