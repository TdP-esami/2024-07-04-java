package it.polito.tdp.ufo.model;

import java.util.ArrayList;
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
	private int punteggioOttimo;
	private List<Integer> occorrenzeMese;

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
		
	
	public List<Sighting> camminoOttimo(){
		this.camminoOttimo = new ArrayList<Sighting>();
		this.lunghezzaCamminoOttimo = 0;
		this.punteggioOttimo = 0;
		this.occorrenzeMese = new ArrayList<Integer>();
		for(int i = 0; i <12; i++) {
			this.occorrenzeMese.add(0);
		}


		for (Sighting s : this.grafo.vertexSet()) {
			//Incrementa occorrenza del mese per il nodo scelto
			int mese = s.getDatetime().getMonthValue()-1;
			int new_value = this.occorrenzeMese.get(mese)+1;
			this.occorrenzeMese.set(mese, new_value);
			//fai partire ricorsione
			List<Sighting> successors = this.successoriAmmissibili(s);
			List<Sighting> parziale = new ArrayList<Sighting>();
			parziale.add(s);
			this.ricorsioneCammino(parziale, successors);
			//finita la ricorsione, resetta l'occorrenza del mese per il nodo iniziale
			this.occorrenzeMese.set(mese, 0);
		}
		return this.camminoOttimo;
	}
	
	public int lunghezzaOttima() {
		return this.lunghezzaCamminoOttimo;
	}
	
	public int punteggioOttimo() {
		return this.punteggioOttimo;
	}
	
	private void ricorsioneCammino(List<Sighting> parziale, List<Sighting> successivi) {
		if (successivi.size()==0) {
			int punteggio = this.calcolaPunteggion(parziale);
			if (punteggio > this.punteggioOttimo) {
				this.lunghezzaCamminoOttimo = parziale.size();
				this.camminoOttimo = new ArrayList<Sighting>(parziale);
				this.punteggioOttimo = punteggio;
			}
			return;
		}
		else {
			for (Sighting s: successivi) {
				//aggiungo il nodo in parziale ed aggiorno le occorrenze del mese corrispondente
				parziale.add(s);
				int mese = s.getDatetime().getMonthValue()-1;
				int newValue = this.occorrenzeMese.get(mese)+1;
				this.occorrenzeMese.set(mese, newValue);
				// nuovi successivi
				List<Sighting> nuovi_successivi = this.successoriAmmissibili(s);
				//ricorsione
				this.ricorsioneCammino(parziale, nuovi_successivi);
				//backtracking, facendo attenzione a decrementare anche le occorrenze del mese quando si toglie un sighitng da parziale
				mese = parziale.get(parziale.size()-1).getDatetime().getMonthValue()-1;
				newValue = this.occorrenzeMese.get(mese)-1;
				this.occorrenzeMese.set(mese, newValue);
				parziale.remove(parziale.size()-1);
			}
 		}
	}
	
	
	private List<Sighting> successoriAmmissibili(Sighting nodo){
		List<Sighting> successors = Graphs.successorListOf(this.grafo, nodo);
		List<Sighting> nuoviSuccessivi = new ArrayList<Sighting>();
		for (Sighting s : successors) {
			int mese = s.getDatetime().getMonthValue()-1;
			if (s.getDuration()> nodo.getDuration() && this.occorrenzeMese.get(mese)<3)  {
				nuoviSuccessivi.add(s);
			}
		}
		return nuoviSuccessivi;
	}
	
	private int calcolaPunteggion(List<Sighting> cammino){
		//parte del punteggio legata al numero di tappe
		int punteggio = 100 * cammino.size();
		// parte del punteggio legata al mese
		for (int i = 1; i<cammino.size(); i++) {
			if (cammino.get(i).getDatetime().getMonth() == cammino.get(i-1).getDatetime().getMonth()) {
				punteggio +=200;
			}
		}
		return punteggio;
	}
}
	

