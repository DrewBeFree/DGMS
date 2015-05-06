package dgms.pkg;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;




public class DGMSItemizedOverlay extends ItemizedOverlay<OverlayItem> 
{
	
	private ArrayList<OverlayItem> pinpoints = new ArrayList<OverlayItem>();
	@SuppressWarnings("unused")
	private Context c;

	public DGMSItemizedOverlay(Drawable m, Context context)
	{
		this(m);
		c = context;
	}
	public DGMSItemizedOverlay(Drawable defaultMarker) 
	{
		super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}
	
	public void addOverlay(OverlayItem overlay) {
	    pinpoints.add(overlay);
	    populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
	  return pinpoints.get(i);
	}

	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return pinpoints.size();
	}

}