package dgms.pkg;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


@SuppressWarnings("unused")
public class CourseMap extends MapActivity
{
    Course course;

	private List<Overlay> mapOverlays;
	private Projection projection;
	private Drawable drawable;
	private DGMSItemizedOverlay pins;
	private MapView mapView;

    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        if (DataHandler.selectedCourse == null)
        {
        	course = new Course();
        }

        else
        	course = DataHandler.selectedCourse;

        
        setContentView(R.layout.mapview);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setSatellite(true);
        mapView.setBuiltInZoomControls(true);
       
        GeoPoint home = new GeoPoint((int) (course.getCourseCoordinate().getLatitude()*1E6),(int) (course.getCourseCoordinate().getLongitude()*1E6));
        mapView.getController().setCenter(home);
        mapView.getController().setZoom(18);
        mapOverlays = mapView.getOverlays();
        projection = mapView.getProjection();
        drawable = this.getResources().getDrawable(R.drawable.basket);
        pins  = new DGMSItemizedOverlay(drawable);

        for(Hole h: course.getCourseHoles())
        {
        	int plat = ((int) (h.getPin().getLatitude()*1E6));
        	int plong = ((int) (h.getPin().getLongitude()*1E6));
            GeoPoint p = new GeoPoint(plat,plong);
            OverlayItem Op = new OverlayItem(p, null, null);
            pins.addOverlay(Op);
        }
        mapOverlays.add(new myOverlay());
        mapOverlays.add(pins);

    }
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
    @Override
	protected boolean isLocationDisplayed() {
        return false;
    }
    
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.existingcoursemenu, menu);

		return (super.onCreateOptionsMenu(menu));

	}

	public boolean onOptionsItemSelected(MenuItem item) 
	{

		if (item.getItemId() == R.id.scoreCard) 
		{
			
			Intent intent = new Intent(this.getBaseContext(),ScoreCardActivity.class);
			
			startActivityForResult(intent, 0);
		}

		

		return (super.onOptionsItemSelected(item));
	}
      
    class myOverlay extends Overlay{
    	
    	public myOverlay(){}
    	
    	@Override
		public void draw(Canvas canvas, MapView mapv, boolean shadow)
    	{
    		super.draw(canvas, mapv, shadow);
    	    Paint mPaint = new Paint();
    	    Paint textPaint = new Paint();
    	    Paint teePaint = new Paint();
            mPaint.setDither(true);
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(2);            
            textPaint.setTextSize(16);
            textPaint.setColor(Color.BLACK);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
            teePaint.setDither(true);
            teePaint.setColor(Color.GREEN);
            teePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            teePaint.setStrokeJoin(Paint.Join.ROUND);
            teePaint.setStrokeCap(Paint.Cap.ROUND);
            teePaint.setStrokeWidth(2);
            
            for(Hole h: course.getCourseHoles())
            {
            	int tlat = (int) (h.getTee().getLatitude()* 1E6);
            	int tlong = (int) (h.getTee().getLongitude()* 1E6);
            	int plat = (int) (h.getPin().getLatitude()* 1E6);
            	int plong = (int) (h.getPin().getLongitude()* 1E6);
            	
            	String hNum = Integer.toString(h.getHoleNumber());
                GeoPoint tDrawPt = new GeoPoint(tlat,tlong);
                GeoPoint pDrawPt = new GeoPoint(plat,plong);
                Point tPoint = new Point();
                Point pPoint = new Point();
                Path path = new Path();
                projection.toPixels(tDrawPt, tPoint);
                projection.toPixels(pDrawPt, pPoint);
                path.moveTo(tPoint.x+1, tPoint.y-9);
                path.lineTo(pPoint.x+1, pPoint.y-9);
                canvas.drawPath(path, mPaint);
                canvas.drawCircle(tPoint.x, tPoint.y-8, 10, teePaint);
                canvas.drawText(hNum, tPoint.x, tPoint.y-3, textPaint);
            }
    	}
    }
}