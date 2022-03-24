package fr.univ_lyon1.info.m1.cv_search.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JfxView {

    public Controller c;
    private HBox searchSkillsBox;
    private VBox resultBox;

    /**
     * Create the main view of the application.
     */
    public JfxView(Controller c1, Stage stage, int width, int height) {
	// Name of window
	stage.setTitle("Search for CV");

	c = c1;
	c.addListView(this);

	VBox root = new VBox();

	Node newSkillBox = createNewSkillWidget();
	root.getChildren().add(newSkillBox);

	Node restore = createRestoreButton();
	root.getChildren().add(restore);
	
	Node searchSkillsBox = createCurrentSearchSkillsWidget();
	root.getChildren().add(searchSkillsBox);

	Node choiceBox = createChoiceBox();
	root.getChildren().add(choiceBox);

	Node search = createSearchWidget((ComboBox) choiceBox);
	root.getChildren().add(search);

	Node resultBox = createResultsWidget();
	root.getChildren().add(resultBox);

	
	
	// Everything's ready: add it to the scene and display it
	Scene scene = new Scene(root, width, height);
	stage.setScene(scene);
	stage.show();
    }

    public HBox getSearchSkillsBox() {
	return searchSkillsBox;
    }

    public void setSearchSkillsBox(HBox searchSkillsBox) {
	this.searchSkillsBox = searchSkillsBox;
    }

    public VBox getResultBox() {
	return resultBox;
    }

    public void setResultBox(VBox resultBox) {
	this.resultBox = resultBox;
    }

    public Node createCurrentSearchSkillsWidget() {
	searchSkillsBox = new HBox();
	return searchSkillsBox;
    }

    /**
     * Create box to choose the strategy
     * 
     * @version 1.0
     */
    public Node createChoiceBox() {
	ComboBox<String> comboBox = new ComboBox();
	Label labelChoice = new Label("Choice :");
	comboBox.getItems().setAll(c.getListOfStrategy());
	comboBox.setStyle("-fx-font-family:'serif';");
	return comboBox;
    }

    /**
     * Create Box who contain the skills to display
     * 
     * @version 1.0
     */
    public Node createNewSkillWidget() {

	HBox newSkillBox = new HBox();
	Label labelSkill = new Label("Skill:");
	TextField textField = new TextField();
	Button submitButton = new Button("Add skill");
	newSkillBox.getChildren().addAll(labelSkill, textField, submitButton);
	newSkillBox.setSpacing(10);

	EventHandler<ActionEvent> skillHandler = new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {

		String text = textField.getText().strip();
		if (text.equals("")) {
		    return; // Do nothing
		}

		final HBox box = new HBox();
		final Label labelContact = new Label(text);
		final Button b = new Button("x");
		box.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
			+ "-fx-border-width: 1;" + "-fx-border-insets: 5;"
			+ "-fx-border-radius: 5;" 
			+ "-fx-border-color: black;" + "-fx-font-family:'serif';");
		box.setAlignment(Pos.BASELINE_CENTER);
		box.getChildren().addAll(labelContact, b);

		searchSkillsBox.getChildren().add(box);

		b.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
			// On regarde l'index du bouton a supprimer
			int index = c.getListSkills().indexOf(text);
			// Une fois trové on le supprime de la liste
			c.getListSkills().remove(index);
			c.notifyView();
		    }
		});
		textField.setText("");
		textField.requestFocus();
		c.addListSkills(text);
		c.notifyView();
	    }
	};
	submitButton.setOnAction(skillHandler);
	textField.setOnAction(skillHandler);
	newSkillBox.setStyle("-fx-font-family:'serif';");
	return newSkillBox;
    }


    /**
     * Create the widget used to print the results.
     */
    public Node createResultsWidget() {
	resultBox = new VBox();
	resultBox.setStyle("-fx-font-family:'serif';");
	return resultBox;
    }

    /**
     * Create the widget used to trigger the search.
     */
    public Node createSearchWidget(ComboBox combo) {
	List<String> listNomLabel = new ArrayList<String>();
	Button search = new Button("Search");
	search.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		listNomLabel.clear();
		String choice = (String) combo.getValue();
		listNomLabel.addAll(c.getResultsSkills(choice));
		c.saveListSkills();
		resultBox.getChildren().clear();
		for (String a : listNomLabel) {
		    resultBox.getChildren().add(new Label(a));
		    resultBox.getChildren().add(new Label(""));
		}
	    }
	});
	search.setStyle("-fx-font-family:'serif';");
	return search;
    }
    
    /**
     * Add a buttom in order to restore a previous state of a controller
     * 
     * @version 1.0
     */
    
    public Node createRestoreButton() {
	Button restore = new Button("Restore");
	restore.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		c.restoreController();
	    }
	});
	
	restore.setStyle("-fx-font-family:'serif';");
	return restore;
    }

    /**
     * Change the display view with the new list of skills
     * 
     * @version 1.0
     */
    public void refreshView() {
	searchSkillsBox.getChildren().clear();
	for (String text : c.getListSkills()) {
	    final HBox box = new HBox();
	    final Label labelContact = new Label(text);
	    final Button b = new Button("x");
	    box.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
		    + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
		    + "-fx-border-radius: 5;" + "-fx-border-color: black;" 
		    + "-fx-font-family:'serif';");
	    box.setAlignment(Pos.BASELINE_CENTER);
	    box.getChildren().addAll(labelContact, b);

	    searchSkillsBox.getChildren().add(box);
	    b.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
		    // On regarde l'index du bouton a supprimer
		    int index = c.getListSkills().indexOf(text);
		    // Une fois trové on le supprime de la liste
		    c.getListSkills().remove(index);
		    c.notifyView();
		}
	    });
	}
    }

}
