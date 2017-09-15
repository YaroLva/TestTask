package com.test.testtask.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.test.testtask.model.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This utility like class provide method for downloading images.
 */
public class DownloadImage
{
	/**
	 * Download image for a given movie and pass it into a movie.
	 * @param url Main URL.
	 * @param movie Movie which you want to download the image.
	 */
	public static void download(String url, Movie movie)
	{
		Bitmap image = null;

		String image_url = url + movie.getSmall_image();

		if (image_url != null && !image_url.equals(""))
		{
			try
			{
				image = BitmapFactory.decodeStream((InputStream) new URL(image_url).getContent());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		movie.setImage(image);
	}
}
