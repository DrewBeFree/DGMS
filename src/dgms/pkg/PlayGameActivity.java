package dgms.pkg;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressWarnings("unused")
public class PlayGameActivity extends Activity
{
	private int currentHole;
	private int par = 0;
	private ArrayList<Player> playersList = new ArrayList<Player>();
	private TextView currentHoleValue, parValue;
	private Button btnAddPlayer, btnNext, btnPrev, btnMarkTee, 
	btnMarkPin, btnViewScores, btnViewMap;
	private TableLayout table;
	private ArrayList<TextView> scoresList = new ArrayList<TextView>();
	private ArrayList<EditText> currentScoresList = new ArrayList<EditText>();
	private Spinner parSpiner;
	private int value;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_game_view);
		InitViews();
	}

	@Override
	public void onBackPressed()
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

	private void goBack()
	{
		super.onBackPressed();		
	}
	
	private void InitViews()
	{
		if(currentHole == 0)		
			currentHole = 1;				
		playersList = Game.getPlayers();
		table = (TableLayout)findViewById(R.id.tblPlayHole);
		currentHoleValue = (TextView)findViewById(R.id.txtHoleNumberValue);
		btnAddPlayer = (Button)findViewById(R.id.btnAddPlayer); 
		btnNext = (Button)findViewById(R.id.btnNextHole);
		btnPrev = (Button)findViewById(R.id.btnPrevHole);
		btnMarkTee = (Button)findViewById(R.id.btnMarkTee);
		btnMarkPin = (Button)findViewById(R.id.btnMarkPin);
		btnViewScores = (Button)findViewById(R.id.btnViewScores);
		btnViewMap = (Button)findViewById(R.id.btnViewMap);
		parSpiner = (Spinner)findViewById(R.id.spinner1);
		
		parSpiner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int pos, long row)
			{
				setParForHole(pos);
				
			}			

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
		addPlayersToTable();
		updateUI();
	}
	
	private void setParForHole(int parIndex)
	{
		Game.setParForHole(currentHole, parIndex + 1);
	}
	
	
	
	private void updateUI()
	{
		currentHoleValue.setText("" + currentHole);
		par = Game.getParForHole(currentHole);
		parSpiner.setSelection(par - 1);
		parSpiner.setClickable(Game.isNewCourse());
		btnNext.setEnabled(Game.hasNextHole());
		btnPrev.setEnabled(currentHole != 1);
		if(Game.needCoordinates())
		{
			btnMarkPin.setVisibility(View.VISIBLE);
			btnMarkTee.setVisibility(View.VISIBLE);
		}
		else
		{
			
			btnMarkPin.setVisibility(View.GONE);
			btnMarkTee.setVisibility(View.GONE);
		}
		if(Game.needMap())
		{
			btnViewMap.setVisibility(View.VISIBLE);
		}
		else
		{
			btnViewMap.setVisibility(View.GONE);
			
		}
		for(int i = 0; i < currentScoresList.size() ; i++)
		{	
			currentScoresList.get(i).setText("" + Game.getPlayerScore(playersList.get(i), currentHole));
		}
	}

	private void addPlayersToTable()
	{
		for(Player p : playersList)
			addTableRow(p);
		
	}

	private void addTableRow(Player p)
	{
		TableRow playerRow = new TableRow(this);
		// Name
		TextView name = new TextView(this);
		name.setText(p.getName());
		playerRow.addView(name);
		
		// Score
		final int playerIndex = playersList.indexOf(p);
		final Player thisPlayer = p;
		final EditText hs = new EditText(this);
		hs.setInputType(2);
		hs.addTextChangedListener(new TextWatcher()
		{
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
				try {
					
					thisPlayer.setScore(currentHole, Integer.parseInt((hs.getText().toString())));
					updateScore(playerIndex);
					
					
					
				} catch (NumberFormatException e) {
					return;
				}
				
			}

			
		});		
		playerRow.addView(hs);
		currentScoresList.add(hs);
		// Total
		TextView pSum = new TextView(this);
		pSum.setGravity(Gravity.CENTER);
		scoresList.add(pSum);
		playerRow.addView(pSum);
		
		table.addView(playerRow);
	}
	
	private void updateScore(int playerIndex)
	{
		String score = "" + playersList.get(playerIndex).getTotal();
		scoresList.get(playerIndex).setText(score);
	}
	
	public void nextHoleClick(View v)
	{
		currentHole ++;
		Game.setHole(currentHole);
		updateUI();
	}
	

	public void prevHoleClick(View v)
	{
		currentHole --;
		Game.setHole(currentHole);
		updateUI();
	}
	
	public void addPlayerClick(View v)
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Enter Name");
		// Set an EditText view to get user name 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) 
		{
		  String playerName = input.getText().toString();
		  Player p = Game.addPlayer(playerName);
		  addTableRow(p);
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
	}
	
	public void viewScoresClick(View v)
	{
		Intent intent = new Intent(v.getContext(), ScoreCardActivity.class);
		startActivityForResult(intent, 0);
	}
	
	public void markTeeClick(View v)
	{
		AlertDialog.Builder alert = getAlertFor("Tee");

		alert.show();

	}
	
	public void markPinClick(View v)
	{
		AlertDialog.Builder alert = getAlertFor("Pin");
		alert.show();
	}

	public void viewMapClick(View v)
	{
		
		
		Bundle extras = getIntent().getExtras();
		value = extras.getInt("1");
		Intent intent;
		if (value == 1)
		{
			intent = new Intent(v.getContext(), CourseMap.class);
		}
		else
			intent = new Intent(v.getContext(), CreateCourse.class);
		
		startActivity(intent);
	}
	
	private AlertDialog.Builder getAlertFor(String forName)
	{

		final Coordinate coor = LocationHandler.getLocation();
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle(forName + " Coordinates");
		// Set Text views
		final LinearLayout panel = new LinearLayout(this);
		panel.setOrientation(1);
		final TextView lat = new TextView(this);
		lat.setText("Latitude: " + coor.getLatitude());
		panel.addView(lat);
		
		final TextView lon = new TextView(this);
		lon.setText("Longitude: " + coor.getLongitude());
		panel.addView(lon);
		
		alert.setView(panel);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) 
		{
		  Game.setPinForHole(currentHole, coor);
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) 
		  {
		    
		  }
		});
		
		return alert;
	}
}
