package com.test.testtask.model;

import android.graphics.Bitmap;

/**
 * This class represents model of Movie object.
 */
public class Movie
{
	private String subtitle;

	private String sources;

	private String thumb;

	private String small_image;

	private String large_image;

	private String title;

	private String studio;

	private Bitmap image;

	/** Movie Constructor.
	 * @param subtitle      String value from JSON file
	 * @param sources       String value from JSON file
	 * @param thumb         String value from JSON file
	 * @param small_image   String value from JSON file
	 * @param large_image   String value from JSON file
	 * @param title         String value from JSON file
	 * @param studio        String value from JSON file
	 */
	public Movie(String subtitle, String sources, String thumb, String small_image, String large_image, String title, String studio)
	{
		this.subtitle = subtitle;
		this.sources = sources;
		this.thumb = thumb;
		this.small_image = small_image;
		this.large_image = large_image;
		this.title = title;
		this.studio = studio;
	}

	/**
	 * Gets the subtitle of this Movie.
	 * @return Movie subtitle.
	 */
	public String getSubtitle()
	{
		return subtitle;
	}

	/**
	 * Gets the sources of this Movie.
	 * @return Movie sources.
	 */
	public String getSources()
	{
		return sources;
	}

	/**
	 * Gets the thumb of this Movie.
	 * @return Movie thumb.
	 */
	public String getThumb()
	{
		return thumb;
	}

	/**
	 * Gets the small_image of this Movie.
	 * @return Movie small_image.
	 */
	public String getSmall_image()
	{
		return small_image;
	}

	/**
	 * Gets the large_image of this Movie.
	 * @return Movie large_image.
	 */
	public String getLarge_image()
	{
		return large_image;
	}

	/**
	 * Gets the title of this Movie.
	 * @return Movie title.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Gets the studio of this Movie.
	 * @return Movie studio.
	 */
	public String getStudio()
	{
		return studio;
	}

	/**
	 * Gets the bitmap image of this Movie.
	 * @return Bitmap image.
	 */
	public Bitmap getImage()
	{
		return image;
	}

	/**
	 * Set bitmap image for this Movie.
	 * @param image Bitmap image.
	 */
	public void setImage(Bitmap image)
	{
		this.image = image;
	}
}
