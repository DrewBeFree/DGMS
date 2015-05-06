package dgms.pkg;

import java.util.ArrayList;


public class Game 
{
	private static Course course;
	private static ArrayList<Player> players;
	private static boolean isNewCourse, hasSavedData;
	private static boolean needMap, needCoord;
	private static int currentHoleNum = 0;

	
	public static void initGame()
	{
		if(!hasSavedData)
		{
			currentHoleNum = 1;
			isNewCourse = true;
			needMap = false;
			needCoord = false;
			course = new Course();
			players = new ArrayList<Player>(1);
			players.add(new Player("me", course.getNumberOfHoles()));
		}
	}
	
	public static void initGame(String cName, String cDesc)
	{
		if(!hasSavedData)
		{
			currentHoleNum = 1;
			isNewCourse = true;
			needMap = true;
			needCoord = true;
			course = new Course(cName, cDesc);
			DataHandler.selectedCourse = course;
			Coordinate coor = LocationHandler.getLocation();
			course.setCourseCoordinate(coor);
			players = new ArrayList<Player>(1);
			players.add(new Player("me", course.getNumberOfHoles()));
		}
	}
	
	public static void initGame(Course c)
	{
		if(!hasSavedData)
		{
			currentHoleNum = 1;
			isNewCourse = false;
			needMap = true;
			needCoord = false;
			course = c;
			DataHandler.selectedCourse = course;
			players = new ArrayList<Player>(1);
			players.add(new Player("me", course.getNumberOfHoles()));
		}
	}
	
	public static Player addPlayer(String name)
	{
		Player p = new Player(name, course.getCourseHoles().size());
		players.add(p);
		return p;
	}
	

	
	public static Course getGameCourse()
	{
		return course;
	}
	

	public static int getParForHole(int holeNumber)
	{
		return course.getParForHole(holeNumber);
	}

	public static ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public static boolean isNewCourse()
	{
		return isNewCourse;
	}

	public static void addHole(int holeNum)
	{
		Hole h = new Hole(holeNum, 3);
		course.addHole(h);
		for(Player p : players)
			p.addHole();
	}

	public static boolean hasNextHole()
	{
		if(isNewCourse)
			return true;
		else
			return currentHoleNum < course.getNumberOfHoles();
	}

	public static boolean needCoordinates()
	{
		return needCoord;
	}


	public static int getPlayerScore(Player player, int holeNum)
	{
		return player.getScoreForHole(holeNum);
	}

	public static void setHole(int currentHole)
	{
		currentHoleNum = currentHole;
		if(isNewCourse)
			addHole(currentHole);
		
	}

	public static void setTeeForHole(int holeNum, Coordinate coor)
	{
		course.setTeeForHole(holeNum, coor);
		
	}

	public static void setPinForHole(int holeNum, Coordinate coor)
	{
		course.setPinForHole(holeNum, coor);
		
	}

	public static boolean needMap()
	{
		return needMap;
	}

	public static void setParForHole(int holeNum, int par)
	{
		course.setParForHole(holeNum, par);
		
	}

	public static void submitCourse()
	{
		DataHandler.SubmitCourse(course);		
	}

	public static void saveGame(boolean save)
	{
		hasSavedData = save;
	}

	public static int getHoleNumber()
	{
		if(currentHoleNum == 0)
			currentHoleNum = 1;
		
		return currentHoleNum;
	}

	public static boolean hasSavedData()
	{
		return hasSavedData;
	}


	

}
