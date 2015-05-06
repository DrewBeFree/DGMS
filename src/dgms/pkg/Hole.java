package dgms.pkg;

public class Hole
{
	private int HoleNumber;
	private int Par;
	private Coordinate Pin;
	private Coordinate Tee;
	private double Distance;

	public Hole()
	{
		super();
	}
	
	public Hole(int i, int j)
	{
		this.Par = j;
		this.HoleNumber = i;
		//super();
	}
	public int getHoleNumber()
	{
		return HoleNumber;
	}
	public void setHoleNumber(int holeNumber)
	{
		HoleNumber = holeNumber;
	}
	public int getPar()
	{
		return Par;
	}
	public void setPar(int par)
	{
		Par = par;
	}
	public Coordinate getPin()
	{
		return Pin;
	}
	public void setPin(Coordinate pin)
	{
		Pin = pin;
	}
	public Coordinate getTee()
	{
		return Tee;
	}
	public void setTee(Coordinate tee)
	{
		Tee = tee;
	}
	public double getDistance()
	{
		return Distance;
	}
	public void setDistance(double distance)
	{
		Distance = distance;
	}
}
