package it.polito.tdp.ufo.model;

import java.util.Objects;

public class State {
	
	private String id;
	private String Name;
	private String Capital;
	private double Lat;
	private double Lng;
	private int Area;
	private int Population;
	private String Neighbors;
	

	
	public State(String id, String name, String capital, double lat, double lng, int area, int population,
			String neighbors) {
		super();
		this.id = id;
		Name = name;
		Capital = capital;
		Lat = lat;
		Lng = lng;
		Area = area;
		Population = population;
		Neighbors = neighbors;
	}



	public String getId() {
		return id;
	}



	public String getName() {
		return Name;
	}



	public String getCapital() {
		return Capital;
	}



	public double getLat() {
		return Lat;
	}



	public double getLng() {
		return Lng;
	}



	public int getArea() {
		return Area;
	}



	public int getPopulation() {
		return Population;
	}



	public String getNeighbors() {
		return Neighbors;
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
		State other = (State) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return this.Name;
	}
	

}