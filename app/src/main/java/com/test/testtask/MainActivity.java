package com.test.testtask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.testtask.utilities.JsonTask;
import com.test.testtask.model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
	private static final String MAIN_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/";

	private static final String JSON_SUB_URL = "videos-enhanced-c.json";

	/**
	 * Called when the activity is starting. Set OnClickListener to refresh button and
	 * call method for downloading JSON data.
	 * @param savedInstanceState May contain Bundle data after re-initializing or just null.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.toolbar).findViewById(R.id.refresh).setOnClickListener(this);

		startTask();
	}

	/**
	 * This method is for downloading JSON data from URL in background.
	 */
	private void startTask()
	{
		new JsonTask(this).execute(MAIN_URL, JSON_SUB_URL);
	}

	/**
	 * This method is for displaying the received data from the URL.
	 * For each Movie, an item is created in the list from movie_item layout.
	 * @param list An array list of Movie items.
	 */
	public void showMovieList(ArrayList<Movie> list)
	{
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		container.removeAllViews();
		for (int i = 0; i < list.size(); i++)
		{
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			ViewGroup item = (ViewGroup) inflater.inflate(R.layout.movie_item, container, false);

			Bitmap bmp = list.get(i).getImage();
			if (bmp != null)
				((ImageView) item.findViewById(R.id.image)).setImageBitmap(bmp);
			else
				((ImageView) item.findViewById(R.id.image)).setImageDrawable(getDrawable(R.mipmap.image_n_a));

			((TextView) item.findViewById(R.id.title)).setText(list.get(i).getTitle());
			((TextView) item.findViewById(R.id.studio)).setText(list.get(i).getStudio());
			container.addView(item);
		}
	}

	/**
	 * An implementation of OnClickListener. Called when a view has been clicked.
	 * @param v A View that has been clicked.
	 */
	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		if (id == R.id.refresh)
		{
			startTask();
		}
	}
}
