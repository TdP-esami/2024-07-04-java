package it.polito.tdp.ufo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.ufo.model.Model;
import it.polito.tdp.ufo.model.Sighting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnPercorso;

    @FXML
    private ComboBox<Integer> cmbAnno;

    @FXML
    private ComboBox<String> cmbShape;

    @FXML
    private TextArea txtResult1;

    @FXML
    private TextArea txtResult2;

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	this.txtResult2.clear();
    	List<Sighting> percorsoOttimo = this.model.camminoOttimo();
    	this.txtResult2.appendText("Il percorso ottimo è costituito da " + percorsoOttimo.size() + " nodi:\n");
    	for (Sighting s : percorsoOttimo) {
    		this.txtResult2.appendText(s + " - " + s.getDuration() + "\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult1.clear();
    	Integer anno = this.cmbAnno.getValue();
    	if (anno==null) {
    		this.txtResult1.setText("Selezionare un anno\n");
    		return;
    	}
    	String shape = this.cmbShape.getValue();
    	if (shape==null) {
    		this.txtResult1.setText("Selezionare una shape\n");
    		return;
    	}
    	this.model.creaGrafo(anno, shape);
    	this.txtResult1.appendText("Numero di vertici: "+ this.model.numNodi() +"\n");
    	this.txtResult1.appendText("Numero di archi: "+ this.model.numArchi()+"\n");
    	List<Set<Sighting>> componentiConnesse = this.model.calcolaComponentiConnesse();
    	this.txtResult1.appendText("Il grafo ha " + componentiConnesse.size() + " componenti connesse.\n");
    	Set<Sighting> largestConnessa = this.model.getLargestConnessa();
    	this.txtResult1.appendText("La componente connessa più grande è costituita da " + largestConnessa.size() + " nodi:\n");
    	for (Sighting c : largestConnessa) {
    		this.txtResult1.appendText(c + "\n");
    	}
    	this.btnPercorso.setDisable(false);
    }

    @FXML
    void fillCmbShape(ActionEvent event) {
    	int anno = this.cmbAnno.getValue();
    	this.cmbShape.getItems().clear(); 
    	this.cmbShape.getItems().setAll(this.model.getShapesYear(anno));
    	
    }

    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbShape != null : "fx:id=\"cmbShape\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult1 != null : "fx:id=\"txtResult1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult2 != null : "fx:id=\"txtResult2\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    void setModel(Model model) {
    	this.model = model;
    	this.cmbAnno.getItems().setAll(this.model.getYears());
    	this.btnPercorso.setDisable(true);
    }

}
