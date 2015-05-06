package dgms.pkg;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CoursesAdapter extends ArrayAdapter<Course>
{

	private int resource;

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout courseView;
		Course c = getItem(position);
		
		if(convertView == null)
		{
			courseView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi;
			vi = (LayoutInflater)getContext().getSystemService(inflater);
			vi.inflate(resource, courseView, true);
		}
		else
		{
			courseView = (LinearLayout)convertView;
		}
		//Get the text boxes from the listitem.xml file
		TextView courseName = (TextView)courseView.findViewById(R.id.txtCourseName);
		TextView courseDescription = (TextView)courseView.findViewById(R.id.txtCourseDescription);
		courseName.setText(c.getCourseName());
		courseDescription.setText(c.getDescription());
		
		return courseView;
	}

	public CoursesAdapter(Context context, int textViewResourceId,
			List<Course> courses)
	{
		super(context, textViewResourceId, courses);
		this.resource = textViewResourceId;
	}

}
