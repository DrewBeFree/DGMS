package dgms.pkg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainScreen extends Activity {
	/** Called when the activity is first created. */
	
	private LocationManager lm;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		InitViews();
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationHandler.startGps(lm);
	}
	

	@Override
	public void onBackPressed()
	{
		if(Game.hasSavedData())
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Save game?");

			alert.setPositiveButton("Save", new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int whichButton) 
				{
					Game.saveGame(true);
					goBack();
				}			
			});

			alert.setNegativeButton("No", new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int whichButton) 
				{
					Game.saveGame(false);
					goBack();
				}
			});
			
			alert.show();
		}
		else
		{
			super.onBackPressed();
		}
	}

	private void goBack()
	{
		LocationHandler.stopGps(lm);
		super.onBackPressed();		
	}
	
	private void InitViews() 
	{
		Button play = (Button) findViewById(R.id.play);
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				Intent intent;
				if(Game.hasSavedData())
					intent = new Intent(v.getContext(), PlayGameActivity.class);
				else
					intent = new Intent(v.getContext(), Play.class);

				startActivityForResult(intent, 0);
			}
		});
		Button playButton2 = (Button) findViewById(R.id.locateCourse);
		playButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), LocateCourse.class);

				startActivityForResult(intent, 0);
			}
		});
	}

}