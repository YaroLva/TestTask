package com.test.testtask.utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.test.testtask.MainActivity;
import com.test.testtask.R;
import com.test.testtask.model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class allows you to download data in background and show results.
 */
public class JsonTask extends AsyncTask<String, String, String>
{
	private Context context;

	private ProgressDialog progress_dialog;

	private ArrayList<Movie> movie_list;

	/** Constructor for JsonTask.
	 * @param c Context.
	 */
	public JsonTask(Context c)
	{
		context = c;
		movie_list = new ArrayList<Movie>();
	}

	/**
	 * Setup progress before doing work in background.
	 */
	protected void onPreExecute()
	{
		progress_dialog = new ProgressDialog(context);
		progress_dialog.setMessage(context.getString(R.string.loading));
		progress_dialog.show();
		progress_dialog.setOnCancelListener(new DialogInterface.OnCancelListener()
		{
			public void onCancel(DialogInterface arg0)
			{
				JsonTask.this.cancel(true);
			}
		});
	}

	/**
	 * Set connection and downloads JSON data, parse JSON data into separate Movies,
	 * downloads images for preview.
	 * @param params takes two URL parameters.
	 * @return RAW JSON data or null if there were some problems.
	 */
	@Override
	protected String doInBackground(String... params)
	{
		HttpURLConnection connection = null;
		BufferedReader reader = null;

		try
		{
			URL url = new URL(params[0] + params[1]);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();

			InputStream is = connection.getInputStream();

			reader = new BufferedReader(new InputStreamReader(is));

			StringBuffer buffer = new StringBuffer();
			String line = "";

			while ((line = reader.readLine()) != null)
			{
				buffer.append(line + "\n");
				Log.d("data: ", line);
			}

			ParseJsonData.parseJSON(buffer.toString(), movie_list);

			for (int i = 0; i < movie_list.size(); i++)
				DownloadImage.download(params[0], movie_list.get(i));

			return buffer.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			showToast(e.toString());
		}
		finally
		{
			if (connection != null)
			{
				connection.disconnect();
			}
			try
			{
				if (reader != null)
				{
					reader.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Remove progress and show data.
	 * @param s RAW JSON data or null if there were some problems.
	 */
	@Override
	protected void onPostExecute(String s)
	{
		super.onPostExecute(s);

		if (progress_dialog.isShowing())
			progress_dialog.dismiss();

		((MainActivity) context).showMovieList(movie_list);
	}

	/**
	 * Text to display in a toast. The text displayed when some error occurred.
	 * @param text Text to display.
	 */
	private void showToast(final String text)
	{
		((Activity) context).runOnUiThread(new Runnable()
		{
			public void run()
			{
				Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
				toast.show();
			}
		});
	}
}
