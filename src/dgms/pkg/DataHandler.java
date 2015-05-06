package dgms.pkg;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

public class DataHandler
{
	static Course selectedCourse;
	static String url = "http://surfthewebb.com.dotnet-host.com/service/getcourse?value=";
	static String urlall = "http://surfthewebb.com.dotnet-host.com/service/getcoursesall";
	static int[] p1S = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] p2S = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] p3S = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] p4S = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] p5S = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	public static Course GetSingleCourse(String id)
	{
		String pasUrl = url + id;
		InputStream source = retrieveStream(pasUrl);
	
		Gson gson = new Gson();
	
		Reader reader = new InputStreamReader(source);
	
		Course c = gson.fromJson(reader, Course.class);
		
		return c;
	}
	
	public static ArrayList<Course> GetCoursesAll()
	{
		
		InputStream source = retrieveStream(urlall);

		Reader reader = new InputStreamReader(source);
		
		
		List<Course> listFromUrl = new ArrayList<Course>();
		//Parse Response into our object
        Type collectionType = new TypeToken<List<Course>>(){}.getType();
        
		ArrayList<Course> toReturn = new ArrayList<Course>();
		
		
		listFromUrl =	new Gson().fromJson(reader, collectionType);
		for (Course c: listFromUrl)
		{
			toReturn.add(c);
		}
		
		
		return toReturn;
	}
	private static InputStream retrieveStream(String url)
	{
    	DefaultHttpClient client = new DefaultHttpClient();

    	HttpGet getRequest = new HttpGet(url);
    	    	
    	try
    	{
    		HttpResponse getResponse = client.execute(getRequest);

    		final int statusCode = getResponse.getStatusLine().getStatusCode();
    		if (statusCode != HttpStatus.SC_OK)
    		{
    			//Log.w(getClass().getSimpleName(),"Error " + statusCode + " for URL " + url);

    			return null;
    		}
    		HttpEntity getResponseEntity = getResponse.getEntity();
    		
    		
    		return getResponseEntity.getContent();
    	}
    	catch(IOException e)
    	{
    		getRequest.abort();
    		//Log.w(getClass().getSimpleName(), "Error IO for URL " + url, e);
    	}
		
		return null;
	}
	
	
	
	public static String SubmitCourse(Course course)
	{		
        String posturl = "http://surfthewebb.com.dotnet-host.com/service/SubmitCourse";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(posturl);
        HttpContext localContext = new BasicHttpContext();
        client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        
        
        
        String data = "{\"course\":";
        
        data += new Gson().toJson(course, course.getClass());
        data += "}";
        Log.w("json", data);
        
        
        			
        
        StringEntity tmp = null;
        String ret = "n";
		try
		{
			tmp = new StringEntity(data,"UTF-8");						
		}
		catch (UnsupportedEncodingException e)
		{			
			Log.d("Entity", "" + e);
		}
		
		post.setEntity(tmp);
		
		Log.d("Posting", posturl + "?" + data);
		
		HttpResponse response = null;
		 try 
		 {
	            response = client.execute(post,localContext);
	 
	            if (response != null) 
	            {
	                ret = EntityUtils.toString(response.getEntity());
	            }
	     } 
		 catch (Exception e) {
	            Log.e("Groshie", "HttpUtils: " + e);
	        }
	 
		return ret;
	}
	
}
