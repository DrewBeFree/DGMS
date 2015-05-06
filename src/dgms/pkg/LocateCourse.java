package dgms.pkg;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class LocateCourse extends MapActivity 
{

	private MyLocationOverlay compass;
	private List<Overlay> mapOverlays;
	private MapView mapView;
	private MapController controller;
	private Drawable drawable;
	private static Drawable drawperson;
	private DGMSItemizedOverlay courses;
	private DGMSItemizedOverlay me;
	private LocationManager lm;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locatecourse);
		InitViews();
	}

	private void InitViews() 
	{
		mapView = (MapView) findViewById(R.id.locatecourse);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);
		mapOverlays = mapView.getOverlays();
		compass = new MyLocationOverlay(LocateCourse.this, mapView);
		mapOverlays.add(compass);
		controller = mapView.getController();
		controller.setZoom(5);

		// Pinpoint my Location
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		GeoPoint location = LocationHandler.startGps(lm);
		controller.animateTo(location);
		controller.setZoom(17);
		drawperson = getResources().getDrawable(R.drawable.person);
		OverlayItem overlayItem = new OverlayItem(location,
				"Your Location", "2nd String");
		me = new DGMSItemizedOverlay(drawperson, LocateCourse.this);
		me.addOverlay(overlayItem);
		mapOverlays.add(me);

		// Drawing Baskets
		List<Course> clist = DataHandler.GetCoursesAll();
		drawable = this.getResources().getDrawable(R.drawable.basket);
		courses = new DGMSItemizedOverlay(drawable);
		for (Course eachcourse : clist)
		{
		GeoPoint cGeo = new GeoPoint((int)(eachcourse.getCourseCoordinate().getLatitude()*1E6), 
										(int) (eachcourse.getCourseCoordinate().getLongitude()*1E6));
		OverlayItem c = new OverlayItem(cGeo, null, null);
		courses.addOverlay(c);
		}
		mapOverlays.add(courses);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	 public boolean onCreateOptionsMenu(Menu menu)
	    { new MenuInflater(this).inflate(R.menu.locatecoursemenu, menu);
		 
	    	return(super.onCreateOptionsMenu(menu));
	    }
	 
		public boolean onOptionsItemSelected(MenuItem item) 
		{

			if(item.getItemId()==R.id.showMe){
				controller.animateTo(LocationHandler.myLocation);
				
				return true;
			}
			return (super.onOptionsItemSelected(item));
		}
		


}


