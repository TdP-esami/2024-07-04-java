package it.polito.tdp.ufo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.ufo.dao.UfoDAO;

public class Model {
	private UfoDAO dao;
	private SimpleDirectedGraph<Sighting,DefaultEdge> grafo;
	private List<Sighting> camminoOttimo;
	private int lunghezzaCamminoOttimo;
	
	public Model() {
		this.dao = new UfoDAO();
	}
	
	public List<Integer> getYears(){
		return this.dao.getYears();
	}
	
	public List<String> getShapesYear(int year){
		return this.dao.getShapesYear(year);
	}
	
	
	public void creaGrafo(int anno, String shape) {
		this.grafo = new SimpleDirectedGraph<Sighting, DefaultEdge>(DefaultEdge.class);
		
		//Vertici
		List<Sighting> vertici= this.dao.getVertici(anno, shape);
		Graphs.addAllVertices(this.grafo, vertici);
		
		// Archi
        for (int i = 0; i < vertici.size()-1; i++) {
            for (int j = i+1; j<vertici.size(); j++) {
                if ((vertici.get(i).getState().equals( vertici.get(j).getState())) && vertici.get(i).getDatetime().isBefore(vertici.get(j).getDatetime())){
                	this.grafo.addEdge(vertici.get(i), vertici.get(j));
                }
            }
        }
	}
	
	
	public List<Set<Sighting>> calcolaComponentiConnesse(){
		ConnectivityInspector<Sighting, DefaultEdge> inspect = new ConnectivityInspector<Sighting, DefaultEdge>(this.grafo);
		return inspect.connectedSets();
	}
	
	public Set<Sighting> getLargestConnessa(){
		List<Set<Sighting>> connesse = this.calcolaComponentiConnesse();
		int bestSize = 0;
		Set<Sighting> res = new HashSet<Sighting>();
		for (Set<Sighting> connessa : connesse) {
			if (connessa.size()>bestSize){
				bestSize = connessa.size();
				res = new HashSet<Sighting>(connessa);
			}
		}
		return res;
	}

	
	public Set<Sighting> getNodi(){
		return this.grafo.vertexSet();
	}
	
	public int numNodi(){
		return this.grafo.vertexSet().size();
	}
	
	public int numArchi(){
		return this.grafo.edgeSet().size();
	}
		
}
	

