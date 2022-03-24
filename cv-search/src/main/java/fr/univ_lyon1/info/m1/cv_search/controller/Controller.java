package fr.univ_lyon1.info.m1.cv_search.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyValue;
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;

public class Controller {
    private List<String> listSkills;
    private List<JfxView> listView;
    Caretaker caretaker;
    Originator originator;
    
    private Strategy s;

    /**
     * Constructor
     * 
     * @version 1.0
     */
    public Controller() {
	listSkills = new ArrayList<String>();
	listView = new ArrayList<JfxView>();
	caretaker = new Caretaker();
	originator = new Originator();
    }

    public List<String> getListSkills() {
	return listSkills;
    }

    public void setListSkills(List<String> listSkills) {
	this.listSkills = listSkills;
    }

    public void addListSkills(String skill) {
	this.listSkills.add(skill);
    }

    public List<JfxView> getListView() {
	return listView;
    }

    public void addListView(JfxView listViews) {
	this.listView.add(listViews);
    }

    public List<String> getListOfStrategy() {
	s = new Strategy();
	return s.getListOfStrategy();
    }



    /**
     * Returns the skills that correspond to the choice
     * 
     * @version 1.0
     */
    public List<String> getResultsSkills(String choice) {
	ApplicantList listApplicants = 
		new ApplicantListBuilder(new File(".")).build();
	List<String> listNomLabel = new ArrayList<String>();
	HashMap<String, Integer> noTreeMap = new HashMap<String, Integer>();
	List<String> nameTree = new ArrayList<String>();
	for (Applicant a : listApplicants) {
	    int selected = 0;
	    if (choice != null) {
		Map strate = new HashMap<>();
		strate.put(getListOfStrategy().get(0), StrategyValue.strategy50(a, listSkills));
		strate.put(getListOfStrategy().get(1), StrategyValue.strategy60(a, listSkills));
		strate.put(getListOfStrategy().get(2), 
			StrategyValue.strategy50Moyenne(a, listSkills));
		strate.put(getListOfStrategy().get(3), 
			StrategyValue.strategyYearsexperience(a, listSkills));
		selected = ((StrategyValue) strate.get(choice)).getStrategy(selected);
	    }
	    if (selected > 0) {
		noTreeMap.put(a.getName(),selected);
	    }
	}
	
	for (int i = 0; i < noTreeMap.size(); i++) {
	    int value = -1;
	    String name = "";
	    for (String key : noTreeMap.keySet()) {
		if (!nameTree.contains(key)) {
		    if (value == -1) {
			value = noTreeMap.get(key);
			name = key;
		    } else {
			if (value <= noTreeMap.get(key)) {
			    value = noTreeMap.get(key);
			    name = key;
			}
		    }
		}
	    }
	    nameTree.add(name);
	}
	
	for (String key : nameTree) {
	    listNomLabel.add(key);
	    listNomLabel.add("Moyenne : " +  String.valueOf(noTreeMap.get(key)));
	    for (Applicant a : listApplicants) {
		int count = 0;
		int sum = 0;
		int moyenne2 = 0;
		if (a.getName().equals(key)) {
		    for (String skillName :a.getSkills().keySet()) {
			count++;
			sum += a.getSkill(skillName);
		    }
		    if (count > 0) {
			moyenne2 = (sum / count);
		    }
		    listNomLabel.add("Niveau moyen " +  String.valueOf(moyenne2));
		}
	    }
	}
	return listNomLabel;
    }

    /**
     * Save the current state of the controller
     * 
     * @version 1.0
     */
    public void saveListSkills() {
	originator.setListSkills(listSkills);
	caretaker.addMemento(originator.save());
    }

    /**
     * Restore the previous state of the controller
     * 
     * @version 1.0
     */

    public void restoreController() {
	Memento memento = caretaker.getMemento();
	if (memento != null) {
	    listSkills = originator.restore(memento);
	}
	notifyView();
    }
    
    /**
     * Notify all views the change
     * 
     * @version 1.0
     */
    public void notifyView() {
	
	for (JfxView view : listView) {
	    view.refreshView();
	}
    }

}
