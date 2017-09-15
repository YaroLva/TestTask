package com.test.testtask.utilities;

import android.util.Log;

import com.test.testtask.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This utility like class provide method for parsing JSON data
 * into a Movie objects.
 */
public class ParseJsonData
{
	private static final String Tag = "ParseJsonData";

	private static final String TAG_CATEGORIES = "categories";

	private static final String TAG_VIDEOS = "videos";

	private static final String TAG_SUBTITLE = "subtitle";

	private static final String TAG_SOURCES = "sources";

	private static final String TAG_THUMB = "thumb";

	private static final String TAG_SMALL_IMAGE = "image-480x270";

	private static final String TAG_LARGE_IMAGE = "image-780x1200";

	private static final String TAG_TITLE = "title";

	private static final String TAG_STUDIO = "studio";

	/**
	 * Parse JSON data into separate Movies.
	 * @param json JSON data.
	 * @param movie_list An array list of Movie items.
	 */
	public static void parseJSON(String json, ArrayList<Movie> movie_list)
	{
		if (json == null)
		{
			Log.e(Tag, "Empty array...");
			return;
		}

		try
		{
			JSONObject j_obj = new JSONObject(json);

			JSONArray array = j_obj.getJSONArray(TAG_CATEGORIES);

			JSONObject array_obj = (JSONObject) array.get(0);

			JSONArray videos = array_obj.getJSONArray(TAG_VIDEOS);

			for (int i = 0; i < videos.length(); i++)
			{
				JSONObject child = videos.getJSONObject(i);

				movie_list.add(new Movie(
										child.getString(TAG_SUBTITLE),
										child.getString(TAG_SOURCES),
										child.getString(TAG_THUMB),
										child.getString(TAG_SMALL_IMAGE),
										child.getString(TAG_LARGE_IMAGE),
										child.getString(TAG_TITLE),
										child.getString(TAG_STUDIO)));
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			return;
		}
	}
}
