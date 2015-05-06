package dgms.pkg;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class LocationHandler extends MapActivity implements LocationListener
{
	static GeoPoint myLocation;
	private static int lat;
	private static int longi;
	private static LocationHandler handler;

	public static GeoPoint startGps(LocationManager lm)
	{

		handler = new LocationHandler();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, handler);
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	
		if (location != null) 
		{
			lat = (int) (location.getLatitude() * 1E6);
			longi = (int) (location.getLongitude() * 1E6);
			myLocation = new GeoPoint(lat, longi);

		} 
		else
			myLocation = new GeoPoint(0, 0);
			
		return myLocation;
	}
	
	public static void stopGps(LocationManager lm)
	{
		lm.removeUpdates(handler);
		
	}
	
	public static Coordinate getLocation()
	{

		Coordinate c = new Coordinate();
		c.setLatitude( (float) (myLocation.getLatitudeE6()/ 1E6));
		c.setLongitude( (float) (myLocation.getLongitudeE6()/ 1E6));
		return c;
	}
	
	@Override
	public void onLocationChanged(Location l) 
	{
		// TODO Auto-generated method stub
		lat = (int) (l.getLatitude() * 1E6);
		longi = (int) (l.getLongitude() * 1E6);
		myLocation = new GeoPoint(lat, longi);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		// TODO Auto-generated method stub

		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
