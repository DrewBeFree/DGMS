package dgms.pkg;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ExistingCourse extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.existing);
	}
	
	 public boolean onCreateOptionsMenu(Menu menu)
	    { new MenuInflater(this).inflate(R.menu.existingcoursemenu, menu);
		 
	    	return(super.onCreateOptionsMenu(menu));
	    }
	 
		public boolean onOptionsItemSelected(MenuItem item) 
		{
			
			if(item.getItemId()==R.id.seeMap)
			{
				startActivity(new Intent(ExistingCourse.this, CourseMap.class));
				return true;
			}
			if(item.getItemId()==R.id.scoreCard){
				Intent i = new Intent(ExistingCourse.this, ScoreCardActivity.class);		
				startActivity(i);
				return true;
			}
			return (super.onOptionsItemSelected(item));
		}
}
