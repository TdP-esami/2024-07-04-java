package it.polito.tdp.ufo.model;

import java.util.Objects;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Sighting {

	private int id;
	private LocalDateTime datetime;
	private String city;
	private String state;
	private String country;
	private String shape;
	private int duration;
	private String duration_hm;
	private String comments;
	private LocalDate date_posted;
	private double latitude;
	private double longitude;
		

	public Sighting(int id, LocalDateTime datetime, String city, String state, String country, String shape,
			int duration, String duration_hm, String comments, LocalDate date_posted, double latitude,
			double longitude) {
		super();
		this.id = id;
		this.datetime = datetime;
		this.city = city;
		this.state = state;
		this.country = country;
		this.shape = shape;
		this.duration = duration;
		this.duration_hm = duration_hm;
		this.comments = comments;
		this.date_posted = date_posted;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public int getId() {
		return id;
	}


	public LocalDateTime getDatetime() {
		return datetime;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getShape() {
		return shape;
	}

	public int getDuration() {
		return duration;
	}

	public String getDuration_hm() {
		return duration_hm;
	}

	public String getComments() {
		return comments;
	}

	public LocalDate getDate_posted() {
		return date_posted;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sighting other = (Sighting) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return this.id + " - " + this.city + " ["+this.state+"], " + this.datetime;
	}
	
}