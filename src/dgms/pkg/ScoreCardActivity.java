package dgms.pkg;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class ScoreCardActivity extends Activity
{
	private TableLayout table;
	private int numberOfHoles = Game.getGameCourse().getCourseHoles().size();
	private TableRow.LayoutParams lp = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorecard);
        
        lp.setMargins(1, 1, 1, 1);
        
        buildTable();
        
    }
    
    private void buildTable()
	{	    	    	    	 
    	table = (TableLayout)findViewById(R.id.tblScoreCard);
    	table.setBackgroundColor(Color.WHITE);
   
    	// Header
    	TableRow header = new TableRow(this);
    	header.setBackgroundColor(Color.BLACK);
    	
    	TextView holes = new TextView(this);
    	holes.setBackgroundColor(Color.LTGRAY);
    	holes.setTextColor(Color.BLACK);
    	holes.setText("Hole #");
    	header.addView(holes, lp);
    	
    	for(int i = 1; i <= numberOfHoles; i++)
    	{
    		TextView h = new TextView(this);
    		h.setBackgroundColor(Color.LTGRAY);
    		h.setTextColor(Color.BLACK);
    		h.setGravity(Gravity.CENTER);
    		h.setText("" + i);
    		header.addView(h, lp);
    	}
    	
    	TextView total = new TextView(this);
    	total.setBackgroundColor(Color.LTGRAY);
    	total.setTextColor(Color.BLACK);
    	total.setGravity(Gravity.CENTER);
    	total.setText("TOTAL");
    	header.addView(total, lp);
    	
		table.addView(header, lp);
		
		// Pars
		TableRow pars = new TableRow(this);
		pars.setBackgroundColor(Color.BLACK);
		TextView par = new TextView(this);
		par.setBackgroundColor(Color.LTGRAY);
		par.setTextColor(Color.BLACK);
		par.setGravity(Gravity.CENTER);
		par.setText("par");
		pars.addView(par, lp);
		
		for(int i = 1; i <= numberOfHoles; i++)
    	{
    		TextView p = new TextView(this);
    		p.setBackgroundColor(Color.LTGRAY);
    		p.setTextColor(Color.BLACK);
    		p.setGravity(Gravity.CENTER);
    		p.setText("" + Game.getGameCourse().getParForHole(i));
    		//p.setText("3");
    		pars.addView(p, lp);
    	}
		 
		TextView empty = new TextView(this);
		empty.setBackgroundColor(Color.LTGRAY);
		empty.setTextColor(Color.BLACK);
		pars.addView(empty, lp);
		
		table.addView(pars, lp);
		
		//Players
		playerRows();
		
	}
    
    private void playerRows()
   	{
    	ArrayList<Player> players = Game.getPlayers();
    	for (int i = 0; i < players.size(); i++)
    	{
    		TableRow player = new TableRow(this);
    		player.setBackgroundColor(Color.BLACK);
    		
       		TextView name = new TextView(this);
       		name.setBackgroundColor(Color.WHITE);
       		name.setTextColor(Color.RED);
       		name.setText(players.get(i).getName());
       		player.addView(name, lp);
       		
       		PlayerScores ps = players.get(i).getScores();
       		
       		for(int j = 1; j <= numberOfHoles; j++)
           	{
       			final TextView hs = new TextView(this);
       			hs.setBackgroundColor(Color.WHITE);
       			hs.setTextColor(Color.RED);
       			hs.setGravity(Gravity.CENTER);
       			hs.setText("" + ps.getScore(j));
       			
       			player.addView(hs, lp);
           	}
       		
       		TextView pSum = new TextView(this);
       		pSum.setBackgroundColor(Color.WHITE);
       		pSum.setTextColor(Color.RED);
       		pSum.setGravity(Gravity.CENTER);
       		pSum.setText("" + players.get(i).getTotal());
       		player.addView(pSum, lp);
       		
       		table.addView(player, lp);
    	}
       	
   		
   	}


}

