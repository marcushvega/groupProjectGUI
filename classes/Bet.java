package classes;

public class Bet {

	private Player player1;		//player1 is object of PLayer Class
	private Player player2;		//player2 is object of Player Class
	private int wager;			//total wager amount	
	private String raceName;	//name of racing event
	
	public Bet(Player p1, Player p2, int w){
		player1 = p1;
		player2 = p2;
		wager = w;
	}
	public void setRace(String r){
		raceName = r;
	}
	public String getRace(){		
		return raceName;
	}
}