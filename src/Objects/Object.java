package Objects;

import java.util.*;

public class Object {
	ArrayList<Player> playerArr = new ArrayList<>();
	ArrayList<Tiles> map = new ArrayList<>();
	int[] initarr = {0,0,0,0};
	int turns = 0;
	public int dicenum;
	public Player p1 = new Player(0,0,"한상훈",0,"player1.png");
	public Player p2 = new Player(0,0,"서동규",1,"player2.png"); 
	
	public Object() {
		
	}
	
	public void init() {
		playerArr.clear();
		
		
		playerArr.add(p1);
		playerArr.add(p2);
		
		//플레이어랑 보드 초기화 하고 게임 시작
		Running StartGame = new Running();
		StartGame.DisplayBoard();

	}
}
