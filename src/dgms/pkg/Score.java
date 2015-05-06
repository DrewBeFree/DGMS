package dgms.pkg;

public class Score 
{
	private int[]ps;
	
	public Score(int hole_count)
	{
		this.ps = new int[hole_count];
		for (int i = 0; i < hole_count; i++)
			ps[i] = 0;
	}
	
	public int getScoreForHole(int hole)
	{
		return ps[hole + 1];
	}
	
	public void putScoreForHole(int hole, int score)
	{
		ps[hole+1] = score;
	}
	
	public int getTotalScore()
	{
		int total = 0;
		for (int i = 0; i < ps.length; i++)
			total += ps[i];
		return total;
	}

}
