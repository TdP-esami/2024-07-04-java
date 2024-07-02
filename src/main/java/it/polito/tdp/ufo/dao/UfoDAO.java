package it.polito.tdp.ufo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ufo.model.State;
import it.polito.tdp.ufo.model.Sighting;

public class UfoDAO {
	
	
	/**
	 * Metodo per leggere la lista di tutti gli avvistamenti nel database
	 * @return
	 */

	public List<Sighting> getAllSightings(){
		String query = "SELECT * "
					+ "FROM sighting s "
					+ "ORDER BY datetime ASC";
		List<Sighting> result = new ArrayList<Sighting>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Sighting(rs.getInt("id"), 
						rs.getTimestamp("datetime").toLocalDateTime(),
						rs.getString("city"), 
						rs.getString("state"),
						rs.getString("country"),
						rs.getString("shape"),
						rs.getInt("duration"),
						rs.getString("duration_hm"),
						rs.getString("comments"),
						rs.getTimestamp("date_posted").toLocalDateTime().toLocalDate(),
						rs.getDouble("latitude"),
						rs.getDouble("longitude")));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
	
	
	/**
	 * Metodo per leggere la lista di tutti gli stati dal database
	 * @return
	 */
	public List<State> getAllStates(){
		String query = "SELECT * "
					+ "FROM state s";
		List<State> result = new ArrayList<State>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new State(rs.getString("id"), 
						rs.getString("Name"), 
						rs.getString("Capital"), 
						rs.getDouble("Lat"), 
						rs.getDouble("Lng"), 
						rs.getInt("Area"),
						rs.getInt("Population"), 
						rs.getString("Neighbors")));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
	
	
	/**
	 * Metodo per leggere la lista di tutti gli anni per cui ci sono avvistamenti registrati nel database
	 * @return
	 */
	public List<Integer> getYears(){
		String query = "SELECT DISTINCT YEAR(datetime) as anno "
				+ "FROM sighting "
				+ "ORDER BY anno DESC";
		List<Integer> result = new ArrayList<Integer>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getInt("anno"));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	/**
	 * Metodo per leggere la lista di tutti gli anni per cui ci sono avvistamenti registrati nel database
	 * @return
	 */
	public List<String> getShapesYear(int year){
		String query = "SELECT DISTINCT s.shape "
				+ "FROM sighting s "
				+ "WHERE YEAR(s.datetime)= ? "
				+ "ORDER BY shape ASC";
		List<String> result = new ArrayList<String>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, year);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("shape"));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	
}
