package dgms.pkg;

public class Player {
	private String name;
	private PlayerScores scores;

	public Player(String name) {
		this.name = name;
		this.scores = new PlayerScores();
	}

	public Player(String name2, int size)
	{
		this.name = name2;
		this.scores = new PlayerScores(size);
	}

	public String getName() {
		return name;
	}

	public PlayerScores getScores() {

		return scores;
	}

	public Score getScore()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setScore(int holeNum, int score)
	{
		// TODO Auto-generated method stub
		scores.setScore(holeNum, score);
		
	}
	
	public int getTotal()
	{
		return scores.getTotal();
	}

	public void addHole()
	{
		scores.addHole();		
	}

	public int getScoreForHole(int holeNum)
	{
		return scores.getScore(holeNum);
	}
	

}