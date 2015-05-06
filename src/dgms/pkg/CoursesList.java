package dgms.pkg;

import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class CoursesList extends ListActivity
{
	

	ListView lstCourses;
	CoursesAdapter adapter;
	ArrayList<Course> courses = null;
	ArrayList<Course> toUse = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
//		toUse = DataHandler.GetCoursesAll();
		courses = DataHandler.GetCoursesAll();
		adapter = new CoursesAdapter(this, R.layout.listitems, courses);

		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		Game.initGame(courses.get(position));
		Intent intent = new Intent(v.getContext(),PlayGameActivity.class);
		intent.putExtra("1", 1);
		startActivityForResult(intent, 0);
	}

}