package it.polito.tdp.ufo.dao;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ufo.model.State;
import it.polito.tdp.ufo.model.Sighting;

public class testDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UfoDAO dao = new UfoDAO();
		List<Sighting> listSightings = new ArrayList<>();
		List<State> listState = new ArrayList<>();
		
		
		listSightings = dao.getAllSightings();
		System.out.println(listSightings.size());
		
		listState = dao.getAllStates();
		System.out.println(listState.size());
		
		
		
	}

}
