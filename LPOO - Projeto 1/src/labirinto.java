
public class labirinto {
	
	public static void main(String[] args) {
		
		// array bidimensional para guardar os símbolos do labirinto
		char[][] symbols = 	
		{
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H','0','0','0','0','0','0','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','D','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','0','0','0','0','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','E','X','X','0','0','0','0','0','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
		
		System.out.println(symbols[1][1]);

	}
	
	 

}
