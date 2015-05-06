package dgms.pkg;


import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class CreateCourse extends MapActivity {

	private MyLocationOverlay compass;
	private List<Overlay> mapOverlays;
	private MapView mapView;
	private MapController controller;
	private Drawable drawperson;
	private DGMSItemizedOverlay me;
	private LocationManager lm;
	GeoPoint touchedPoint;

	Course course;

	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		InitViews();
	}

	private void InitViews() 
	{
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);

		mapOverlays = mapView.getOverlays();

		compass = new MyLocationOverlay(CreateCourse.this, mapView);
		mapOverlays.add(compass);
		controller = mapView.getController();
		GeoPoint home = new GeoPoint((int) (33.651208 * 1E6),
				(int) (-84.023437 * 1E6));
		controller.animateTo(home);
		controller.setZoom(17);

		course = DataHandler.selectedCourse;
		Toast.makeText(getBaseContext(), "Name: " + course.getCourseName(),
				Toast.LENGTH_SHORT).show();
		Toast.makeText(getBaseContext(),
				"Number of Holes: " + course.getCourseHoles().size(),
				Toast.LENGTH_SHORT).show();

		// Pinpoint my Location
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		GeoPoint location = LocationHandler.startGps(lm);

			controller.animateTo(location);
			controller.setZoom(17);
			drawperson = getResources().getDrawable(R.drawable.person);
			OverlayItem overlayItem = new OverlayItem(location,
					"Your Location", "2nd String");
			me = new DGMSItemizedOverlay(drawperson, CreateCourse.this);
			me.addOverlay(overlayItem);
			mapOverlays.add(me);
		} 




	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}



	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.newcoursemenu, menu);

		return (super.onCreateOptionsMenu(menu));

	}

	public boolean onOptionsItemSelected(MenuItem item) {

	
		if (item.getItemId() == R.id.submitCourse) {

			List<Hole> holes = DataHandler.selectedCourse.getCourseHoles();
			DataHandler.selectedCourse.setCourseHoles(holes);
			String result = DataHandler.SubmitCourse(DataHandler.selectedCourse);

			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("" + result + " Successfully submitted");
			alert.setMessage("Thank You!");
			alert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int whichButton) {

				}
			});
			alert.show();
			return true;
		}
		if (item.getItemId() == R.id.scoreCard) {

			Intent intent = new Intent(this.getBaseContext(), ScoreCardActivity.class);

			startActivityForResult(intent, 0);
		}
		return (super.onOptionsItemSelected(item));

	}

}
