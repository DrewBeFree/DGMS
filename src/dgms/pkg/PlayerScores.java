package dgms.pkg;

import java.util.ArrayList;


public class PlayerScores
{
	
	private ArrayList<Integer> playerScore;
	
	public PlayerScores()
	{
		playerScore = new ArrayList<Integer>();
	}
	
	public PlayerScores(int size)
	{
		playerScore = new ArrayList<Integer>(size);
		for(int i = 0; i < size; i++)
			playerScore.add(0);
	}
		
	public void setScore(int holeNumber, int score)
	{
		playerScore.set(holeNumber - 1, score);
	}
	
	public void addHole()
	{
		playerScore.add(0);
	}
	
	public int getScore(int holeNumber)
	{
		return playerScore.get(holeNumber - 1);
	}
	
	public int getTotal()
	{
		int total = 0;
		for(int i : playerScore)
			total += i;
		return total;
	}
	
	
}