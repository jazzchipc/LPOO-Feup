import java.util.Scanner;


public class Heroi {
	
	/****ATRIBUTOS****/
	
	boolean armado = false;
	char letra;
	Posicao pos = new Posicao();
	Posicao posAnt = new Posicao();
	
	/****MÉTODOS****/
	
	public void updateLetra()
	{
		if(armado == false)
			letra = 'H';
		else
			letra = 'A';
	}
	
	public static void main(String[] args) 
	{
		Labirinto lab = new Labirinto();
		lab.printLab();
	}
	
	public void getPos()
	{
		this.posAnt = this.pos;
		Scanner in = new Scanner(System.in);
		char move;
		
		System.out.println("Move: ");
		move = in.next().charAt(0);
		
		if (!(move == 'w' || move == 'a'|| move == 's'|| move == 'd'))
			System.out.println("Error!");
		
		switch (move)
		{
		case 'w': this.pos.x = this.pos.x - 1;
		break;
		case 'a': this.pos.y = this.pos.y - 1;
		break;
		case 's': this.pos.x = this.pos.x + 1;
		break;
		case 'd': this.pos.y = this.pos.y + 1;
		break;
		
		}
		
	}
	
	public void newPos(char letra)
	{
		switch(letra)
		{
		case 'X': this.pos = this.posAnt;
		break;
		case ' ':break;
		case 'D':
		}
		
	}
	
	public void moveHeroi(Posicao pos)
	{
		this.posAnt = this.pos;
		this.pos = pos;
	}
	
	
}
