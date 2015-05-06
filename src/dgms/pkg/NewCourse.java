package dgms.pkg;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewCourse extends Activity
{

	private EditText etName;
	private EditText etDesc;
	private String courseName;
	private String courseDescription;
		
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newcourse);
		
		etName = (EditText)findViewById(R.id.courseNameInput);
		etDesc = (EditText)findViewById(R.id.courseDescriptionInput);
		
	}
	
	public void playClick(View v)
	{
		courseName = etName.getText().toString();
		courseDescription = etDesc.getText().toString();
		if (courseName.length() != 0)
		{
			Game.initGame(courseName, courseDescription);
			Intent intent = new Intent(v.getContext(), PlayGameActivity.class);
			intent.putExtra("1", 2);
			startActivityForResult(intent, 0);
		}
		else
		{
			Toast.makeText(getBaseContext(), "Must fill in name first!", 1500).show();
		}
	}
}

