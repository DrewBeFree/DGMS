package dgms.pkg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Play extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		
	}
	
	
	public void courseLookupClick(View v) 
	{
		Intent intent = new Intent(getApplicationContext(), CoursesList.class);
		intent.putExtra("1",1);
		startActivity(intent);
	}
	
	public void newCourseClick(View v) 
	{
		Intent intent = new Intent(getApplicationContext(), NewCourse.class);
		intent.putExtra("1", 2);
		startActivity(intent);
	}
	
	public void justScorecardClick(View v) 
	{
		Game.initGame();
		Intent intent = new Intent(v.getContext(), PlayGameActivity.class);
		
		startActivityForResult(intent, 0);
	}

}