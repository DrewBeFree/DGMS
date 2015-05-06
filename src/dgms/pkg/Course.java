package dgms.pkg;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private Coordinate CourseCoordinate;
	private String CourseName;
	private String Description;
	private ArrayList<Hole> CourseHoles;
	
	public Course()
	{
		CourseHoles = new ArrayList<Hole>(1);
		Hole h = new Hole(1,3);
		CourseHoles.add(h);		
	}
	
	public Course(String courseName, String courseDesc)
	{
		CourseName = courseName;
		Description = courseDesc;
		CourseHoles = new ArrayList<Hole>(1);
		Hole h = new Hole(1,3);
		CourseHoles.add(h);
	}

	public void addHole(Hole h)
	{
		if (h.getHoleNumber() == 1)
			setCourseCoordinate(h.getTee());
		CourseHoles.add(h);
	}

	public Coordinate getCourseCoordinate() 
	{
		return CourseCoordinate;
	}

	public void setCourseCoordinate(Coordinate courseCoordinate) 
	{
		CourseCoordinate = courseCoordinate;
	}

	public String getCourseName() 
	{
		return CourseName;
	}

	public void setCourseName(String courseName) 
	{
		CourseName = courseName;
	}

	public String getDescription() 
	{
		return Description;
	}

	public void setDescription(String description) 
	{
		Description = description;
	}

	public ArrayList<Hole> getCourseHoles() 
	{
		return CourseHoles;
	}

	public void setCourseHoles(List<Hole> courseHoles) 
	{
		CourseHoles = (ArrayList<Hole>) courseHoles;
	}

	public int getParForHole(int holeNumber)
	{
		return CourseHoles.get(holeNumber - 1).getPar();
	}

	public void setPinForHole(int holeNum, Coordinate coor)
	{
		CourseHoles.get(holeNum - 1).setPin(coor);		
	}

	public void setTeeForHole(int holeNum, Coordinate coor)
	{
		CourseHoles.get(holeNum - 1).setTee(coor);		
	}

	public int getNumberOfHoles()
	{
		return CourseHoles.size();
	}

	public void setParForHole(int holeNum, int par)
	{
		CourseHoles.get(holeNum - 1).setPar(par);
	}

}
