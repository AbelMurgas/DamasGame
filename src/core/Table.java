package core;

public class Table {
	private int[][] structure = 
		{
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{-1,0,-1,0,-1,0,-1,0},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,-0,-1,0,-1,0}
		};
	private int currentPlayer = 1;
	
	@SuppressWarnings("unused")
	private String stringToNumberCoords(String coordString) {
		coordString = coordString.toLowerCase();
		String coordsNumber = "";
		for (int i = 0; i < 2; i ++) {
			String letter = coordString.substring(i,i+1);
			switch (letter) {
			case "a": {
				coordsNumber = coordsNumber.concat("1");
				break;
			}
			case "b": {
				coordsNumber = coordsNumber.concat("2");
				break;
			}
			case "c": {
				coordsNumber = coordsNumber.concat("3");
				break;
			}
			case "d": {
				coordsNumber = coordsNumber.concat("4");
				break;
			}
			case "e": {
				coordsNumber = coordsNumber.concat("5");
				break;
			}	
			case "f": {
				coordsNumber = coordsNumber.concat("6");
				break;
			}
			case "g": {
				coordsNumber = coordsNumber.concat("7");
				break;
			}
			case "h": {
				coordsNumber = coordsNumber.concat("8");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value coord String: " + letter);
			}
		}
		return  coordsNumber;
	}
	
	void makeMovement(String fromString, String toString) {
		String fromNumber = stringToNumberCoords(fromString);
		String toNumber = stringToNumberCoords(toString);
		System.out.println(fromNumber);
		System.out.println(toNumber);
	}

}
