package it.polito.tdp.gosales;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.gosales.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnProdotti;

    @FXML
    private Button btnRicorsione;

    @FXML
    private ComboBox<?> cmbAnno;

    @FXML
    private ComboBox<?> cmbMetodo;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtS;

    @FXML
    void doCalcolaProdotti(ActionEvent event) {
    	
    }
    

    @FXML
    void doCreaGrafo(ActionEvent event) {

    }
    

    @FXML
    void doRicorsione(ActionEvent event) {

    }

    
    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProdotti != null : "fx:id=\"btnProdotti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicorsione != null : "fx:id=\"btnRicorsione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMetodo != null : "fx:id=\"cmbMetodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtS != null : "fx:id=\"txtS\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    
    public void setModel(Model model) {
    	this.model = model;    	
    }
    

}
