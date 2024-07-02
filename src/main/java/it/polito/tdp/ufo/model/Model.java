package it.polito.tdp.ufo.model;

import java.util.List;
import it.polito.tdp.ufo.dao.UfoDAO;

public class Model {
	private UfoDAO dao;
	
	public Model() {
		this.dao = new UfoDAO();
	}
	
	public List<Integer> getYears(){
		return this.dao.getYears();
	}
	
	public List<String> getShapesYear(int year){
		return this.dao.getShapesYear(year);
	}
		
}
	

