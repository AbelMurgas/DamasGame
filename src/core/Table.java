package core;

import java.util.Arrays;

public class Table {
	/*
	 * Model :
	 * A B C D E F G H
	 * a[][][1][][][][][]
	 * b[][][][][][][][]
	 * c[1][][][][1][][][]
	 * d[][][][][][][][]
	 * e[][][][][][][][]
	 * f[][][-1][][][][][]
	 * g[-1][][][][][][][]
	 * h[][][][][][][][]
	 */
	private int[][] structure = {
			{ 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ -1, 0, -1, 0, -1, 0, -1, 0 },
			{ 0, -1, 0, -1, 0, -1, 0, -1 },
			{ -1, 0, -1, -0, -1, 0, -1, 0 }
	};
	private int currentPlayer = 1;
	private boolean currentPieceMoveExist;
	private boolean isCurrentPieceMaster;
	private int fromCoordNumberX;
	private int fromCoordNumberY;
	private int toCoordNumberX;
	private int toCoordNumberY;
	private boolean currentMovementAllow;

	public void checkMovement(String fromCoordString, String toCoordString) {
		String fromCoordNumber = stringToNumberCoords(fromCoordString);
		String toCoordNumber = stringToNumberCoords(toCoordString);
		this.fromCoordNumberX = Integer.parseInt(fromCoordNumber.substring(0, 1));
		this.fromCoordNumberY = Integer.parseInt(fromCoordNumber.substring(1, 2));
		this.toCoordNumberX = Integer.parseInt(toCoordNumber.substring(0, 1));
		this.toCoordNumberY = Integer.parseInt(toCoordNumber.substring(1, 2));
		this.currentPieceMoveExist = checkPieceInTable(this.currentPlayer, this.fromCoordNumberX,
				this.fromCoordNumberY);
		if (this.currentPieceMoveExist) {
			System.out.println("La pieza existe");
			this.isCurrentPieceMaster = checkCurrentPieceMaster(fromCoordNumber);
			System.out.println("Es una pieza Maestra?" + this.isCurrentPieceMaster);
			if (checkMovementAllow()) {

				this.currentMovementAllow = true;
			}
			System.out.println("Es permitido?" + this.currentMovementAllow);

		}else{
		this.currentMovementAllow = false;
		}
	}

	private String stringToNumberCoords(String coordString) {
		/*
		 * take the coords in string and change to number coords to acces in better way
		 * on the array
		 * 
		 * @param coordString the string use to change
		 * 
		 * @return The numeric coord
		 */
		coordString = coordString.toLowerCase();
		String coordsNumber = "";
		for (int i = 0; i < 2; i++) {
			String letter = coordString.substring(i, i + 1);
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
		return coordsNumber;
	}

	private boolean checkPieceInTable(int player, int CoordNumberX, int CoordNumberY) {
		/**
		 * check if the piece exist in the table (of the current player)
		 * 
		 * @param fromNumber the piece that want to check
		 * @return True or false if the piece exist
		 */
		/*System.out.println(CoordNumberY);
		System.out.println(CoordNumberX); 
		System.out.println(this.structure[CoordNumberY][CoordNumberX]);
		System.out.println(player);
		*/
		if (this.structure[CoordNumberY][CoordNumberX] != player) {
			System.out.println("not this piece does not exits ");
			return false;
		}
		return true;
	}

	private boolean checkCurrentPieceMaster(String fromNumber) {
		/**
		 * check if the current piece in use is a piece Master:
		 * 
		 * @param FromNumber the piece coords that want to shake
		 * @return True or false if the piece is a Master
		 */
		int fromNumberX = Integer.parseInt(fromNumber.substring(0, 1));
		int fromNumberY = Integer.parseInt(fromNumber.substring(1, 2));
		if (Math.abs(this.structure[fromNumberY][fromNumberX]) != 2) {
			return false;
		}
		return true;
	}

	private boolean checkMovementAllow() {
		/**
		 * check if the movement is an allow movement based on the move type and if the
		 * piece is master piece
		 * 
		 * @return True or false if the movement is available
		 */
		int diferentCoordsY = this.toCoordNumberY - this.fromCoordNumberY;
		if (this.currentPlayer == 1) {
			if (diferentCoordsY == 1 && (this.toCoordNumberX == this.fromCoordNumberX + 1
					|| this.toCoordNumberX == this.fromCoordNumberX - 1)) {
				if (this.toCoordNumberX == this.fromCoordNumberX + 1
						&& checkPieceInTable(0, this.fromCoordNumberX + 1, this.fromCoordNumberY + 1)) {
					return true;
				} else if ((this.toCoordNumberX == this.fromCoordNumberX - 1
						&& checkPieceInTable(0, this.fromCoordNumberX - 1, this.fromCoordNumberY + 1))) {
					return true;
				} else {
					return false;
				}
			} else if (diferentCoordsY == 2 && (this.toCoordNumberX == this.fromCoordNumberX + 2
					|| this.toCoordNumberX == this.fromCoordNumberX - 2)) {
				if ((this.toCoordNumberX == this.fromCoordNumberX + 2) &&
						checkPieceInTable(this.currentPlayer * -1, this.fromCoordNumberX + 1, fromCoordNumberY + 1) ||
						(this.toCoordNumberX == this.fromCoordNumberX - 2 &&
								checkPieceInTable(this.currentPlayer * -1, this.fromCoordNumberX - 1,
										fromCoordNumberY + 1))) {
					return true; // Case when move 2 square and validate eat the enemy
				} else
					return false;
			} else {
				return false;
			}
		} else {
			if (diferentCoordsY == -1 && (this.toCoordNumberX == this.fromCoordNumberX + 1
					|| this.toCoordNumberX == this.fromCoordNumberX - 1)) {
				return true; // Case when only move 1 square
			} else if (diferentCoordsY == -2 && (this.toCoordNumberX == this.fromCoordNumberX + 2
					|| this.toCoordNumberX == this.fromCoordNumberX - 2)) {
				if ((this.toCoordNumberX == this.fromCoordNumberX + 2) &&
						checkPieceInTable(this.currentPlayer * -1, this.fromCoordNumberX + 1, fromCoordNumberY - 1) ||
						(this.toCoordNumberX == this.fromCoordNumberX - 2 &&
								checkPieceInTable(this.currentPlayer * -1, this.fromCoordNumberX - 1,
										fromCoordNumberY - 1))) {
					return true; // Case when move 2 square and validate eat the enemy
				}
			}
		}

		return false;
	}

	public void makeMovement() {
		if (checkMovementAllow()) {
			setStructure();
			swapPlayer();
		}
	}

	private void setStructure() {
		this.structure[this.fromCoordNumberY][this.fromCoordNumberX] = 0;
		this.structure[this.toCoordNumberY][this.toCoordNumberX] = this.currentPlayer;
	}

	public int[][] getStructure() {
		return this.structure;
	}

	private void swapPlayer() {
		/* If the player is the 1 swap to -1 and viceversa */
		this.currentPlayer = this.currentPlayer * -1;
	}

	public boolean checkGameFinish() {
		boolean contains;
		for (int[] i : this.structure) {
			contains = Arrays.stream(i).anyMatch(x -> x == (this.currentPlayer));
			if (contains) {
				return false;
			}
		}
		return true;
	}

	public int getCurrentPlayer() {
		return this.currentPlayer;
	}

	public boolean getCurrentMovementAllow() {
		return this.currentMovementAllow;
	}
}
